package com.example.demo.Service;

import com.example.demo.Entity.Mentee;
import com.example.demo.Repository.MenteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenteeService {
    @Autowired
    private MenteeRepository menteeRepository;

    public List<Mentee> getAllMentees() {
        return menteeRepository.findAll();
    }

    public Optional<Mentee> getMenteeById(Long id) {
        return menteeRepository.findById(id);
    }

    public Mentee createMentee(Mentee mentee) {
        return menteeRepository.save(mentee);
    }

    public Optional<Mentee> updateMentee(Long id, Mentee mentee) {
        return menteeRepository.findById(id).map(existingMentee -> {
            existingMentee.setName(mentee.getName());
            existingMentee.setEmail(mentee.getEmail());
            return menteeRepository.save(existingMentee);
        });
    }
    
    public void deleteMentee(Long id) {
        menteeRepository.deleteById(id);
    }

    // New method for pagination and sorting
    public Page<Mentee> getAllMenteesPaginated(Pageable pageable) {
        return menteeRepository.findAll(pageable);
    }
}
