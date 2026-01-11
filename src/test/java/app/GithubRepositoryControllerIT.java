package app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9561)
class GithubRepositoryControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnRepositoriesWhenUserExists() {
        String existingUser = "rejo1234";

        ResponseEntity<RepositoryResponseDto[]> response = restTemplate.getForEntity(
                "/api/v1/github/{username}/repositories",
                RepositoryResponseDto[].class,
                existingUser
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());

        RepositoryResponseDto[] repos = response.getBody();
        assertNotNull(repos);
        assertTrue(repos.length > 0);

        RepositoryResponseDto firstRepo = repos[0];
        assertNotNull(firstRepo.getRepositoryName());
        assertNotNull(firstRepo.getOwnerLogin());
        assertNotNull(firstRepo.getBranches());
    }

    @Test
    void shouldReturn404WhenUserNotFound() {
        String nonExistingUser = "user_dla_testu";

        ResponseEntity<GitHubErrorResponse> response = restTemplate.getForEntity(
                "/api/v1/github/{username}/repositories",
                GitHubErrorResponse.class,
                nonExistingUser
        );

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        GitHubErrorResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(404, body.getStatus());
        assertTrue(body.getMessage().contains(nonExistingUser));
    }
}
