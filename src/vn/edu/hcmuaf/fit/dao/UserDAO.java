package vn.edu.hcmuaf.fit.dao;

import java.util.List;

import vn.edu.hcmuaf.fit.model.User;

public interface UserDAO {
	List<User> findAll();
	User findById(String id);
	User findByPhone(String phone);
	void save(User user);
}
