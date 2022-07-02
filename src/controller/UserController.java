package controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import constant.RoleConstant;
import dto.user.UserRegister;
import handle.AppBaseResult;
import handle.AppResult;
import model.User;
import view.Main;

public class UserController {
	
	public UserController() {
	}
	
	public AppBaseResult register(UserRegister userRegister) {
		try {
			boolean isExist = Main.users.stream().anyMatch(user -> user.getPhone().equals(userRegister.getPhone()));
			if (isExist)
				return AppBaseResult.GenarateIsFailed("Tài khoản đã tồn tại");
			
			if (userRegister.getId().isBlank())
				return AppBaseResult.GenarateIsFailed("Vui lòng nhập CMND/CCCD");
			
			if (userRegister.getFullname().isBlank())
				return AppBaseResult.GenarateIsFailed("Vui lòng nhập họ tên");
			
			if (userRegister.getPhone().isBlank())
				return AppBaseResult.GenarateIsFailed("Vui lòng nhập số điện thoại");
			
			if (userRegister.getPassword().isBlank())
				return AppBaseResult.GenarateIsFailed("Vui lòng nhập mật khẩu");
			
			if (userRegister.getAddress().isBlank())
				return AppBaseResult.GenarateIsFailed("Vui lòng nhập địa chỉ");
			
			User user = new User();

			user.setId(userRegister.getId());
			user.setFullname(userRegister.getFullname());
			user.setPhone(userRegister.getPhone());
			user.setPassword(hashPassword(userRegister.getPassword()));
			user.setRoleId(RoleConstant.USER);
			
			Main.users.add(user);
			
			return new AppBaseResult(true, "Đăng ký tài khoản thành công!\nVui lòng đăng nhập lại.");
		} catch (Exception e) {
			return AppBaseResult.GenarateIsFailed("Đăng ký không thành công");
		}
	}
	
	public AppResult<User> checkLogin(String phone, String password) {
		if (phone == null || phone.isBlank())
			return new AppResult<>(false, "Vui lòng nhập số điện thoại!", null);
		
		if (password == null || password.isBlank())
			return new AppResult<>(false, "Vui lòng nhập mật khẩu!", null);
		
		User userLogin = Main.users.stream().filter(user -> user.getPhone().equals(phone)).findFirst().orElse(null);
		
		if (userLogin == null)
			return new AppResult<>(false, "Tài khoản không tồn tại", null);
		
		if (!hashPassword(password).equalsIgnoreCase(userLogin.getPassword()))
			return new AppResult<>(false, "Sai tên tài khoản hoặc mật khẩu!", null);
		
		return new AppResult<>(true, "Đăng nhập thành công", userLogin);
	}
	
	private String hashPassword(String password) {
		try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(password.getBytes());
            BigInteger number = new BigInteger(1, hash);
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
	}
}
