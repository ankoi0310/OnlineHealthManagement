package vn.edu.hcmuaf.fit.controller.admin;

import java.util.List;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.constant.RoleConstant;
import vn.edu.hcmuaf.fit.controller.UserController;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.Request;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.service.PatientService;
import vn.edu.hcmuaf.fit.service.PatientServiceImpl;
import vn.edu.hcmuaf.fit.service.RequestService;
import vn.edu.hcmuaf.fit.service.RequestServiceImpl;
import vn.edu.hcmuaf.fit.service.UserService;
import vn.edu.hcmuaf.fit.service.UserServiceImpl;
import vn.edu.hcmuaf.fit.view.Home;
import vn.edu.hcmuaf.fit.view.Home_user;
import vn.edu.hcmuaf.fit.view.UpdateRequestStatus;

/**
 * 
 * Đây là lớp Controller các hoạt động của view Home của admin
 *
 */
public class HomeController {
	private User user; // logged in user
	private UserService userService;
	private final RequestService requestService;
	private PatientService patientService;
	private final Home view;
//	private Home_user viewUser;

	public HomeController(User user) {
		this.userService = new UserServiceImpl();
		this.requestService = new RequestServiceImpl();
		this.patientService = new PatientServiceImpl();

		this.user = user;

		view = new Home(this, user);
		view.createView();
		getRequests();
		App.frames.add(view);

	}

	// update lại bảng ở view home
	public void getRequests() {
		List<Request> requests = requestService.getRequests().getData();

		view.redraw(requests);

	}

	public void getUpdateRequestStatus(Long requestId) {
		AppResult<Request> result = requestService.getRequest(requestId);

		if (result.isSuccess()) {
			UpdateRequestStatus view = new UpdateRequestStatus(this, result.getData());// tạo đối tượng
																						// UpdateRequestStatus
			view.createView(); // hiện ra cái view của UpdateRequestStatus
			view.getStatus();

			App.frames.add(view);
		} else {
			view.showError(result.getMessage());
		}
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
		view.close();
		UserController controller = new UserController(new User());
		controller.getLogin();
	}

	public void search() {
	}

	public void getUpdateInfo() {
	}

	public void updateInfo() {
	}

	public void removeRequest(Long requestId) {
		AppBaseResult result = requestService.removeRequest(requestId);

		if (result.isSuccess()) {
			view.showMessage(result.getMessage());
			getRequests();
		} else {
			view.showError(result.getMessage());
		}
	}
}
