package br.edu.ifrs.gabrielanceski.techrepo.api.discussion;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DiscussionRequest(
    @Nullable Long id,
    @NotBlank String content,
    @NotBlank String issueId,
    @NotNull Long authorId,
    @Nullable Long parentId
) {
    
}
