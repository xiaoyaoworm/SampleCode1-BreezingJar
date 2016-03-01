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

public class SaveAbsorbDataFile {

	private static String tag = "SaveAbsorbData";

	private static PrintWriter mCurrentFile;

	/**
	 * Saves the absorbsance data in Excel file. File is named as
	 * Absorbance_DateTime in the Breath folder in SD Card. Data won't be saved
	 * if the phone is connected via USB.
	 */
	public static void saveAbsorbanceData(Context ctx,
			ArrayList<SavedAbsorbData> absorbData_list) {
		try {
			Log.d(tag, "Trying to save absorbance data.");
			File folder = new File(Environment.getExternalStorageDirectory()
					+ "/BreezingData/RawData");
			if (!folder.exists()) {
				folder.mkdir();
			}
			String filename = folder + "/Absorbance_" + getDateTimeFile()
					+ ".csv";
			Log.d(tag, "Created file: " + filename);
			File outputFile = new File(filename);
			mCurrentFile = new PrintWriter(new FileOutputStream(outputFile));

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
			StringBuffer buff = new StringBuffer();
			buff.append("Breath Signal");
			buff.append(",");
			buff.append("Absorbance O2");
			buff.append(",");
			buff.append("Absorbance CO2");
			buff.append(",");
			buff.append("Gap numbers");
			buff.append(",");
			buff.append("Time");
			buff.append(",");
			buff.append("Abs_o2_init");
			buff.append(",");
			buff.append("Abs_o2_final");
			buff.append(",");
			buff.append("Abs_co2_init");
			buff.append(",");
			buff.append("Abs_co2_final");
			buff.append("\n");
			
			for(int i =0;i<absorbData_list.size();i++) {
				buff.append(absorbData_list.get(i).getBreathSignal());
				buff.append(",");
				buff.append(absorbData_list.get(i).getAbsorbance_O2());
				buff.append(",");
				buff.append(absorbData_list.get(i).getAbsorbance_CO2());
				buff.append(",");
				buff.append(absorbData_list.get(i).getDataPoints());
				buff.append(",");
				buff.append(absorbData_list.get(i).getTime());
				buff.append(",");
				buff.append(absorbData_list.get(i).getAbs_o2_int());
				buff.append(",");
				buff.append(absorbData_list.get(i).getAbs_o2_final());
				buff.append(",");
				buff.append(absorbData_list.get(i).getAbs_co2_int());
				buff.append(",");
				buff.append(absorbData_list.get(i).getAbs_co2_final());
				buff.append("\n");
			}
			
			mCurrentFile.println(buff.toString());
			Log.d(tag, "Data: " + buff.toString());
			mCurrentFile.flush();
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
