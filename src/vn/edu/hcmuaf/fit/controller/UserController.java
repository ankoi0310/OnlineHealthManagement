package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.admin.AdminHomeController;
import vn.edu.hcmuaf.fit.controller.client.ClientHomeController;
import vn.edu.hcmuaf.fit.dto.UserLogin;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.service.UserService;
import vn.edu.hcmuaf.fit.service.UserServiceImpl;
import vn.edu.hcmuaf.fit.view.Login;
import vn.edu.hcmuaf.fit.view.Registration;

import javax.swing.*;

import static vn.edu.hcmuaf.fit.constant.RoleConstant.*;

public class UserController {
	private final User model;
	private final UserService userService;
	
	public UserController(User model) {
		this.userService = new UserServiceImpl();

		this.model = model;
	}

	public void getRegister(Login login) {
		login.off();

		Registration view = new Registration(this, model);
		view.createView();
		view.setCurrentLogin(login);

		App.frames.add(view);
	}
	
	public void register(User user) {
		Registration registration = (Registration) App.frames.peek();
		AppBaseResult result = userService.register(user);

		if (result.isSuccess()) {
			Login login = registration.getCurrentLogin();

			registration.showMessage(result.getMessage());
			registration.close();
			login.on();
		} else {
			registration.showError(result.getMessage());
		}
	}

	public void unregister(Registration registration) {
		registration.close();

		Login login = registration.getCurrentLogin();
		login.on();
	}

	public void getLogin() {
		Login view = new Login(this, new UserLogin());
		view.createView();

		App.frames.add(view);
	}

	public void login(Login view, String username, String password) {
		AppResult<User> result = userService.login(new UserLogin(username, password));

		if (result.isSuccess()) {
			User user = result.getData();

			view.showMessage(result.getMessage());
			view.close();

			if (ADMIN.equals(user.getRole()) || EMPLOYEE.equals(user.getRole()) || HOSPITAL.equals(user.getRole())) {
				new AdminHomeController(user);
			} else if (USER.equals(user.getRole())) {
				new ClientHomeController(user);
			}
		} else {
			view.showError(result.getMessage());
		}
	}
}
