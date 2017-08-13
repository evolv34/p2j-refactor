package p2j.evolv.com.p2j_v2.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import p2j.evolv.com.p2j_v2.utils.exceptions.NoDirectoryException;
import p2j.evolv.com.p2j_v2.utils.exceptions.ParentNotFoundException;
import p2j.evolv.com.p2j_v2.model.FileDto;

public class FileUtils {
    private static FileDto fileDto = null;
    private String path = null;

    public static FileDto getFileDto() {
        return fileDto;
    }

    public static boolean isValidPath() {
        return fileDto.getPath().contains(FileType.PDF.fileType());
    }

    public FileUtils(String path) {
        this.path = path;
    }

    public FileDto files() throws NoDirectoryException {
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

    public String getParent() throws Exception {
        try {
            return new File(path).getCanonicalFile().getParent();
        } catch (Exception e) {
            throw new ParentNotFoundException("Could not find parent for " + path, e);
        }
    }

    public void delete() throws IOException {
        File file = new File(path);
        if (file.isDirectory()) {
            org.apache.commons.io.FileUtils.deleteDirectory(new File(path));
        } else {
            org.apache.commons.io.FileUtils.deleteQuietly(file);
        }
    }

    public void create() {
        String folderPath = path.split(".pdf")[0];
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public Long size() {
        return org.apache.commons.io.FileUtils.sizeOf(new File(path));
    }

    public static class Conversions {
        private static final FileUnits[] FILE_UNITS = FileUnits.values();

        public static String convert(final long value) {
            return org.apache.commons.io.FileUtils.byteCountToDisplaySize(BigInteger.valueOf(value));
//            if (value <= 0) return "0.0 " + FileUnits.B.getUnit();
//            int digitGroups = (int) (Math.log10(value) / Math.log10(1024));
//            return new DecimalFormat("#,##0.#").format(value / Math.pow(1024, digitGroups)) + " " + FILE_UNITS[digitGroups].getUnit();
        }
    }
}
