package vn.edu.hcmuaf.fit;

import static vn.edu.hcmuaf.fit.constant.RequestStatusConstant.*;
import static vn.edu.hcmuaf.fit.constant.RoleConstant.*;

import java.util.*;

import javax.swing.JFrame;

import vn.edu.hcmuaf.fit.controller.UserController;
import vn.edu.hcmuaf.fit.database.DbManager;
import vn.edu.hcmuaf.fit.dto.Role;
import vn.edu.hcmuaf.fit.model.*;

public class App {
	public static Map<Role, Stack<JFrame>> frameMap = new HashMap<>();
	public static Stack<JFrame> frames = new Stack<>();

	public static void main(String[] args) {
		initData();
		frameMap.put(ADMIN, new Stack<>());
		frameMap.put(EMPLOYEE, new Stack<>());
		frameMap.put(HOSPITAL, new Stack<>());
		frameMap.put(USER, new Stack<>());

		UserController adminController = new UserController();
		adminController.getLogin();

		// UserController userController = new UserController();
		// userController.getLogin();
	}
	
	private static void initData() {
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
		User user1 = new User("092343234234", "Nguyễn Văn A", 20, true, "4",
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "ABCXYZ", USER);

		User user2 = new User("092343234634", "Nguyễn Văn B", 20, true, "5",
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "ABCXYZ", USER);

		User user3 = new User("08234t276424", "Nguyễn Văn C", 20, true, "6",
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "ABCXYZ", USER);

		User user4 = new User("078236236452", "Nguyễn Văn D", 20, true, "7",
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "ABCXYZ", USER);

		DbManager.users.add(user1);
		DbManager.users.add(user2);
		DbManager.users.add(user3);
		DbManager.users.add(user4);
		
		// Tạo bệnh nhân
		Patient patient1 = new Patient("123456789012", "Nguyễn Văn B", 19, true);
		Patient patient2 = new Patient("123456789013", "Nguyễn Văn C", 20, true);
		Patient patient3 = new Patient("123456789014", "Nguyễn Văn D", 21, true);
		Patient patient4 = new Patient("123456789015", "Nguyễn Văn E", 22, true);
		Patient patient5 = new Patient("123456789016", "Nguyễn Văn F", 23, true);

		DbManager.patients.add(patient1);
		DbManager.patients.add(patient2);
		DbManager.patients.add(patient3);
		DbManager.patients.add(patient4);
		DbManager.patients.add(patient5);
		
		// Tạo yêu cầu
		List<Patient> patients1 = new ArrayList<>();
		patients1.add(patient1);
		Request request1 = new Request(1L, patients1, user1.getPhone(), user1.getAddress(),
				"Bệnh nhân đang trong tình trạng nguy cấp, cần xe cấp cứu ngay!", PENDING.status(), user1);

		List<Patient> patients2 = new ArrayList<>();
		patients2.add(patient2);
		Request request2 = new Request(2L, patients2, user2.getPhone(), user2.getAddress(),
				"Khó thở, chóng mặt!", SUBMITTED.status(), user2);

		List<Patient> patients3 = new ArrayList<>();
		patients3.add(patient3);
		patients3.add(patient4);
		Request request3 = new Request(3L, patients3, user3.getPhone(), user3.getAddress(),
				"Tai nạn giao thông, 2 người bất tỉnh!", REQUEST_AMBULANCE.status(), user3);

		List<Patient> patients4 = new ArrayList<>();
		patients4.add(patient5);
		Request request4 = new Request(4L, patients4, user4.getPhone(), user4.getAddress(),
				"Ho nhiều, mất vị giác!", COMPLETED.status(), user4);

		DbManager.requests.add(request1);
		DbManager.requests.add(request2);
		DbManager.requests.add(request3);
		DbManager.requests.add(request4);
	}
}
