package org.freekode.wowauction.engine.dao.interfaces;


import org.freekode.wowauction.persistence.models.UserEntity;

import java.util.List;

public interface UserDAO {
    void save(UserEntity user);

    UserEntity authenticate(UserEntity user);

    UserEntity getById(Integer id);

    List<UserEntity> findAll();
}
