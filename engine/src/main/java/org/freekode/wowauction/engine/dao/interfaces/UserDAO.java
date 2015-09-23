package org.freekode.wowauction.engine.dao.interfaces;


import org.freekode.wowauction.persistence.models.User;

import java.util.List;

public interface UserDAO {
    void save(User user);

    User authenticate(User user);

    User getById(Integer id);

    List<User> findAll();
}
