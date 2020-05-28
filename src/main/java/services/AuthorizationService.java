package services;

import domain.AccessToken;
import domain.SignInData;
import domain.User;
import repositories.entities.UserRepository;

public class AuthorizationService {
    private final UserRepository userRepo = new UserRepository();

    public AccessToken signIn(SignInData data) throws Exception {
        User authorizeUser = userRepo.getUserByLogin(data);
        if (authorizeUser == null) {
            throw new Exception("Authorization failed!");
        }
        AccessToken token = new AccessToken(getToken(authorizeUser));
        return token;
    }

    private String getToken(User user) {
        String token = user.getUsername() + ":" + user.getPassword();
        return token;
    }
}
