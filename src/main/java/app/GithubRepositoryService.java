package app;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GithubRepositoryService {
    private final GithubApiClient githubApiClient;

    public List<RepositoryResponseDto> getRepositories(String username) {

        List<GithubRepositoryDto> repos =
                githubApiClient.getRepositories(username);

        return repos.stream()
                .filter(repo -> !repo.isFork())
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private List<BranchResponseDto> toResponseList(List<GithubBranchDto> githubBranchDtoList) {
        if (githubBranchDtoList == null) {
            return Collections.emptyList();
        }

        return githubBranchDtoList.stream()
                .map(branch -> BranchResponseDto.builder()
                        .name(branch.getName())
                        .lastCommitSha(branch.getCommit() != null ? branch.getCommit().getSha() : null)
                        .build()
                )
                .collect(Collectors.toList());
    }

    private RepositoryResponseDto mapToResponse(GithubRepositoryDto repo) {
        List<GithubBranchDto> githubBranchDtoList = githubApiClient.getBranches(repo.getOwner().getLogin(), repo.getName());
        List<BranchResponseDto> branchResponseDtoList = toResponseList(githubBranchDtoList);
        RepositoryResponseDto dto = new RepositoryResponseDto();
        dto.setRepositoryName(repo.getName());
        dto.setOwnerLogin(repo.getOwner().getLogin());
        dto.setBranches(branchResponseDtoList);
        return dto;
    }
}
