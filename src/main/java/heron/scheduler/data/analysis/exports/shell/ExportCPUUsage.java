package heron.scheduler.data.analysis.exports.shell;

import heron.scheduler.data.analysis.entity.shell.CPUUsage;
import heron.scheduler.data.analysis.utils.ExportExcelUtil;
import heron.scheduler.data.analysis.utils.ImportFileUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExportCPUUsage {

    // 定义throughput导出文件目录
    private static String cpuUsageDataFile = "D:\\logs\\cpu-usage\\";
    private static final String pattern = "yyyy-MM-dd hh:mm:ss";
    private static final String[] columnNames = {"date", "hostname", "cpuUsageValue"};
    private static final String title = "cpuUsageExport";

    /**
     *
     * @param cpuUsageFilePrefix 待处理文件目录前缀
     * @param inputFilename 待处理文件名（生成的excel文件与之同名）
     * @throws IOException
     */
    private static void exportCPUUsageForBWHeron(String cpuUsageFilePrefix, String inputFilename) throws IOException {
        // 定义待处理文件目录
        cpuUsageDataFile = cpuUsageDataFile + inputFilename + ".xlsx";
        String cpuUsageInputFile = cpuUsageFilePrefix + inputFilename + ".txt";
        ExportExcelUtil<CPUUsage> cpuUsageExportExcelUtil = new ExportExcelUtil<CPUUsage>();

        // 获取throughput数据
        List<CPUUsage> cpuUsageList = ImportFileUtil.importCPUUsageData(cpuUsageInputFile);

        // 导出数据至excel
        List<CPUUsage> usageExportList = buildExportDataList(cpuUsageList);
        cpuUsageExportExcelUtil.exportExcel(title, columnNames, usageExportList, new FileOutputStream(cpuUsageDataFile), pattern);
    }

    private static List<CPUUsage> buildExportDataList(List<CPUUsage> cpuUsageList) {
        // 按照taskid对数据进行分组存储
        Set<String> hostnameSet = new HashSet<String>();
        for (CPUUsage usage : cpuUsageList) {
            hostnameSet.add(usage.getHostname());
        }
        System.out.println("Output hostname set...");
        for (String hostname : hostnameSet) {
            System.out.println("Hostname is: " + hostname);
        }

        // 构建实际导出的数据集合
        List<CPUUsage> cpuUsageExportList =  new ArrayList<>();
        for (String hostname : hostnameSet) {
            System.out.println("Current hostname is: " + hostname);
            for (CPUUsage usage : cpuUsageList) {
                if (usage.getHostname().equals(hostname)) {
                    cpuUsageExportList.add(usage);
                }
            }
        }
        return cpuUsageExportList;
    }

    // 主运行方法
    public static void main(String[] args) throws IOException {

        // rr-running file path
//        String cpuUsageFilePrefix = "C:\\Users\\Administrator\\Desktop\\bw-datas\\1001-rr-running-4\\";
//        String inputFilename = "cpu-usage-data-heron05";

        // cluster-test data path
        String cpuUsageFilePrefix = "C:\\Users\\Administrator\\Desktop\\ad-datas-formal\\1211-kafkaspout-2（实验部分2）\\ad-dsc-2-15000-pending-1000-2\\";
        String inputFilename = "cpu-usage-data-heron02";

        exportCPUUsageForBWHeron(cpuUsageFilePrefix, inputFilename);
    }
}
