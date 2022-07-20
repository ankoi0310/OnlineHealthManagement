package vn.edu.hcmuaf.fit.controller.user;

import java.util.List;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.RequestController;
import vn.edu.hcmuaf.fit.controller.UserController;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.model.Request;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.service.PatientService;
import vn.edu.hcmuaf.fit.service.PatientServiceImpl;
import vn.edu.hcmuaf.fit.service.RequestService;
import vn.edu.hcmuaf.fit.service.RequestServiceImpl;
import vn.edu.hcmuaf.fit.service.UserService;
import vn.edu.hcmuaf.fit.service.UserServiceImpl;
import vn.edu.hcmuaf.fit.view.Home_user;
import vn.edu.hcmuaf.fit.view.UpdateRequestStatus;

/**
 * 
 * Đây là lớp Controller các hoạt động của view Home của user
 *
 */
public class HomeControllerUser {
	private User user; // logged in user
	private UserService userService;
	private final RequestService requestService;
	private PatientService patientService;
//	private final Home view;
	private final Home_user viewUser;

	public HomeControllerUser(User user) {
		this.userService = new UserServiceImpl();
		this.requestService = new RequestServiceImpl();
		this.patientService = new PatientServiceImpl();

		this.user = user;

		viewUser = new Home_user(this, user);
		viewUser.createView();
		getRequests();
		App.frames.add(viewUser);
	}

	public void getRequests() {
		List<Request> requests = requestService.getRequest(user.getId()).getData();

		viewUser.redraw(requests);

	}

	public void getCreateReques() {
		new RequestController(viewUser,user);	
	}

	public void updateRequestStatus(Long id, int status) {
		UpdateRequestStatus currentFrame = (UpdateRequestStatus) App.frames.peek();

		AppBaseResult result = requestService.updateStatus(id, status);

		if (result.isSuccess()) {
			currentFrame.showMessage(result.getMessage());
			currentFrame.close();
			getRequests();
		} else {
			currentFrame.showError(result.getMessage());
		}
	}

	public void logout() {
		viewUser.close();
		UserController controller = new UserController(new User());
		controller.getLogin();
	}

	public void search() {
	}

	public void getUpdateRequest(Long id) {
		new RequestController(viewUser, requestService.getRequest(id).getData());
	}
	public void getUpdateInfo(User user) {
		UserController userc = new UserController(user);
		userc.getUpdateUser();
	}

	public void updateInfo() {
	}

	public void removeRequest(Long requestId) {
		AppBaseResult result = requestService.removeRequest(requestId);

		if (result.isSuccess()) {
			viewUser.showMessage(result.getMessage());
			getRequests();
		} else {
			viewUser.showError(result.getMessage());
		}
	}
}
