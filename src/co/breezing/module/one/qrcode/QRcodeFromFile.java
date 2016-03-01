package co.breezing.module.one.qrcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.os.Environment;
import android.util.Log;

public class QRcodeFromFile {

	private static String qrcode;
	private static String tag = "QR code from file";

	public static String getQrcode() {
		return qrcode;
	}

	public static void setQrcode(String qrcode) {
		QRcodeFromFile.qrcode = qrcode;
	}

	public static String getQrcodeFromFile() {
		File sdcard = Environment.getExternalStorageDirectory();

		// Get the text file
		File file = new File(sdcard + "/BreezingData/", "QRcode.txt");

		// Read text from file
		StringBuilder text = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				text.append(line);
			}
			qrcode = text.toString();
			br.close();
		}
		catch (IOException e) {
			Log.d(tag, "Exception in getting QR code from file.");
			qrcode = null;
		}
		return qrcode;
	}

}
