package vn.edu.hcmuaf.fit.service;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.handle.*;
import vn.edu.hcmuaf.fit.model.*;

import static vn.edu.hcmuaf.fit.constant.RequestStatusConstant.*;

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
			return new AppResult<>(false, "Yêu cầu không tồn tại", null);
		}

		return new AppResult<>(true, "Success", request);
	}

	@Override
	public AppBaseResult createRequest(Request request) {
		if (requestDAO.findById(request.getId()) != null) {
			return new AppBaseResult(false, "Yêu cầu đã tồn tại");
		}

		if (request.getPatients().size() == 0) {
			return new AppBaseResult(false, "Yêu cầu phải có bệnh nhân");
		}

		if (!request.getPhone().matches("^(0)\\d{9}$"))
			return AppBaseResult.GenarateIsFailed("Số điện thoại không hợp lệ\nSố điện thoại phải là 10 số và bắt đầu bởi 0");

		if (request.getAddress().isBlank())
			return AppBaseResult.GenarateIsFailed("Địa chỉ không được để trống");

		if (request.getProblemDescription().isBlank())
			return AppBaseResult.GenarateIsFailed("Mô tả yêu cầu không được để trống");

		requestDAO.save(request);

		return new AppBaseResult(true, "Tạo yêu cầu thành công");
	}

	@Override
	public AppBaseResult updateRequest(Request request) {
		if (request.getPatients().size() == 0) {
			return new AppBaseResult(false, "Yêu cầu phải có bệnh nhân");
		}

		if (!request.getPhone().matches("^(0)\\d{9}$"))
			return AppBaseResult.GenarateIsFailed("Số điện thoại không hợp lệ\nSố điện thoại phải là 10 số và bắt đầu bởi 0");

		if (request.getAddress().isBlank())
			return AppBaseResult.GenarateIsFailed("Địa chỉ không được để trống");

		if (request.getProblemDescription().isBlank())
			return AppBaseResult.GenarateIsFailed("Mô tả yêu cầu không được để trống");

		requestDAO.save(request);
		return new AppBaseResult(true, "Cập nhật yêu cầu thành công");
	}

	@Override
	public AppBaseResult updateStatus(Long id, int status) {
		Request updateRequest = requestDAO.findById(id);

		if (updateRequest == null) {
			return new AppBaseResult(false, "Yêu cầu không tồn tại");
		}

		updateRequest.setStatus(status);
		requestDAO.save(updateRequest);

		return new AppBaseResult(true, "Cập nhật thành công!");
	}

	@Override
	public AppBaseResult removeRequest(Long id) {
		Request request = requestDAO.findById(id);

		if (request == null) {
			return new AppBaseResult(false, "Yêu cầu không tồn tại");
		}

		if (request.getStatus() != PENDING.status()) {
			return new AppBaseResult(false, "Bạn không thể xóa yêu cầu đã được xác nhận");
		}

		requestDAO.remove(id);

		return new AppBaseResult(true, "Xóa thành công!");
	}

	@Override
	public AppResult<List<Request>> search(String keyword) {
		List<Request> requests = new ArrayList<>();

		if (keyword.isEmpty()) {
			return new AppResult<>(true, "Success", requestDAO.findAll());
		}

		loop: for (Request request : requestDAO.findAll()) {
			List<Patient> patients = request.getPatients();

			switch (request.getStatus()) {
				case 0 -> {
					if (PENDING.message().toLowerCase().contains(keyword.toLowerCase())) {
						requests.add(request);
						continue;
					}
				}
				case 1 -> {
					if (SUBMITTED.message().toLowerCase().contains(keyword.toLowerCase())) {
						requests.add(request);
						continue;
					}
				}
				case 2 -> {
					if (REQUEST_AMBULANCE.message().toLowerCase().contains(keyword.toLowerCase())) {
						requests.add(request);
						continue;
					}
				}
				case 3 -> {
					if (AMBULANCE_MOVING.message().toLowerCase().contains(keyword.toLowerCase())) {
						requests.add(request);
						continue;
					}
				}
				case 4 -> {
					if (AMBULANCE_ARRIVED.message().toLowerCase().contains(keyword.toLowerCase())) {
						requests.add(request);
						continue;
					}
				}
				case 5 -> {
					if (COMPLETED.message().contains(keyword)) {
						requests.add(request);
						continue;
					}
				}
			}

			if (request.getId().toString().contains(keyword) ||
				request.getPhone().contains(keyword) ||
				request.getAddress().toLowerCase().contains(keyword.toLowerCase()) ||
				request.getProblemDescription().toLowerCase().contains(keyword.toLowerCase())) {
				requests.add(request);
				continue;
			}

			for (Patient patient : patients) {
				if (patient.getId().contains(keyword) ||
					patient.getFullname().toLowerCase().contains(keyword.toLowerCase()) ||
					String.valueOf(patient.getAge()).contains(keyword) ||
					(patient.isMale() && keyword.equalsIgnoreCase("nam")) ||
					(!patient.isMale() && keyword.equalsIgnoreCase("nữ"))) {
					requests.add(request);
					continue loop;
				}
			}
		}
		return new AppResult<>(true, "Success", requests);
	}

	@Override
	public AppResult<List<Request>> search(String userId, String keyword) {
		List<Request> requests = new ArrayList<>();

		if (keyword.isEmpty()) {
			return new AppResult<>(true, "Success", requestDAO.findByUserId(userId));
		}

		loop: for (Request request : requestDAO.findByUserId(userId)) {
			List<Patient> patients = request.getPatients();

			switch (request.getStatus()) {
				case 0 -> {
					if (PENDING.message().toLowerCase().contains(keyword.toLowerCase())) {
						requests.add(request);
						continue;
					}
				}
				case 1 -> {
					if (SUBMITTED.message().toLowerCase().contains(keyword.toLowerCase())) {
						requests.add(request);
						continue;
					}
				}
				case 2 -> {
					if (REQUEST_AMBULANCE.message().toLowerCase().contains(keyword.toLowerCase())) {
						requests.add(request);
						continue;
					}
				}
				case 3 -> {
					if (AMBULANCE_MOVING.message().toLowerCase().contains(keyword.toLowerCase())) {
						requests.add(request);
						continue;
					}
				}
				case 4 -> {
					if (AMBULANCE_ARRIVED.message().toLowerCase().contains(keyword.toLowerCase())) {
						requests.add(request);
						continue;
					}
				}
				case 5 -> {
					if (COMPLETED.message().contains(keyword)) {
						requests.add(request);
						continue;
					}
				}
			}

			if (request.getId().toString().contains(keyword) ||
					request.getPhone().contains(keyword) ||
					request.getAddress().toLowerCase().contains(keyword.toLowerCase()) ||
					request.getProblemDescription().toLowerCase().contains(keyword.toLowerCase())) {
				requests.add(request);
				continue;
			}

			for (Patient patient : patients) {
				if (patient.getId().contains(keyword) ||
						patient.getFullname().toLowerCase().contains(keyword.toLowerCase()) ||
						String.valueOf(patient.getAge()).contains(keyword) ||
						(patient.isMale() && keyword.equalsIgnoreCase("nam")) ||
						(!patient.isMale() && keyword.equalsIgnoreCase("nữ"))) {
					requests.add(request);
					continue loop;
				}
			}
		}
		return new AppResult<>(true, "Success", requests);
	}

}
