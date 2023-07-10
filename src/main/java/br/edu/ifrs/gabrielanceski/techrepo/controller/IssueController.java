package br.edu.ifrs.gabrielanceski.techrepo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.gabrielanceski.techrepo.api.issue.IssueRequest;
import br.edu.ifrs.gabrielanceski.techrepo.api.issue.IssueResponse;
import br.edu.ifrs.gabrielanceski.techrepo.model.Discussion;
import br.edu.ifrs.gabrielanceski.techrepo.service.IssueService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {
    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public ResponseEntity<List<IssueResponse>> getAll() {
        return ResponseEntity.ok(issueService.findAll());
    }

    @PostMapping
    public ResponseEntity<IssueResponse> save(@Valid @RequestBody IssueRequest issueRequest) {
        return ResponseEntity.ok(issueService.save(issueRequest));
    }

    @PatchMapping
    public ResponseEntity<IssueResponse> patch(@Valid @RequestBody IssueRequest issueRequest) {
        return ResponseEntity.ok(issueService.save(issueRequest));
    }

    @PutMapping
    public ResponseEntity<IssueResponse> put(@Valid @RequestBody IssueRequest issueRequest) {
        return ResponseEntity.ok(issueService.save(issueRequest));
    }

    @DeleteMapping
    public ResponseEntity<IssueResponse> delete(@Valid @RequestBody IssueRequest issueRequest) {
        return ResponseEntity.ok(issueService.delete(UUID.fromString(issueRequest.id())));
    }

    @GetMapping("/discussions")
    public ResponseEntity<List<Discussion>> getDiscussions(@Valid @RequestBody String issueId) {
        return ResponseEntity.ok(issueService.getDiscussions(UUID.fromString(issueId)));
    }
}
