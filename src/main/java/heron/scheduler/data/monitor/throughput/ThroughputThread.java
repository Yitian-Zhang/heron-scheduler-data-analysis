package heron.scheduler.data.monitor.throughput;

import heron.scheduler.data.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class ThroughputThread extends Thread {

    // aurora cluster: AuroraFFDPSentenceWordCountTopology with para (2-5-5)
    String trackerCountExecuteApiUrl = "";
    String trackerSplitExecuteApiUrl = "";
    // local test log url
    private String throughputFilename = "D:\\logs\\throughput-test.txt";
    private int lastThroughput = 0;
    // component list for ALL topology
    private List<String> splitList = new ArrayList<>();
    private List<String> countList = new ArrayList<>();
    private int count = 0;

    // component list for yahoo benchmark
    private List<String> eventDeserializerList = new ArrayList<>();
    private List<String> eventFilterList = new ArrayList<>();
    private List<String> eventProjectionList = new ArrayList<>();
    private List<String> redisJoinList =  new ArrayList<>();
    private List<String> campaignProcessorList = new ArrayList<>();

    String trackerExecuteApiUrl1 = "";
    String trackerExecuteApiUrl2 = "";
    String trackerExecuteApiUrl3 = "";
    String trackerExecuteApiUrl4 = "";
    String trackerExecuteApiUrl5 = "";

    // 转换方法的地方
    public ThroughputThread() {
        // running ffdp topology
//        constructFFDPComponentList();

        // running rr topology
//        constructComponentListForBSWCWithRR();

        // running sc algorithm (DSC used)
//        constructComponentListForBSWCWithDSCHeron();

//        constructComponentListForClusterSentenceWordCount(); // constructSWCForClusterTest

//        constructComponentListForFileWordCountWithRR();

//        constructComponentListForFileWordCountWithRR_FormalCluster();

//        constructComponentListForExclamationTopology();

//        constructComponentListForBenchmarkWordCount();

        constructComponentListForYahooBenchmark();
    }


    // ffdp component list
    private void constructComponentListWithFFDP() {
        // aurora cluster: AuroraFFDPSentenceWordCountTopology with para (2-5-5)
        trackerCountExecuteApiUrl = "http://218.195.228.24:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AuroraFFDPSentenceWordCountTopology&component=count&metricname=__execute-count/default";
        trackerSplitExecuteApiUrl = "http://218.195.228.24:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AuroraFFDPSentenceWordCountTopology&component=split&metricname=__execute-count/default";

        splitList.add("container_1_split_1");
        splitList.add("container_1_split_2");
        splitList.add("container_2_split_3");
        splitList.add("container_2_split_4");
        splitList.add("container_3_split_5");

        countList.add("container_4_count_8");
        countList.add("container_5_count_9");
        countList.add("container_5_count_10");
        countList.add("container_6_count_11");
        countList.add("container_6_count_12");
    }

    // rr and sc-heron componennt list
    private void constructComponentListForBSWCWithRR() {
        // aurora cluster: BenchmarkSentenceWordCountTopology with para (4-10-10)
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=BenchmarkSentenceWordCountTopology&component=count&metricname=__execute-count/default";
        trackerSplitExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=BenchmarkSentenceWordCountTopology&component=split&metricname=__execute-count/default";

        splitList.add("container_6_split_6");
        splitList.add("container_5_split_5");
        splitList.add("container_4_split_4");
        splitList.add("container_4_split_10");
        splitList.add("container_3_split_9");
        splitList.add("container_3_split_3");
        splitList.add("container_2_split_8");
        splitList.add("container_2_split_2");
        splitList.add("container_1_split_7");
        splitList.add("container_1_split_1");

        countList.add("container_6_count_24");
        countList.add("container_6_count_18");
        countList.add("container_5_count_23");
        countList.add("container_5_count_17");
        countList.add("container_4_count_22");
        countList.add("container_4_count_16");
        countList.add("container_3_count_21");
        countList.add("container_3_count_15");
        countList.add("container_2_count_20");
        countList.add("container_1_count_19");
    }

    // 重调度后，组件的各个实例名称动态变化，手动修改
    private void constructComponentListForBSWCWithDSCHeron() {
        // aurora cluster: BenchmarkSentenceWordCountTopology with para (4-10-10)
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=BenchmarkSentenceWordCountTopology&component=count&metricname=__execute-count/default";
        trackerSplitExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=BenchmarkSentenceWordCountTopology&component=split&metricname=__execute-count/default";

        // RR-Algorithm 不需要修改
//        splitList.add("container_6_split_6");
//        splitList.add("container_5_split_5");
//        splitList.add("container_4_split_4");
//        splitList.add("container_4_split_10");
//        splitList.add("container_3_split_9");
//        splitList.add("container_3_split_3");
//        splitList.add("container_2_split_8");
//        splitList.add("container_2_split_2");
//        splitList.add("container_1_split_7");
//        splitList.add("container_1_split_1");
//
//        countList.add("container_6_count_24");
//        countList.add("container_6_count_18");
//        countList.add("container_5_count_23");
//        countList.add("container_5_count_17");
//        countList.add("container_4_count_22");
//        countList.add("container_4_count_16");
//        countList.add("container_3_count_21");
//        countList.add("container_3_count_15");
//        countList.add("container_2_count_20");
//        countList.add("container_1_count_19");

        // 组件的各个实例名称动态变化，手动修改
        splitList.add("container_6_split_6");
        splitList.add("container_5_split_9");
        splitList.add("container_5_split_5");
        splitList.add("container_4_split_7");
        splitList.add("container_4_split_2");
        splitList.add("container_4_split_1");
        splitList.add("container_3_split_10");
        splitList.add("container_2_split_8");
        splitList.add("container_2_split_3");
        splitList.add("container_1_split_4");

        countList.add("container_6_count_24");
        countList.add("container_6_count_23");
        countList.add("container_5_count_21");
        countList.add("container_5_count_18");
        countList.add("container_4_count_16");
        countList.add("container_3_count_20");
        countList.add("container_3_count_19");
        countList.add("container_2_count_17");
        countList.add("container_1_count_22");
        countList.add("container_1_count_15");
    }

    /**
     *
     */
    private void constructComponentListForWordCountWithBW() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=WordCountTopology&component=consumer&metricname=__execute-count/default";
//        trackerSplitExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=WordCountTopology&component=split&metricname=__execute-count/default";
        // this is default algorithm (RR), it is static and will not changed
        countList.add("container_3_consumer_9");
        countList.add("container_3_consumer_6");
        countList.add("container_2_consumer_8");
        countList.add("container_2_consumer_5");
        countList.add("container_2_consumer_11");
        countList.add("container_1_consumer_7");
        countList.add("container_1_consumer_10");

        // bw algorithm: will be changed during running topology
//        countList.add("container_3_consumer_7");
//        countList.add("container_3_consumer_10");
//        countList.add("container_2_consumer_8");
//        countList.add("container_2_consumer_5");
//        countList.add("container_1_consumer_9");
//        countList.add("container_1_consumer_6");
//        countList.add("container_1_consumer_11");
    }

    private void constructComponentListForSentenceWordCountWithBW() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=Benchmark4SentenceWordCountTopology&component=count&metricname=__execute-count/default";
        trackerSplitExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=Benchmark4SentenceWordCountTopology&component=split&metricname=__execute-count/default";

        // this is default algorithm (RR), it is static and will not changed
        countList.add("container_3_count_9");
        countList.add("container_3_count_12");
        countList.add("container_2_count_8");
        countList.add("container_2_count_11");
        countList.add("container_1_count_10");

        splitList.add("container_3_split_3");
        splitList.add("container_2_split_5");
        splitList.add("container_2_split_2");
        splitList.add("container_1_split_4");
        splitList.add("container_1_split_1");

        // bw algorithm: will be changed during running topology
//        countList.add("container_3_count_9");
//        countList.add("container_3_count_12");
//        countList.add("container_2_count_8");
//        countList.add("container_2_count_11");
//        countList.add("container_1_count_10");
//
//        splitList.add("container_3_split_3");
//        splitList.add("container_2_split_5");
//        splitList.add("container_2_split_2");
//        splitList.add("container_1_split_4");
//        splitList.add("container_1_split_1");
    }

    /**
     * For cluster test (inter-node/inter-stmgr traffic experiment)
     */
    private void constructComponentListForClusterSentenceWordCount() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=ClusterSentenceWordCountTopology&component=count&metricname=__execute-count/default";
        trackerSplitExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=ClusterSentenceWordCountTopology&component=split&metricname=__execute-count/default";

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

    private void constructComponentListForClusterWordCount() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=ClusterWordCountTopology&component=consumer&metricname=__execute-count/default";
        countList.add("container_1_consumer_4");
    }

    private void constructComponentListForFileWordCountWithRR() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=FileWordCountTopology&component=count&metricname=__execute-count/default";
        trackerSplitExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=FileWordCountTopology&component=split&metricname=__execute-count/default";

        // this is default algorithm (RR), it is static and will not changed
//        countList.add("container_3_count_9");
//        countList.add("container_3_count_12");
//        countList.add("container_2_count_8");
//        countList.add("container_2_count_11");
//        countList.add("container_1_count_10");
//
//        splitList.add("container_3_split_3");
//        splitList.add("container_2_split_5");
//        splitList.add("container_2_split_2");
//        splitList.add("container_1_split_4");
//        splitList.add("container_1_split_1");

        // bw algorithm: will be changed during running topology
        splitList.add("container_3_split_4");
        splitList.add("container_2_split_3");
        splitList.add("container_2_split_1");
        splitList.add("container_1_split_5");
        splitList.add("container_1_split_2");

        countList.add("container_3_count_10");
        countList.add("container_2_count_8");
        countList.add("container_2_count_12");
        countList.add("container_1_count_9");
        countList.add("container_1_count_11");
    }

    private void constructComponentListForFileWordCountWithRR_FormalCluster() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=FileWordCountTopology&component=count&metricname=__execute-count/default";
        trackerSplitExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=FileWordCountTopology&component=split&metricname=__execute-count/default";

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

        // bw algorithm: will be changed during running topology
//        splitList.add("container_6_split_6");
//        splitList.add("container_6_split_4");
//        splitList.add("container_5_split_8");
//        splitList.add("container_5_split_7");
//        splitList.add("container_4_split_2");
//        splitList.add("container_3_split_9");
//        splitList.add("container_3_split_10");
//        splitList.add("container_2_split_3");
//        splitList.add("container_1_split_5");
//        splitList.add("container_1_split_1");
//
//        countList.add("container_6_count_23");
//        countList.add("container_6_count_21");
//        countList.add("container_5_count_24");
//        countList.add("container_5_count_20");
//        countList.add("container_4_count_18");
//        countList.add("container_3_count_17");
//        countList.add("container_3_count_15");
//        countList.add("container_2_count_22");
//        countList.add("container_1_count_19");
//        countList.add("container_1_count_16");
    }

    private void constructComponentListForExclamationTopology() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=ExclamationTopology&component=exclaim1&metricname=__execute-count/default";
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

    private void constructComponentListForBenchmarkWordCount() {
        trackerCountExecuteApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=BenchmarkWordCountTopology&component=consumer&metricname=__execute-count/default";
//        countList.add("container_6_consumer_24");
//        countList.add("container_6_consumer_18");
//        countList.add("container_6_consumer_12");
//        countList.add("container_5_consumer_23");
//        countList.add("container_5_consumer_17");
//        countList.add("container_5_consumer_11");
//        countList.add("container_4_consumer_22");
//        countList.add("container_4_consumer_16");
//        countList.add("container_4_consumer_10");
//        countList.add("container_3_consumer_9");
//        countList.add("container_3_consumer_21");
//        countList.add("container_3_consumer_15");
//        countList.add("container_2_consumer_20");
//        countList.add("container_2_consumer_14");
//        countList.add("container_1_consumer_19");
//        countList.add("container_1_consumer_13");

        // DSC-HERON
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


    private void constructComponentListForYahooBenchmark() {
        trackerExecuteApiUrl1 = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=event_deserializer&metricname=__execute-count/default";
        trackerExecuteApiUrl2 = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=event_filter&metricname=__execute-count/default";
        trackerExecuteApiUrl3 = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=event_projection&metricname=__execute-count/default";
        trackerExecuteApiUrl4 = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=redis_join&metricname=__execute-count/default";
        trackerExecuteApiUrl5 = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=campaign_processor&metricname=__execute-count/default";
        // DSC-HERON
        eventDeserializerList.add("container_3_event_deserializer_3");
        eventDeserializerList.add("container_3_event_deserializer_3");
        eventDeserializerList.add("container_3_event_deserializer_3");

        eventFilterList.add("container_4_event_filter_16");
        eventFilterList.add("container_3_event_filter_15");
        eventFilterList.add("container_2_event_filter_14");

        eventProjectionList.add("container_6_event_projection_12");
        eventProjectionList.add("container_5_event_projection_11");
        eventProjectionList.add("container_1_event_projection_13");

        redisJoinList.add("container_6_redis_join_18");
        redisJoinList.add("container_5_redis_join_17");
        redisJoinList.add("container_1_redis_join_19");

        campaignProcessorList.add("container_6_campaign_processor_6");
        campaignProcessorList.add("container_5_campaign_processor_5");
        campaignProcessorList.add("container_4_campaign_processor_10");
        campaignProcessorList.add("container_3_campaign_processor_9");
        campaignProcessorList.add("container_2_campaign_processor_8");
        campaignProcessorList.add("container_1_campaign_processor_7");


    }

    // 运行方法
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60 * 1000);
//                count += 1;
//                // 修改这里的url和list  ------------------------------------------------------------------------------------------------
//                // running for sentenceWordCountTopolog that has spout, split and count bolt
//                int currentThroughput = TrackerTools.getAllThroughput(trackerSplitExecuteApiUrl, trackerCountExecuteApiUrl, splitList, countList);
//
//                // running for wordcountTopology that only has one spout and one bolt named consumer
////                int currentThroughput = TrackerTools.getAllThroughput(trackerCountExecuteApiUrl, countList);
//                // -------------------------------------------------------------------------------------------------------------------
//                int diffThroughput = currentThroughput - lastThroughput;
//                System.out.println("Count: " + count + ", Last Throughput: "+ lastThroughput + " Current Throughput: " + currentThroughput + " Diff Throughput: " + diffThroughput) ;
//                FileUtils.writeToFile(throughputFilename, "" + diffThroughput);
//                lastThroughput = currentThroughput;

                calculateThroughputForAD();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void calculateThroughputForSWC() {
        count += 1;
        // 修改这里的url和list  ------------------------------------------------------------------------------------------------
        // running for sentenceWordCountTopolog that has spout, split and count bolt
//                int currentThroughput = TrackerTools.getAllThroughput(trackerSplitExecuteApiUrl, trackerCountExecuteApiUrl, splitList, countList);

        // running for wordcountTopology that only has one spout and one bolt named consumer
        int currentThroughput = TrackerTools.getAllThroughput(trackerCountExecuteApiUrl, countList);
        // -------------------------------------------------------------------------------------------------------------------
        int diffThroughput = currentThroughput - lastThroughput;
        System.out.println("Count: " + count + ", Last Throughput: "+ lastThroughput + " Current Throughput: " + currentThroughput + " Diff Throughput: " + diffThroughput) ;
        FileUtils.writeToFile(throughputFilename, "" + diffThroughput);
        lastThroughput = currentThroughput;
    }

    private void calculateThroughputForWC() {
        count += 1;
        // 修改这里的url和list  ------------------------------------------------------------------------------------------------
        // running for wordcountTopology that only has one spout and one bolt named consumer
        int currentThroughput = TrackerTools.getAllThroughput(trackerCountExecuteApiUrl, countList);
        // -------------------------------------------------------------------------------------------------------------------
        int diffThroughput = currentThroughput - lastThroughput;
        System.out.println("Count: " + count + ", Last Throughput: "+ lastThroughput + " Current Throughput: " + currentThroughput + " Diff Throughput: " + diffThroughput) ;
        FileUtils.writeToFile(throughputFilename, "" + diffThroughput);
        lastThroughput = currentThroughput;
    }

    private void calculateThroughputForAD() {

        count += 1;
        // 修改这里的url和list  ------------------------------------------------------------------------------------------------
        int currentThroughput = TrackerTools.getAllThroughput(trackerExecuteApiUrl1, trackerExecuteApiUrl2, trackerExecuteApiUrl3, trackerExecuteApiUrl4, trackerExecuteApiUrl5, eventDeserializerList, eventFilterList, eventProjectionList, redisJoinList, campaignProcessorList);
        // -------------------------------------------------------------------------------------------------------------------
        int diffThroughput = currentThroughput - lastThroughput;
        System.out.println("Count: " + count + ", Last Throughput: "+ lastThroughput + " Current Throughput: " + currentThroughput + " Diff Throughput: " + diffThroughput) ;
        FileUtils.writeToFile(throughputFilename, "" + diffThroughput);
        lastThroughput = currentThroughput;
    }


    // 运行主方法
    public static void main(String[] args) {
        // 启动线程
        new ThroughputThread().start();

        // 线程暂停后重启后出现如下问题：（若线程不暂定，拓扑暂停，则不会有问题）
//        Current all topology througput is: 9295988
//        Last Throughput: 0 Current Throughput: 9295988 Diff Throughput: 9295988
//        Starting write file : D:\logs\throughput-test.txt : 9295988
    }
}
