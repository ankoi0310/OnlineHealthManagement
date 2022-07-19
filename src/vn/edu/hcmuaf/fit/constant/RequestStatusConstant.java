package vn.edu.hcmuaf.fit.constant;

import vn.edu.hcmuaf.fit.dto.RequestStatus;

public class RequestStatusConstant {
	public static final RequestStatus PENDING = new RequestStatus(0, "Chờ xử lý");
	public static final RequestStatus SUBMITTED = new RequestStatus(1, "Đã tiếp nhận");
	public static final RequestStatus REQUEST_AMBULANCE = new RequestStatus(2, "Đã yêu cầu sử dụng xe");
	public static final RequestStatus AMBULANCE_MOVING = new RequestStatus(3, "Xe đang di chuyển");
	public static final RequestStatus AMBULANCE_ARRIVED = new RequestStatus(4, "Xe đã đến");
	public static final RequestStatus COMPLETED = new RequestStatus(5, "Yêu cầu hoàn thành");
}
