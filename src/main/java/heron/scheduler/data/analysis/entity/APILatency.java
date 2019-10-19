package heron.scheduler.data.analysis.entity;

public class APILatency {

    private String date;

    private String latency;

    public APILatency(String date, String latency) {
        this.date = date;
        this.latency = latency;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", latency: " + latency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLatency() {
        return latency;
    }

    public void setLatency(String latency) {
        this.latency = latency;
    }
}
