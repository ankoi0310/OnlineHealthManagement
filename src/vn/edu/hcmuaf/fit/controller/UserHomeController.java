package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.model.Request;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.service.*;
import vn.edu.hcmuaf.fit.view.home.UserHome;

import java.util.List;

public class UserHomeController {
    private final User user; // logged in user
    private final RequestService requestService;
    private final UserHome view;

    public UserHomeController(User user) {
        this.requestService = new RequestServiceImpl();

        this.user = user;
        view = new UserHome(this, user);
        view.createView();
        view.loadData();

        refresh();

        App.frames.add(view);
        App.frameMap.get(user.getRole()).add(view);
    }

    public void search(String keyword) {
        List<Request> requests = requestService.search(user.getId(), keyword).getData();
        view.redraw(requests);
    }

    public void refresh() {
        List<Request> requests = requestService.getRequests(user.getId()).getData();
        view.redraw(requests);
    }

    public void getCreateRequest() {
        Request request = new Request();
        request.setId(0L);
        request.setUser(user);

        RequestController controller = new RequestController(this, request);
        controller.getCreateRequest();
    }

    public void getUpdateRequest(Long requestId) {
        Request request = requestService.getRequest(requestId).getData();

        RequestController controller = new RequestController(this, request);
        controller.getUpdateRequest();
    }

    public void getUpdateProfile(User user) {
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
