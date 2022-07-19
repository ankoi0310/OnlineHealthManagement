package vn.edu.hcmuaf.fit.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.edu.hcmuaf.fit.model.Patient;
import vn.edu.hcmuaf.fit.model.Request;
import vn.edu.hcmuaf.fit.model.User;

public class DbManager {
	public static Map<Integer, String> requestStatus = new HashMap<>();
	public static List<User> users = new ArrayList<>();
	public static List<Request> requests = new ArrayList<>();
	public static List<Patient> patients = new ArrayList<>();
}
