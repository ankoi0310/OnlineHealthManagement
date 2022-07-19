package vn.edu.hcmuaf.fit.controller;

import java.util.List;

import javax.swing.JOptionPane;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.model.Patient;
import vn.edu.hcmuaf.fit.model.Request;
import vn.edu.hcmuaf.fit.service.PatientService;
import vn.edu.hcmuaf.fit.service.PatientServiceImpl;
import vn.edu.hcmuaf.fit.view.CreateRequestView;
import vn.edu.hcmuaf.fit.view.PatientForm;
import vn.edu.hcmuaf.fit.view.UpdateCreate;
import vn.edu.hcmuaf.fit.view.UpdatePatient;

public class PatientController {
	PatientForm form;
	UpdatePatient updateP;
	private PatientService patientService;
	private CreateRequestView reView;
	private UpdateCreate upReView;

	public PatientController(CreateRequestView view) {
		this.patientService = new PatientServiceImpl();
		reView = view;
		form = new PatientForm(this);
		form.createView();
		App.frames.add(form);
	}

	public PatientController(CreateRequestView view, int row) {
		// TODO Auto-generated constructor stub
		this.patientService = new PatientServiceImpl();

		Patient p = patientService.getPatientByRow(row).getData();
		reView = view;
		updateP = new UpdatePatient(this, p, row);
		updateP.createView();
		App.frames.add(updateP);
	}
	
	public PatientController(UpdateCreate createUpdate , int row) {
		// TODO Auto-generated constructor stub
		this.patientService = new PatientServiceImpl();

		Patient p = patientService.getPatientByRow(row).getData();
		upReView = createUpdate;
		updateP = new UpdatePatient(this, p, row);
		updateP.createView();
		App.frames.add(updateP);
	}

	public void addPatient(Patient p) {
		PatientForm currentFrame = (PatientForm) App.frames.peek();

		AppBaseResult result = patientService.addPatient(p);

		if (result.isSuccess()) {
			currentFrame.showMessage(result.getMessage());
			currentFrame.close();
			getRequests();

		}
	}

	public void createPatients(List<Patient> patients) {
	}

	public void getRequests() {
		List<Patient> patients = patientService.getPatients().getData();

		if (reView != null) {
			reView.redraw(patients);
		}else {
			upReView.redraw(patients);
		}
	}

	public void updatePatient(Patient p, int row) {
		UpdatePatient currentFrame = (UpdatePatient) App.frames.peek();

		AppBaseResult result = patientService.updatePatientInfo(p, row);

		if (result.isSuccess()) {
			currentFrame.showMessage(result.getMessage());
			currentFrame.close();
			getRequests();

		}
	}

}
