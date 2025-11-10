package dto;

public class AuthResponse {
    private boolean success;
    private String message;
    private Object student;
    
    public AuthResponse() {}
    
    public AuthResponse(boolean success, String message, Object student) {
        this.success = success;
        this.message = message;
        this.student = student;
    }
    
    // Getters and Setters
    public boolean isSuccess() { 
        return success; 
    }
    
    public void setSuccess(boolean success) { 
        this.success = success; 
    }
    
    public String getMessage() { 
        return message; 
    }
    
    public void setMessage(String message) { 
        this.message = message; 
    }
    
    public Object getStudent() {
        return student; 
    }
    
    public void setStudent(Object student) { 
        this.student = student; 
    }
}