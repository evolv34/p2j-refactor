package p2j.evolv.com.p2j_v2.processors;

import p2j.evolv.com.p2j_v2.model.FileDto;

public abstract class Processor {

    public static Processor getProcessor(FileDto fileDto, Processor.ProcessType processType) {
        switch (processType) {
            case PDF_TO_IMAGE_PROCESSOR:
                return new PdfProcessor(fileDto);
            default:
                return null;
        }
    }

    public enum ProcessType {
        PDF_TO_IMAGE_PROCESSOR
    }

    public abstract void execute();
}
