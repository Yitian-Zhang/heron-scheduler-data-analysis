package heron.scheduler.data.monitor.throughput;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author yitian
 */
public class TrackerTools {

    public static String getTrackerRestApi(String trackerApiUrl) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            // 1.URL类封装了大量复杂的实现细节，这里将一个字符串构造成一个URL对象
            URL url = new URL(trackerApiUrl);
            // 2.获取HttpURRLConnection对象
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            // 3.调用connect方法连接远程资源
            connection.connect();
            // 4.访问资源数据，使用getInputStream方法获取一个输入流用以读取信息
            BufferedReader bReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));

            // 对数据进行访问
            String line = null;
            while ((line = bReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // 关闭流
            bReader.close();
            // 关闭链接
            connection.disconnect();
            // 打印获取的结果
            System.out.println(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static int getAllThroughput(String apiSplitUrl, String apiCountUrl, List<String> splitList, List<String> countList) {
        // get count all throughput
        String apiJson = getTrackerRestApi(apiCountUrl);
        JSONObject countMetricsObject = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject countExecuteObject = countMetricsObject.getJSONObject("__execute-count/default");

        int countAll = 0;
        for (String countName : countList) {
            double countThroughput = countExecuteObject.getDouble(countName);
            countAll += countThroughput;
        }
        System.out.println("Current count component all throughput is: " + countAll);

        // get split all throughput
        apiJson = getTrackerRestApi(apiSplitUrl);
        JSONObject splitMetricsObject = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject splitExecuteObject = splitMetricsObject.getJSONObject("__execute-count/default");

        int splitAll = 0;
        for (String splitName : splitList) {
            double splitThroughput = splitExecuteObject.getDouble(splitName);
            splitAll += splitThroughput;
        }
        System.out.println("Current split component all throughput is: " + splitAll);

        // calculate all topology throughput
        int allThroughput = countAll + splitAll;
        System.out.println("Current all topology througput is: " + allThroughput);
        return allThroughput;
    }

    public static int getAllThroughput(String apiCountUrl, List<String> countList) {
        // get count all throughput
        String apiJson = getTrackerRestApi(apiCountUrl);
        JSONObject countMetricsObject = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject countExecuteObject = countMetricsObject.getJSONObject("__execute-count/default");

        int countAll = 0;
        for (String countName : countList) {
            double countThroughput = countExecuteObject.getDouble(countName);
            countAll += countThroughput;
        }
        System.out.println("Current count component all throughput is: " + countAll);

        // calculate all topology throughput
        int allThroughput = countAll;
        System.out.println("Current all topology througput is: " + allThroughput);
        return allThroughput;
    }

    /**
     * Get throughput for advertising topology
     * @param apiUrl1
     * @param apiUrl2
     * @param apiUrl3
     * @param apiUrl4
     * @param apiUrl5
     * @param apiList1
     * @param apiList2
     * @param apiList3
     * @param apiList4
     * @param apiList5
     * @return
     */
    public static int getAllThroughput(String apiUrl1, String apiUrl2, String apiUrl3, String apiUrl4, String apiUrl5,
                                       List<String> apiList1, List<String> apiList2, List<String> apiList3,
                                       List<String> apiList4, List<String> apiList5) {
        // get throughput of commponent1
        String apiJson = getTrackerRestApi(apiUrl1);
        JSONObject metricsObject1 = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject executeObject1 = metricsObject1.getJSONObject("__execute-count/default");

        int allThroughput1 = 0;
        for (String name : apiList1) {
            double throughput = executeObject1.getDouble(name);
            allThroughput1 += throughput;
        }
        System.out.println("Current component1 all throughput is: " + allThroughput1);

        // get throughput of commponent2
        apiJson = getTrackerRestApi(apiUrl2);
        JSONObject metricsObject2 = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject executeObject2 = metricsObject2.getJSONObject("__execute-count/default");

        int allThroughput2 = 0;
        for (String name : apiList2) {
            double throughput = executeObject2.getDouble(name);
            allThroughput2 += throughput;
        }
        System.out.println("Current component2 all throughput is: " + allThroughput2);

        // get throughput of commponent3
        apiJson = getTrackerRestApi(apiUrl3);
        JSONObject metricsObject3 = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject executeObject3 = metricsObject3.getJSONObject("__execute-count/default");

        int allThroughput3 = 0;
        for (String name : apiList3) {
            double throughput = executeObject3.getDouble(name);
            allThroughput3 += throughput;
        }
        System.out.println("Current component3 all throughput is: " + allThroughput3);

        // get throughput of commponent4
        apiJson = getTrackerRestApi(apiUrl4);
        JSONObject metricsObject4 = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject executeObject4 = metricsObject4.getJSONObject("__execute-count/default");

        int allThroughput4 = 0;
        for (String name : apiList4) {
            double throughput = executeObject4.getDouble(name);
            allThroughput4 += throughput;
        }
        System.out.println("Current component4 all throughput is: " + allThroughput4);


        // get throughput of commponent5
        apiJson = getTrackerRestApi(apiUrl5);
        JSONObject metricsObject5 = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject executeObject5 = metricsObject5.getJSONObject("__execute-count/default");

        int allThroughput5 = 0;
        for (String name : apiList5) {
            double throughput = executeObject5.getDouble(name);
            allThroughput5 += throughput;
        }
        System.out.println("Current component5 all throughput is: " + allThroughput5);

        // calculate all topology throughput
        int allThroughput = allThroughput1 + allThroughput2 + allThroughput3 + allThroughput4 + allThroughput5;
        System.out.println("Current all topology througput is: " + allThroughput);
        return allThroughput;
    }

    // 20181128 added for calculating latency
    public static double getAverageLatency(String latencyApiUrl, List<String> spoutList) {
        double latency = 0.0;
        // get throughput of commponent1
        String apiJson = getTrackerRestApi(latencyApiUrl);
        JSONObject metricsObject = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject executeObject = metricsObject.getJSONObject("__complete-latency/default");

        for (String name : spoutList) {
            double itemLatency = executeObject.getDouble(name) / 1000000;
            System.out.println("Component: " + name + ", latency is: " + itemLatency);
            latency += itemLatency;
        }
        double averageLatency = latency / spoutList.size();

        return averageLatency;
    }

    public static int getAllEmitCount(String emitApiUrl, List<String> spoutList) {
        // get spout all emit count
        String apiJson = getTrackerRestApi(emitApiUrl);
        JSONObject metricsObject = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject executeObject = metricsObject.getJSONObject("__emit-count/default");

        int emitAll = 0;
        for (String name : spoutList) {
            double emitCount = executeObject.getDouble(name);
            System.out.println("Component: " + name + ", emit count is: " + emitCount);
            emitAll += emitCount;
        }
        // calculate all emit count
        System.out.println("Current all spout emit is: " + emitAll);
        return emitAll;
    }

    // 这样获取的值仍然是一分钟依次的统计值，而不是连续的
    public static void main(String[] args) {
        String trackerCountExecuteApiUrl = "http://192.168.209.129:8888/topologies/metrics?cluster=local&environ=default&topology=SentenceWordCountTopology&component=count&metricname=__execute-count/default";
        String trackerSplitExecuteApiUrl = "http://192.168.209.129:8888/topologies/metrics?cluster=local&environ=default&topology=SentenceWordCountTopology&component=split&metricname=__execute-count/default";

        String apiJson = getTrackerRestApi(trackerCountExecuteApiUrl);
        JSONObject metricsObject = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject executeCountObject = metricsObject.getJSONObject("__execute-count/default");
        double count4 = executeCountObject.getDouble("container_2_count_4");
        double count3 = executeCountObject.getDouble("container_1_count_3");
        int countAll = (int) (count3 + count4);
        System.out.println(countAll);

        apiJson = getTrackerRestApi(trackerSplitExecuteApiUrl);
        JSONObject splitMetricsObject = JSONObject.parseObject(apiJson).getJSONObject("result").getJSONObject("metrics");
        JSONObject splitExecuteObject = splitMetricsObject.getJSONObject("__execute-count/default");
        double split1 = splitExecuteObject.getDouble("container_1_split_1");
        int splitAll = (int) split1;
        System.out.println(splitAll);

        int allThroughput = countAll + splitAll;
        System.out.println("Current throught put is: " + allThroughput);
    }


}
