package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.Patient;

import java.util.List;

public interface PatientService {
    AppResult<List<Patient>> getPatients();
    AppResult<Patient> getPatient(String id);
    AppResult<Patient> createPatient(Patient patient);
    AppBaseResult updatePatientInfo(Patient patient, int row);
    AppBaseResult removePatient(String id);
    AppBaseResult addPatient(Patient patient);
    AppResult<Patient> getPatientByRow(int row);
    void removeAll();
    void saveAll(List<Patient> list);
}
