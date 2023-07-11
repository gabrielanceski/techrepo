package br.edu.ifrs.gabrielanceski.techrepo.api.issue;

import java.time.LocalDateTime;

import br.edu.ifrs.gabrielanceski.techrepo.model.Device;
import br.edu.ifrs.gabrielanceski.techrepo.model.IssueStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IssueResponse(
        String id,
        @Nullable String chassis,
        @NotBlank String cause,
        @NotBlank String reportedIssue,
        @Nullable String foundIssue,
        @NotNull IssueStatus statusId,
        @NotNull Long authorId,
        Device device,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
