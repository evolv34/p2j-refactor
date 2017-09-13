package p2j.evolv.com.p2j_v2.processors.image.writers;

import android.graphics.Bitmap;

public abstract class Writer {
    abstract void write() throws Exception;

    public enum WriterType {
        FILE_WRITER;
    }

    public static void write_2(WriterType writerType, Bitmap bitmap, String path) throws Exception {
        switch (writerType) {
            case FILE_WRITER: {
                new FileWriter(bitmap, path).write();
            }
        }
    }
}