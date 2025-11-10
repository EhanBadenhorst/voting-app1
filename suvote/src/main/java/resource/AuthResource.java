package resource;

import dto.AuthResponse;
import entity.Student;
import service.AuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Optional;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    
    @Inject
    AuthService authService;
    
    public static class LoginRequest {
        public String studentNo;
        public String password;
        
        public LoginRequest() {}
        
        public LoginRequest(String studentNo, String password) {
            this.studentNo = studentNo;
            this.password = password;
        }
    }
    
    @POST
    @Path("/login")
    public Response login(LoginRequest loginRequest) {
        try {
            // Validate input
            if (loginRequest.studentNo == null || loginRequest.studentNo.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new AuthResponse(false, "Student number is required", null))
                    .build();
            }
            
            if (loginRequest.password == null || loginRequest.password.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new AuthResponse(false, "Password is required", null))
                    .build();
            }
            
            // Authenticate user
            Optional<Student> studentOpt = authService.authenticate(
                loginRequest.studentNo.trim(), 
                loginRequest.password
            );
            
            if (studentOpt.isPresent()) {
                Student student = studentOpt.get();
                // Remove password from response
                student.setPassword(null);
                return Response.ok(new AuthResponse(true, "Login successful", student)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new AuthResponse(false, "Invalid student number or password", null))
                    .build();
            }
            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new AuthResponse(false, "Login failed", null))
                .build();
        }
    }
    
    @GET
    @Path("/status")
    public Response status() {
        return Response.ok(new AuthResponse(true, "Auth service is running", null)).build();
    }
}