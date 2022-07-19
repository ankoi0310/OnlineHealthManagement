package vn.edu.hcmuaf.fit.controller;

import java.util.List;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.model.Patient;
import vn.edu.hcmuaf.fit.model.Request;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.service.PatientService;
import vn.edu.hcmuaf.fit.service.PatientServiceImpl;
import vn.edu.hcmuaf.fit.service.RequestService;
import vn.edu.hcmuaf.fit.service.RequestServiceImpl;
import vn.edu.hcmuaf.fit.view.CreateRequestView;
import vn.edu.hcmuaf.fit.view.Home_user;
import vn.edu.hcmuaf.fit.view.UpdateCreate;

public class RequestController {

	private RequestService requestService;
	private PatientService patientService;
	CreateRequestView create;
	User user; 
	Home_user viewUser;
	UpdateCreate update;

	public RequestController(Home_user home, User user) {
		// TODO Auto-generated constructor stub
		requestService = new RequestServiceImpl();
		patientService = new PatientServiceImpl();
		this.user = user;
		this.viewUser = home;
		create = new CreateRequestView(this);
		create.createView();
		App.frames.add(create);
		cleanPatients();
	}
	
	public RequestController(Home_user user, Request request) {
		// TODO Auto-generated constructor stub
		requestService = new RequestServiceImpl();
		patientService = new PatientServiceImpl();
//		this.user = user;
		this.viewUser = user;
		update = new UpdateCreate(this, request);
		update.createView();
		App.frames.add(update);
		SaveAllPatient(request.getPatients());
	}

	public void getPatient(int num) {
		if (num == -1) { //day la tao
			new PatientController(create);
		}if(num != -1 && create != null){//day la update
			new PatientController(create, num);
		}else {
			new PatientController(update, num);
		}
	}
	
	public void getPatientInUpdateCre(int num) {
		new PatientController(update, num);
	}

	public void SaveAllPatient(List<Patient> list) {
		patientService.saveAll(list);
	}
	public void cleanPatients() {
		patientService.removeAll();
	}
	
	public void deletePatient (String id) {
		AppBaseResult result = patientService.removePatient(id);
		
		if(result.isSuccess()) {
			create.showMessage(result.getMessage());
			create.redraw(patientService.getPatients().getData());
			
		}
	}
	
	public void CreateRquest(String phone, String address, String proDesc) {
		CreateRequestView currentCreate = (CreateRequestView) App.frames.peek();
		Request request = new Request(0L, patientService.getPatients().getData(), phone, address, proDesc, 0, user);
		AppBaseResult result = requestService.createRequest(request);
		
		if(result.isSuccess()) {
			currentCreate.showMessage(result.getMessage());
//			currentCreate.redraw(patientService.getPatients().getData());
			currentCreate.close();
			viewUser.redraw(requestService.getRequests().getData());
			
		}
	}
	
	public void UpdateRequest(Long id, String phone, String address, String proDesc) {
		UpdateCreate currentUpdateCreate = (UpdateCreate) App.frames.peek();
		Request request = new Request(id, patientService.getPatients().getData(), phone, address, proDesc, 0, user);

		
		AppBaseResult result = requestService.updateRequest(request);
		
		if(result.isSuccess()) {
			currentUpdateCreate.showMessage(result.getMessage());
//			currentUpdateCreate.redraw(patientService.getPatients().getData());
			currentUpdateCreate.close();
			viewUser.redraw(requestService.getRequests().getData());
			
		}
	}
}
