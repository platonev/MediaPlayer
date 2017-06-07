package tk.platonev.mediaplayer;

public class MediaFileInfo {

    private String fileName;
    private String filePath;
    private String fileType;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {

        StringBuilder sb = new StringBuilder();
        long d = Long.valueOf(duration);
        d = d / 1000;
        if (d > 3600) {
            sb.append(d / 3600 + "h");
            d = d % 3600;
        }
        if (d > 60) {
            sb.append(d / 60 + "m");
            d = d % 60;
        }
        sb.append(d + "s");

        this.duration = sb.toString();
    }

    private String duration;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
