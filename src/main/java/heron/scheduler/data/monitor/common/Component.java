package heron.scheduler.data.monitor.common;

/**
 * @author yitian
 */
public interface Component {

    void constructOriginalComponent();

    void constructRescheduledComponent();

    void constructApiUrls();

    void calculateThroughput();

}
