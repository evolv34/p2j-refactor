package p2j.evolv.com.p2j_v2.converters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.pdf.PdfRenderer;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import p2j.evolv.com.p2j_v2.model.FileDto;
import p2j.evolv.com.p2j_v2.writers.Writer;

public class PDFToJPEGConverter extends Converter {

    private int startPage;
    private int endPage;
    private FileDto fileDto;
    private PdfRenderer pdfRenderer;

    public PDFToJPEGConverter(PdfRenderer pdfRenderer,
                              FileDto fileDto,
                              int startPage,
                              int endPage) {
        this.startPage = startPage;
        this.endPage = endPage;
        this.pdfRenderer = pdfRenderer;
        this.fileDto = fileDto;
    }

    @Override
    public void convert() {
        String folderPath = createFolderIfNotExists(this.fileDto.getPath());
        List<Bitmap> pages = new ArrayList<>();
        for (int i = this.startPage; i <= this.endPage; i++) {
            try {
                Bitmap newPageBitmap = createBitmap();
                createCanvas(newPageBitmap);

                PdfRenderer.Page page = pdfRenderer.openPage(i);
                page.render(newPageBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_PRINT);

                Writer.write_2(Writer.WriterType.FILE_WRITER, newPageBitmap, folderPath + "/page-" + i + ".jpg");
                page.close();
            } catch (Exception e) {
                Log.e(getClass().getName(), "Could not create image for the page [" + i + "]", e);
            }
        }
    }

    @NonNull
    private String createFolderIfNotExists(String src) {
        String folderPath = src.split("\\.pdf")[0];
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder.getAbsolutePath();
    }

    private Bitmap createBitmap() {
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        return Bitmap.createBitmap(1080, 1920, conf);
    }

    private Canvas createCanvas(Bitmap mBitmap) {
        Canvas canvas = new Canvas(mBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(mBitmap, 0, 0, null);

        return canvas;
    }
}
