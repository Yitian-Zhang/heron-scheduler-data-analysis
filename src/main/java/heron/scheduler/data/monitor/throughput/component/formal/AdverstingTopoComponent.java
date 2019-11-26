package heron.scheduler.data.monitor.throughput.component.formal;

import heron.scheduler.data.monitor.common.Component;
import heron.scheduler.data.monitor.throughput.TrackerTools;
import heron.scheduler.data.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yitian
 */
public class AdverstingTopoComponent implements Component {

    // common variable
    private final static String THROUGHPUT_DATA_FILE = "D:\\logs\\throughput-test.txt";
    private int lastThroughput = 0;
    private int count = 0;

    // trackerApiUrls
    private List<String> trackerApiUrl = new ArrayList<>();

    // component list for yahoo benchmark
    private List<String> eventDeserializerList = new ArrayList<>();
    private List<String> eventFilterList = new ArrayList<>();
    private List<String> eventProjectionList = new ArrayList<>();
    private List<String> redisJoinList =  new ArrayList<>();
    private List<String> campaignProcessorList = new ArrayList<>();

    public AdverstingTopoComponent() {

    }

    @Override
    public void constructOriginalComponent() {
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

    @Override
    public void constructRescheduledComponent() {
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

    @Override
    public void constructApiUrls() {
        String trackerExecuteApiUrl1 = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=event_deserializer&metricname=__execute-count/default";
        String trackerExecuteApiUrl2 = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=event_filter&metricname=__execute-count/default";
        String trackerExecuteApiUrl3 = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=event_projection&metricname=__execute-count/default";
        String trackerExecuteApiUrl4 = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=redis_join&metricname=__execute-count/default";
        String trackerExecuteApiUrl5 = "http://218.195.228.10:8888/topologies/metrics?cluster=aurora&environ=devel&topology=AdvertisingTopology&component=campaign_processor&metricname=__execute-count/default";
        trackerApiUrl.add(trackerExecuteApiUrl1);
        trackerApiUrl.add(trackerExecuteApiUrl2);
        trackerApiUrl.add(trackerExecuteApiUrl3);
        trackerApiUrl.add(trackerExecuteApiUrl4);
        trackerApiUrl.add(trackerExecuteApiUrl5);
    }

    @Override
    public void calculateThroughput() {
        count += 1;
        int currentThroughput = TrackerTools.getAllThroughput(
                trackerApiUrl.get(0),
                trackerApiUrl.get(1),
                trackerApiUrl.get(2),
                trackerApiUrl.get(3),
                trackerApiUrl.get(4),
                eventDeserializerList,
                eventFilterList,
                eventProjectionList,
                redisJoinList,
                campaignProcessorList);

        int diffThroughput = currentThroughput - lastThroughput;
        System.out.println("Count: " + count + ", Last Throughput: "+ lastThroughput + " Current Throughput: " + currentThroughput + " Diff Throughput: " + diffThroughput) ;
        FileUtils.writeToFile(THROUGHPUT_DATA_FILE, "" + diffThroughput);
        lastThroughput = currentThroughput;
    }


}
