package Logic.Users;

public class User {

    private int id;
    private String name;
    private String email;
    private String pass;
    Roles role;

    public User(int id, String name, String email, String pass, Roles role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public Roles getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
