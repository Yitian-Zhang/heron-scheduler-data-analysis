package heron.scheduler.data.monitor.throughput;

import heron.scheduler.data.utils.FileUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Throughput monitor
 * Monitoring the throughput of Heron by using the REST API.
 *
 * @author yitian
 */
public class ThroughputThread extends Thread {

    String trackerCountExecuteApiUrl = "";
    String trackerSplitExecuteApiUrl = "";
    String trackerExecuteApiUrl1 = "";
    String trackerExecuteApiUrl2 = "";
    String trackerExecuteApiUrl3 = "";
    String trackerExecuteApiUrl4 = "";
    String trackerExecuteApiUrl5 = "";
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
    private List<String> redisJoinList = new ArrayList<>();
    private List<String> campaignProcessorList = new ArrayList<>();

    // 转换方法的地方
    public ThroughputThread() {
//        constructComponentListForBenchmarkWordCount();
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

    /**
     * Invoke start() function to monitor the throughput of Heron
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60 * 1000);
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
        System.out.println("Count: " + count + ", Last Throughput: " + lastThroughput + " Current Throughput: " + currentThroughput + " Diff Throughput: " + diffThroughput);
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
        System.out.println("Count: " + count + ", Last Throughput: " + lastThroughput + " Current Throughput: " + currentThroughput + " Diff Throughput: " + diffThroughput);
        FileUtils.writeToFile(throughputFilename, "" + diffThroughput);
        lastThroughput = currentThroughput;
    }

    private void calculateThroughputForAD() {

        count += 1;
        // 修改这里的url和list  ------------------------------------------------------------------------------------------------
        int currentThroughput = TrackerTools.getAllThroughput(trackerExecuteApiUrl1, trackerExecuteApiUrl2, trackerExecuteApiUrl3, trackerExecuteApiUrl4, trackerExecuteApiUrl5, eventDeserializerList, eventFilterList, eventProjectionList, redisJoinList, campaignProcessorList);
        // -------------------------------------------------------------------------------------------------------------------
        int diffThroughput = currentThroughput - lastThroughput;
        System.out.println("Count: " + count + ", Last Throughput: " + lastThroughput + " Current Throughput: " + currentThroughput + " Diff Throughput: " + diffThroughput);
        FileUtils.writeToFile(throughputFilename, "" + diffThroughput);
        lastThroughput = currentThroughput;
    }

}
