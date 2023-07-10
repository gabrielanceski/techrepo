package br.edu.ifrs.gabrielanceski.techrepo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.gabrielanceski.techrepo.model.Discussion;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    @Query("SELECT d FROM discussions d WHERE d.issue.id = :issueId")
    List<Discussion> findByIssueId(@Param("issueId") UUID issueId);

    Optional<Discussion> findById(Long id);
    
    @Query("SELECT d FROM discussions d WHERE d.parent.id = :parentId")
    List<Discussion> findChildDiscussions(@Param("parentId") Long parentId);

}
