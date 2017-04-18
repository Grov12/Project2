package sample;

import java.util.ArrayList;


public class Team {
    //Coach coach = new Coach();
    private String teamName;
    private int yearFounded;
    ArrayList<Player> players = new ArrayList<>();

    public Team(String teamName, int yearFounded) {
        this.teamName = teamName;
        this.yearFounded = yearFounded;

    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }
}
