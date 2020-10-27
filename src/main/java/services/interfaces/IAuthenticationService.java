package services.interfaces;

import domain.User;

public interface IAuthenticationService {

    User findUniqueUsername(String newuser) throws Exception;
    void createUserByIssue(User newuser);

    User prepareUser(User data) throws Exception;
}