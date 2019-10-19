package heron.scheduler.data.analysis.entity;

public class Latency {

    public Latency() {}

    public Latency(String date, String taskId, String latencyValue, String windows) {
        this.date = date;
        this.taskId = taskId;
        this.latencyValue = latencyValue;
        this.windows = windows;
    }

    private String date;
    private String taskId;
    private String latencyValue;
    private String windows;

    @Override
    public String toString() {
        String result = "Latency: [date = "+date+", taskId = "+taskId+", latencyValue = "+latencyValue+", windows = "+windows+"]";
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

    public String getLatencyValue() {
        return latencyValue;
    }

    public void setLatencyValue(String latencyValue) {
        this.latencyValue = latencyValue;
    }

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }
}
