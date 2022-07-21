package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.model.Patient;
import vn.edu.hcmuaf.fit.model.Request;
import vn.edu.hcmuaf.fit.service.PatientService;
import vn.edu.hcmuaf.fit.service.PatientServiceImpl;
import vn.edu.hcmuaf.fit.service.RequestService;
import vn.edu.hcmuaf.fit.service.RequestServiceImpl;
import vn.edu.hcmuaf.fit.view.request.CreateRequest;
import vn.edu.hcmuaf.fit.view.request.UpdateRequest;

import java.util.ArrayList;
import java.util.List;

public class RequestController {
    public static List<Patient> currentPatients = new ArrayList<>();
    public static List<Patient> updatedPatients = new ArrayList<>();
    private final UserHomeController controller;
    private final Request model;
    private CreateRequest createView;
    private UpdateRequest updateView;
    private final RequestService requestService;
    private final PatientService patientService;

    public RequestController(UserHomeController controller, Request model) {
        requestService = new RequestServiceImpl();
        patientService = new PatientServiceImpl();

        this.controller = controller;
        this.model = model;
    }

    public void refresh(boolean isCreate) {
        if (isCreate) {
            createView.redraw(currentPatients);
        } else {
            updateView.redraw(updatedPatients);
        }
    }

    public void getCreatePatient(boolean isCreate) {
        PatientController controller = new PatientController(this, isCreate, new Patient());
        controller.getCreatePatient();
    }

    public void getUpdatePatient(boolean isCreate, String patientId) {
        Patient patient = patientService.getPatient(patientId).getData();
        PatientController controller = new PatientController(this, isCreate, patient);
        controller.getUpdatePatient();
    }

    public void getCreateRequest() {
        currentPatients.clear();

        createView = new CreateRequest(this, model);
        createView.createView();

        refresh(true);

        App.frameMap.get(model.getUser().getRole()).add(createView);
    }

    public void getUpdateRequest() {
        currentPatients = model.getPatients();
        updatedPatients = new ArrayList<>();
        for (Patient patient : currentPatients) {
            Patient copy = new Patient(patient);
            updatedPatients.add(copy);
        }
        updateView = new UpdateRequest(this, model);
        updateView.createView();
        updateView.loadData();

        refresh(false);

        App.frameMap.get(model.getUser().getRole()).add(updateView);
    }

    public void createRequest(Request request) {
        request.setPatients(currentPatients);
        AppBaseResult result = requestService.createRequest(request);

        if (result.isSuccess()) {
            createView.showMessage(result.getMessage());
            createView.close(model.getUser().getRole());
            controller.refresh();
        } else {
            createView.showMessage(result.getMessage());
        }
    }

    public void updateRequest(Request request) {
        request.setPatients(updatedPatients);
        AppBaseResult result = requestService.updateRequest(request);

        if (result.isSuccess()) {
            updateView.showMessage(result.getMessage());
            updateView.close(model.getUser().getRole());
            controller.refresh();
        } else {
            updateView.showError(result.getMessage());
        }
    }

    public void removePatient(String id, boolean isCreate) {
        Patient patient = patientService.getPatient(id).getData();

        if (isCreate) {
            if (patient != null) {
                createView.showMessage("Xoá bệnh nhân thành công");
                currentPatients.stream().filter(p -> p.getId().equals(id)).findFirst().ifPresent(currentPatients::remove);
                refresh(true);
            } else {
                createView.showError("Bệnh nhân không tồn tại");
            }
        } else {
            if (patient != null) {
                updateView.showMessage("Xoá bệnh nhân thành công");
                updatedPatients.stream().filter(p -> p.getId().equals(id)).findFirst().ifPresent(updatedPatients::remove);
                refresh(false);
            } else {
                updateView.showError("Bệnh nhân không tồn tại");
            }
        }
    }

    public void cancel(boolean isCreate) {
        if (isCreate) {
            if (currentPatients.size() > 0) {
                for (Patient patient : currentPatients) {
                    patientService.removePatient(patient.getId());
                }
            }
        } else {
            model.setPatients(currentPatients);
            requestService.updateRequest(model);

            for (Patient patient : currentPatients) {
                patientService.updatePatient(patient);
            }
        }
    }
}
