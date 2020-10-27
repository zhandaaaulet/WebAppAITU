package services.interfaces;

import domain.AccessToken;
import domain.User;
import domain.UserLoginData;

public interface IAuthorizationService {
    AccessToken authenticateUser(UserLoginData data) throws Exception;

    User getUserByUsername(String issuer);
}