package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.dto.UserLogin;
import vn.edu.hcmuaf.fit.handle.AppBaseResult;
import vn.edu.hcmuaf.fit.handle.AppResult;
import vn.edu.hcmuaf.fit.model.User;

public interface UserService {
	AppBaseResult register(User user);
	AppResult<User> login(UserLogin userLogin);
	AppBaseResult updateProfile(User user);
}
