package com.example.gestionfoyer.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public record UserDto(String firstname,String lastname,String email,Role role){
}
