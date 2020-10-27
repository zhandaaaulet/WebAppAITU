package repositories;

import domain.User;
import domain.UserLoginData;
import repositories.db.PostgresRepository;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IUserRepository;

import javax.ws.rs.BadRequestException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDBRepository dbrepo = new PostgresRepository();


    @Override
    public void add(User entity) {
        try {
            String sql = "INSERT INTO users(name, surname, username, password, birthday,role) " +
                    "VALUES(?, ?, ?, ?, ?,?)";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getSurname());
            stmt.setString(3, entity.getUsername());
            stmt.setString(4, entity.getPassword());
            stmt.setString(5,  entity.getBirthday());
            stmt.setString(6,  entity.getRole());


            stmt.execute();
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: Username already exists!!!");
        }
    }


    @Override
    public void update(User entity) {
        String sql = "UPDATE users " +
                "SET ";
        int c = 0;
        if (entity.getName() != null) {
            sql += "name=?, ";
            c++;
        }
        if (entity.getSurname() != null) {
            sql += "surname=?, ";
            c++;
        }
        if (entity.getPassword() != null) {
            sql += "password=?, ";
            c++;
        }
        if (entity.getBirthday() != null) {
            sql += "birthday=?, ";
            c++;
        }
        if (entity.getRole() != null) {
            sql += "role=?, ";
            c++;
        }

        sql = sql.substring(0, sql.length() - 2);

        sql += " WHERE username = ?";

        try {
            int i = 1;
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            if (entity.getName() != null) {
                stmt.setString(i++, entity.getName());
            }
            if (entity.getSurname() != null) {
                stmt.setString(i++, entity.getSurname());
            }
            if (entity.getPassword() != null) {
                stmt.setString(i++, entity.getPassword());
            }
            if (entity.getBirthday() != null) {
                stmt.setString(i++,entity.getBirthday());
            }
            if (entity.getRole() != null) {
                stmt.setString(i++,entity.getRole());
            }
            stmt.setString(i++, entity.getUsername());

            stmt.execute();
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }

    @Override
    public void remove(User entity) {

    }

    //long id, String name, String surname, String username, String password, String birthday, String role
    @Override
    public List<User> query(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<User> users = new LinkedList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("birthday"),
                        rs.getString("role"));


                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getSQLState());
        }
    }

    @Override
    public User queryOne(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("birthday"),
                        rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            throw new BadRequestException();
        }
        return null;
    }

    @Override
    public User getUserByID(long id) {
        String sql = "SELECT * FROM users WHERE id=" + id + " LIMIT 1";
        return queryOne(sql);
    }

    @Override
    public User findUserbyLogin(UserLoginData data) {
        String sql = "SELECT * FROM users WHERE username=? and password=? LIMIT 1";
        try {
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, data.getUsername());
            stmt.setString(2, data.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("birthday"),
                        rs.getString("role"));

                return user;

            }

        } catch (SQLException e) {
            throw new BadRequestException();
        }
        return null;
    }

    @Override
    public User getUserByUsername(String issuer) {
        String sql = "SELECT * FROM users WHERE username='" + issuer + "' LIMIT 1";
        return queryOne(sql);
    }


}