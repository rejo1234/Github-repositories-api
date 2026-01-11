package app;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class GithubApiClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<GithubRepositoryDto> getRepositories(String username) {
        try {
            return Arrays.asList(
                    Objects.requireNonNull(restTemplate.getForObject(
                            "https://api.github.com/users/{username}/repos",
                            GithubRepositoryDto[].class,
                            username
                    ))
            );
        } catch (HttpClientErrorException.NotFound ex) {
            throw new GithubUserNotFoundException(username);
        }
    }

    public List<GithubBranchDto> getBranches(String owner, String repo) {
        String url = "https://api.github.com/repos/{owner}/{repo}/branches";
        ResponseEntity<GithubBranchDto[]> response =
                restTemplate.getForEntity(url, GithubBranchDto[].class, owner, repo);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
