package sample;

import java.util.ArrayList;

/**
 * Created by jimmy on 2017-04-12.
 */
public class DataStorage {

    private ArrayList<Player> playerList;
    private ArrayList<Match> matchList;
    private ArrayList<Training> trainingList;
    private ArrayList<Coach> coachList;
    private ArrayList<Team> teamList;
    private int playerID;

    private static DataStorage instance;

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    private DataStorage() {
        playerList = new ArrayList<Player>();
        matchList = new ArrayList<Match>();
        trainingList = new ArrayList<Training>();
        coachList = new ArrayList<Coach>();
        teamList = new ArrayList<Team>();
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public ArrayList<Match> getMatchList() {
        return matchList;
    }

    public ArrayList<Training> getTrainingList() {
        return trainingList;
    }

    public ArrayList<Coach> getCoachList() {
        return coachList;
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void UpdatePlayerStorage() {
        playerList.clear();
        DBHandler dbHandler = new DBHandler();
       dbHandler.playerController(playerList);




    }
}
