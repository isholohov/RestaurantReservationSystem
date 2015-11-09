package core;

import javax.persistence.*;

@Entity
public class RegisteredUser {
    private int idRegisteredUser;
    private String login;
    private String password;
    private String phoneNumber;
    private String name;

    public RegisteredUser() {

    }

    public RegisteredUser(String login, String password, String phoneNumber, String name) {
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    @Id
    @Column(name = "idRegisteredUser")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdRegisteredUser() {
        return idRegisteredUser;
    }

    public void setIdRegisteredUser(int idRegisteredUser) {
        this.idRegisteredUser = idRegisteredUser;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisteredUser that = (RegisteredUser) o;

        if (idRegisteredUser != that.idRegisteredUser) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRegisteredUser;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }


}
