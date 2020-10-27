package services;

import domain.User;


import repositories.UserRepository;
import repositories.interfaces.IUserRepository;
import services.interfaces.IAuthenticationService;

public class AuthenticationService implements IAuthenticationService {
    private final IUserRepository userRepository = new UserRepository();


    @Override
    public User findUniqueUsername(String newuser) throws Exception {
        User user = userRepository.getUserByUsername(newuser);
        if (user != null) {
            throw new Exception("The username already exists");
        } else {
            return user;
        }


    }

    @Override
    public void createUserByIssue(User newuser) {
        userRepository.add(newuser);
    }

    @Override
    public User prepareUser(User data) throws Exception {
        User user = findUniqueUsername(data.getUsername());
        if(data.getUsername().equals(user.getUsername())){
            createUserByIssue(user);
            return user;
        }
        return null;
    }
}