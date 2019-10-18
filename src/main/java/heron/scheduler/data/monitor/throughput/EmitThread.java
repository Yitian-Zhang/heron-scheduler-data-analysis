package heron.scheduler.data.monitor.throughput;

import heron.scheduler.data.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class EmitThread extends Thread {

    private static int count = 0;
    private String emitApiUrl = "";
    private List<String> spoutList = new ArrayList<>();
    private final static String EMIT_FILE = "D:\\logs\\emit-test.txt";
    private int lastEmitCount = 0;

    public EmitThread() {
//        constructComponentListForADT();

//        constructComponentListForFWC_test();

//        constructComponentListForBenchmarkWordCount_FormalCluster();

//        constructComponentListForBSWC_FormalCluster();

        constructComponentListForFileWordCountWithRR_FormalCluster();
    }

    // test
    private void constructComponentListForFWC_test() {
        emitApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=FileWordCountTopology&component=spout&metricname=__emit-count/default";
        spoutList.add("container_6_spout_12");
        spoutList.add("container_5_spout_11");
        spoutList.add("container_2_spout_14");
        spoutList.add("container_1_spout_13");
    }

    private void constructComponentListForADT() {
        emitApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=ads&metricname=__emit-count/default";
//        spoutList.add("container_6_ads_6");
//        spoutList.add("container_5_ads_5");

        spoutList.add("container_4_ads_5");
        spoutList.add("container_1_ads_6");
    }

    private void constructComponentListForBenchmarkWordCount_FormalCluster() {
        emitApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=BenchmarkWordCountTopology&component=word&metricname=__emit-count/default";

        // DSC-HERON
        spoutList.add("container_6_word_6");
        spoutList.add("container_5_word_5");
        spoutList.add("container_4_word_4");
        spoutList.add("container_3_word_3");
        spoutList.add("container_2_word_8");
        spoutList.add("container_2_word_2");
        spoutList.add("container_1_word_7");
        spoutList.add("container_1_word_1");

    }

    private void constructComponentListForFileWordCountWithRR_FormalCluster() {
        emitApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=FileWordCountTopology&component=spout&metricname=__emit-count/default";

        // this is default algorithm (RR), it is static and will not changed
        spoutList.add("container_6_spout_12");
        spoutList.add("container_5_spout_11");
        spoutList.add("container_2_spout_14");
        spoutList.add("container_1_spout_13");


//        spoutList.add("container_6_count_18");
//        spoutList.add("container_6_count_15");
//        spoutList.add("container_5_count_23");
//        spoutList.add("container_4_count_22");



    }

    private void constructComponentListForBSWC_FormalCluster() {
        // aurora cluster: BenchmarkSentenceWordCountTopology with para (4-10-10)
        emitApiUrl = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=BenchmarkSentenceWordCountTopology&component=spout&metricname=__emit-count/default";

        // 组件的各个实例名称动态变化，手动修改
        spoutList.add("container_6_spout_12");
        spoutList.add("container_5_spout_11");
        spoutList.add("container_2_spout_14");
        spoutList.add("container_1_spout_13");

//        spoutList.add("container_4_split_2");
//        spoutList.add("container_4_split_1");
//        spoutList.add("container_3_split_10");
//        spoutList.add("container_2_split_8");


    }


    @Override
    public void run() {
        while (true) {
            try {
                statisticsEmitCountForADT();

                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void statisticsEmitCountForADT() {
        count += 1;
        int currentEmitCount = TrackerTools.getAllEmitCount(emitApiUrl, spoutList);
        int diffEmitCount = currentEmitCount - lastEmitCount;
        System.out.println("Count: " + count + ", Last EmitCount: "+ lastEmitCount + " Current EmitCount: " + currentEmitCount + " Diff EmitCount: " + diffEmitCount) ;
        FileUtils.writeToFile(EMIT_FILE, "" + diffEmitCount);
        lastEmitCount = currentEmitCount;
    }

    public static void main(String[] args) {
        new EmitThread().start();
    }
}
