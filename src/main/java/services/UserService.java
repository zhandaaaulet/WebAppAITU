package services;

import domain.User;

import repositories.UserRepository;
import repositories.interfaces.IUserRepository;
import services.interfaces.IUserService;

public class UserService implements IUserService {
    private final IUserRepository userrepo= new UserRepository();

    @Override
    public User getUserByID(long id) {
        return userrepo.getUserByID(id);
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}