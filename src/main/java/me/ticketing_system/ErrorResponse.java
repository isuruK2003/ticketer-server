package me.ticketing_system;

public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
    	return this.status;
    }

    public String getMessage() {
    	return this.message;
    }

    public void setStatus(int newStatus) {
    	this.status = newStatus;
    }

    public void setMessage(String newMessage) {
    	this.message = newMessage;
    }
}

