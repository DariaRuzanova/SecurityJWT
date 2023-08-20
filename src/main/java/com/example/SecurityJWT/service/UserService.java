package com.example.SecurityJWT.service;

import com.example.SecurityJWT.entityes.User;
import com.example.SecurityJWT.repositories.RoleRepository;
import com.example.SecurityJWT.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(()->new UsernameNotFoundException(String.format("пользователь %s не найден",username)));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));

    }
    public void createNewUser(User user){
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        userRepository.save(user);
    }
}
