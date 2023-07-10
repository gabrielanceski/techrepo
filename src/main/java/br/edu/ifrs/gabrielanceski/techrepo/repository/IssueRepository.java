package br.edu.ifrs.gabrielanceski.techrepo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.gabrielanceski.techrepo.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, UUID> {
    
}
