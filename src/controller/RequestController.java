package controller;

import java.util.List;

import constant.RequestStatusConstant;
import dto.request.RequestCreate;
import handle.AppBaseResult;
import model.Patient;
import model.Request;
import model.User;
import view.Main;

public class RequestController {
	
	public RequestController() {
	}
	
	public AppBaseResult createRequest(RequestCreate request) {
		Long id = Main.requests.get(Main.requests.size() - 1).getId() + 1;
		List<Patient> patient = request.getPatients();
		String phone = request.getPhone();
		String address = request.getAddress();
		String problemDescription = request.getProblemDescription();
		User user = Main.users.stream().filter(item -> item.getId().equals(request.getUserId())).findFirst().orElse(null);
		
		Request newRequest = new Request(id, patient, phone, address, problemDescription, RequestStatusConstant.PENDING, user);
		Main.requests.add(newRequest);
		
		return new AppBaseResult(true, "Tạo yêu cầu thành công");
	}
	
	public AppBaseResult updateRequestStatus(Request request, int status) {
		request.setStatus(status);
		
		int index = Main.requests.indexOf(request);
		Main.requests.set(index, request);
		
		return new AppBaseResult(true, "Cập nhật trạng thái thành công!");
	}
}
