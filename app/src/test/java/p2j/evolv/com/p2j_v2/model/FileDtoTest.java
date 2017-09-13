package p2j.evolv.com.p2j_v2.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileDtoTest {

    @Test
    public void fileDtoWhenVariablesAreNotNullReturnFilePathAndListOfFiles() {
        List<File> files = Arrays.asList(new File(""));
        String path = "/path/file";
        FileDto fileDto = new FileDto(path, files);

        Assert.assertEquals(fileDto.getPath(), path);
        Assert.assertEquals(fileDto.getFiles(), files);
    }

    @Test
    public void fileDtoWhenPathIsNullReturnNullValue() {
        List<File> files = Arrays.asList(new File(""));
        String path = null;
        FileDto fileDto = new FileDto(path, files);

        Assert.assertEquals(fileDto.getPath(), path);
        Assert.assertEquals(fileDto.getFiles(), files);
    }


    @Test
    public void fileDtoWhenFilesIsNullReturnNullValue() {
        List<File> files = null;
        String path = "/path/file";
        FileDto fileDto = new FileDto(path, files);

        Assert.assertEquals(fileDto.getPath(), path);
        Assert.assertEquals(fileDto.getFiles(), files);
    }

    @Test
    public void fileDtoWhenFilesIsEmptyArrayReturnEmptyArray() {
        List<File> files = new ArrayList<>();
        String path = "/path/file";
        FileDto fileDto = new FileDto(path, files);

        Assert.assertEquals(fileDto.getPath(), path);
        Assert.assertEquals(fileDto.getFiles(), files);
    }
}
