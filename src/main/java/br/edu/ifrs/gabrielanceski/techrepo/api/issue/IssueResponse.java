package br.edu.ifrs.gabrielanceski.techrepo.api.issue;

import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.ifrs.gabrielanceski.techrepo.model.IssueStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IssueResponse(
        UUID id,
        @Nullable String chassis,
        @NotBlank String cause,
        @NotBlank String reportedIssue,
        @Nullable String foundIssue,
        @NotNull IssueStatus statusId,
        @NotNull Long authorId,
        @NotNull Long deviceId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
