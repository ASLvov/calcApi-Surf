package app.service;


import app.domain.UserEntity;
import app.repository.RoleRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity saveUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> findByLogin(String login) {
        return userRepository.findByUserName(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        Optional<UserEntity> userEntity = findByLogin(login);
        if (userEntity.get() != null) {
            if (passwordEncoder.matches(password, userEntity.get().getPassword())) {
                return userEntity.get();
            }
        }
        return null;
    }
}