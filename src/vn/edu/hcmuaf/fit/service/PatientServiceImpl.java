package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.dao.PatientDAO;
import vn.edu.hcmuaf.fit.dao.PatientDAOImpl;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.Patient;

import java.util.List;

public class PatientServiceImpl implements PatientService {
    private PatientDAO patientDAO;

    public PatientServiceImpl() {
        patientDAO = PatientDAOImpl.getInstance();
    }

    @Override
    public AppResult<List<Patient>> getPatients() {
        List<Patient> patients = patientDAO.findAll();
        return new AppResult<>(true, "Success", patients);
    }

    @Override
    public AppResult<Patient> getPatient(String id) {
        return null;
    }

    @Override
    public AppResult<Patient> createPatient(Patient patient) {
        return null;
    }

    @Override
    public AppBaseResult updatePatientInfo(Patient patient) {
        return null;
    }

    @Override
    public AppBaseResult removePatient(String id) {
        return null;
    }
}
