package br.edu.ifrs.gabrielanceski.techrepo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.gabrielanceski.techrepo.api.issue.IssueRequest;
import br.edu.ifrs.gabrielanceski.techrepo.api.issue.IssueResponse;
import br.edu.ifrs.gabrielanceski.techrepo.exception.ResourceNotFoundException;
import br.edu.ifrs.gabrielanceski.techrepo.model.Discussion;
import br.edu.ifrs.gabrielanceski.techrepo.model.Issue;
import br.edu.ifrs.gabrielanceski.techrepo.repository.DeviceRepository;
import br.edu.ifrs.gabrielanceski.techrepo.repository.DiscussionRepository;
import br.edu.ifrs.gabrielanceski.techrepo.repository.IssueRepository;
import br.edu.ifrs.gabrielanceski.techrepo.repository.UserRepository;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final DiscussionRepository discussionRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, DeviceRepository deviceRepository,
            UserRepository userRepository, DiscussionRepository discussionRepository) {
        this.issueRepository = issueRepository;
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.discussionRepository = discussionRepository;
    }

    public List<Discussion> getDiscussions(UUID issueId) {
        return discussionRepository.findByIssueId(issueId);
    }

    public List<IssueResponse> findAll() {
        return issueRepository.findAll().stream().map(issue -> {
                return new IssueResponse(
                        issue.getId(),
                        issue.getChassis(),
                        issue.getCause(),
                        issue.getReportedIssue(),
                        issue.getFoundIssue(), 
                        issue.getStatus(), 
                        issue.getAuthor().getId(), 
                        issue.getDevice().getId(),
                        issue.getCreatedAt(), 
                        issue.getUpdatedAt()
                );
        }).toList();
    }

    public IssueResponse save(IssueRequest issueRequest) {
        Issue issue = new Issue();
        issue.setId(UUID.fromString(issueRequest.id()));
        issue.setChassis(issueRequest.chassis());
        issue.setCause(issueRequest.cause());
        issue.setReportedIssue(issueRequest.reportedIssue());
        issue.setFoundIssue(issueRequest.foundIssue());
        issue.setStatus(issueRequest.status());

        issue.setDevice(deviceRepository.findById(issueRequest.deviceId())
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo não encontrado!")));
        issue.setAuthor(userRepository.findById(issueRequest.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!")));

        issueRepository.save(issue);

        return new IssueResponse(
                issue.getId(),
                issue.getChassis(),
                issue.getCause(),
                issue.getReportedIssue(),
                issue.getFoundIssue(), 
                issue.getStatus(), 
                issue.getAuthor().getId(), 
                issue.getDevice().getId(),
                issue.getCreatedAt(), 
                issue.getUpdatedAt()
        );
    }

    
    // Delete Issue
    public IssueResponse delete(UUID issueId) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new ResourceNotFoundException("Issue not found!"));
        issueRepository.delete(issue);
        return new IssueResponse(
                issue.getId(),
                issue.getChassis(),
                issue.getCause(),
                issue.getReportedIssue(),
                issue.getFoundIssue(), 
                issue.getStatus(), 
                issue.getAuthor().getId(), 
                issue.getDevice().getId(),
                issue.getCreatedAt(), 
                issue.getUpdatedAt()
        );
    }

}
