package vn.edu.hcmuaf.fit.service;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.fit.constant.RequestStatusConstant;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.dto.RequestStatus;
import vn.edu.hcmuaf.fit.handle.*;
import vn.edu.hcmuaf.fit.model.*;

import static vn.edu.hcmuaf.fit.constant.RequestStatusConstant.PENDING;

public class RequestServiceImpl implements RequestService {
	private final RequestDAO requestDAO;
	
	public RequestServiceImpl() {
		requestDAO = RequestDAOImpl.getInstance();
	}

	@Override
	public AppResult<List<Request>> getRequests() {
		List<Request> requests = requestDAO.findAll();
		return new AppResult<>(true, "Success", requests);
	}

	@Override
	public AppResult<List<Request>> getRequests(String userId) {
		List<Request> requests = requestDAO.findByUserId(userId);
		return new AppResult<>(true, "Success", requests);
	}

	@Override
	public AppResult<Request> getRequest(Long id) {
		Request request = requestDAO.findById(id);

		if (request == null) {
			return new AppResult<>(false, "Request not found", null);
		}

		return new AppResult<>(true, "Success", request);
	}

	@Override
	public AppBaseResult createRequest(Request request) {
		requestDAO.save(request);
		
		return new AppBaseResult(true, "Tạo yêu cầu thành công");
	}

	@Override
	public AppBaseResult updateRequest(Request request) {
		requestDAO.save(request);
		return new AppBaseResult(true, "Cập nhật yêu cầu thành công");
	}

	@Override
	public AppBaseResult updateStatus(Long id, int status) {
		Request updateRequest = requestDAO.findById(id);

		if (updateRequest == null) {
			return new AppBaseResult(false, "Request not found");
		}

		updateRequest.setStatus(status);
		requestDAO.save(updateRequest);

		return new AppBaseResult(true, "Cập nhật thành công!");
	}

	@Override
	public AppBaseResult removeRequest(Long id) {
		Request request = requestDAO.findById(id);

		if (request == null) {
			return new AppBaseResult(false, "Request not found");
		}

		requestDAO.remove(id);

		return new AppBaseResult(true, "Xóa thành công!");
	}

	@Override
	public AppResult<List<Request>> search(String keyword) {
		List<Request> requests = new ArrayList<>();

		if (keyword.isEmpty()) {
			return new AppResult<>(true, "Success", requests);
		}

		loop: for (Request request : requestDAO.findAll()) {
			List<Patient> patients = request.getPatients();

			switch (request.getStatus()) {
				case 1:
					if (PENDING.getMessage().contains(keyword)) requests.add(request);
					continue;
			}

			if (request.getId().toString().contains(keyword) ||
				request.getPhone().contains(keyword) ||
				request.getAddress().contains(keyword) ||
				request.getProblemDescription().contains(keyword)) {
				requests.add(request);
			} else if (patients.size() > 0) {
				for (Patient patient : patients) {
					if (patient.getId().contains(keyword) ||
						patient.getFullname().contains(keyword) ||
						String.valueOf(patient.getAge()).contains(keyword)) {
						requests.add(request);
						continue loop;
					}
				}
			}
		}
		return new AppResult<>(true, "Success", requests);
	}

}
