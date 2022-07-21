package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.dao.PatientDAO;
import vn.edu.hcmuaf.fit.dao.PatientDAOImpl;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.Patient;

import java.util.List;

public class PatientServiceImpl implements PatientService {
    private final PatientDAO patientDAO;

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
        Patient patient = patientDAO.findById(id);

        if (patient == null) {
            return new AppResult<>(false, "Patient not found", null);
        }

        return new AppResult<>(true, "Success", patient);
    }

    @Override
    public AppBaseResult createPatient(Patient patient) {
        if (patient.getId() == null || patient.getId().isBlank()) {
            return new AppBaseResult(false, "Vui lòng nhập CCCD/CMND");
        }

        if (!patient.getId().matches("^\\d+$"))
            return AppBaseResult.GenarateIsFailed("CMND/CCCD không hợp lệ");

        if (patient.getId().length() != 9 && patient.getId().length() != 12)
            return AppBaseResult.GenarateIsFailed("CMND/CCCD phải có 9 hoặc 12 số");

        if (patient.getFullname() == null || patient.getFullname().isBlank()) {
            return new AppBaseResult(false, "Vui lòng nhập họ tên");
        }

        if (patientDAO.findById(patient.getId()) != null) {
            return new AppBaseResult(false, "Bệnh nhân đã tồn tại");
        }

        if (patient.getFullname().matches("^[0-9]+$")) {
            return new AppBaseResult(false, "Tên bệnh nhân không hợp lệ");
        }

        patientDAO.save(patient);

        return new AppBaseResult(true, "Tạo bệnh nhân thành công");
    }

    @Override
    public AppBaseResult updatePatient(Patient patient) {

        if (patientDAO.findById(patient.getId()) == null) {
            return new AppBaseResult(false, "Bệnh nhân không tồn tại");
        }

        // contain number
        if (patient.getFullname().matches("^[0-9]+$")) {
            return new AppBaseResult(false, "Tên bệnh nhân không hợp lệ");
        }

        patientDAO.save(patient);
        return new AppBaseResult(true, "Cập nhật bệnh nhân thành công");
    }

    @Override
    public AppBaseResult removePatient(String id) {
        if (patientDAO.findById(id) == null) {
            return new AppBaseResult(false, "Bệnh nhân không tồn tại");
        }

        patientDAO.remove(id);
        return new AppBaseResult(true, "Xóa bệnh nhân thành công");
    }
}
