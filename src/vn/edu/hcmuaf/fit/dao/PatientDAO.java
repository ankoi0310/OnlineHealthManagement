package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.model.Patient;

import java.util.List;

public interface PatientDAO {
    List<Patient> findAll();
    Patient findById(String id);
    void save(Patient patient);
    void remove(String id);
}
