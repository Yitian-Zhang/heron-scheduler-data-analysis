package heron.scheduler.data.analysis.exports.shell;

import heron.scheduler.data.analysis.entity.shell.MemoryUsage;
import heron.scheduler.data.analysis.utils.ExportExcelUtil;
import heron.scheduler.data.analysis.utils.ImportFileUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExportMemoryUsage {
    // 定义导出文件目录
    private static String memoryUsageDataFile = "D:\\logs\\memory-usage\\";
    private static final String pattern = "yyyy-MM-dd hh:mm:ss";
    private static final String[] columnNames = {"date", "hostname", "memoryUsageValue"};
    private static final String title = "memoryUsageExport";

    /**
     *
     * @param memoryUsageFilePrefix
     * @param inputFilename
     * @throws IOException
     */
    private static void exportMemoryUsage(String memoryUsageFilePrefix, String inputFilename) throws IOException {
        // 定义待处理文件目录
        memoryUsageDataFile = memoryUsageDataFile + inputFilename + ".xlsx";
        String memoryUsageInputFile = memoryUsageFilePrefix + inputFilename + ".txt";
        ExportExcelUtil<MemoryUsage> memoryUsageExportExcelUtil = new ExportExcelUtil<MemoryUsage>();

        // 获取throughput数据
        List<MemoryUsage> memoryUsageList = ImportFileUtil.importMemoryUsageData(memoryUsageInputFile);

        // 导出数据至excel
        List<MemoryUsage> usageExportList = buildExportDataList(memoryUsageList);
        memoryUsageExportExcelUtil.exportExcel(title, columnNames, usageExportList, new FileOutputStream(memoryUsageDataFile), pattern);
    }

    private static List<MemoryUsage> buildExportDataList(List<MemoryUsage> usageList) {
        // 按照taskid对数据进行分组存储
        Set<String> hostnameSet = new HashSet<String>();
        for (MemoryUsage usage : usageList) {
            hostnameSet.add(usage.getHostname());
        }
        System.out.println("Output hostname set...");
        for (String hostname : hostnameSet) {
            System.out.println("Hostname is: " + hostname);
        }

        // 构建实际导出的数据集合
        List<MemoryUsage> usageExportList =  new ArrayList<>();
        for (String hostname : hostnameSet) {
            System.out.println("Current hostname is: " + hostname);
            for (MemoryUsage usage : usageList) {
                if (usage.getHostname().equals(hostname)) {
                    usageExportList.add(usage);
                }
            }
        }
        return usageExportList;
    }

    // 主运行方法
    public static void main(String[] args) throws IOException {
        // rr running file path
//        String memoryUsageFilePrefix = "C:\\Users\\Administrator\\Desktop\\bw-datas\\1001-rr-running-4\\";
//        String inputFilename = "memory-usage-data-heron06";

        // cluster test data path
        String memoryUsageFilePrefix = "C:\\Users\\Administrator\\Desktop\\ad-datas-formal\\1211-kafkaspout-2（实验部分2）\\ad-dsc-2-15000-pending-1000-2\\";
        String inputFilename = "memory-usage-data-heron11";
        exportMemoryUsage(memoryUsageFilePrefix, inputFilename);
    }
}
