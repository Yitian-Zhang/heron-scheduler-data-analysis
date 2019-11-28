package heron.scheduler.data.monitor.throughput.component.cluster;

import heron.scheduler.data.monitor.common.Component;
import heron.scheduler.data.monitor.throughput.TrackerTools;
import heron.scheduler.data.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class ClusterWordCountTopoComponent implements Component {
    // common variable
    private final static String THROUGHPUT_DATA_FILE = "D:\\logs\\throughput-test.txt";
    private int lastThroughput = 0;
    private int count = 0;

    // trackerApiUrls
    private String trackerCountExecuteApiUrl = "";

    // count component list
    private List<String> countList = new ArrayList<>();

    @Override
    public void constructOriginalComponent() {
        countList.add("container_1_consumer_4");
    }

    @Override
    public void constructRescheduledComponent() {
        // 无重调度过程
    }

    @Override
    public void constructApiUrls() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=ClusterWordCountTopology&component=consumer&metricname=__execute-count/default";

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
