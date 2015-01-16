package com.erayarslan.publeak.util;

import android.os.Environment;
import android.os.StrictMode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
    public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    private static final int EOF = -1;

    public static File stream2file(InputStream in) throws IOException {
        strictMode();
        //
        File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
        copy(in, out);
        return tempFile;
    }

    public static File createTemporaryFile(String part, String ext) throws IOException {
        File tempDir = Environment.getExternalStorageDirectory();
        tempDir = new File(tempDir.getAbsolutePath() + "/.temp/");
        if(!tempDir.exists()) {
            tempDir.mkdir();
        } return File.createTempFile(part, ext, tempDir);
    }

    public static int copy(final InputStream input, final OutputStream output) throws IOException {
        final long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        } return (int) count;
    }

    public static long copyLarge(final InputStream input, final OutputStream output)
        throws IOException {
        return copy(input, output, DEFAULT_BUFFER_SIZE);
    }

    public static long copy(final InputStream input, final OutputStream output, final int bufferSize) throws IOException {
        return copyLarge(input, output, new byte[bufferSize]);
    }

    public static long copyLarge(final InputStream input, final OutputStream output, final byte[] buffer) throws IOException {
        long count = 0;
        int n = 0;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        } return count;
    }

    public static void strictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
