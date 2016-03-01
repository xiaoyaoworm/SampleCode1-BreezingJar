package co.breezing.module.one.qrcode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class SaveQRFile {

	private static String tag = "saveFile";

	/**
	 * Saves QR code string in order to get it later.
	 */
	public static void saveQRcode(Context ctx, String qrcode) {
		PrintWriter mCurrentFile = null;
		try {
			File folder = new File(Environment.getExternalStorageDirectory()
					+ "/BreezingData");
			if (!folder.exists()) {
				folder.mkdir();
			}
			String qrcodeFile = folder + "/" + "QRcode.txt";
			File outputFile = new File(qrcodeFile);
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			mCurrentFile = new PrintWriter(new FileOutputStream(outputFile));

			// Tell the media scanner about the new file so that it is
			// immediately available to the user.
			MediaScannerConnection.scanFile(ctx,
					new String[] { outputFile.toString() }, null,
					new MediaScannerConnection.OnScanCompletedListener() {
						public void onScanCompleted(String path, Uri uri) {
							Log.i("ExternalStorage", "Scanned " + path + ":");
							Log.i("ExternalStorage", "-> uri=" + uri);
						}
					});
		}
		catch (Exception e) {
			Log.d(tag, "Caught exception mCurrentFile" + e);
		}

		try {
			StringBuffer buff = new StringBuffer();
			buff.append(qrcode);
			mCurrentFile.println(buff.toString());
			buff.delete(0, buff.length());
			mCurrentFile.flush();
		}
		catch (Exception e) {
			Log.d(tag, "Caught Exception in save data into QRcode" + e);
		}
	}
}
