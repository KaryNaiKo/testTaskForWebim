package com.github.karynaiko.service;

import com.github.karynaiko.AuthorizedUser;
import com.github.karynaiko.model.User;
import com.github.karynaiko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository repository;


    @Override
    public User create(User user) {
        return repository.create(user);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public void update(User user) {
        repository.update(user);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String id) throws UsernameNotFoundException {
        User u = null;
        try {
            int userId = Integer.parseInt(id);
            u = repository.getById(userId);
            if (u == null) {
                throw new UsernameNotFoundException("User " + id + " is not found");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return new AuthorizedUser(u);
    }
}
