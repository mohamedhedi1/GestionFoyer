package com.example.gestionfoyer.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private final  UserRepository userRepository;

     public void deleteUser(Integer id)
    {
        this.userRepository.deleteById(id);
    }
    public List<User> getAllUsers()
    {
        return this.userRepository.findAll();
    }


}
