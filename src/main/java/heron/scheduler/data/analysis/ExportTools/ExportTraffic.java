package heron.scheduler.data.analysis.ExportTools;

import heron.scheduler.data.analysis.Entity.InterNodeTraffic;
import heron.scheduler.data.analysis.Utils.ExportExcelUtil;
import heron.scheduler.data.analysis.Utils.ImportFileUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExportTraffic {

    // 定义traffic导出文件目录
    private static final String latencyFilename = "D:\\logs\\traffic-data\\traffic-data.xlsx";
    private static final String pattern = "yyyy-MM-dd hh:mm:ss";
    private static final String[] columnNames = {"date", "taskId", "trafficValue"};
    private static final String title = "trafficExport";

    // 主运行方法
    public static void main(String[] args) throws IOException {
        // test
//        List<InterNodeTraffic> trafficList = new ArrayList<InterNodeTraffic>();
//        InterNodeTraffic traffic = new InterNodeTraffic("11", "1", "2");
//        trafficList.add(traffic);

        exportTrafficForDSCHeron();

//        exportTrafficForBWHeron();

//        exportTrafficForSWCClusterTest();
    }

    private static void exportTrafficForDSCHeron() throws IOException {
        // 定义待处理文件目录
        String trafficInputFilePrefix = "C:\\Users\\Administrator\\Desktop\\ad-datas-formal\\1211-kafkaspout-2（实验部分2）\\ad-dsc-2-15000-pending-1000-2\\";
        String trafficInputFilename1 = trafficInputFilePrefix + "traffic-data-0926-heron02.txt";
        String trafficInputFilename2 = trafficInputFilePrefix + "traffic-data-0926-heron05.txt";
        String trafficInputFilename3 = trafficInputFilePrefix + "traffic-data-0926-heron06.txt";
        String trafficInputFilename4 = trafficInputFilePrefix + "traffic-data-0926-heron08.txt";
        String trafficInputFilename5 = trafficInputFilePrefix + "traffic-data-0926-heron10.txt";
        String trafficInputFilename6 = trafficInputFilePrefix + "traffic-data-0926-heron11.txt";

        ExportExcelUtil<InterNodeTraffic> interNodeTrafficExportExcelUtil = new ExportExcelUtil<InterNodeTraffic>();

        // active
        List<InterNodeTraffic> trafficList1 = ImportFileUtil.importTrafficData(trafficInputFilename1);
        List<InterNodeTraffic> trafficList2 = ImportFileUtil.importTrafficData(trafficInputFilename2);
        List<InterNodeTraffic> trafficList3 = ImportFileUtil.importTrafficData(trafficInputFilename3);
        List<InterNodeTraffic> trafficList4 = ImportFileUtil.importTrafficData(trafficInputFilename4);
        List<InterNodeTraffic> trafficList5 = ImportFileUtil.importTrafficData(trafficInputFilename5);
        List<InterNodeTraffic> trafficList6 = ImportFileUtil.importTrafficData(trafficInputFilename6);

        // 合并所有数据
        List<InterNodeTraffic> allTrafficList = new ArrayList<InterNodeTraffic>();
        allTrafficList.addAll(trafficList1);
        allTrafficList.addAll(trafficList2);
        allTrafficList.addAll(trafficList3);
        allTrafficList.addAll(trafficList4);
        allTrafficList.addAll(trafficList5);
        allTrafficList.addAll(trafficList6);

        // 导出数据到文件
        List<InterNodeTraffic> exportList = buildExportDataList(allTrafficList);
        interNodeTrafficExportExcelUtil.exportExcel2007(title, columnNames, exportList, new FileOutputStream(latencyFilename), pattern);
    }

    private static void exportTrafficForBWHeron() throws IOException {
        // 定义待处理文件目录
        String trafficInputFilePrefix = "C:\\Users\\Administrator\\Desktop\\20181017 DSC-Heron Now\\20181020 bw-update-1\\";
        String trafficInputFilename1 = trafficInputFilePrefix + "traffic-data-0926-heron05.txt";
        String trafficInputFilename2 = trafficInputFilePrefix + "traffic-data-0926-heron06.txt";
        String trafficInputFilename3 = trafficInputFilePrefix + "traffic-data-0926-heron07.txt";

        ExportExcelUtil<InterNodeTraffic> interNodeTrafficExportExcelUtil = new ExportExcelUtil<InterNodeTraffic>();

        // active
        List<InterNodeTraffic> trafficList1 = ImportFileUtil.importTrafficData(trafficInputFilename1);
        List<InterNodeTraffic> trafficList2 = ImportFileUtil.importTrafficData(trafficInputFilename2);
        List<InterNodeTraffic> trafficList3 = ImportFileUtil.importTrafficData(trafficInputFilename3);

        // 合并所有数据
        List<InterNodeTraffic> allTrafficList = new ArrayList<InterNodeTraffic>();
        allTrafficList.addAll(trafficList1);
        allTrafficList.addAll(trafficList2);
        allTrafficList.addAll(trafficList3);

        // 导出数据到文件
        List<InterNodeTraffic> exportList = buildExportDataList(allTrafficList);
        interNodeTrafficExportExcelUtil.exportExcel2007(title, columnNames, exportList, new FileOutputStream(latencyFilename), pattern);
    }

    /**
     *
     * @throws IOException
     */
    private static void exportTrafficForSWCClusterTest() throws IOException {
        // 定义待处理文件目录
        String trafficInputFilePrefix = "C:\\Users\\Administrator\\Desktop\\cluster-test-add\\pending-10000-1\\";
        String trafficInputFilename1 = trafficInputFilePrefix + "traffic-data-0926-heron05.txt";
        String trafficInputFilename2 = trafficInputFilePrefix + "traffic-data-0926-heron08.txt";
//        String trafficInputFilename3 = trafficInputFilePrefix + "traffic-data-0926-heron05.txt";

        ExportExcelUtil<InterNodeTraffic> interNodeTrafficExportExcelUtil = new ExportExcelUtil<InterNodeTraffic>();

        // active
        List<InterNodeTraffic> trafficList1 = ImportFileUtil.importTrafficData(trafficInputFilename1);
        List<InterNodeTraffic> trafficList2 = ImportFileUtil.importTrafficData(trafficInputFilename2);
//        List<InterNodeTraffic> trafficList3 = ImportFileUtil.importTrafficData(trafficInputFilename3);

        // 合并所有数据
        List<InterNodeTraffic> allTrafficList = new ArrayList<InterNodeTraffic>();
        allTrafficList.addAll(trafficList1);
        allTrafficList.addAll(trafficList2);
//        allTrafficList.addAll(trafficList3);

        // 导出数据到文件
        List<InterNodeTraffic> exportList = buildExportDataList(allTrafficList);
        interNodeTrafficExportExcelUtil.exportExcel2007(title, columnNames, exportList, new FileOutputStream(latencyFilename), pattern);
    }

    private static List<InterNodeTraffic> buildExportDataList(List<InterNodeTraffic> allTrafficList) {
        // 按照taskid对数据进行分组存储
        Set<String> taskIdSet = new HashSet<String>();
        for (InterNodeTraffic traffic : allTrafficList) {
            taskIdSet.add(traffic.getTaskId());
        }
        System.out.println("Output taskid set...");
        for (String taskId : taskIdSet) {
            System.out.println("taskId set: " + taskId);
        }

        // 构建实际导出的数据集合
        List<InterNodeTraffic> exportList = new ArrayList<InterNodeTraffic>();
        for (String taskId : taskIdSet) {
            System.out.println("Current taskId: " + taskId);
            for (InterNodeTraffic traffic : allTrafficList) {
                if (traffic.getTaskId().equals(taskId)) {
                    exportList.add(traffic);
                }
            }
        }
        return exportList;
    }
}
