package co.breezing.module.eleven.reerq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import co.breezing.module.nine.absorbcalc.SavedAbsorbData;

public class SaveFinalDataFile {

	private static String tag = "SaveFinalData";
	
	private static PrintWriter mCurrentFile;

	/**
	 * Saves the absorbsance data in Excel file. File is named as
	 * Absorbance_DateTime in the Breath folder in SD Card. Data won't be saved
	 * if the phone is connected via USB.
	 */
	public static void saveFinalData(Context ctx, SavedAbsorbData savedAbsorbData) {
		try {
			Log.d(tag, "Trying to save final data.");
			File folder = new File(Environment.getExternalStorageDirectory()
					+ "/BreezingData/RawData");
			if (!folder.exists()) {
				folder.mkdir();
			}
			String filename = folder + "/Final_" + getDateTimeFile() + ".csv";
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
			buff.append("Total_delta_o2");
			buff.append(",");
			buff.append("Total_delta_co2");
			buff.append(",");
			buff.append("Total_delta_time");
			buff.append(",");
			buff.append("Concentrate CO2");
			buff.append(",");
			buff.append("Concentrate O2");
			buff.append(",");
			buff.append("VCO2");
			buff.append(",");
			buff.append("VO2");
			buff.append(",");
			buff.append("Factor");
			buff.append(",");
			buff.append("Ve");
			buff.append(",");
			buff.append("Ree");
			buff.append(",");
			buff.append("Rq");
			buff.append(",");
			buff.append("Total Test Time");
			buff.append(",");
			buff.append("Var1");
			buff.append(",");
			buff.append("Var2");
			buff.append(",");
			buff.append("Var3");
			buff.append(",");
			buff.append("Var4");
			buff.append(",");
			buff.append("Var5");
			buff.append(",");
			buff.append("Var6");
			buff.append(",");
			buff.append("\n");
			
			
			buff.append(savedAbsorbData.getTotal_delta_o2());
			buff.append(",");
			buff.append(savedAbsorbData.getTotal_delta_co2());
			buff.append(",");
			buff.append(savedAbsorbData.getTotal_delta_time());
			buff.append(",");
			buff.append(savedAbsorbData.getConc_co2());
			buff.append(",");
			buff.append(savedAbsorbData.getConc_o2());
			buff.append(",");
			buff.append(savedAbsorbData.getVco2());
			buff.append(",");
			buff.append(savedAbsorbData.getVo2());
			buff.append(",");
			buff.append(savedAbsorbData.getFactor());
			buff.append(",");
			buff.append(savedAbsorbData.getVe());
			buff.append(",");
			buff.append(savedAbsorbData.getRee());
			buff.append(",");
			buff.append(savedAbsorbData.getRq());
			buff.append(",");
			buff.append(savedAbsorbData.getTotal_time());
			buff.append(",");
			buff.append(savedAbsorbData.getQr_var1());
			buff.append(",");
			buff.append(savedAbsorbData.getQr_var2());
			buff.append(",");
			buff.append(savedAbsorbData.getQr_var3());
			buff.append(",");
			buff.append(savedAbsorbData.getQr_var4());
			buff.append(",");
			buff.append(savedAbsorbData.getQr_var5());
			buff.append(",");
			buff.append(savedAbsorbData.getQr_var6());
			buff.append(",");
			buff.append("\n");
			

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
