package org.freekode.wowauction.dao.interfaces;

import org.freekode.wowauction.models.User;

import java.util.List;

public interface UserDAO {
    void create(User user);
    User authenticate(User user);
    User getById(Integer id);
    List<User> getAll();
}
