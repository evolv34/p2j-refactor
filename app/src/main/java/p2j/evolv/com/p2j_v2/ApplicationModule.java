package p2j.evolv.com.p2j_v2;

import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

import p2j.evolv.com.p2j_v2.model.FileDto;

public class ApplicationModule {
    public static PdfRenderer providePdfRenderer(FileDto fileDto) {
        try {
            return new PdfRenderer(getSeekableFileDescriptor(fileDto));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ParcelFileDescriptor getSeekableFileDescriptor(FileDto fileDto) {
        ParcelFileDescriptor fd = null;
        File pdfFile = new File(fileDto.getPath());
        if (!pdfFile.isDirectory()) {
            pdfFile.setReadable(true);
            try {
                fd = ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return fd;
    }
}
