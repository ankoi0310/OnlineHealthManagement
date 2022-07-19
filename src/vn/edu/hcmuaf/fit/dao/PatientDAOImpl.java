package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.database.DbManager;
import vn.edu.hcmuaf.fit.model.Patient;

import java.util.ArrayList;
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
		DbManager.patients.add(patient);
	}

	@Override
	public void remove(String id) {
		DbManager.patients.removeIf(p -> p.getId().equals(id));
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		DbManager.patients = new ArrayList<Patient>();
	}

	@Override
	public Patient findByRow(int row) {
		// TODO Auto-generated method stub
		return DbManager.patients.get(row);
	}

	@Override
	public void update(Patient patient, int row) {
		// TODO Auto-generated method stub
		DbManager.patients.add(row+1, patient);
		DbManager.patients.remove(row);
	}

	@Override
	public void saveAll(List<Patient> list) {
		// TODO Auto-generated method stub
		DbManager.patients = list;
	}
	
}
