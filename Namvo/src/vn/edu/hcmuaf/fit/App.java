package vn.edu.hcmuaf.fit;

import static vn.edu.hcmuaf.fit.constant.RequestStatusConstant.PENDING;
import static vn.edu.hcmuaf.fit.constant.RoleConstant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;

import vn.edu.hcmuaf.fit.controller.UserController;
import vn.edu.hcmuaf.fit.database.DbManager;
import vn.edu.hcmuaf.fit.model.*;

public class App {
	public static Stack<JFrame> frames = new Stack<>();

	public static void main(String[] args) {
		initData();	//them thong tin
		UserController controller = new UserController(new User()); //tao 1 userController de lay form login(login view)
		controller.getLogin();	//goi lay form login (login view)
	}
	
	private static void initData() {
		// Tạo trạng thái
		DbManager.requestStatus.put(0, "Chờ xử lý");
		DbManager.requestStatus.put(1, "Đang xử lý");
		DbManager.requestStatus.put(2, "Đang yêu cầu xe cấp cứu");
		DbManager.requestStatus.put(3, "Xe cấp cứu đang di chuyển");
		DbManager.requestStatus.put(4, "Yêu cầu hoàn thành");
		
		// Tạo thông tin người quản lý
		User admin = new User("1", "Admin", 19, true, "1", 
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "ABCXYZ", ADMIN);
		DbManager.users.add(admin);
		
		User employee = new User("2", "Employee", 19, true, "2", 
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "ABCXYZ", EMPLOYEE);
		DbManager.users.add(employee);
		
		// Tạo thông tin người trực bệnh viện
		User hospital = new User("3", "Bệnh viện Trung ương III", 0, true, "3",
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "Địa chỉ bệnh viện", HOSPITAL);
		DbManager.users.add(hospital);
		
		// Tạo thông tin người dùng
		User user = new User("092343234234", "Nguyễn Văn A", 20, true, "4", 
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "ABCXYZ", USER);
		DbManager.users.add(user);
		
		// Tạo bệnh nhân
		Patient patient = new Patient("123456789012", "Nguyễn Văn B", 19, true);
		Patient p = new Patient("123456", "Nguyễn Văn C", 20, false);
		DbManager.patients.add(patient);
		DbManager.patients.add(p);
		
		// Tạo yêu cầu
		List<Patient> patients = new ArrayList<>();
		patients.add(patient);
		patients.add(p);
		Request request = new Request(1L, patients, user.getPhone(), user.getAddress(), 
				"Bệnh nhân đang trong tình trạng nguy cấp, cần xe cấp cứu ngay!", PENDING.getStatus(), user);
		DbManager.requests.add(request);
	}
}
