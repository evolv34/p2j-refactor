package p2j.evolv.com.p2j_v2.processors;

import android.graphics.pdf.PdfRenderer;

import java.util.concurrent.ForkJoinPool;

import p2j.evolv.com.p2j_v2.ApplicationModule;
import p2j.evolv.com.p2j_v2.model.FileDto;
import p2j.evolv.com.p2j_v2.processors.image.PDFtoJPEGConverterTask;

public class PdfProcessor implements Processor {
    private FileDto fileDto;

    private ForkJoinPool forkJoinPool = ApplicationModule.provideForkJoinPool();
    private PdfRenderer pdfRenderer;

    public PdfProcessor(FileDto fileDto) {
        this.fileDto = fileDto;
        this.pdfRenderer = ApplicationModule.providePdfRenderer(this.fileDto);
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
