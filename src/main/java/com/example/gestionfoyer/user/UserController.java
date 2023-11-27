package com.example.gestionfoyer.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final IUserService userService;


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id)
    {
       this.userService.deleteUser(id);
    }
    @GetMapping()
    public List<User> getAllUsers()
    {
        return this.userService.getAllUsers();
    }

}