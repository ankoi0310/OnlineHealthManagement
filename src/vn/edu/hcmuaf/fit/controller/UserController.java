package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.constant.RoleConstant;
import vn.edu.hcmuaf.fit.controller.admin.HomeController;
import vn.edu.hcmuaf.fit.controller.user.HomeControllerUser;
import vn.edu.hcmuaf.fit.dto.UserLogin;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.service.UserService;
import vn.edu.hcmuaf.fit.service.UserServiceImpl;
import vn.edu.hcmuaf.fit.view.Login;
import vn.edu.hcmuaf.fit.view.Registration;
import vn.edu.hcmuaf.fit.view.UpdateUser;

import javax.swing.*;

public class UserController {
	private final User model;
	private final UserService userService;

	public UserController(User model) {
		this.userService = new UserServiceImpl();

		this.model = model;
	}
	
	public void getUpdateUser() {
		UpdateUser update = new UpdateUser(model, this);
		
		update.createView();
		App.frames.add(update);
	}

	public void getRegister() {
		Registration view = new Registration(this, new User());
		view.createView();

		App.frames.add(view);
	}

	public void update(User user, User newUser) {
		UpdateUser currentUpdateUser = (UpdateUser) App.frames.peek();
		AppBaseResult result = userService.updateProfile(user, newUser);
		
		if (result.isSuccess()) {
			currentUpdateUser.showMessage(result.getMessage());
			currentUpdateUser.close();
		} else {
			currentUpdateUser.showError(result.getMessage());
		}
		
	}
	public void register(User user) {
		Registration registration = (Registration) App.frames.peek();
		AppBaseResult result = userService.register(user);

		if (result.isSuccess()) {
			registration.showMessage(result.getMessage());
			registration.close();
		} else {
			registration.showError(result.getMessage());
		}
	}

	public void unregister() {
		Registration frame = (Registration) App.frames.pop();
		frame.dispose();
	}

	// tao form login
	public void getLogin() {
		Login view = new Login(this, new UserLogin()); // tao doi tuong login
		view.createView(); // tao view cho login

		App.frames.add(view); // add view login vao frames
		if (App.frames.size() == 1) {
			view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else {
			view.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}
	}

	// hàm này là để chọn xem sẽ hiển thị Home của admin hay Home của user, ....
	public void login(String username, String password) {
		Login view = (Login) App.frames.peek();
		// handle coi đã đăng nhập được chưa bằng cách vào userService (tương tác của
		// người dùng với dữ
		// dữ liệu user xem coi có người dùng trong database không =>return ra
		// AppResult)
		AppResult<User> result = userService.login(new UserLogin(username, password));

		if (result.isSuccess()) {
			view.showMessage(result.getMessage());
			view.close();

			User user = result.getData();
			if (user.getRole() == RoleConstant.ADMIN) {// nếu người dùng đăng nhập có role là admin
				new HomeController(user);
			} else {// nếu người dùng đăng nhập có role không phải là admin
				new HomeControllerUser(user);
			}
		} else {
			view.showError(result.getMessage());// nếu không đăng nhập được thì hiện bảng lỗi với message của result
												// trên
		}
	}
}
