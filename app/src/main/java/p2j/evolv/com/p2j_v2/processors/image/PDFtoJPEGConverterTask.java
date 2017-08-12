package p2j.evolv.com.p2j_v2.processors.image;

import android.graphics.pdf.PdfRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.RecursiveAction;

import p2j.evolv.com.p2j_v2.model.FileDto;
import p2j.evolv.com.p2j_v2.processors.image.converters.PDFToJPEGConverter;


public class PDFtoJPEGConverterTask extends RecursiveAction {

    private PdfRenderer renderer;
    private FileDto fileDto;
    private int numOfExecutors = 0;
    private int startPage = 0;
    private int endPage = 0;
    private boolean isFanOut = false;
    private UUID threadId = UUID.randomUUID();

    private PDFToJPEGConverter pdfToJPEGConverter;

    public PDFtoJPEGConverterTask(PdfRenderer renderer,
                                  FileDto fileDto,
                                  int numOfExecutors,
                                  int startPage,
                                  int endPage,
                                  boolean isFanOut) {
        this.renderer = renderer;
        this.fileDto = fileDto;
        this.numOfExecutors = numOfExecutors;
        this.startPage = startPage;
        this.endPage = endPage;
        this.isFanOut = isFanOut;
    }

    public PDFtoJPEGConverterTask(PdfRenderer renderer,
                                  FileDto fileDto,
                                  int numOfExecutors,
                                  int startPage,
                                  int endPage) {
        this.renderer = renderer;
        this.fileDto = fileDto;
        this.numOfExecutors = numOfExecutors;
        this.startPage = startPage;
        this.endPage = endPage;
        this.pdfToJPEGConverter = new PDFToJPEGConverter(renderer, this.fileDto, startPage, endPage);
    }

    @Override
    protected void compute() {
        if (isFanOut) {
            List<PDFtoJPEGConverterTask> PDFtoJPEGConverterTasks = createSubtasks();

            for (PDFtoJPEGConverterTask PDFtoJPEGConverterTask : PDFtoJPEGConverterTasks) {
                PDFtoJPEGConverterTask.fork();
            }
        } else {
            pdfToJPEGConverter.convert();
        }
    }

    private List<PDFtoJPEGConverterTask> createSubtasks() {
        List<PDFtoJPEGConverterTask> subtasks = new ArrayList();
        int poolSize = this.endPage / numOfExecutors;

        for (int i = 0; i < poolSize; i++) {
            int startPage = this.startPage + (this.numOfExecutors * i);
            int endPage = this.startPage + (this.numOfExecutors * (i + 1)) - 1;

            subtasks.add(new PDFtoJPEGConverterTask(this.renderer,
                                                    this.fileDto,
                                                    this.numOfExecutors,
                                                    startPage,
                                                    endPage));
        }
        subtasks.add(new PDFtoJPEGConverterTask(this.renderer,
                                                this.fileDto,
                                                this.numOfExecutors,
                                                (endPage - (this.endPage % numOfExecutors)),
                                                endPage));
        return subtasks;
    }
}