package db;

public class User {
    private String name;
    private String password;
    private String lang;

    public User(String name, String password, String lang) {
        this.name = name;
        this.password = password;
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", password='" + password + '\'' + ", lang='" + lang + '\'' + '}';
    }
}
