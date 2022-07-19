package vn.edu.hcmuaf.fit.dao;

import java.util.List;

import vn.edu.hcmuaf.fit.database.DbManager;
import vn.edu.hcmuaf.fit.model.User;

public class UserDAOImpl implements UserDAO {
	private static UserDAOImpl _instance;
	
	private UserDAOImpl() {}
	
	public static UserDAOImpl getInstance() {
		if (_instance == null) _instance = new UserDAOImpl();
		return _instance;
	}

	@Override
	public List<User> findAll() {
		return DbManager.users;
	}

	@Override
	public User findById(String id) {
		return DbManager.users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public User findByPhone(String phone) {
		return DbManager.users.stream().filter(user -> user.getPhone().equals(phone)).findFirst().orElse(null);
	}

	@Override
	public void save(User user) {
		User existUser = findById(user.getId());
		if (existUser == null) {
			DbManager.users.add(user);
		} else {
			existUser.setFullname(user.getFullname());
			existUser.setAge(user.getAge());
			existUser.setMale(user.isMale());
			existUser.setPhone(user.getPhone());
			existUser.setPassword(user.getPassword());
			existUser.setAddress(user.getAddress());
			existUser.setRole(user.getRole());
		}
	}

}
