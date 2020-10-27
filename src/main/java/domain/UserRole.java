package domain;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER"),
    UNKNOWN("UNKNOWN");

    protected  String permission;

    UserRole(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}