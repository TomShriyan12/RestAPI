package com.example.demo.Service;

import com.example.demo.Entity.Mentor;
import com.example.demo.Repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    // New method for pagination and sorting
    public Page<Mentor> getAllMentorsPaginated(Pageable pageable) {
        return mentorRepository.findAll(pageable);
    }

    public Optional<Mentor> getMentorById(Long id) {
        return mentorRepository.findById(id);
    }

    public Mentor createMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    public Optional<Mentor> updateMentor(Long id, Mentor mentor) {
        return mentorRepository.findById(id).map(existingMentor -> {
            existingMentor.setName(mentor.getName());
            existingMentor.setEmail(mentor.getEmail());
            return mentorRepository.save(existingMentor);
        });
    }

    public void deleteMentor(Long id) {
        mentorRepository.deleteById(id);
    }

    // New method to fetch paginated mentors by industry
    public Page<Mentor> getMentorsByIndustryPaginated(String industry, Pageable pageable) {
        return mentorRepository.findByIndustry(industry, pageable);
    }
}
