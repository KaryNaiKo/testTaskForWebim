package com.github.karynaiko.repository;

import com.github.karynaiko.model.User;

public interface UserRepository {
    User update(User user);

    User create(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User getByEmail(String email);

    User getById(int id);
}
