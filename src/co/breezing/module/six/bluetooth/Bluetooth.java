package co.breezing.module.six.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class Bluetooth {

	private static Bluetooth instance;

	// bluetooth def
	private BluetoothAdapter bluetoothAdapter;
	private BluetoothDevice btDevice = null;
	private String btDeviceName;

	// bluetooth transmission
	private BluetoothSocket btSocket = null;
	private boolean connectionEstablished;
	private InputStream in;
	private OutputStream out;

	public static Bluetooth getInstance() {
		if (instance == null)
			instance = new Bluetooth();
		return instance;
	}

	// tag
	private static String tag = "Bluetooth";

	// changed by Linyao Oct 6
	public BluetoothAdapter getBluetoothAdapter() {
		return BluetoothAdapter.getDefaultAdapter();
	}

	public void setBluetoothAdapter(BluetoothAdapter bluetoothAdapter) {
		this.bluetoothAdapter = bluetoothAdapter;
	}

	public BluetoothDevice getBtDevice() {
		return btDevice;
	}

	public void setBtDevice(BluetoothDevice btDevice) {
		this.btDevice = btDevice;
	}

	public String getBtDeviceName() {
		return btDeviceName;
	}

	public void setBtDeviceName(String btDeviceName) {
		this.btDeviceName = btDeviceName;
	}

	public BluetoothSocket getBtSocket() {
		return btSocket;
	}

	public void setBtSocket(BluetoothSocket btSocket) {
		this.btSocket = btSocket;
	}

	public boolean isConnectionEstablished() {
		return connectionEstablished;
	}

	public void setConnectionEstablished(boolean connectionEstablished) {
		this.connectionEstablished = connectionEstablished;
	}

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}

	// function to connect Bluetooth socket
	public boolean connectBTSocket(String MAC) {
		try {
			connectionEstablished = false;
			Long bt_time_start = System.currentTimeMillis();
			bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			btDevice = bluetoothAdapter.getRemoteDevice(MAC);
			btDeviceName = btDevice.getName();
			if (!bluetoothAdapter.isEnabled()) {
				Log.d(tag, " Bluetooth Adapter not Enabled");
				bluetoothAdapter.enable();
			}
			BluetoothDevice device = BluetoothAdapter.getDefaultAdapter()
					.getRemoteDevice(btDevice.getAddress());
			try {
				try {
					if (btSocket != null)
						btSocket.close();
				}
				catch (Exception e) {
					Log.d(tag,
							"Bluetooth testing: Exception in closing bt socket");
				}
				Method m = device.getClass().getMethod("createRfcommSocket",
						new Class[] { int.class });
				btSocket = (BluetoothSocket) m.invoke(device,
						Integer.valueOf(1));
				Log.d(tag, "Bluetooth Socket : " + btSocket);
				Log.d(tag, "Bluetooth Connecting to Device...............: "
						+ btDeviceName + " @ " + MAC);
				Long bt_time_connect_start = System.currentTimeMillis();
				btSocket.connect();

				try {
					Long bt_time_end = System.currentTimeMillis();
					Log.d(tag, "Bluetooth testing: Timestamps: "
							+ bt_time_start + " " + bt_time_connect_start + " "
							+ bt_time_end);
					Log.d(tag, "Bluetooth testing: connecting time: "
							+ (bt_time_end - bt_time_start) + " ms");
					Log.d(tag, "Bluetooth testing: device connecting time: "
							+ (bt_time_end - bt_time_connect_start + " ms"));
				}
				catch (Exception e) {
					Log.d(tag,
							"Bluetooth testing: Exception in reading time information: "
									+ e.toString());
				}
				connectionEstablished = true;

				in = btSocket.getInputStream();
				Log.d(tag, "Input socket : " + in);
				instance.in = in;

				out = btSocket.getOutputStream();
				Log.d(tag, " Output socket : " + out);
				instance.out = out;
			}
			catch (Exception e) {
				Log.d(tag, " Exception in creating RF socket Comm" + e);
				connectionEstablished = false;
				return connectionEstablished;

			}
			return connectionEstablished;

		}
		catch (Exception e) {
			Log.d(tag,
					"Exception in establishing bluetooth socket communication : "
							+ e);
			connectionEstablished = false;
			return connectionEstablished;
		}
	}

	public boolean sendCommand(Bluetooth bluetooth) {
		/** HEX Equivalent of unsigned integer 234 "11101010" */
		int config = 0xEA & 0xff;
		Log.d(tag, "data written - outputStream of BTSocket : " + config);
		OutputStream out;
		try {
			out = bluetooth.getOut();
			out.write(config);
			return true;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d(tag, "bluetooth send command failed.");
			return false;
		}
	}

	public void disconnectBTSocket() {
		try {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			if (btSocket != null) {
				btSocket.close();
			}
		}
		catch (Exception e) {
			Log.d(tag,
					"Exception in disconnect with bluetooth socket communication : "
							+ e);
		}
	}

	public ArrayList<String> listAllDevices() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
				.getBondedDevices();

		ArrayList<String> s = new ArrayList<String>();
		for (BluetoothDevice bt : pairedDevices)
			s.add(bt.getName());

		return s;
	}

	/**
	 * Function to handle the case when device disconnects for a very short time
	 * and connects back again
	 */
	public void quickDisconnect(Timer timer, BluetoothSocket btSocket) {
		Log.d(tag, "Bluetooth Quick Disconnect");
		try {
			InputStream in = btSocket.getInputStream();
			OutputStream out = btSocket.getOutputStream();
			if (timer != null) {
				timer.cancel();
			}

			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}

			if (btSocket != null) {
				btSocket.close();
			}
		}
		catch (Exception e) {
			Log.d(tag, "Exception in closing Bluetooth socket : " + e);
		}
	}
}
