package heron.scheduler.data.analysis.Entity.kafka;

public class ProducerSpeed {
    private String date;
    private String speed;

    public ProducerSpeed(String date, String speed) {
        this.date = date;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Date: " + date + " , Speed: " + speed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
