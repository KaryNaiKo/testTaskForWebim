package com.github.karynaiko.service;


import com.github.karynaiko.model.User;

public interface UserService {

    User create(User user);

    void delete(int id);

    User get(String id);

    User getByEmail(String email);

    void update(User user);

}
