package com.example.demo.Repository;

import com.example.demo.Entity.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findByIndustry(String industry);

    // New method for pagination and sorting
    Page<Mentor> findByIndustry(String industry, Pageable pageable);
    Page<Mentor> findAll(Pageable pageable);

    
}
