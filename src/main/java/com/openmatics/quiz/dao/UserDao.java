package com.openmatics.quiz.dao;

import com.openmatics.quiz.model.UserEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

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
        userEntity.setCreated(LocalDateTime.now());
        entityManager.persist(userEntity);
    }

    @Transactional
    public UserEntity merge(UserEntity userEntity) {
        if (userEntity.getCreated()==null) {
            userEntity.setCreated(LocalDateTime.now());
        }
        userEntity.setUpdated(LocalDateTime.now());
        return entityManager.merge(userEntity);
    }

    public List<UserEntity> findAll() {
        TypedQuery<UserEntity> query = entityManager.createQuery("Select ue From UserEntity ue order by ue.email",UserEntity.class);
        return query.getResultList();
    }
}
