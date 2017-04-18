package sample;



public class Player {


    private int player_ID;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int goalsScored;
    private int yellowCards;
    private int redCards;
    private Position position;



    public Player(int player_ID, String firstName, String lastName, String userName, String password, Position position) {
        this.player_ID = player_ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.position = position;

    }

    public enum Position{
        GOALKEEPER, DEFENDER, MIDFIELDER, FORWARD
    }

    public int getPlayer_ID() {
        return player_ID;
    }

    public void setPlayer_ID(int player_ID) {
        this.player_ID = player_ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }


}
