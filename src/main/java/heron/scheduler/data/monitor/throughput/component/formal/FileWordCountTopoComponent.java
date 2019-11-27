package heron.scheduler.data.monitor.throughput.component.formal;

import heron.scheduler.data.monitor.common.Component;
import heron.scheduler.data.monitor.throughput.TrackerTools;
import heron.scheduler.data.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class FileWordCountTopoComponent implements Component {
    // common variable
    private final static String THROUGHPUT_DATA_FILE = "D:\\logs\\throughput-test.txt";
    private int lastThroughput = 0;
    private int count = 0;

    // trackerApiUrls
    private String trackerCountExecuteApiUrl = "";
    private String trackerSplitExecuteApiUrl = "";

    // count component list
    private List<String> countList = new ArrayList<>();
    private List<String> splitList = new ArrayList<>();

    @Override
    public void constructOriginalComponent() {
        // this is default algorithm (RR), it is static and will not changed
        splitList.add("container_6_split_8");
        splitList.add("container_5_split_10");
        splitList.add("container_5_split_1");
        splitList.add("container_4_split_9");
        splitList.add("container_4_split_7");
        splitList.add("container_4_split_3");
        splitList.add("container_3_split_6");
        splitList.add("container_3_split_2");
        splitList.add("container_2_split_5");
        splitList.add("container_1_split_4");

        countList.add("container_6_count_18");
        countList.add("container_6_count_15");
        countList.add("container_5_count_23");
        countList.add("container_4_count_22");
        countList.add("container_3_count_20");
        countList.add("container_2_count_24");
        countList.add("container_2_count_16");
        countList.add("container_1_count_21");
        countList.add("container_1_count_19");
        countList.add("container_1_count_17");
    }

    @Override
    public void constructRescheduledComponent() {
        // bw algorithm: will be changed during running topology
        splitList.add("container_6_split_6");
        splitList.add("container_6_split_4");
        splitList.add("container_5_split_8");
        splitList.add("container_5_split_7");
        splitList.add("container_4_split_2");
        splitList.add("container_3_split_9");
        splitList.add("container_3_split_10");
        splitList.add("container_2_split_3");
        splitList.add("container_1_split_5");
        splitList.add("container_1_split_1");

        countList.add("container_6_count_23");
        countList.add("container_6_count_21");
        countList.add("container_5_count_24");
        countList.add("container_5_count_20");
        countList.add("container_4_count_18");
        countList.add("container_3_count_17");
        countList.add("container_3_count_15");
        countList.add("container_2_count_22");
        countList.add("container_1_count_19");
        countList.add("container_1_count_16");
    }

    @Override
    public void constructApiUrls() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=FileWordCountTopology&component=count&metricname=__execute-count/default";
        trackerSplitExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=FileWordCountTopology&component=split&metricname=__execute-count/default";

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
