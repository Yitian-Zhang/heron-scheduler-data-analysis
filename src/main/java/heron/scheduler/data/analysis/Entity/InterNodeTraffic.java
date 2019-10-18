package heron.scheduler.data.analysis.Entity;

public class InterNodeTraffic {
    private String date;
    private String taskId;
    private String trafficValue;

    public InterNodeTraffic() {
    }

    public InterNodeTraffic(String date, String taskId, String trafficValue) {
        this.date = date;
        this.taskId = taskId;
        this.trafficValue = trafficValue;
    }

    @Override
    public String toString() {
        String result = "InterNodeTraffic: [date = "+date+", taskId = "+taskId+", trafficValue = "+trafficValue+"]";
        return result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTrafficValue() {
        return trafficValue;
    }

    public void setTrafficValue(String trafficValue) {
        this.trafficValue = trafficValue;
    }
}
