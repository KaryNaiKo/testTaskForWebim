package com.github.karynaiko.repository;

import com.github.karynaiko.model.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImp implements UserRepository{
    @PersistenceContext
    private EntityManager em;



    @Override
    @Transactional
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    @Transactional
    public User create(User user) {
        em.persist(user);
        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery("DELETE").setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = em.createNamedQuery("GET_BY_EMAIL", User.class).setParameter(1, email).getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getById(int id) {
        List<User> users = em.createNamedQuery("GET_BY_ID", User.class).setParameter(1, id).getResultList();
        return DataAccessUtils.singleResult(users);
    }
}
