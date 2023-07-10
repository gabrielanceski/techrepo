package br.edu.ifrs.gabrielanceski.techrepo.api.discussion;

import java.time.LocalDateTime;

import br.edu.ifrs.gabrielanceski.techrepo.model.Issue;
import br.edu.ifrs.gabrielanceski.techrepo.model.User;

public record DiscussionResponse(
    Long id,
    String content,
    User authorId,
    Issue issueId,
    DiscussionResponse parentId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
