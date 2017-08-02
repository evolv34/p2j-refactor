package p2j.evolv.com.p2j_v2.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.util.List;

public class FileDto implements Parcelable {
    private String path;
    private List<File> files;

    public FileDto(String path, List<File> files) {
        this.path = path;
        this.files = files;
    }

    protected FileDto(Parcel in) {
        path = in.readString();
        files = in.readArrayList(ClassLoader.getSystemClassLoader());
    }


    public List<File> getFiles() {
        return files;
    }

    public String getPath() {
        return path;
    }

    public static final Creator<FileDto> CREATOR = new Creator<FileDto>() {
        @Override
        public FileDto createFromParcel(Parcel in) {
            return new FileDto(in);
        }

        @Override
        public FileDto[] newArray(int size) {
            return new FileDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeArray(files.toArray());
    }
}
