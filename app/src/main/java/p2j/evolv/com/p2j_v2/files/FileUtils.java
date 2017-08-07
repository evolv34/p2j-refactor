package p2j.evolv.com.p2j_v2.files;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import p2j.evolv.com.p2j_v2.files.exceptions.NoDirectoryException;
import p2j.evolv.com.p2j_v2.files.exceptions.ParentNotFoundException;
import p2j.evolv.com.p2j_v2.model.FileDto;

public class FileUtils {
    private static FileDto fileDto = null;

    public static FileDto getFileDto() {
        return fileDto;
    }

    public static boolean isValidPath() {
        return fileDto.getPath().contains(FileType.PDF.fileType());
    }

    public FileDto files(String path) throws NoDirectoryException {
        try {
            File file = new File(path);
            FileUtils.fileDto = new FileDto(path, Arrays.asList(file.listFiles()));
            return FileUtils.fileDto;
        } catch (Exception e) {
            Log.e(getClass().getName(), "path is not a directory " + path);
            FileUtils.fileDto = new FileDto(path, new ArrayList<File>());
            throw new NoDirectoryException("path is not a directory " + path, e);
        }
    }

    public String getParent(String path) throws Exception {
        try {
            return new File(path).getCanonicalFile().getParent();
        } catch (Exception e) {
            throw new ParentNotFoundException("Could not find parent for " + path, e);
        }
    }

    public boolean delete(String path) {
        return new File(path).delete();
    }

    public void create(String path) {
        String folderPath = path.split(".pdf")[0];
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
