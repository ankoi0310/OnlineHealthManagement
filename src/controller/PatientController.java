package controller;

import java.util.ArrayList;
import java.util.List;

import dto.patient.PatientCreate;
import handle.AppResult;
import model.Patient;
import view.Main;

public class PatientController {
	public PatientController() {
	}
	
	public AppResult<List<Patient>> createPatientList(List<PatientCreate> patients) {
		List<Patient> newPatients = new ArrayList<>();
		
		for (PatientCreate patientCreate : patients) {
			if (patientCreate.getId().isBlank())
				return new AppResult<>(false, "Thiếu cmnd/cccd", null);
			
			if (patientCreate.getFullname().isBlank())
				return new AppResult<>(false, "Thiếu họ tên", null);
				
			Main.patients.forEach(patient -> {
				if (patient.getId().equals(patientCreate.getId())) {
					patient.setAge(patientCreate.getAge()); // Tự động cập nhật tuổi
					newPatients.add(patient);
				} else {
					String id = patientCreate.getId();
					String fullname = patientCreate.getFullname();
					int age = patientCreate.getAge();
					boolean male = patientCreate.isMale();
					
					Patient newPatient = new Patient(id, fullname, age, male);
					Main.patients.add(newPatient); // Thêm bệnh nhân mới
					newPatients.add(newPatient);
				}
			});
		}
		
		return new AppResult<>(true, "Tạo thông tin bệnh nhân thành công!", newPatients);
	}
}
