package heron.scheduler.data.analysis.Utils;

import heron.scheduler.data.analysis.Entity.*;
import heron.scheduler.data.analysis.Entity.shell.CPUUsage;
import heron.scheduler.data.analysis.Entity.kafka.ProducerSpeed;
import heron.scheduler.data.analysis.Entity.shell.MemoryUsage;
import heron.scheduler.data.monitor.throughput.EmitThread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImportFileUtil {

    private Set<String> taskIdSet = new HashSet<String>();

    /**
     * 读取latency txt文件方法
     * 保证latency文件，开头和中间无空行
     *
     * @param filename 文件名
     * @return 为export excel文件准备的list集合
     * @throws IOException
     */
    public static List<Latency> importLatencyData(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = null;
        List<Latency> latencyList = new ArrayList<Latency>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");

                String date = data[0] + " " + data[1];
                String taskId = data[2];
                String latencyValue = data[4];
                String windows = data[8];
//                System.out.println("Split data: [" + date+":"+taskId+":"+latencyValue+":"+windows+"]");

                Latency latency = new Latency(date, taskId, latencyValue, windows);
//                System.out.println(latency);
                latencyList.add(latency);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return latencyList;
    }

    /**
     * 读取traffic文件，文件开头和结尾不能有空行
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static List<InterNodeTraffic> importTrafficData(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = null;
        List<InterNodeTraffic> trafficList = new ArrayList<InterNodeTraffic>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");

                String date = data[0] + " " + data[1];
                String taskId = data[2];
                String trafficValue = data[4];

//                System.out.println("Split data: [" + date + ":" + taskId + ":" + trafficValue + "]");
                InterNodeTraffic traffic = new InterNodeTraffic(date, taskId, trafficValue);
//                System.out.println(traffic);

                trafficList.add(traffic);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return trafficList;
    }

    /**
     * 读取throughput的txt文件，文件开头和结尾不能有空行
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static List<Throughput> importThroughputData(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = null;
        List<Throughput> throughputList = new ArrayList<Throughput>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");

                String date = data[0] + " " + data[1];
                String throughputValue = data[2];

                Throughput throughput = new Throughput(date, Long.valueOf(throughputValue));
//                System.out.println(throughput);
                throughputList.add(throughput);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return throughputList;
    }

    /**
     * Import cpu usage data from DB / data that from shell-monitor
     *
     * @return
     */
    public static List<CPUUsage> importCPUUsageData(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = null;
        List<CPUUsage> cpuUsageList = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String date = data[0] + " " + data[1];
                String hostname = data[2];
                String cpuUsageValue = data[4];

                CPUUsage cpuUsage = new CPUUsage(date, hostname, cpuUsageValue);
                cpuUsageList.add(cpuUsage);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return cpuUsageList;
    }

    /**
     * Import producer speed data
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static List<ProducerSpeed> importProducerSpeedData(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = null;
        List<ProducerSpeed> producerSpeedList = new ArrayList<ProducerSpeed>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String date = data[0] + " " + data[1];
                String speedValue = data[4];
                ProducerSpeed speed = new ProducerSpeed(date, speedValue);
//                System.out.println(speed);
                producerSpeedList.add(speed);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return producerSpeedList;
    }

    /**
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static List<MemoryUsage> importMemoryUsageData(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = null;
        List<MemoryUsage> memoryUsageList = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String date = data[0] + " " + data[1];
                String hostname = data[2];
                String memoryUsageValue = data[4];

                MemoryUsage memoryUsage = new MemoryUsage(date, hostname, memoryUsageValue);
                memoryUsageList.add(memoryUsage);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return memoryUsageList;
    }

    public static List<APILatency> importAPILatencyData(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = null;
        List<APILatency> latencytList = new ArrayList<APILatency>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");

                String date = data[0] + " " + data[1];
                String latencyValue = data[2];

                APILatency latency = new APILatency(date, latencyValue);
//                System.out.println(throughput);
                latencytList.add(latency);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return latencytList;
    }

    public static List<EmitCount> importEmitCountData(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader reader = null;
        List<EmitCount> countList = new ArrayList<EmitCount>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");

                String date = data[0] + " " + data[1];
                String countValue = data[2];

                EmitCount count = new EmitCount(date, countValue);
//                System.out.println(throughput);
                countList.add(count);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return countList;
    }

    /**
     * 测试方法
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//        String latencyFilename = "C:\\Users\\Administrator\\Desktop\\0727-7-node-customschedule\\latency-data-0727-heron07.txt";
//        List<Latency> latencyList = importLatencyData(latencyFilename);
//        for (Latency latency : latencyList) {
//            System.out.println(latency);
//        }

//        String trafficFilename = "C:\\Users\\Administrator\\Desktop\\0727-7-node-customschedule\\traffic-data-0727-heron06.txt";
//        List<InterNodeTraffic> trafficList = importTrafficData(trafficFilename);
//        for (InterNodeTraffic traffic : trafficList) {
//            System.out.println(traffic);
//        }

//        String throughputFilename = "C:\\Users\\Administrator\\Desktop\\datas\\throughput-test.txt";
//        List<Throughput> throughputList = importThroughputData(throughputFilename);
//        for (Throughput throughput : throughputList) {
//            System.out.println(throughput);
//        }

//        String cpuUsageFilename = "C:\\Users\\Administrator\\Desktop\\bw-datas\\0927-bw-running-nodex4-3\\cpu-usage.txt";
//        List<CPUUsage> cpuUsageList = importCPUUsageData(cpuUsageFilename);
//        for (CPUUsage usage : cpuUsageList) {
//            System.out.println(usage);
//        }

//        String producerSpeedFilename = "C:\\Users\\Administrator\\Desktop\\producer-speed-data\\send_message_speed.txt";
//        List<ProducerSpeed> speedList = importProducerSpeedData(producerSpeedFilename);
//        for (ProducerSpeed speed : speedList) {
//            System.out.println(speed);
//        }

//        String cpuUsageFilename = "C:\\Users\\Administrator\\Desktop\\cpu-ram-monitor\\cpu-usage-data.txt";
//        List<CPUUsage> cpuUsageList = importCPUUsageData(cpuUsageFilename);
//        for (CPUUsage usage : cpuUsageList) {
//            System.out.println(usage);
//        }

        String memoryFilename = "C:\\Users\\Administrator\\Desktop\\cpu-ram-monitor\\memory-usage-data.txt";
        List<MemoryUsage> memoryUsageList = importMemoryUsageData(memoryFilename);
        for (MemoryUsage usage : memoryUsageList) {
            System.out.println(usage);
        }
    }

}
