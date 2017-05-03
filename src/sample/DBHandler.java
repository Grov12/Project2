package sample;


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
            ResultSet rs = statement.executeQuery("SELECT * FROM team");

            while (rs.next()) {
                System.out.printf("%s%n", rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPlayerToDB(String name, String surName, String position, String userName, String password, String team) {


        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `player` (Firstname,Surname,Playerposition,Username,team_name,Password) VALUE (?,?,?,?,?,?)");
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
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO `match` (Date,Opponents) VALUE (?,?)");
            pstm.setString(1, date);
            pstm.setString(2, opponent);
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
                    System.out.println(result);
                } else {
                    result = false;
                    System.out.println(result);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void viewTrainingDB() {
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM training");
            while (rs.next()) {
                System.out.println(rs.getString("TrainingID"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewMatchDB() {
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM match");
            while (rs.next()) {
                System.out.println(rs.getString("MatchID"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPlayerStatisticsDB() {
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Firstname, Surname, GoalsScored, Yellowcards, Redcards FROM player "); //Implement the correct table
            while (rs.next()) {
                System.out.println(rs.getString("PlayerID"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewMatchStatisticsDB() {
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM match"); //Implement the correct table
            while (rs.next()) {
                System.out.println(rs.getString("MatchID"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                s = s1 + " " + s2 + "\n";
                playerList.add(s);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerList;
    }

    public void viewTeamDB() {
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM team"); //Implement the correct table
            while (rs.next()) {
                System.out.println(rs.getString("CoachUsername"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //checks if match entry exist in database to prevent duplicate entries
    public boolean doesMatchEntryExist(String opponent, String date) {

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

}









