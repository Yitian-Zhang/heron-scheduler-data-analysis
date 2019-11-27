package heron.scheduler.data.monitor.throughput.component.formal;

import heron.scheduler.data.monitor.common.Component;
import heron.scheduler.data.monitor.throughput.TrackerTools;
import heron.scheduler.data.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Example for using the MonitorStarter.
 *
 * @author yitian
 */
public class WordCountTopoComponent implements Component {

    // common variable
    private final static String THROUGHPUT_DATA_FILE = "D:\\logs\\throughput-test.txt";
    private int lastThroughput = 0;
    private int count = 0;

    // trackerApiUrls
    private String trackerCountExecuteApiUrl = "";

    // count component list
    private List<String> countList = new ArrayList<>();

    /**
     * RoundRobin-Algorithm (default)
     * countList do not change here
     */
    @Override
    public void constructOriginalComponent() {
        countList.add("container_6_consumer_24");
        countList.add("container_6_consumer_18");
        countList.add("container_6_consumer_12");
        countList.add("container_5_consumer_23");
        countList.add("container_5_consumer_17");
        countList.add("container_5_consumer_11");
        countList.add("container_4_consumer_22");
        countList.add("container_4_consumer_16");
        countList.add("container_4_consumer_10");
        countList.add("container_3_consumer_9");
        countList.add("container_3_consumer_21");
        countList.add("container_3_consumer_15");
        countList.add("container_2_consumer_20");
        countList.add("container_2_consumer_14");
        countList.add("container_1_consumer_19");
        countList.add("container_1_consumer_13");
    }

    /**
     * Stream-category and Load-aware algorithm
     * countList will changed here. Manual to change this.
     */
    @Override
    public void constructRescheduledComponent() {
        countList.add("container_6_consumer_23");
        countList.add("container_6_consumer_17");
        countList.add("container_5_consumer_18");
        countList.add("container_5_consumer_13");
        countList.add("container_5_consumer_12");
        countList.add("container_4_consumer_9");
        countList.add("container_4_consumer_22");
        countList.add("container_4_consumer_21");
        countList.add("container_3_consumer_24");
        countList.add("container_3_consumer_15");
        countList.add("container_3_consumer_10");
        countList.add("container_2_consumer_20");
        countList.add("container_2_consumer_16");
        countList.add("container_2_consumer_14");
        countList.add("container_1_consumer_19");
        countList.add("container_1_consumer_11");
    }

    @Override
    public void constructApiUrls() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=BenchmarkWordCountTopology&component=consumer&metricname=__execute-count/default";

    }

    @Override
    public void calculateThroughput() {
        count += 1;
        // 修改这里的url和list
        // running for wordcountTopology that only has one spout and one bolt named consumer
        int currentThroughput = TrackerTools.getAllThroughput(trackerCountExecuteApiUrl, countList);

        int diffThroughput = currentThroughput - lastThroughput;
        System.out.println("Count: " + count + ", Last Throughput: " + lastThroughput + " Current Throughput: " + currentThroughput + " Diff Throughput: " + diffThroughput);
        FileUtils.writeToFile(THROUGHPUT_DATA_FILE, "" + diffThroughput);
        lastThroughput = currentThroughput;
    }
}
