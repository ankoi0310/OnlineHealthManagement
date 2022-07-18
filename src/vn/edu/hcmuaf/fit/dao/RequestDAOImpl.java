package vn.edu.hcmuaf.fit.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import vn.edu.hcmuaf.fit.database.DbManager;
import vn.edu.hcmuaf.fit.model.Request;

public class RequestDAOImpl implements RequestDAO {
	private static RequestDAOImpl _instance;
	private long lastId;
	
	private RequestDAOImpl() {
		lastId = DbManager.requests.get(DbManager.requests.size() - 1).getId();
	}
	
	public static RequestDAOImpl getInstance() {
		if (_instance == null) _instance = new RequestDAOImpl();
		return _instance;
	}

	@Override
	public List<Request> findAll() {
		return DbManager.requests;
	}

	@Override
	public List<Request> findByUserId(String userId) {
		List<Request> requests = new ArrayList<>();
		for (Request request : DbManager.requests) {
			if (Objects.equals(request.getUser().getId(), userId)) {
				requests.add(request);
			}
		}
		return requests;
	}

	@Override
	public Request findById(Long id) {
		return DbManager.requests.stream().filter(r -> Objects.equals(r.getId(), id)).findFirst().orElse(null);
	}

	@Override
	public void save(Request request) {
		if (request.getId() == 0) {
			request.setId(++lastId);
			DbManager.requests.add(request);
		} else {
			for (Request item : DbManager.requests) {
				if (Objects.equals(item.getId(), request.getId())) {
					int index = DbManager.requests.indexOf(item);
					DbManager.requests.set(index, request);
					break;
				}
			}
		}
	}

	@Override
	public void remove(Long id) {
		DbManager.requests.removeIf(r -> Objects.equals(r.getId(), id));
	}

}
