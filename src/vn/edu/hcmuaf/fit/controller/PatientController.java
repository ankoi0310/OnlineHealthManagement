package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.model.Patient;
import vn.edu.hcmuaf.fit.service.PatientService;
import vn.edu.hcmuaf.fit.service.PatientServiceImpl;
import vn.edu.hcmuaf.fit.view.patient.CreatePatient;
import vn.edu.hcmuaf.fit.view.patient.UpdatePatient;

public class PatientController {
	private final RequestController controller;
	private final boolean isCreate;
	private final Patient model;
	private CreatePatient createView;
	private UpdatePatient updateView;
	private final PatientService patientService;

	public PatientController(RequestController controller, boolean isCreate, Patient model) {
		this.patientService = new PatientServiceImpl();
		this.controller = controller;
		this.isCreate = isCreate;

		this.model = model;
	}

	public void getCreatePatient() {
		createView = new CreatePatient(this, model);
		createView.createView();
	}

	public void createPatient(Patient patient) {
		AppBaseResult result = patientService.createPatient(patient);

		if (result.isSuccess()) {
			createView.showMessage(result.getMessage());
			createView.close();
			if (isCreate) {
				RequestController.currentPatients.add(patient);
				controller.refresh(true);
			} else {
				RequestController.updatedPatients.add(patient);
				controller.refresh(false);
			}
		} else {
			createView.showMessage(result.getMessage());
		}
	}

	public void getUpdatePatient() {
		updateView = new UpdatePatient(this, model);
		updateView.createView();
		updateView.loadData();
	}

	public void updatePatient(Patient patient) {
		AppBaseResult result = patientService.updatePatient(patient);

		if (result.isSuccess()) {
			updateView.showMessage(result.getMessage());
			updateView.close();

			for (int i = 0; i < RequestController.updatedPatients.size(); i++) {
				if (RequestController.updatedPatients.get(i).getId().equals(patient.getId())) {
					RequestController.updatedPatients.set(i, patient);
					break;
				}
			}
			controller.refresh(isCreate);
		} else {
			updateView.showMessage(result.getMessage());
		}
	}
}
