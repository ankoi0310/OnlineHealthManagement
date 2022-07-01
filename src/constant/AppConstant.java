package constant;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class AppConstant {
	public static final Map<Integer, String> role = new HashMap<>() {{
		put(1, "Admin");
		put(2, "User");
	}};
	
	public static final Map<String, String> users = new HashMap<>() {{
		put("admin", "admin");
	}};
}
