package com.ecommerce.service;

import com.ecommerce.dto.UserRequestDTO;
import com.ecommerce.entity.User;
import com.ecommerce.enums.Role;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void register(UserRequestDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        //Encode password
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        System.out.println(passwordEncoder.encode("1234"));
        user.setRole(Role.USER);
        userRepository.save(user);

    }

}
