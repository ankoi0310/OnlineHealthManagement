package vn.edu.hcmuaf.fit.controller.client;

import vn.edu.hcmuaf.fit.model.Request;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.service.*;

import java.util.List;

public class ClientHomeController {
    private User user; // logged in user
    private UserService userService;
    private final RequestService requestService;
    private PatientService patientService;
    // private final Home view;

    public ClientHomeController(User user) {
        this.userService = new UserServiceImpl();
        this.requestService = new RequestServiceImpl();
        this.patientService = new PatientServiceImpl();

        this.user = user;
        // view = new Home(this, user);
        // view.createView();

        getRequests();

        // App.frames.add(view);
    }

    public void getRequests() {
        List<Request> requests = requestService.getRequests(user.getId()).getData();
        // view.redraw(requests);
    }
}
