package com.yolohealth.yolokioskapp.model;

import java.io.Serializable;

public class FileModel implements Serializable {
    String filename="";
    String filePath="";
    String reporttitle="";
    String reportdescription="";

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String fileType) {
        this.filePath = fileType;
    }

    public String getReporttitle() {
        return reporttitle;
    }

    public void setReporttitle(String reporttitle) {
        this.reporttitle = reporttitle;
    }

    public String getReportdescription() {
        return reportdescription;
    }

    public void setReportdescription(String reportdescription) {
        this.reportdescription = reportdescription;
    }
}
