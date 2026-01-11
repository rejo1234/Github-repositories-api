package app;

import lombok.Data;

@Data
public class GithubBranchDto {
    private String name;
    private Commit commit;

    @Data
    public static class Commit {
        private String sha;
    }
}
