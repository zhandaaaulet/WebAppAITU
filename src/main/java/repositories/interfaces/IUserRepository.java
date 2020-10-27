package repositories.interfaces;

import domain.User;
import domain.UserLoginData;

public interface IUserRepository extends IEntityRepository<User> {

    User getUserByID(long id);
    User findUserbyLogin(UserLoginData data);
    User getUserByUsername(String issuer);


}