package heron.scheduler.data.monitor.throughput.component.cluster;

import heron.scheduler.data.monitor.common.Component;

import java.util.ArrayList;
import java.util.List;

public class ClusterSentenceWordCountTopoComponent implements Component {
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

    /**
     * For cluster test (inter-node/inter-stmgr traffic experiment)
     */
    @Override
    public void constructOriginalComponent() {
        // this is for 3-stmgrs-3heron-instances (3nodes3stmgrs 3N3Stmgr)
//        countList.add("container_3_count_3");
//        splitList.add("container_1_split_1");

        // this is for 1-stmgrs-3heron-instances (1nodes1stmgr 1N1Stmgr)
//        countList.add("container_1_count_3");
//        splitList.add("container_1_split_1");

        // this is for 2-stmgrs-3heron-instances (2nodes2stmgrs 2N2Stmgr)
        countList.add("container_3_count_3");
        splitList.add("container_1_split_1");
    }

    @Override
    public void constructRescheduledComponent() {
        // 无重调度过程
    }

    @Override
    public void constructApiUrls() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=ClusterSentenceWordCountTopology&component=count&metricname=__execute-count/default";
        trackerSplitExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=ClusterSentenceWordCountTopology&component=split&metricname=__execute-count/default";

    }

    @Override
    public void calculateThroughput() {

    }
}
