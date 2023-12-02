package com.example.gestionfoyer.user;

import com.example.gestionfoyer.entities.Etudiant;
import com.example.gestionfoyer.repositories.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private final  UserRepository userRepository;
    private final EtudiantRepository etudiantRepository;

     public void deleteUser(Integer id)
    {
        this.userRepository.deleteById(id);
    }
    public List<User> getAllUsers()
    {
        return this.userRepository.findAll();
    }

    @Override
    public Long findStudentIdByEmail(String email) {
        Etudiant e=this.etudiantRepository.findByEmail(email);
        return e.getIdEtudiant();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> userOp = this.userRepository.findByEmail(email);
        return userOp.map(user -> new UserDto(user.getFirstname(), user.getLastname(), user.getEmail(), user.getRole()))
                .orElse(null);
    }



}
