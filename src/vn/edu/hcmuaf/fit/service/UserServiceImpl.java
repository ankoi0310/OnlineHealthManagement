package vn.edu.hcmuaf.fit.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import vn.edu.hcmuaf.fit.constant.RoleConstant;
import vn.edu.hcmuaf.fit.dao.UserDAO;
import vn.edu.hcmuaf.fit.dao.UserDAOImpl;
import vn.edu.hcmuaf.fit.database.DbManager;
import vn.edu.hcmuaf.fit.dto.UserLogin;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.User;

public class UserServiceImpl implements UserService {
	private final UserDAO userDAO;
	public UserServiceImpl() {
		userDAO = UserDAOImpl.getInstance();
	}

	@Override
	public AppBaseResult register(User newUser) {
		try {
			
			if (newUser.getId().isBlank())
				return AppBaseResult.GenarateIsFailed("Vui lòng nhập CMND/CCCD");
			
			if (newUser.getFullname().isBlank())
				return AppBaseResult.GenarateIsFailed("Vui lòng nhập họ tên");
			
			if (newUser.getPhone().isBlank())
				return AppBaseResult.GenarateIsFailed("Vui lòng nhập số điện thoại");
			
			if (newUser.getPassword().isBlank())
				return AppBaseResult.GenarateIsFailed("Vui lòng nhập mật khẩu");
			
			if (newUser.getAddress().isBlank())
				return AppBaseResult.GenarateIsFailed("Vui lòng nhập địa chỉ");

			User user = userDAO.findById(newUser.getId());
			if (user != null)
				return AppBaseResult.GenarateIsFailed("Tài khoản đã tồn tại");

			newUser.setPassword(hashPassword(newUser.getPassword()));
			newUser.setRole(RoleConstant.USER);
			userDAO.save(newUser);
			
			return new AppBaseResult(true, "Đăng ký tài khoản thành công!\nVui lòng đăng nhập lại.");
		} catch (Exception e) {
			return AppBaseResult.GenarateIsFailed("Đăng ký không thành công");
		}
	}

	@Override
	public AppResult<User> login(UserLogin userLogin) {
		String username = userLogin.getUsername();
		if (username == null || username.isBlank())
			return new AppResult<>(false, "Vui lòng nhập tên đăng nhập!", null);

		String password = userLogin.getPassword();
		if (password == null || password.isBlank())
			return new AppResult<>(false, "Vui lòng nhập mật khẩu!", null);
		
		User currentUser = DbManager.users.stream()
				.filter(user -> user.getPhone().equals(username) || user.getId().equals(username))
				.findFirst().orElse(null);
		
		if (currentUser == null)
			return new AppResult<>(false, "Tài khoản không tồn tại", null);

		if (!Objects.requireNonNull(hashPassword(password)).equalsIgnoreCase(currentUser.getPassword()))
			return new AppResult<>(false, "Sai tên tài khoản hoặc mật khẩu!", null);
		
		return new AppResult<>(true, "Đăng nhập thành công", currentUser);
	}

	@Override
	public AppBaseResult updateProfile(User user) {
		// TODO Auto-generated method stub
		return null;
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
