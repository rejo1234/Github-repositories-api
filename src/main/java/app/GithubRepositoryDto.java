package app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepositoryDto {
    private String name;
    private boolean fork;
    private Owner owner;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Owner {
        private String login;
    }
}
