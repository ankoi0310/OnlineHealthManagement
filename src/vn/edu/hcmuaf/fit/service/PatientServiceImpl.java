package vn.edu.hcmuaf.fit.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import vn.edu.hcmuaf.fit.dao.PatientDAO;
import vn.edu.hcmuaf.fit.dao.PatientDAOImpl;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.Patient;

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
    public AppBaseResult updatePatientInfo(Patient patient,int row) {
    	try {
			if (patient.getId().isBlank())
				return new AppBaseResult(false,"Vui lòng CMND");

			if (patient.getFullname().isBlank())
				return new AppBaseResult(false,"Vui lòng nhập tên");

			if (patient.getAge() == 0)
				return new AppBaseResult(false,"Vui lòng nhập tuổi");

			if(!(StringUtils.isNumeric(patient.getId()) && (patient.getId().length() == 9 || patient.getId().length() == 12))) {
				return new AppBaseResult(false,"CMND không hợp lệ");
			}
			patientDAO.update(patient, row);
			//
			return new AppBaseResult(true, "Update patient thành công");
		} catch (Exception e) {
			return new AppBaseResult(false,"Update patient không thành công");
		}
//    	patientDAO.update(patient, row);
//        return new AppBaseResult(true, "Update thành công");
    }

    @Override
    public AppBaseResult removePatient(String id) {
    	patientDAO.remove(id);
        return new AppBaseResult(true, "Xóa thành công");
    }

	@Override
	public AppBaseResult addPatient(Patient patient) {
		// TODO Auto-generated method stub
		
		try {
			if (patient.getId().isBlank())
				return new AppBaseResult(false,"Vui lòng CMND");

			if (patient.getFullname().isBlank())
				return new AppBaseResult(false,"Vui lòng nhập tên");

			if (patient.getAge() == 0)
				return new AppBaseResult(false,"Vui lòng nhập tuổi");

			if(!(StringUtils.isNumeric(patient.getId()) && (patient.getId().length() == 9 || patient.getId().length() == 12))) {
				return new AppBaseResult(false,"CMND không hợp lệ");
			}
				patientDAO.save(patient);
			//
			return new AppBaseResult(true, "Thêm patient thành công");
		} catch (Exception e) {
			return new AppBaseResult(false,"Thêm patient không thành công");
		}
		
//		return new AppBaseResult(true, "Đã thêm thành công");
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		patientDAO.removeAll();
	}

	@Override
	public AppResult<Patient> getPatientByRow(int row) {
		// TODO Auto-generated method stub
		Patient patient = patientDAO.findByRow(row);
		if(patient != null) {
			return new AppResult<Patient>(true, "lay thanh cong", patient);
		}else
			return new AppResult<Patient>(false, "khong tim thay", null);
	}

	@Override
	public void saveAll(List<Patient> list) {
		// TODO Auto-generated method stub
		patientDAO.saveAll(list);
	}
    
    
}
