package com.trio.trio;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TrioClerk {
    
    private final VolunRepository volunRepository;
    private final AdminRepository adminRepository;

    public void setUser(Volunteer user) {
        volunRepository.save(user);
    }

    public Volunteer findByUserEmail(String email) {
        return volunRepository.findByEmail(email);
    }

    public List<Volunteer> findByUserName(String name) {
        return volunRepository.findByName(name);
    }

    public List<Volunteer> getAllUsers() {
        return volunRepository.findAll();
    }

    public void deleteByUserEmail(String email) {
        volunRepository.deleteByEmail(email);
    }

    public void deleteAllUsers() {
        volunRepository.deleteAll();
    }

    public void setAdmin(Administrator developer) {
        adminRepository.save(developer);
    }

    public List<Administrator> getAllAdmins() {
        return adminRepository.findAll();
    }

}