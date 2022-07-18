package vn.edu.hcmuaf.fit.dto;

public class RequestStatus {
    public int status;
    public String message;

    public RequestStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
