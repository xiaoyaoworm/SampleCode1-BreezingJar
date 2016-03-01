package co.breezing.module.nine.absorbcalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class SavePressureDataFile {

	private static String tag = "SavePressureData";

	private static PrintWriter mCurrentFile;

	/**
	 * Saves the pressure data in Excel file. File is named as Pressure_DateTime
	 * in the Breath folder in SD Card. Data won't be saved if the phone is
	 * connected via USB.
	 */
	public static void saveData(Context ctx,
			ArrayList<SavedRawData> rawData_list) {

		try {
			Log.d(tag, "Trying to save pressure data.");
			File folder = new File(Environment.getExternalStorageDirectory()
					+ "/BreezingData/RawData");
			if (!folder.exists()) {
				folder.mkdir();
			}
			String filename = folder + "/Pressure_" + getDateTimeFile()
					+ ".csv";
			Log.d(tag, "Created file: " + filename);
			File outputFile = new File(filename);
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
		catch (FileNotFoundException e) {
			Log.d(tag, "Caught exception mCurrentFile" + e);
		}
		try {
			for (int i = 0; i < rawData_list.size() + 1; i++) {
				StringBuffer buff = new StringBuffer();
				if (i == 0) {
					buff.append("Time Stamp");
					buff.append(",");
					buff.append("PD1");
					buff.append(",");
					buff.append("PD2");
					buff.append(",");
					buff.append("PD3");
					buff.append(",");
					buff.append("PD4");
					buff.append(",");
					buff.append("PRESSURE");
					buff.append(",");
					buff.append("RESOLUTION");
					buff.append(",");
					buff.append("STATUS");
					buff.append(",");
					buff.append("Breaths/min");
					buff.append(",");
					buff.append("Absorbance O2");
					buff.append(",");
					buff.append("Absorbance CO2");
					buff.append(",");
					buff.append("Volume");
					buff.append(",");
					buff.append("Thermistor");
					buff.append(",");
				}
				else {
					buff.append(String.valueOf(rawData_list.get(i - 1)
							.getTime()));
					buff.append(",");
					buff.append(String
							.valueOf(rawData_list.get(i - 1).getPd1()));
					buff.append(",");
					buff.append(String
							.valueOf(rawData_list.get(i - 1).getPd2()));
					buff.append(",");
					buff.append(String
							.valueOf(rawData_list.get(i - 1).getPd3()));
					buff.append(",");
					buff.append(String
							.valueOf(rawData_list.get(i - 1).getPd4()));
					buff.append(",");
					buff.append(String.valueOf(rawData_list.get(i - 1)
							.getPresure()));
					buff.append(",");
					buff.append(String.valueOf(rawData_list.get(i - 1)
							.getResolution()));
					buff.append(",");
					buff.append(String.valueOf(rawData_list.get(i - 1)
							.getStatus()));
					buff.append(",");
					buff.append(String.valueOf(rawData_list.get(i - 1)
							.getBreathFreq()));
					buff.append(",");
					buff.append(String.valueOf(rawData_list.get(i - 1)
							.getAbsorbanceO2()));
					buff.append(",");
					buff.append(String.valueOf(rawData_list.get(i - 1)
							.getAbsorbanceCO2()));
					buff.append(",");
					buff.append(String.valueOf(rawData_list.get(i - 1)
							.getVolume()));
					buff.append(",");
					buff.append(String.valueOf(rawData_list.get(i - 1)
							.getThermistor()));
				}
				mCurrentFile.println(buff.toString());
				mCurrentFile.flush();
			}
		}
		catch (Exception e) {
			Log.d(tag, "Caught Exception in appending data into buffer" + e);
		}

	}

	/**
	 * Returns the Date format to be displayed while saving file. Used just for
	 * naming the file.
	 */
	public static String getDateTimeFile() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH.mm.ss");
			Date date = new Date();
			return dateFormat.format(date);
		}
		catch (Exception e) {
			Log.d(tag, "Caught Exception in getting date/time" + e);
			return "";
		}
	}

}
