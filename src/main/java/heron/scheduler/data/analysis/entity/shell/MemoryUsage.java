package heron.scheduler.data.analysis.entity.shell;

public class MemoryUsage {
    private String date;
    private String hostname;
    private String memoryUsage;

    public MemoryUsage(String date, String hostname, String memoryUsage) {
        this.date = date;
        this.hostname = hostname;
        this.memoryUsage = memoryUsage;
    }

    @Override
    public String toString() {
        String result = "MemoryUsage: [date = " + date + ", hostname = " + hostname + ", memoryUsage = " + memoryUsage + "]";
        return result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage;
    }
}
