package vn.edu.hcmuaf.fit.dao;

import java.util.List;

import vn.edu.hcmuaf.fit.model.Request;

public interface RequestDAO {
	List<Request> findAll();
	List<Request> findByUserId(String userId);
	Request findById(Long id);
	void save(Request request);
	void remove(Long id);
}
