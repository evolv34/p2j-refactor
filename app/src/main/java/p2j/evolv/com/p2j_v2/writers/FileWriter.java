package p2j.evolv.com.p2j_v2.writers;

import android.graphics.Bitmap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter extends Writer {

    private Bitmap newPage;
    private String src;

    public FileWriter(Bitmap newPage, String src) {
        this.newPage = newPage;
        this.src = src;
    }

    @Override
    public void write() throws Exception {
        try {
            FileOutputStream out = new FileOutputStream(src);
            newPage.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        } catch (FileNotFoundException e) {
            throw new Exception("Could not find file ", e);
        } catch (IOException e) {
            throw new Exception("Could not write to file ", e);
        }
    }
}
