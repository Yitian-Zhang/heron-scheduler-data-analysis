package heron.scheduler.data.analysis.ExportTools.kafka;

import heron.scheduler.data.analysis.Entity.kafka.ProducerSpeed;
import heron.scheduler.data.analysis.Utils.ExportExcelUtil;
import heron.scheduler.data.analysis.Utils.ImportFileUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportProducerSpeed {

    // 定义throughput导出文件目录
    private static final String prodcuerSpeedFile = "D:\\logs\\producer-speed-data\\producer-speed-data.xlsx";
    private static final String pattern = "yyyy-MM-dd hh:mm:ss";
    private static final String[] columnNames = {"date", "producerSpeedData"};
    private static final String title = "producerSpeedData";

    private static void exportKafkaProducerSpeedData() throws IOException {
        // 定义待处理文件目录
        String producerSpeedFilePrefix = "C:\\Users\\Administrator\\Desktop\\producer-speed-data\\";
        String producerSpeedFileInputFile = producerSpeedFilePrefix + "send_message_speed_all_sleep_100.txt";
        ExportExcelUtil<ProducerSpeed> speedtExportExcelUtil = new ExportExcelUtil<ProducerSpeed>();

        // 获取throughput数据
        List<ProducerSpeed> producerSpeedList = ImportFileUtil.importProducerSpeedData(producerSpeedFileInputFile);
        // 导出数据至excel
        speedtExportExcelUtil.exportExcel2007(title, columnNames, producerSpeedList, new FileOutputStream(prodcuerSpeedFile), pattern);
    }

    // 主运行方法
    public static void main(String[] args) throws IOException {
        exportKafkaProducerSpeedData();
    }
}
