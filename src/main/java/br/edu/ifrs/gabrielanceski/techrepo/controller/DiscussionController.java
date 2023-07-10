package br.edu.ifrs.gabrielanceski.techrepo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.gabrielanceski.techrepo.api.discussion.DiscussionRequest;
import br.edu.ifrs.gabrielanceski.techrepo.api.discussion.DiscussionResponse;
import br.edu.ifrs.gabrielanceski.techrepo.service.DiscussionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/discussions")
public class DiscussionController {
    private final DiscussionService discussionService;

    @Autowired
    public DiscussionController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    @PostMapping
    public ResponseEntity<DiscussionResponse> save(@Valid @RequestBody DiscussionRequest discussionRequest) {
        return ResponseEntity.ok(discussionService.save(discussionRequest));
    }

    @PatchMapping
    public ResponseEntity<DiscussionResponse> patch(@Valid @RequestBody DiscussionRequest discussionRequest) {
        return ResponseEntity.ok(discussionService.save(discussionRequest));
    }

}
