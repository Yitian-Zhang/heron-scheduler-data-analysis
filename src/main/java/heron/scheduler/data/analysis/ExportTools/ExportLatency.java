package heron.scheduler.data.analysis.ExportTools;

import heron.scheduler.data.analysis.Entity.Latency;
import heron.scheduler.data.analysis.Utils.ExportExcelUtil;
import heron.scheduler.data.analysis.Utils.ImportFileUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * READ ME:
 * 1. 数据excel文件的导出是覆盖的方式
 * 2. 对待处理文件需要先进行处理：
 * （1） 首行和中间行无空行
 * （2） 删除多余数据
 * 3. 注意待处理文件名和路径是否正确
 * 4. 注意待处理文件的数量是否正确
 */
public class ExportLatency {

    // latency的导出文件目录
    private static final String latencyFilename = "D:\\logs\\latency-data\\latency-data.xlsx";
    private static final String pattern = "yyyy-MM-dd hh:mm:ss";
    private static final String[] columnNames = {"date", "taskId", "latencyValue", "windows"};
    private static final String title = "latencyExport";

    // 主运行方法
    public static void main(String[] args) throws IOException {

        // test
//        List<Latency> latencyList = new ArrayList<Latency>();
//        Latency latency = new Latency("111", "1", "2", "3");
//        latencyList.add(latency);

//        exportLatencyForDSCHeron();

        // 2018-10-02 重构代码
//        String latencyInputFilePrefix = "C:\\Users\\Administrator\\Desktop\\bw-datas\\1001-rr-running-4\\";
//        String inputFilenamePrefix = "latency-data-0926-heron0";
//        String inputFilenameSuffix = ".txt";
//        int inputFileSize = 3;
//        List<String> inputFileList = new ArrayList<>();
//        for (int i = 0; i < inputFileSize; i++) {
//            String inputFilenameMiddle = i + 1 + "";
//            String inputFile = latencyInputFilePrefix + inputFilenamePrefix + inputFilenameMiddle + inputFilenameSuffix;
//            System.out.println("Build filename is: " + inputFile);
//            inputFileList.add(inputFile);
//        }

//        exportLatencyForHeronWithRR();

        // export latency for bw algorithm
//        exportLatencyForBWHeron();
//        exportLatencyForRRHeron();

        // export latency for cluster test
//        exportLatencyForClusterTest();

        exportLatencyForWordCount();
    }

    private static void exportLatencyForHeronWithRR() throws IOException {
        // 定义待处理文件目录
        String latencyInputFilePrefix = "C:\\Users\\Administrator\\Desktop\\dsc-heron-datas\\rr-fwc-2\\";
        String latencyInputFilename1 = latencyInputFilePrefix + "latency-data-0926-heron02.txt";
        String latencyInputFilename2 = latencyInputFilePrefix + "latency-data-0926-heron05.txt";
        String latencyInputFilename3 = latencyInputFilePrefix + "latency-data-0926-heron06.txt";
        String latencyInputFilename4 = latencyInputFilePrefix + "latency-data-0926-heron08.txt";

        ExportExcelUtil<Latency> latencyExportExcelUtil = new ExportExcelUtil<Latency>();

        // active for DSC-HERON
        List<Latency> latencyList1 = ImportFileUtil.importLatencyData(latencyInputFilename1);
        List<Latency> latencyList2 = ImportFileUtil.importLatencyData(latencyInputFilename2);
        List<Latency> latencyList3 = ImportFileUtil.importLatencyData(latencyInputFilename3);
        List<Latency> latencyList4 = ImportFileUtil.importLatencyData(latencyInputFilename4);

        // 合并所有数据
        List<Latency> allLatency = new ArrayList<Latency>();
        allLatency.addAll(latencyList1);
        allLatency.addAll(latencyList2);
        allLatency.addAll(latencyList3);
        allLatency.addAll(latencyList4);

        // 导出数据到文件
        List<Latency> exportList = buildExportDataList(allLatency);
        latencyExportExcelUtil.exportExcel2007(title, columnNames, exportList, new FileOutputStream(latencyFilename), pattern);
    }

    /**
     *
     * @throws IOException
     */
    private static void exportLatencyForDSCHeron() throws IOException {
        // 定义待处理文件目录
        String latencyInputFilePrefix = "C:\\Users\\Administrator\\Desktop\\dsc-heron-datas\\dsc-1\\";
        String latencyInputFilename1 = latencyInputFilePrefix + "latency-data-0926-heron02.txt";
        String latencyInputFilename2 = latencyInputFilePrefix + "latency-data-0926-heron05.txt";
        String latencyInputFilename3 = latencyInputFilePrefix + "latency-data-0926-heron06.txt";
        String latencyInputFilename4 = latencyInputFilePrefix + "latency-data-0926-heron07.txt";
        String latencyInputFilename5 = latencyInputFilePrefix + "latency-data-0926-heron10.txt";

        ExportExcelUtil<Latency> latencyExportExcelUtil = new ExportExcelUtil<Latency>();

        // active for DSC-HERON
        List<Latency> latencyList1 = ImportFileUtil.importLatencyData(latencyInputFilename1);
        List<Latency> latencyList2 = ImportFileUtil.importLatencyData(latencyInputFilename2);
        List<Latency> latencyList3 = ImportFileUtil.importLatencyData(latencyInputFilename3);
        List<Latency> latencyList4 = ImportFileUtil.importLatencyData(latencyInputFilename4);
        List<Latency> latencyList5 = ImportFileUtil.importLatencyData(latencyInputFilename5);

        // 合并所有数据
        List<Latency> allLatency = new ArrayList<Latency>();
        allLatency.addAll(latencyList1);
        allLatency.addAll(latencyList2);
        allLatency.addAll(latencyList3);
        allLatency.addAll(latencyList4);
        allLatency.addAll(latencyList5);

        // 导出数据到文件
        List<Latency> exportList = buildExportDataList(allLatency);
        latencyExportExcelUtil.exportExcel2007(title, columnNames, exportList, new FileOutputStream(latencyFilename), pattern);
    }

    /**
     *
     * @throws IOException
     */
    private static void exportLatencyForBWHeron() throws IOException {
        String latencyInputFilePrefix = "C:\\Users\\Administrator\\Desktop\\20181017 DSC-Heron Now\\20181020 bw-update-1\\";
        String latencyInputFilename1 = latencyInputFilePrefix + "latency-data-0926-heron05.txt";
        String latencyInputFilename2 = latencyInputFilePrefix + "latency-data-0926-heron06.txt";
        String latencyInputFilename3 = latencyInputFilePrefix + "latency-data-0926-heron07.txt";

        ExportExcelUtil<Latency> latencyExportExcelUtil = new ExportExcelUtil<Latency>();

        // active for DSC-HERON ---------------------------------------------
        List<Latency> latencyList1 = ImportFileUtil.importLatencyData(latencyInputFilename1);
        List<Latency> latencyList2 = ImportFileUtil.importLatencyData(latencyInputFilename2);
        List<Latency> latencyList3 = ImportFileUtil.importLatencyData(latencyInputFilename3);

        // 合并所有数据
        List<Latency> allLatency = new ArrayList<Latency>();
        allLatency.addAll(latencyList1);
        allLatency.addAll(latencyList2);
        allLatency.addAll(latencyList3);

        // 导出数据到文件
        List<Latency> exportList = buildExportDataList(allLatency);
        latencyExportExcelUtil.exportExcel2007(title, columnNames, exportList, new FileOutputStream(latencyFilename), pattern);
    }

    private static void exportLatencyForRRHeron() throws IOException {
        String latencyInputFilePrefix = "C:\\Users\\Administrator\\Desktop\\bw-datas\\1015 fwc-test\\rr\\";
        String latencyInputFilename1 = latencyInputFilePrefix + "latency-data-0926-heron07.txt";
        String latencyInputFilename2 = latencyInputFilePrefix + "latency-data-0926-heron08.txt";

        ExportExcelUtil<Latency> latencyExportExcelUtil = new ExportExcelUtil<Latency>();

        // active for DSC-HERON ---------------------------------------------
        List<Latency> latencyList1 = ImportFileUtil.importLatencyData(latencyInputFilename1);
        List<Latency> latencyList2 = ImportFileUtil.importLatencyData(latencyInputFilename2);

        // 合并所有数据
        List<Latency> allLatency = new ArrayList<Latency>();
        allLatency.addAll(latencyList1);
        allLatency.addAll(latencyList2);

        // 导出数据到文件
        List<Latency> exportList = buildExportDataList(allLatency);
        latencyExportExcelUtil.exportExcel2007(title, columnNames, exportList, new FileOutputStream(latencyFilename), pattern);
    }

    private static void exportLatencyForWordCount() throws IOException {
        String latencyInputFilePrefix = "C:\\Users\\Administrator\\Desktop\\load-aware-datas\\dsc-swc-2\\";
        String latencyInputFilename1 = latencyInputFilePrefix + "latency-data-0926-heron02.txt";
        String latencyInputFilename2 = latencyInputFilePrefix + "latency-data-0926-heron07.txt";
        String latencyInputFilename3 = latencyInputFilePrefix + "latency-data-0926-heron08.txt";
        String latencyInputFilename4 = latencyInputFilePrefix + "latency-data-0926-heron10.txt";
        String latencyInputFilename5 = latencyInputFilePrefix + "latency-data-0926-heron11.txt";
//        String latencyInputFilename6 = latencyInputFilePrefix + "latency-data-0926-heron11.txt";


        ExportExcelUtil<Latency> latencyExportExcelUtil = new ExportExcelUtil<Latency>();

        // active for DSC-HERON ---------------------------------------------
        List<Latency> latencyList1 = ImportFileUtil.importLatencyData(latencyInputFilename1);
        List<Latency> latencyList2 = ImportFileUtil.importLatencyData(latencyInputFilename2);
        List<Latency> latencyList3 = ImportFileUtil.importLatencyData(latencyInputFilename3);
        List<Latency> latencyList4 = ImportFileUtil.importLatencyData(latencyInputFilename4);
        List<Latency> latencyList5 = ImportFileUtil.importLatencyData(latencyInputFilename5);
//        List<Latency> latencyList6 = ImportFileUtil.importLatencyData(latencyInputFilename6);


        // 合并所有数据
        List<Latency> allLatency = new ArrayList<Latency>();
        allLatency.addAll(latencyList1);
        allLatency.addAll(latencyList2);
        allLatency.addAll(latencyList3);
        allLatency.addAll(latencyList4);
        allLatency.addAll(latencyList5);
//        allLatency.addAll(latencyList6);


        // 导出数据到文件
        List<Latency> exportList = buildExportDataList(allLatency);
        latencyExportExcelUtil.exportExcel2007(title, columnNames, exportList, new FileOutputStream(latencyFilename), pattern);
    }

    /**
     *
     * @throws IOException
     */
    private static void exportLatencyForClusterTest() throws IOException {
        String latencyInputFilePrefix = "C:\\Users\\Administrator\\Desktop\\cluster-test-add\\1n3s-pending-10000\\";
        String latencyInputFilename1 = latencyInputFilePrefix + "latency-data-0926-heron07.txt";

        ExportExcelUtil<Latency> latencyExportExcelUtil = new ExportExcelUtil<Latency>();
        List<Latency> latencyList1 = ImportFileUtil.importLatencyData(latencyInputFilename1);

        // 合并所有数据
        List<Latency> allLatency = new ArrayList<Latency>();
        allLatency.addAll(latencyList1);

        // 导出数据到文件
        List<Latency> exportList = buildExportDataList(allLatency);
        latencyExportExcelUtil.exportExcel2007(title, columnNames, exportList, new FileOutputStream(latencyFilename), pattern);
    }

    /**
     *
     * @param allLatencyList
     * @return
     */
    private static List<Latency> buildExportDataList(List<Latency> allLatencyList) {
        // 按照taskid对数据进行分组存储
        Set<String> taskIdSet = new HashSet<String>();
        for (Latency latency : allLatencyList) {
            taskIdSet.add(latency.getTaskId());
        }
        System.out.println("Output taskid set...");
        for (String taskId : taskIdSet) {
            System.out.println("taskId set: " + taskId);
        }

        // 构建实际导出的数据集合
        List<Latency> exportList = new ArrayList<Latency>();
        for (String taskId : taskIdSet) {
            System.out.println("Current taskId: " + taskId);
            for (Latency latency : allLatencyList) {
                if (latency.getTaskId().equals(taskId)) {
                    exportList.add(latency);
                }
            }
        }
        return exportList;
    }


}
