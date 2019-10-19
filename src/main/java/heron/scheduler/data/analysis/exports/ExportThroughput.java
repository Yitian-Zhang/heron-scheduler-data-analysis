package heron.scheduler.data.analysis.exports;

import heron.scheduler.data.analysis.entity.Throughput;
import heron.scheduler.data.analysis.utils.ExportExcelUtil;
import heron.scheduler.data.analysis.utils.ImportFileUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportThroughput {

    // 定义throughput导出文件目录
    private static final String throughputFile = "D:\\logs\\throughput-data\\throughput-data.xlsx";
    private static final String pattern = "yyyy-MM-dd hh:mm:ss";
    private static final String[] columnNames = {"date", "throughputValue"};
    private static final String title = "throughputExport";

    /**
     *
     * @throws IOException
     */
    private static void exportThroughputForDSCHeron() throws IOException {
        // 定义待处理文件目录
        String throughputFilePrefix = "C:\\Users\\Administrator\\Desktop\\datas\\";
        String throughputInputFile = throughputFilePrefix + "throughput-test.txt";
        ExportExcelUtil<Throughput> throughputExportExcelUtil = new ExportExcelUtil<Throughput>();

        // 获取throughput数据
        List<Throughput> throughputs = ImportFileUtil.importThroughputData(throughputInputFile);

        // 导出数据至excel
        throughputExportExcelUtil.exportExcel2007(title, columnNames, throughputs, new FileOutputStream(throughputFile), pattern);
    }

    /**
     *
     * @param throughputFilePrefix
     * @param inputFileName
     * @throws IOException
     */
    private static void exportThroughputForBWHeron(String throughputFilePrefix, String inputFileName) throws IOException {
        // 根据参数构建输出文件目录
        String throughputInputFile = throughputFilePrefix + inputFileName;
        ExportExcelUtil<Throughput> throughputExportExcelUtil = new ExportExcelUtil<Throughput>();

        // 获取throughput数据
        List<Throughput> throughputs = ImportFileUtil.importThroughputData(throughputInputFile);
        // 导出数据至excel
        throughputExportExcelUtil.exportExcel2007(title, columnNames, throughputs, new FileOutputStream(throughputFile), pattern);
    }

    // 主运行方法
    public static void main(String[] args) throws IOException {
//        exportThroughputForDSCHeron();

        // 2018-10-02 重构代码
        // 定义待处理文件目录
//        String throughputFilePrefix = "C:\\Users\\Administrator\\Desktop\\bw-datas\\1001-rr-running-4\\";
//        String inputFileName = "throughput-test.txt";

        String throughputFilePrefix = "C:\\Users\\Administrator\\Desktop\\load-aware-datas\\again\\rr-fwc-again\\";
        String inputFileName = "throughput-test.txt";

        exportThroughputForBWHeron(throughputFilePrefix, inputFileName);
    }
}
