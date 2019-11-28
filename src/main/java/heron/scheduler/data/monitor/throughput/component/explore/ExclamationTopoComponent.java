package heron.scheduler.data.monitor.throughput.component.explore;

import heron.scheduler.data.monitor.common.Component;

import java.util.ArrayList;
import java.util.List;

public class ExclamationTopoComponent implements Component {
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
        countList.add("container_6_exclaim1_6");
        countList.add("container_6_exclaim1_12");
        countList.add("container_5_exclaim1_5");
        countList.add("container_5_exclaim1_11");
        countList.add("container_4_exclaim1_4");
        countList.add("container_4_exclaim1_16");
        countList.add("container_4_exclaim1_10");
        countList.add("container_3_exclaim1_9");
        countList.add("container_3_exclaim1_3");
        countList.add("container_3_exclaim1_15");
        countList.add("container_2_exclaim1_8");
        countList.add("container_2_exclaim1_2");
        countList.add("container_2_exclaim1_14");
        countList.add("container_1_exclaim1_7");
        countList.add("container_1_exclaim1_13");
        countList.add("container_1_exclaim1_1");
    }

    @Override
    public void constructRescheduledComponent() {
        // no code
    }

    @Override
    public void constructApiUrls() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=ExclamationTopology&component=exclaim1&metricname=__execute-count/default";

    }

    @Override
    public void calculateThroughput() {
        // no code for now
    }
}
