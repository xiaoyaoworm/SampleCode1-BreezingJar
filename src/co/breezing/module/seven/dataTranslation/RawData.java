package co.breezing.module.seven.dataTranslation;

import java.io.IOException;
import java.io.InputStream;
import android.util.Log;
import co.breezing.module.six.bluetooth.Bluetooth;

public class RawData {

	// results
	// public static int progress_percentage;

	// tag
	private static String tag = "RawData";

	public static RawData instance = new RawData();

	// other classes
	Bluetooth bluetooth = Bluetooth.getInstance();

	// // all data def
	private byte[] bufferData = new byte[34];

	public static RawData getInstance() {
		if (instance == null)
			instance = new RawData();
		return instance;
	}

	public byte[] getBufferData() {
		return bufferData;
	}

	public void setBufferData(byte[] bufferData) {
		this.bufferData = bufferData;
	}

	public boolean readBytes() {
		// get the bluetooth inputStream and outStream
		InputStream in = bluetooth.getIn();
		byte[] buffer = new byte[34];
		byte[] redundant_buffer = new byte[34];
		// read data
		try {
			Log.d(tag, "Available Bytes: " + in.available());
			if (in.available() >= 1) {
				while (in.available() > 34)
					in.read(redundant_buffer, 0, 34);
				in.read(buffer);
			}
			instance.bufferData = buffer;
			return true;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(tag, "get bytes error: " + e);
			e.printStackTrace();
			return false;
		}
	}
}
