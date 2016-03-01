package co.breezing.module.four.guidance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.os.Environment;
import android.util.Log;

public class BfAndTimeFromFile {

	private static String breathFreq;
	private static String time;
	private static String tag = "BF and testing time from file";

	public static String getBreathFreq() {
		return breathFreq;
	}

	public static void setBreathFreq(String breathFreq) {
		BfAndTimeFromFile.breathFreq = breathFreq;
	}

	public static String getTime() {
		return time;
	}

	public static void setTime(String time) {
		BfAndTimeFromFile.time = time;
	}

	public static String[] getBfAndTimeFromFile() {
		File sdcard = Environment.getExternalStorageDirectory();

		String[] bf_time = new String[5];

		// Get the text file
		File file = new File(sdcard + "/BreezingData/", "breathFrequency.csv");

		// Read text from file
		StringBuilder text = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				text.append(line);
			}
			bf_time = text.toString().split(",");
			br.close();
		}
		catch (IOException e) {
			Log.d(tag, "Exception in getting breath freq from file.");
			breathFreq = String.valueOf(15);
			time = String.valueOf(0);
			bf_time[3] = breathFreq;
			bf_time[4] = time;
		}
		return bf_time;
	}

}
