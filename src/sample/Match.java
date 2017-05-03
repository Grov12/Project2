package sample;

/**
 * Created by jimmyjonsson on 2017-04-17.
 */
public class Match {
    private int match_ID;
    private String opponent;
    private String result = "Not set";
    private String date;

    public Match(int match_ID, String opponent, String result, String date) {
        this.opponent = opponent;
        this.date = date;
    }

    public int getMatch_ID() {
        return match_ID;
    }

    public void setMatch_ID(int match_ID) {
        this.match_ID = match_ID;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Match{" +
                "match_ID=" + match_ID +
                ", opponent='" + opponent + '\'' +
                ", result='" + result + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
