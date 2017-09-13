package p2j.evolv.com.p2j_v2.processors;

import android.graphics.pdf.PdfRenderer;

import java.util.concurrent.ForkJoinPool;

import p2j.evolv.com.p2j_v2.ApplicationModule;
import p2j.evolv.com.p2j_v2.model.FileDto;
import p2j.evolv.com.p2j_v2.processors.image.PDFtoJPEGConverterTask;

public class PdfProcessor extends Processor {
    private FileDto fileDto;

    private ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    private PdfRenderer pdfRenderer;

    public PdfProcessor(FileDto fileDto) {
        this.fileDto = fileDto;
        this.pdfRenderer = ApplicationModule.providePdfRenderer(fileDto);
    }

    @Override
    public void execute() {
        forkJoinPool.submit(new PDFtoJPEGConverterTask( pdfRenderer,
                                                        this.fileDto,
                                                        Runtime.getRuntime().availableProcessors(),
                                                        0,
                                                        pdfRenderer.getPageCount(),
                                                        true));
    }
}
