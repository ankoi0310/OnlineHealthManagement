package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.dto.UserLogin;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.service.UserService;
import vn.edu.hcmuaf.fit.service.UserServiceImpl;
import vn.edu.hcmuaf.fit.view.Login;
import vn.edu.hcmuaf.fit.view.Registration;
import vn.edu.hcmuaf.fit.view.UpdateProfile;
import vn.edu.hcmuaf.fit.view.home.AdminHome;
import vn.edu.hcmuaf.fit.view.home.UserHome;

import javax.swing.*;

import static vn.edu.hcmuaf.fit.constant.RoleConstant.*;

public class UserController {
	private JFrame view;
	private Registration registration;
	private Login login;
	private UpdateProfile updateProfile;
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
				new UserHomeController(user);
			}
		} else {
			login.showError(result.getMessage());
		}
	}

	public void getUpdateProfile(JFrame view, User user) {
		this.view = view;
		updateProfile = new UpdateProfile(this, user);
		updateProfile.createView();
		updateProfile.setProfile();
	}

	public void updateProfile(User user) {
		AppBaseResult result = userService.updateProfile(user);

		if (result.isSuccess()) {
			updateProfile.showMessage(result.getMessage());
			updateProfile.close();

			if (USER.equals(user.getRole())) {
				((UserHome) view).user = user;
				((UserHome) view).loadData();
			} else {
				((AdminHome) view).user = user;
				((AdminHome) view).loadData();
			}
		} else {
			updateProfile.showError(result.getMessage());
		}
	}
}
