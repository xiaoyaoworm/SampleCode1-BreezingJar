package co.breezing.module.eight.flowcalc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class SaveBreathfreqFile {

	private static String tag = "saveBreathfreqFile";

	/**
	 * Saves breathFrequency array in file to get it later.
	 */
	public static void saveBreathFreqAndTime(Context ctx, double first_freq,
			double second_freq, double third_freq, double frequency, int time) {
		PrintWriter mCurrentFile = null;
		try {
			File folder = new File(Environment.getExternalStorageDirectory()
					+ "/BreezingData");
			if (!folder.exists()) {
				folder.mkdir();
			}
			String bfFile = folder + "/" + "breathFrequency.csv";
			File outputFile = new File(bfFile);
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
			buff.append(first_freq);
			buff.append(",");
			buff.append(second_freq);
			buff.append(",");
			buff.append(third_freq);
			buff.append(",");
			buff.append(frequency);
			buff.append(",");
			buff.append(time);
			mCurrentFile.println(buff.toString());
			buff.delete(0, buff.length());
			mCurrentFile.flush();
		}
		catch (Exception e) {
			Log.d(tag, "Caught Exception in save data into breathFrequency.csv"
					+ e);
		}
	}
}
