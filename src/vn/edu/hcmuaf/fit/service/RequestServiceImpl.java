package vn.edu.hcmuaf.fit.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import vn.edu.hcmuaf.fit.dao.RequestDAO;
import vn.edu.hcmuaf.fit.dao.RequestDAOImpl;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.Request;
import vn.edu.hcmuaf.fit.model.User;

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
	public AppResult<Request> getRequest(Long id) {
		Request request = requestDAO.findById(id);//làm việc với csdl và tìm ra request

		if (request == null) {
			return new AppResult<>(false, "Request not found", null);
		}

		return new AppResult<>(true, "Success", request);
	}

	@Override
	public AppBaseResult createRequest(Request request,User user) {
		try {
			if (request.getPatients().isEmpty())
				return new AppBaseResult(false,"Vui lòng thêm bệnh nhân");

			if (request.getPhone().isBlank()) {
				request.setPhone(user.getPhone());
			}else if(!(StringUtils.isNumeric(request.getPhone()) && request.getPhone().length() == 10 )) {
				return new AppBaseResult(false,"Số điện thoại không hợp lệ");
			}
			if (request.getAddress().isBlank())
				return new AppBaseResult(false,"Vui lòng nhập địa chỉ");

			if (request.getProblemDescription().isBlank())
				return new AppBaseResult(false,"Vui lòng nhập Problem Description");
			
			
				requestDAO.save(request);
			//
			return new AppBaseResult(true, "Tạo yêu cầu thành công");
		} catch (Exception e) {
			return new AppBaseResult(false,"Tạo yêu cầu không thành công");
		}
	}

	@Override
	public AppBaseResult updateRequest(Request request) {
		try {
			if (request.getPatients().isEmpty())
				return new AppBaseResult(false,"Vui lòng thêm bệnh nhân");

			if (request.getPhone().isBlank())
				return new AppBaseResult(false,"Vui lòng nhập số điện thoại");

			if (request.getAddress().isBlank())
				return new AppBaseResult(false,"Vui lòng nhập địa chỉ");

			if (request.getProblemDescription().isBlank())
				return new AppBaseResult(false,"Vui lòng nhập Problem Description");
			
				requestDAO.save(request);
			//
			return new AppBaseResult(true, "Cập nhật yêu cầu thành công");
		} catch (Exception e) {
			return new AppBaseResult(false,"Cập nhật yêu cầu không thành công");
		}
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
	public AppResult<List<Request>> getRequest(String id) {
		// TODO Auto-generated method stub
		List<Request> requests = requestDAO.findByUserId(id);
		return new AppResult<>(true, "Success", requests);
	}

}
