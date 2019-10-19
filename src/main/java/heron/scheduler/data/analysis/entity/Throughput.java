package heron.scheduler.data.analysis.entity;

public class Throughput {

    private String date;
    private long throughput;

    public Throughput(String date, long throughput) {
        this.date = date;
        this.throughput = throughput;
    }

    @Override
    public String toString() {
        return "Date: " + date + " , Throughput: " + throughput;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getThroughput() {
        return throughput;
    }

    public void setThroughput(long throughput) {
        this.throughput = throughput;
    }
}
