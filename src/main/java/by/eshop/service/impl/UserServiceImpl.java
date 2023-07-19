package by.eshop.service.impl;

import by.eshop.model.User;
import by.eshop.repository.UserRepository;
import by.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByLogin(String email, String password) {
        return userRepository.getUserByEmailAndPassword(email, password);
    }

    @Override
    public boolean checkUser(String email, String password) {
        return getUserByLogin(email, password) != null;
    }
}