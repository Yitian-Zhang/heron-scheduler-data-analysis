package heron.scheduler.data.analysis.Entity;

public class EmitCount {

    private String date;
    private String emitCount;

    public EmitCount(String date, String emitCount) {
        this.date = date;
        this.emitCount = emitCount;
    }

    @Override
    public String toString() {
        return "Date: " + date + " , emitCount: " + emitCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmitCount() {
        return emitCount;
    }

    public void setEmitCount(String emitCount) {
        this.emitCount = emitCount;
    }
}
