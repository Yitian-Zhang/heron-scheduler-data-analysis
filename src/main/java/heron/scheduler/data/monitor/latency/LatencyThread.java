package heron.scheduler.data.monitor.latency;

import heron.scheduler.data.monitor.throughput.TrackerTools;
import heron.scheduler.data.utils.FileUtils;

import javax.swing.table.TableRowSorter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LatencyThread extends Thread {

    private static int count = 0;
    private String latencyApiUrl = "";
    private List<String> spoutList = new ArrayList<>();
    private final static String LATENCY_FILE = "D:\\logs\\latency-test.txt";

    public LatencyThread() {
        // invoke list constructed function
//        constructComponentListForFWC_test();
        constructComponentListForADT();
    }

    private void constructComponentListForFWC_test() {
        latencyApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=FileWordCountTopology&component=spout&metricname=__complete-latency/default&interval=600";
        spoutList.add("container_6_spout_12");
        spoutList.add("container_5_spout_11");
        spoutList.add("container_2_spout_14");
        spoutList.add("container_1_spout_13");
    }

    private void constructComponentListForADT() {
        latencyApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=ads&metricname=__complete-latency/default&interval=600";
        // RR
//        spoutList.add("container_6_ads_6");
//        spoutList.add("container_5_ads_5");

        // custom scheduler

        spoutList.add("container_4_ads_5");
        spoutList.add("container_1_ads_6");
    }

    private void calculateLatencyValue() {
        count += 1;
        double averageLatency = TrackerTools.getAverageLatency(latencyApiUrl, spoutList);
        System.out.println("Count: " + count + ", Average latency is: " + averageLatency);
        FileUtils.writeToFile(LATENCY_FILE, "" + averageLatency);
    }

    @Override
    public void run() {
        while (true) {
            //
            try {
                // invoke function
                calculateLatencyValue();
                Thread.sleep(50 * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new LatencyThread().start();
    }
}
