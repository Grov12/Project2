package sample;

/**
 * Created by robin on 2017-04-14.
 */
public class Coach {
    private String userName;
    private String password;
    private String firstName;
    private String surName;

    public Coach(String userName, String password, String firstName, String surName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    @Override
    public String toString() {
        return "Coach" +
                "userName " + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}
