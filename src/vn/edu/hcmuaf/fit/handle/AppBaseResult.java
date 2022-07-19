package vn.edu.hcmuaf.fit.handle;

public class AppBaseResult {
	private boolean success;
	private String message;

	public AppBaseResult() {
	}

	public AppBaseResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public static AppBaseResult GenarateIsSucceed() {
		return new AppBaseResult(true, "Success");
	}

	public static AppBaseResult GenarateIsFailed(String message) {
		return new AppBaseResult(false, message);
	}

	public boolean isSuccess() {
		return success;
	}

	protected void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}
}
