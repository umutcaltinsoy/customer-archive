package com.altinsoy.customerarchive.service.impl;

import com.altinsoy.customerarchive.model.Role;
import com.altinsoy.customerarchive.model.User;
import com.altinsoy.customerarchive.repository.RoleRepository;
import com.altinsoy.customerarchive.repository.UserRepository;
import com.altinsoy.customerarchive.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user to db");
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role to db", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        log.info("Saving new role to user");
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("fetching user");
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("fetching all users");
        return userRepository.findAll();
    }
}
