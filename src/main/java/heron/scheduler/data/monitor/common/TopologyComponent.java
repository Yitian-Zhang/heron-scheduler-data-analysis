package heron.scheduler.data.monitor.common;

public interface TopologyComponent {

    void constructOriginalComponent();

    void constructRescheduledComponent();

    void constructApiUrls();

    void calculateThroughput();

}
