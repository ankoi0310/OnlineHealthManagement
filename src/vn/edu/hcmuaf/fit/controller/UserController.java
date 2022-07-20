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

import static vn.edu.hcmuaf.fit.constant.RoleConstant.*;

public class UserController {
	private Registration registration;
	private Login login;
	private final UserService userService;
	
	public UserController() {
		this.userService = new UserServiceImpl();
	}

	public void getRegister() {
		registration = new Registration(this, new User());
		registration.createView();
		login.off();

		App.frames.add(registration);
	}
	
	public void register(User user) {
		AppBaseResult result = userService.register(user);

		if (result.isSuccess()) {
			registration.showMessage(result.getMessage());
			registration.close();
			login.on();
		} else {
			registration.showError(result.getMessage());
		}
	}

	public void unregister(Registration registration) {
		registration.close();
		login.on();
	}

	public void getLogin() {
		login = new Login(this, new UserLogin());
		login.createView();

		App.frames.add(login);
	}

	public void login(UserLogin userLogin) {
		AppResult<User> result = userService.login(userLogin);

		if (result.isSuccess()) {
			User user = result.getData();

			login.showMessage(result.getMessage());
			login.close();

			if (ADMIN.equals(user.getRole()) || EMPLOYEE.equals(user.getRole()) || HOSPITAL.equals(user.getRole())) {
				new AdminHomeController(user);
			} else if (USER.equals(user.getRole())) {
				new ClientHomeController(user);
			}
		} else {
			login.showError(result.getMessage());
		}
	}
}
