package br.edu.ifrs.gabrielanceski.techrepo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.gabrielanceski.techrepo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM users u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);

}
