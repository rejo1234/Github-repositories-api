package app;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/github")
@AllArgsConstructor
public class GithubRepositoryController {
    private final GithubRepositoryService service;

    @GetMapping("/{username}/repositories")
    public List<RepositoryResponseDto> getUserRepositories(
            @PathVariable String username) {

        return service.getRepositories(username);
    }
}
