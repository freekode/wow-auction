package org.freekode.wowauction.dao.interfaces;


import org.freekode.wowauction.models.UserEntity;

import java.util.List;

public interface UserDAO {
    void save(UserEntity user);

    UserEntity authenticate(UserEntity user);

    UserEntity getById(Integer id);

    List<UserEntity> findAll();
}
