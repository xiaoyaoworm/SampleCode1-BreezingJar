package co.breezing.module.six.bluetooth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.os.Environment;
import android.util.Log;

public class MACFromFile {

	private static String MAC;
	private static String tag = "MAC from file";

	public static String getMAC() {
		return MAC;
	}

	public static void setMAC(String mAC) {
		MAC = mAC;
	}

	public static String getMACFromFile() {
		File sdcard = Environment.getExternalStorageDirectory();

		// Get the text file
		File file = new File(sdcard + "/BreezingData/", "MAC.csv");

		// Read text from file
		StringBuilder text = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				text.append(line);
			}
			MAC = text.toString();
			br.close();
		}
		catch (IOException e) {
			Log.d(tag, "Exception in getting breath freq from file.");
			MAC = null;
		}
		return MAC;
	}

}
