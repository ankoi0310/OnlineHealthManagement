package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.Request;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.service.*;
import vn.edu.hcmuaf.fit.view.home.AdminHome;
import vn.edu.hcmuaf.fit.view.request.UpdateRequestStatus;

import java.util.List;

public class AdminHomeController {
    private final User user; // logged in user
    private final RequestService requestService;
    private final AdminHome view;

    public AdminHomeController(User user) {
        this.requestService = new RequestServiceImpl();

        this.user = user;
        view = new AdminHome(this, user);
        view.createView();
        view.loadData();

        refresh();

        App.frameMap.get(user.getRole()).add(view);
    }

    public void search(String keyword) {
        List<Request> requests = requestService.search(keyword).getData();
        view.redraw(requests);
    }

    public void refresh() {
        List<Request> requests = requestService.getRequests().getData();
        view.redraw(requests);
    }

    public void getUpdateRequestStatus(Long requestId) {
        AppResult<Request> result = requestService.getRequest(requestId);

        if (result.isSuccess()) {
            UpdateRequestStatus view = new UpdateRequestStatus(this, result.getData());
            view.createView();
            view.getStatus();

            App.frames.add(view);
            App.frameMap.get(user.getRole()).add(view);
        } else {
            view.showError(result.getMessage());
        }
    }

    public void updateRequestStatus(Long id, int status) {
        UpdateRequestStatus currentFrame = (UpdateRequestStatus) App.frameMap.get(user.getRole()).peek();

        AppBaseResult result = requestService.updateStatus(id, status);

        if (result.isSuccess()) {
            currentFrame.showMessage(result.getMessage());
            currentFrame.close(user.getRole());
            refresh();
        } else {
            currentFrame.showError(result.getMessage());
        }
    }

    public void getUpdateProfile() {
        UserController controller = new UserController();
        controller.getUpdateProfile(view, user);
    }

    public void removeRequest(Long requestId) {
        AppBaseResult result = requestService.removeRequest(requestId);

        if (result.isSuccess()) {
            view.showMessage(result.getMessage());
            refresh();
        } else {
            view.showError(result.getMessage());
        }
    }

    public void logout() {
        view.close(user.getRole());
        UserController controller = new UserController();
        controller.getLogin();
    }
}
