package app;

import lombok.Data;

import java.util.List;

@Data
public class RepositoryResponseDto {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchResponseDto> branches;
}
