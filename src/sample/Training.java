package sample;

/**
 * Created by jimmyjonsson on 2017-04-17.
 */
public class Training {
    private int training_ID;
    private String date;
    private String time;

    public Training(int training_ID, String date, String time) {
        this.training_ID = training_ID;
        this.date = date;
        this.time = time;
    }

    public int getTraining_ID() {
        return training_ID;
    }

    public void setTraining_ID(int training_ID) {
        this.training_ID = training_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Training{" +
                "training_ID=" + training_ID +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
