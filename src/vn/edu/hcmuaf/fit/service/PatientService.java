package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.Patient;

import java.util.List;

public interface PatientService {
    AppResult<List<Patient>> getPatients();
    AppResult<Patient> getPatient(String id);
    AppResult<Patient> createPatient(Patient patient);
    AppBaseResult updatePatientInfo(Patient patient);
    AppBaseResult removePatient(String id);
}
