package co.breezing.module.nine.absorbcalc;

import java.util.ArrayList;

public class SavedRawData {

	private static ArrayList<SavedRawData> instance_list = new ArrayList<SavedRawData>();

	private int index;
	private String time;
	private Double pd1;
	private Double pd2;
	private Double pd3;
	private Double pd4;
	private Double presure;
	private Double resolution;
	private Double status;
	private String breathFreq;
	private Double absorbanceO2;
	private Double absorbanceCO2;
	private Double volume;
	private Double thermistor;

	public static ArrayList<SavedRawData> getInstance_list() {
		return instance_list;
	}

	public static void setInstance_list(ArrayList<SavedRawData> instance_list) {
		SavedRawData.instance_list = instance_list;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getPd1() {
		return pd1;
	}

	public void setPd1(Double pd1) {
		this.pd1 = pd1;
	}

	public Double getPd2() {
		return pd2;
	}

	public void setPd2(Double pd2) {
		this.pd2 = pd2;
	}

	public Double getPd3() {
		return pd3;
	}

	public void setPd3(Double pd3) {
		this.pd3 = pd3;
	}

	public Double getPd4() {
		return pd4;
	}

	public void setPd4(Double pd4) {
		this.pd4 = pd4;
	}

	public Double getPresure() {
		return presure;
	}

	public void setPresure(Double presure) {
		this.presure = presure;
	}

	public Double getResolution() {
		return resolution;
	}

	public void setResolution(Double resolution) {
		this.resolution = resolution;
	}

	public Double getStatus() {
		return status;
	}

	public void setStatus(Double status) {
		this.status = status;
	}

	public String getBreathFreq() {
		return breathFreq;
	}

	public void setBreathFreq(String breathFreq) {
		this.breathFreq = breathFreq;
	}

	public Double getAbsorbanceO2() {
		return absorbanceO2;
	}

	public void setAbsorbanceO2(Double absorbanceO2) {
		this.absorbanceO2 = absorbanceO2;
	}

	public Double getAbsorbanceCO2() {
		return absorbanceCO2;
	}

	public void setAbsorbanceCO2(Double absorbanceCO2) {
		this.absorbanceCO2 = absorbanceCO2;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getThermistor() {
		return thermistor;
	}

	public void setThermistor(Double thermistor) {
		this.thermistor = thermistor;
	}

	// // Store data
	// DataStorage[x + 1] = time_index[index];
	// DataStorage[x + 2] = Double.toString(PhDiode1[index]);
	// DataStorage[x + 3] = Double.toString(PhDiode2[index]);
	// DataStorage[x + 4] = Double.toString(PhDiode3[index]);
	// DataStorage[x + 5] = Double.toString(PhDiode4[index]);
	// DataStorage[x + 6] = Long.toString(Pressure[index]);
	// Log.d(tag, "Data storage for pressure "
	// + DataStorage[x + 6]);
	// DataStorage[x + 7] = Long.toString(RS[index]);
	// DataStorage[x + 8] = Integer.toString(ST[index]);
	// if (BF[index] != 0) {
	// DataStorage[x + 9] = Double.toString(BF[index]);
	// }
	// else {
	// DataStorage[x + 9] = "NA";
	// }
	// DataStorage[x + 10] = Double.toString(Abs_o2[index]);
	// DataStorage[x + 11] = Double.toString(Abs_co2[index]);
	// DataStorage[x + 12] = Double.toString(volume[index]);
	// DataStorage[x + 13] = Double
	// .toString(Thermistor[index]);

}
