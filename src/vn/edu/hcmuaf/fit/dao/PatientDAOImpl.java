package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.database.DbManager;
import vn.edu.hcmuaf.fit.model.Patient;

import java.util.List;
import java.util.Objects;

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
		List<String> ids = DbManager.patients.stream().map(Patient::getId).toList();
		if (!ids.contains(patient.getId())) {
			DbManager.patients.add(patient);
		} else {
			for (Patient item : DbManager.patients) {
				if (Objects.equals(item.getId(), patient.getId())) {
					int index = DbManager.patients.indexOf(item);
					DbManager.patients.set(index, patient);
					break;
				}
			}
		}
	}

	@Override
	public void remove(String id) {
		DbManager.patients.removeIf(p -> p.getId().equals(id));
	}
}
