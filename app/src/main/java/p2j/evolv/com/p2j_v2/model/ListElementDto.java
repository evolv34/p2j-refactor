package p2j.evolv.com.p2j_v2.model;

public class ListElementDto {
    private String fileName;
    private String fileSize;
    private String fileType;

    public String getFileName() {
        return fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public ListElementDto(String fileName, String fileSize, String fileType) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }
}
