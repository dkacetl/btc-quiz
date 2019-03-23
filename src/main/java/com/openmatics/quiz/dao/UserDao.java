package com.openmatics.quiz.dao;

import com.openmatics.quiz.model.UserEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity find(String email) {
        return entityManager.find(UserEntity.class, email);
    }

    public boolean exists(String email) {
        UserEntity ue = find(email);
        return ue != null;
    }

    @Transactional
    public void persist(UserEntity userEntity) {
        entityManager.persist(userEntity);
    }

    @Transactional
    public UserEntity merge(UserEntity userEntity) {
        return entityManager.merge(userEntity);
    }


}
