package br.edu.ifrs.gabrielanceski.techrepo.api.issue;

import br.edu.ifrs.gabrielanceski.techrepo.model.IssueStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IssueRequest(
        @Nullable String id,
        @Nullable String chassis,
        @Nullable String cause,
        @NotBlank String reportedIssue,
        @Nullable String foundIssue,
        @Nullable IssueStatus status,
        @NotNull Long authorId,
        @NotNull Long deviceId) {   
}
