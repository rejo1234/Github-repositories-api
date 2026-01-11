package app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GithubUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GitHubErrorResponse handleUserNotFound(GithubUserNotFoundException ex) {
        return new GitHubErrorResponse(404, ex.getMessage());
    }
}
