package heron.scheduler.data.analysis.exports;

import heron.scheduler.data.analysis.entity.EmitCount;
import heron.scheduler.data.analysis.utils.ExportExcelUtil;
import heron.scheduler.data.analysis.utils.ImportFileUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportEmitCount {

    private static final String EMIT_FILE = "D:\\logs\\emit-count\\emit-data.xlsx";
    private static final String PATTERN = "yyyy-MM-dd hh:mm:ss";
    private static final String[] COLUMN_NAME = {"date", "emitCountValue"};
    private static final String TITLE = "emit-count-export";

    private static void exportEmitCountData(String emitCountFilePrefix, String inputFileName) throws IOException {
        String emitInputFile = emitCountFilePrefix + inputFileName;
        ExportExcelUtil<EmitCount> emitCountExportExcelUtil = new ExportExcelUtil<>();

        List<EmitCount> emitCounts = ImportFileUtil.importEmitCountData(emitInputFile);
        emitCountExportExcelUtil.exportExcel2007(TITLE, COLUMN_NAME, emitCounts, new FileOutputStream(EMIT_FILE), PATTERN);
    }

    public static void main(String[] args) throws IOException {
        String emitCountFilePrefix = "C:\\Users\\Administrator\\Desktop\\ad-datas-formal\\kafkaspout-2\\ad-rr-2-10000-1\\";
        String inputFileName = "emit-test.txt";

        exportEmitCountData(emitCountFilePrefix, inputFileName);
    }


}
