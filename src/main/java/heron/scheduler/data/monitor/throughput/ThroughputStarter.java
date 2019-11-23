package heron.scheduler.data.monitor.throughput;

/**
 * after reconstruction
 *
 * ThroughputMonitor extend Monitor
 * AdverstingTopologyComponent implements TopologyComponent
 *
 *
 * ThroughputStarter extends Thread to start the monitor
 *
 */
public class ThroughputStarter extends Thread {

    private ThroughputMonitor throughputMonitor;

    public ThroughputStarter() {
        // TODO: restructure this class
        throughputMonitor.init();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60 * 1000);

                // TODO: restructure this class
                throughputMonitor.calculate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 启动线程
        new ThroughputStarter().start();
    }
}
