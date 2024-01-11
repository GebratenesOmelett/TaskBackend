package backend.task.taskbackend.config.dto;

public record AuthenticationResponse(String token, String email, String expiresIn) {
}
