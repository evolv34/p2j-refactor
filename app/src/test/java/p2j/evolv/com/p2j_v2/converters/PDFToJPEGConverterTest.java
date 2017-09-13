package p2j.evolv.com.p2j_v2.converters;

import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import p2j.evolv.com.p2j_v2.model.FileDto;

public class PDFToJPEGConverterTest {

    @Test
    public void conversionTest() throws IOException {
        FileDto fileDto = new FileDto("app/src/test/resources/sample/pdf-test.pdf", new ArrayList<File>());
        PdfRenderer pdfRenderer = new PdfRenderer(getSeekableFileDescriptor(fileDto));

        System.out.println(pdfRenderer.getPageCount());
        new PDFToJPEGConverter(pdfRenderer, fileDto, 0, 2).convert();
    }

    private static ParcelFileDescriptor getSeekableFileDescriptor(FileDto fileDto) {
        ParcelFileDescriptor fd = null;
        File pdfFile = new File(PDFToJPEGConverterTest.class.getResource("/sample/pdf-test.pdf").getFile());
        if (!pdfFile.isDirectory()) {
            pdfFile.setReadable(true);
            try {
                fd = ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("fd ===> " + fd);
        return fd;
    }
}
