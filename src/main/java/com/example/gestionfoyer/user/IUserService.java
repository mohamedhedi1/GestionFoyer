package com.example.gestionfoyer.user;

import java.util.List;

public interface IUserService {

    void deleteUser(Integer id);

    List<User> getAllUsers();

    Long findStudentIdByEmail(String email);

    UserDto getUserByEmail(String email);
}
