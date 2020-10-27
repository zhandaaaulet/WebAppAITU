package domain;

public class UserDTO {
    private String name;
    private String surname;
    private String username;
    private String birthday;

    public UserDTO(User user) {
        setName(user.getName());
        setSurname(user.getSurname());
        setBirthday(user.getBirthday());
        setUsername(user.getUsername());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}