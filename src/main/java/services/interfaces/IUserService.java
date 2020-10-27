package services.interfaces;

import domain.User;

public interface IUserService {

    User getUserByID(long id);
    void addUser(User user);
    void updateUser(User user);
}