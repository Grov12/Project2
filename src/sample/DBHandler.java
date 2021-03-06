package sample;


import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import javafx.scene.control.Alert;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class DBHandler {
    private final String dbName;
    private final String user;
    private final String password;
    private final String connectionURL;

    public DBHandler() {
        Properties p = loadProperties();
        dbName = p.getProperty("dbName");
        user = p.getProperty("user");
        password = p.getProperty("password");
        connectionURL = "jdbc:mysql://localhost/" + dbName + "?user=" + user + "&password=" + password + "&useSSL=false";
    }

    private Properties loadProperties() {
        Properties appProp = new Properties();
        try (FileInputStream fis = new FileInputStream("DB.properties")) {
            appProp.load(fis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return appProp;
    }


    public void printAll() {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `team");

            while (rs.next()) {
                System.out.printf("%s%n", rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPlayerToDB(String name, String surName, String position, String userName, String password, String team) {


        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO player (Firstname,Surname,Playerposition,Username,team_name,Password) VALUE (?,?,?,?,?,?)");
            pstmt.setString(1, name);
            pstmt.setString(2, surName);
            pstmt.setString(3, position);
            pstmt.setString(4, userName);
            pstmt.setString(5, team);
            pstmt.setString(6, password);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deletePlayerFromDB(int index) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM player WHERE PlayerID=" + index);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMatchToDB(String date, String opponent) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO `match` (Date,Opponents,Result) VALUE (?,?,?)");
            pstm.setString(1, date);
            pstm.setString(2, opponent);
            pstm.setString(3,"TBA");
            pstm.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeMatchFromDB(int id) {

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM `match` WHERE `MatchID` = ?");
            pstm.setInt(1, id);
            pstm.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void setMatchResultToDB(String date, String opponent, String result) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            PreparedStatement pstm = conn.prepareStatement("UPDATE `match` SET `Result`=? WHERE `Date`=? AND `Opponents`=?");
            pstm.setString(1, result);
            pstm.setString(2, date);
            pstm.setString(3, opponent);
            pstm.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    public boolean handleLoginPlayer(String userName, String password) {
        boolean result = false;
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement pstm = conn.createStatement();
            ResultSet rs = pstm.executeQuery("SELECT * FROM player where Username='" + userName + "'and Password='" + password + "'");
            if (rs.next()) {
                String checkUser = rs.getString("Username");
                String checkPass = rs.getString("Password");
                int playerID = rs.getInt("PlayerID");


                if (checkUser.equals(userName) && checkPass.equals(password)) {
                    result = true;
                    DataStorage.getInstance().setPlayerID(playerID);
                } else {
                    result = false;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean handleLoginCoach(String userName, String password) {
        boolean result = false;
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement pstm = conn.createStatement();
            ResultSet rs = pstm.executeQuery("SELECT * FROM coach where Username='" + userName + "'and Password='" + password + "'");
            if (rs.next()) {
                String checkUser = rs.getString(1);
                String checkPass = rs.getString(2);


                if (checkUser.equals(userName) && checkPass.equals(password)) {
                    result = true;

                } else {
                    result = false;

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> viewTrainingDB() {
        ArrayList<String> viewTraining = new ArrayList<>();
        String m = null;
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `training`");
            while (rs.next()) {
                String s1 = rs.getString("TrainingID");
                String s2 = rs.getString("Time");
                String s3 = rs.getString("Date");
                m = "TrainingID: " + s1 + " |Time: " + s2 + " |Date: " + s3 + "\n";

                viewTraining.add(m);

            }

            } catch (SQLException e) {
            e.printStackTrace();
        }
        return viewTraining;



    }

    public ArrayList<String> viewMatchDB() {
        ArrayList<String> matchList = new ArrayList<>();
        String m = null;
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT `MatchID`, `Date`, `Opponents` FROM `match`");
            while (rs.next()) {
                String s1 = rs.getString("MatchID");
                String s2 = rs.getString("Date");
                String s3 = rs.getString("Opponents");
                m = "MatchID: " + s1 + " | Date: " + s2 + " | Opponents: " + s3+"\n";
                matchList.add(m);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchList;
    }

    public ArrayList<String> viewPlayerStatisticsDB() {
        ArrayList<String> playerStatistic = new ArrayList<>();
        String m = null;
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player"); //Implement the correct table
            while (rs.next()) {
                String s1 = rs.getString("Surname");
                String s2 = rs.getString("Firstname");
                String s3 = rs.getString("GoalsScored");
                String s4 = rs.getString("Yellowcards");
                String s5 = rs.getString("Redcards");
                m = "Surname: " + s1 + " | Firstname: " + s2 + " | Goalsscored: "+ s3 + " | Yellowcards: " + s4 + " | Redcards: " + s5+"\n";
                playerStatistic.add(m);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerStatistic;
    }

    public ArrayList<String> viewMatchStatisticsDB() {
        ArrayList<String> matchStatistic = new ArrayList<>();
        String m = null;
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `match`"); //Implement the correct table
            while (rs.next()) {
                String s1 = rs.getString("MatchID");
                String s2 = rs.getString("Date");
                String s3 = rs.getString("Opponents");
                String s4 = rs.getString("Result");
                m = "MatchID: " + s1 + " | Date: " + s2 + " | Opponents: "+ s3 + " | Result: " + s4 + "\n";
                matchStatistic.add(m);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchStatistic;
    }

    public ArrayList<String> viewPlayersDB() {
        ArrayList<String> playerList = new ArrayList<>();
        String s = null;
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player"); //Implement the correct table
            while (rs.next()) {
                String s1 = rs.getString("Surname");
                String s2 = rs.getString("Firstname");
                int id = Integer.parseInt(rs.getString("PlayerID"));
                s = "[ " + id + " ]"+ s1 + " " + s2 + "\n";
                playerList.add(s);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerList;
    }


    //checks if match entry exist in database to prevent duplicate entries
    public boolean doesMatchEntryExist(String date, String opponent) {

        boolean exists = false;

            try (Connection conn = DriverManager.getConnection(connectionURL)) {
                PreparedStatement pstm = conn.prepareStatement("SELECT `Opponents`,`Date` FROM `match` WHERE `Opponents`=? AND `Date`=?");
                pstm.setString(1, opponent);
                pstm.setString(2, date);
                ResultSet rs = pstm.executeQuery();

                if (rs.next()) {
                    exists = true;
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;

    }

    public void playerController(ArrayList<Player> list) {
        Player player = null;

        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from player");

            while (rs.next()) {
                String name = rs.getString("Firstname");
                String surname = rs.getString("Surname");
                String userername = rs.getString("Username");
                String password = rs.getString("Password");
                String playerposition = rs.getString("Playerposition");
                player = new Player(name, surname, userername, password, Player.Position.valueOf(playerposition));
                list.add(player);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void scheduleTraining(String date, String time){

        try (Connection connection = DriverManager.getConnection(connectionURL)){
            PreparedStatement pstm = connection.prepareStatement("INSERT into `training` (Date,Time) VALUE (?,?)");
            pstm.setString(1, date);
            pstm.setString(2, time);
            pstm.executeUpdate();

        } catch (SQLException ex){
            ex.printStackTrace();
        }


    }

    public boolean duplicateTraining(String date, String time){

        boolean isDuplicate = false;

        try(Connection connection = DriverManager.getConnection(connectionURL)){
            PreparedStatement pstm = connection.prepareStatement("SELECT `Date`, `Time` FROM `Training` WHERE Date=? AND Time=?");
            pstm.setString(1, date);
            pstm.setString(2, time);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()){
                isDuplicate = true;
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }


        return isDuplicate;

    }

    public void attendTrainingDB(int playerID, String team, int trainingID) {



        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            PreparedStatement pstm = connection.prepareStatement("INSERT into `player_has_training` " +
                    "(player_PlayerID,player_team_Name,training_TrainingID) VALUE (?,?,?)");
            pstm.setInt(1, playerID);
            pstm.setString(2, team );
            pstm.setInt(3, trainingID);
            pstm.executeUpdate();

        } catch (SQLException ex){
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error:");
            dialog.setContentText("PlayerID or TrainingID doesn't exists in database");
            dialog.showAndWait();
        }

    }
    public void playerReportAbsenceDB (int playerID, int trainingID) {

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM player_has_training WHERE player_PlayerID=? AND training_TrainingID=?"  );
            pstmt.setInt(1, playerID);
            pstmt.setInt(2, trainingID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    public void editPlayerYellowCard(int playerID, int yellowCard) {

                try (Connection conn = DriverManager.getConnection(connectionURL)) {

                    //get the yellowcards the player already has.
                    PreparedStatement pstm = conn.prepareStatement("select * from player where PlayerID = ?");
                    pstm.setInt(1, playerID);
                    ResultSet rs = pstm.executeQuery();
                    rs.next();
                    int beforeYellowCards = rs.getInt("Yellowcards");

                    //adds the cards entered and prevoius cards together and updates the databse
                    pstm = conn.prepareStatement("UPDATE `player` SET  Yellowcards=? WHERE PlayerID=" + playerID);
                    pstm.setInt(1,beforeYellowCards+yellowCard);
                    pstm.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    public void editPlayerRedCard(int playerID, int redCard) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            //get the redcard the player already has.
            PreparedStatement pstm = conn.prepareStatement("select * from player where PlayerID = ?");
            pstm.setInt(1, playerID);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int beforeRedCards = rs.getInt("Redcards");

            //adds the cards entered and prevoius cards together and updates the database
            pstm = conn.prepareStatement("UPDATE `player` SET Redcards=? WHERE PlayerID=" + playerID);
            pstm.setInt(1, beforeRedCards+redCard);
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editPlayerGoals(int playerID, int goalsScored) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            //get the goals the player already has.
            PreparedStatement pstm = conn.prepareStatement("select * from player where PlayerID = ?");
            pstm.setInt(1, playerID);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int beforeGoals = rs.getInt("GoalsScored");

            //adds the goals entered and prevoius goals together and updates the database
            pstm = conn.prepareStatement("UPDATE `player` SET GoalsScored=? WHERE PlayerID=" + playerID);
            pstm.setInt(1, beforeGoals+goalsScored);
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrainingFromDB(int index) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM training WHERE TrainingID=" + index);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}









