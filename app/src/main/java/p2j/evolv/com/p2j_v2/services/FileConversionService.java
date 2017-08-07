package p2j.evolv.com.p2j_v2.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import p2j.evolv.com.p2j_v2.files.FileUtils;
import p2j.evolv.com.p2j_v2.model.FileDto;
import p2j.evolv.com.p2j_v2.processors.PdfProcessor;


public class FileConversionService extends IntentService {
    public FileConversionService() {
        super("FileConversionService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        FileDto fileDto = (FileDto) intent.getExtras().get("fileDto");
        new PdfProcessor(fileDto).execute();
    }

    @Override
    public void onDestroy() {
        Log.i(getClass().getName(), "Destroying the service");
        super.onDestroy();
    }
}
