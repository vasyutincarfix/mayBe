package classesJava;

public class User {
    int id;
    String login;
    String email;
    String usercol;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsercol() {
        return usercol;
    }

    public void setUsercol(String usercol) {
        this.usercol = usercol;
    }

    @Override
    public String toString() {
        System.out.println("ID = " + id + " LOGIN = " + login + " EMAIL = " + email + " USERCOL = " + usercol);
        return "";
    }
}
