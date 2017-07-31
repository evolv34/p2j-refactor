package p2j.evolv.com.p2j_v2.files;

import android.util.Log;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import p2j.evolv.com.p2j_v2.exceptions.NotDirectoryException;

public class FileUtils {

    public List<File> files(String path) throws NotDirectoryException {
        try {
            File file = new File(path);
            return Arrays.asList(file.listFiles());
        } catch (Exception e) {
            Log.e(getClass().getName(), "path is not a directory " + path);
            throw new NotDirectoryException("path is not a directory " + path, e);
        }
    }

}
