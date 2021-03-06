package services;

import domain.AccessToken;
import domain.User;
import domain.UserLoginData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import repositories.UserRepository;
import repositories.interfaces.IUserRepository;
import services.interfaces.IAuthorizationService;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class AuthorizationService implements IAuthorizationService {
    private final IUserRepository userRepository = new UserRepository();

    @Override
    public AccessToken authenticateUser(UserLoginData data) throws Exception {
        User authenticateUser = signIn(data);
        return new AccessToken((issueToken(authenticateUser)));
    }

    @Override
    public User getUserByUsername(String issuer) {
        return userRepository.getUserByUsername(issuer);
    }

    private User signIn(UserLoginData data) throws Exception {
        User user = userRepository.findUserbyLogin(data);
        if (user == null) {
            throw new Exception("Authentication failed");
        }
        return user;
    }

    private String issueToken(User user) {
        Instant now = Instant.now();
        String secretWord = "TheStrongestSecretKeyICanThinkOf";
        return Jwts.builder()
                .setIssuer(user.getUsername())
                .setIssuedAt(Date.from(now))
                .claim("1d20", new Random().nextInt(20) + 1)
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secretWord.getBytes()))
                .compact();
    }

}