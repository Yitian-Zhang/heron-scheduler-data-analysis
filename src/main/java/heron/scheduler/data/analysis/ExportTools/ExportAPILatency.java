package heron.scheduler.data.analysis.ExportTools;

import heron.scheduler.data.analysis.Entity.APILatency;
import heron.scheduler.data.analysis.Entity.Throughput;
import heron.scheduler.data.analysis.Utils.ExportExcelUtil;
import heron.scheduler.data.analysis.Utils.ImportFileUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportAPILatency {

    private static final String LATENCY_FILE = "D:\\logs\\api-latency-data\\api-latency-data.xlsx";
    private static final String PATTERN = "yyyy-MM-dd hh:mm:ss";
    private static final String[] COLUMN_NAME = {"date", "latencyValue"};
    private static final String TITLE = "latency-REST-API-export";

    private static void exportLatencyOfRESTAPI(String latencyFilePrefix, String inputFileName) throws IOException {
        // 根据参数构建输出文件目录
        String latencyInputFile = latencyFilePrefix + inputFileName;
        ExportExcelUtil<APILatency> latencyExportExcelUtil = new ExportExcelUtil<APILatency>();

        // 获取throughput数据
        List<APILatency> latencies = ImportFileUtil.importAPILatencyData(latencyInputFile);
        // 导出数据至excel
        latencyExportExcelUtil.exportExcel2007(TITLE, COLUMN_NAME, latencies, new FileOutputStream(LATENCY_FILE), PATTERN);
    }



    public static void main(String[] args) throws IOException {

        String latencyFilePrefix = "C:\\Users\\Administrator\\Desktop\\ad-datas-formal\\1211-kafkaspout-2（实验部分2）\\ad-dsc-2-15000-pending-1000-2\\";
        String inputFileName = "latency-test.txt";

        exportLatencyOfRESTAPI(latencyFilePrefix, inputFileName);
    }
}
