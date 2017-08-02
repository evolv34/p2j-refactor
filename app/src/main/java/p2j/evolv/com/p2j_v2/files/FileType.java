package p2j.evolv.com.p2j_v2.files;

public enum FileType {
    PDF(".pdf"),
    JPEG(".jpg");

    private String fileType;

    FileType(String fileType) {
        this.fileType = fileType;
    }

    public String fileType() {
        return fileType;
    }
}
