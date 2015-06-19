package com.cetc.cloud.enginePlugin.isoUploaderPlugin;

import java.text.NumberFormat;

import org.apache.commons.fileupload.ProgressListener;

public class FileUploadListener implements ProgressListener {

    private final FileUploadStatus status;
    private double megaBytes = -1;

    public FileUploadListener(FileUploadStatus status) {
        this.status = status;
    }

    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        double mBytes = pBytesRead / 1000000;
        double total = pContentLength / 1000000;
        if (megaBytes == mBytes) {
            return;
        }
        // System.out.println("total====>" + total);
        // System.out.println("mBytes====>" + mBytes);
        megaBytes = mBytes;
        // System.out.println("megaBytes====>" + megaBytes);
        // System.out.println("We are currently reading item " + pItems);
        if (pContentLength == -1) {
            // System.out.println("So far, " + pBytesRead + " bytes have been read.");
        } else {
            // System.out.println("So far, " + pBytesRead + " of " + pContentLength
            // + " bytes have been read.");
            double read = (mBytes / total);
            NumberFormat nf = NumberFormat.getPercentInstance();
            String percent = nf.format(read);
            status.setPercent(percent);
            status.setBytesRead(pBytesRead);
            status.setContentLength(pContentLength);
            status.setItems(pItems);
        }
    }

}
