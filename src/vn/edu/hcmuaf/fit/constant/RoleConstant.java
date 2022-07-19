package vn.edu.hcmuaf.fit.constant;

import vn.edu.hcmuaf.fit.dto.Role;

public class RoleConstant {
	public static final Role ADMIN = new Role(1L, "ADMIN");
	public static final Role EMPLOYEE = new Role(2L, "EMPLOYEE");
	public static final Role HOSPITAL = new Role(3L, "HOSPITAL");
	public static final Role USER = new Role(4L, "USER");
}
