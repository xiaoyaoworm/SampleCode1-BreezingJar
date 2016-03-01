package co.breezing.module.seven.dataTranslation;

import java.util.ArrayList;
import android.util.Log;
import co.breezing.math.round.RoundData;

public class DataTranslation {

	private static String tag = "rawData calculation";

	private static DataTranslation data = new DataTranslation();

	private double pd1;
	private double pd2;
	private double pd3;
	private double pd4;
	private double status = 0;
	private double resolution = 0;
	private double pressure = 0;
	private double thermistor = 0;
	private double volume = 0;

	private ArrayList<Double> volume_list = new ArrayList<Double>();
	private ArrayList<Double> pd1_list = new ArrayList<Double>();
	private ArrayList<Double> pd2_list = new ArrayList<Double>();
	private ArrayList<Double> pd3_list = new ArrayList<Double>();
	private ArrayList<Double> pd4_list = new ArrayList<Double>();

	public double getPd1() {
		return pd1;
	}

	public void setPd1(double pd1) {
		this.pd1 = pd1;
	}

	public double getPd2() {
		return pd2;
	}

	public void setPd2(double pd2) {
		this.pd2 = pd2;
	}

	public double getPd3() {
		return pd3;
	}

	public void setPd3(double pd3) {
		this.pd3 = pd3;
	}

	public double getPd4() {
		return pd4;
	}

	public void setPd4(double pd4) {
		this.pd4 = pd4;
	}

	public double getStatus() {
		return status;
	}

	public void setStatus(double status) {
		this.status = status;
	}

	public double getResolution() {
		return resolution;
	}

	public void setResolution(double resolution) {
		this.resolution = resolution;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getThermistor() {
		return thermistor;
	}

	public void setThermistor(double thermistor) {
		this.thermistor = thermistor;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public ArrayList<Double> getVolume_list() {
		return volume_list;
	}

	public void setVolume_list(ArrayList<Double> volume_list) {
		this.volume_list = volume_list;
	}

	public ArrayList<Double> getPd1_list() {
		return pd1_list;
	}

	public void setPd1_list(ArrayList<Double> pd1_list) {
		this.pd1_list = pd1_list;
	}

	public ArrayList<Double> getPd2_list() {
		return pd2_list;
	}

	public void setPd2_list(ArrayList<Double> pd2_list) {
		this.pd2_list = pd2_list;
	}

	public ArrayList<Double> getPd3_list() {
		return pd3_list;
	}

	public void setPd3_list(ArrayList<Double> pd3_list) {
		this.pd3_list = pd3_list;
	}

	public ArrayList<Double> getPd4_list() {
		return pd4_list;
	}

	public void setPd4_list(ArrayList<Double> pd4_list) {
		this.pd4_list = pd4_list;
	}

	public boolean calcUsefulData(byte[] buffer) {
		// algorithm
		return true;
	}

	public boolean dataExists(byte[] buffer) {
		// algorithm
		return true;
	}

	private static boolean dataNoError(double pr, double res, byte buffer_16,
			byte buffer_17) {
		// algorithm
		return true;
	}

	private double calcBufferVal(byte a, byte b, byte c, byte d) {
		// algorithm
		return 0;
	}

}
