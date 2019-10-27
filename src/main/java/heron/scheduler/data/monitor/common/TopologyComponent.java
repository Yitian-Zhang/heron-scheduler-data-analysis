package heron.scheduler.data.monitor.common;

/**
 * @author yitian
 */
public interface TopologyComponent {

    void constructOriginalComponent();

    void constructRescheduledComponent();

    void constructApiUrls();

    void calculateThroughput();

}
