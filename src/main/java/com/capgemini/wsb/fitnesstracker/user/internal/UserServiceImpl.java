package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Creating User with Id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, User user) {
        var foundUser = userRepository.findById(id);
        if(foundUser.isEmpty()){
            throw new UserNotFoundException(id);
        }

        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        var user = userRepository.findById(userId);

        if(user.isEmpty()){
            throw new UserNotFoundException(userId);
        }
        return user;
    }

    @Override
    public List<User> getUsersByEmailPart(final String email) {
        return userRepository.findUsersByEmailPart(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersOlderThan(LocalDate time){
        return userRepository.findAllOlderThan(time);
    }

}