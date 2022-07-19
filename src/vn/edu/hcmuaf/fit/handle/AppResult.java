package vn.edu.hcmuaf.fit.handle;

public class AppResult<T> extends AppBaseResult {
	private T data;
	
	public AppResult() {
    }

    public AppResult(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }

    public boolean isSuccess() {
        return super.isSuccess();
    }

    public void setSuccess(boolean success) {
        super.setSuccess(success);
    }

    public String getMessage() {
        return super.getMessage();
    }

    public void setMessage(String message) {
        super.setMessage(message);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
