package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.database.DbManager;
import vn.edu.hcmuaf.fit.model.Patient;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {
	private static PatientDAOImpl _instance;
	
	private PatientDAOImpl() {}
	
	public static PatientDAOImpl getInstance() {
		if (_instance == null) _instance = new PatientDAOImpl();
		return _instance;
	}

	@Override
	public List<Patient> findAll() {
		return DbManager.patients;
	}

	@Override
	public Patient findById(String id) {
		return DbManager.patients.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public void save(Patient patient) {

	}

	@Override
	public void remove(String id) {
		DbManager.patients.removeIf(p -> p.getId().equals(id));
	}
}
