package br.edu.ifrs.gabrielanceski.techrepo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifrs.gabrielanceski.techrepo.api.discussion.DiscussionRequest;
import br.edu.ifrs.gabrielanceski.techrepo.api.discussion.DiscussionResponse;
import br.edu.ifrs.gabrielanceski.techrepo.exception.ResourceNotFoundException;
import br.edu.ifrs.gabrielanceski.techrepo.model.Discussion;
import br.edu.ifrs.gabrielanceski.techrepo.repository.DiscussionRepository;
import br.edu.ifrs.gabrielanceski.techrepo.repository.IssueRepository;
import br.edu.ifrs.gabrielanceski.techrepo.repository.UserRepository;

@Service
public class DiscussionService {
    private DiscussionRepository discussionRepository;
    private IssueRepository issueRepository;
    private UserRepository userRepository;

    @Autowired
    public DiscussionService(DiscussionRepository discussionRepository, IssueRepository issueRepository, UserRepository userRepository) {
        this.discussionRepository = discussionRepository;
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
    }

    public List<Discussion> findChildDiscussions(Long parentId) {
        return discussionRepository.findChildDiscussions(parentId);
    }

    public DiscussionResponse save(DiscussionRequest request) {
        Discussion discussion = new Discussion();
        discussion.setId(request.id());
        discussion.setContent(request.content());
        discussion.setIssue(issueRepository.findById(UUID.fromString(request.issueId())).orElseThrow(() -> new ResourceNotFoundException("Solução não encontrada.")));
        discussion.setParent(discussionRepository.findById(request.parentId()).orElse(null));
        discussion.setAuthor(userRepository.findById(request.authorId()).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado.")));

        discussionRepository.save(discussion);

        return toResponse(discussion);
    }

    private DiscussionResponse toResponse(Discussion discussion) {
        if (discussion == null) return null;
        return new DiscussionResponse(
            discussion.getId(),
            discussion.getContent(),
            discussion.getAuthor(),
            discussion.getIssue(),
            toResponse(discussion.getParent()),
            discussion.getCreatedAt(),
            discussion.getUpdatedAt()
        );
    }
}
