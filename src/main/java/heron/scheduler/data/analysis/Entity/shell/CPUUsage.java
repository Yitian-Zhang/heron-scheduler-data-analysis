package heron.scheduler.data.analysis.Entity.shell;

public class CPUUsage {
    private String date;
    private String hostname;
    private String cpuUsage;

    public CPUUsage(String date, String hostname, String cpuUsage) {
        this.date = date;
        this.hostname = hostname;
        this.cpuUsage = cpuUsage;
    }

    @Override
    public String toString() {
        String result = "CPUUsage: [date = " + date + ", hostname = " + hostname + ", cpuUsage = " + cpuUsage + "]";
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

    public String getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(String cpuUsage) {
        this.cpuUsage = cpuUsage;
    }
}
