package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.admin.HomeController;
import vn.edu.hcmuaf.fit.dto.UserLogin;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.service.UserService;
import vn.edu.hcmuaf.fit.service.UserServiceImpl;
import vn.edu.hcmuaf.fit.view.Login;
import vn.edu.hcmuaf.fit.view.Registration;

import javax.swing.*;

public class UserController {
	private final User model;
	private final UserService userService;
	
	public UserController(User model) {
		this.userService = new UserServiceImpl();

		this.model = model;
	}

	public void getRegister() {
		Registration view = new Registration(this, new User());
		view.createView();

		App.frames.add(view);
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

	public void getLogin() {
		Login view = new Login(this, new UserLogin());
		view.createView();

		App.frames.add(view);
		if (App.frames.size() == 1) {
			view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else {
			view.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}
	}

	public void login(String username, String password) {
		Login view = (Login) App.frames.peek();
		AppResult<User> result = userService.login(new UserLogin(username, password));

		if (result.isSuccess()) {
			view.showMessage(result.getMessage());
			view.close();

			new HomeController(result.getData());
		} else {
			view.showError(result.getMessage());
		}
	}
}
