package heron.scheduler.data.monitor.throughput;

import org.junit.Test;

/**
 * After reconstruction
 *
 * ThroughputMonitor extend Monitor
 * AdverstingTopoComponent implements Component
 * ThroughputStarter to start the monitor
 *
 * Config files is useless now:
 * - throughput-api.yaml
 * - top-component.yaml
 *
 * Useful config file:
 * - throughput-topology.yaml
 */
public class ThroughputStarter {

    private ThroughputMonitor throughputMonitor;

    /**
     * RoundRobin algorithm
     */
    @Test
    public void startOriginThroughput() {
        // 计算原始topo的吞吐量
        new OriginThroughputThread().start();
    }

    /**
     * Stream and category algorithm
     * And
     * Load-aware algorithm
     */
    @Test
    public void startRescheduledThroughtput() {
        // 计算重调度后的topo吞吐量
        new RescheduledThroughputThread().start();
    }

    /**
     * The thread for starting calculateOriginThroughput
     */
    static class OriginThroughputThread extends Thread {
        private ThroughputMonitor throughputMonitor;

        public OriginThroughputThread() {
            throughputMonitor = new ThroughputMonitor();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(60 * 1000);

                    // TODO: restructure this class
                    throughputMonitor.calculateOriginThroughput();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * The thread for starting calculateRescheuleThroughput
     */
    static class RescheduledThroughputThread extends Thread {
        private ThroughputMonitor throughputMonitor;

        public RescheduledThroughputThread() {
            throughputMonitor = new ThroughputMonitor();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(60 * 1000);

                    // TODO: restructure this class
                    throughputMonitor.calculateRescheuleThroughput();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
