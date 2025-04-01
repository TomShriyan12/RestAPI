package com.example.demo.Repository;

import com.example.demo.Entity.Mentee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    Page<Mentee> findAll(Pageable pageable); // Pagination and sorting
}
