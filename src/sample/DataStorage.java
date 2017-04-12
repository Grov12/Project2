package sample;

import java.util.ArrayList;

/**
 * Created by jimmy on 2017-04-12.
 */
public class DataStorage {

    public ArrayList<Player> playerList;
    public ArrayList<Match> matchList;
    public ArrayList<Training> trainingList;
    public ArrayList<Coach> coachList;
    public ArrayList<Team> teamList;

    private static DataStorage instance;

    public static DataStorage getInstance() {
        if(instance == null){
            instance = new DataStorage();
        }
        return instance;
    }

    private DataStorage() {
       // playerList = new ArrayList<Player>();
        //matchList = new ArrayList<Match>();
        //trainingList = new ArrayList<Training>();
        //coachList = new ArrayList<Coach>();
        //teamList = new Arraylist<Team>();
    }
}
