package common.txbpt;

import java.io.Serializable;

public class TXBPTAuthPolicyResponseDTO implements Serializable {

    private int status;
    private String message;
    private String returnDate;
    private String errorInfo;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return "TXBPTAuthPolicyResponseDTO{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", errorInfo='" + errorInfo + '\'' +
                '}';
    }
}
