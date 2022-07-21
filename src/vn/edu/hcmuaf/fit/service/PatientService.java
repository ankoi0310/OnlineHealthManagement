package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.Patient;

import java.util.List;

public interface PatientService {
    AppResult<List<Patient>> getPatients();
    AppResult<Patient> getPatient(String id);
    AppBaseResult createPatient(Patient patient);
    AppBaseResult updatePatient(Patient patient);
    AppBaseResult removePatient(String id);
}
