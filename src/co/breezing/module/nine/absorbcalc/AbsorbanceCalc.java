package co.breezing.module.nine.absorbcalc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.content.Context;
import android.util.Log;
import co.breezing.module.seven.dataTranslation.DataTranslation;

public class AbsorbanceCalc {

	private String tag = "Module9: calculate absorbance data";

	private boolean increasing_flag = false; // true means increasing, false
												// means flat
	private int cycle_index_i = 0;// i value for cycle, means how many cycles
	private int cycle_index_j = 0;// j value for cycle, means in one cycles how
									// many data there

	private ArrayList<Double> abs_co2_X_list = new ArrayList<Double>();
	private ArrayList<Double> abs_o2_X_list = new ArrayList<Double>();

	private ArrayList<Double> delta_time_list = new ArrayList<Double>();
	private ArrayList<Double> delta_absorbance_co2_list = new ArrayList<Double>();
	private ArrayList<Double> delta_absorbance_o2_list = new ArrayList<Double>();

	private double absorbance_co2_init = 0;
	private double absorbance_o2_init = 0;
	private int index_initial;

	private double delta_abosrbance_co2_data = 0;
	private double delta_abosrbance_o2_data = 0;
	private double delta_time_data = 0;

	private double total_delta_time = 0;
	private double total_delta_absorbance_o2 = 0;
	private double total_delta_absorbance_co2 = 0;
	private boolean init_delta_all_zero_flag = false;

	private ArrayList<SavedRawData> savedRawData_list = new ArrayList<SavedRawData>();
	private ArrayList<SavedAbsorbData> savedAbsorbData_list = new ArrayList<SavedAbsorbData>();

	public ArrayList<Double> getDelta_absorbance_co2_list() {
		return delta_absorbance_co2_list;
	}

	public void setDelta_absorbance_co2_list(
			ArrayList<Double> delta_absorbance_co2_list) {
		this.delta_absorbance_co2_list = delta_absorbance_co2_list;
	}

	public ArrayList<Double> getDelta_absorbance_o2_list() {
		return delta_absorbance_o2_list;
	}

	public void setDelta_absorbance_o2_list(
			ArrayList<Double> delta_absorbance_o2_list) {
		this.delta_absorbance_o2_list = delta_absorbance_o2_list;
	}

	public ArrayList<Double> getDelta_time_list() {
		return delta_time_list;
	}

	public void setDelta_time_list(ArrayList<Double> delta_time_list) {
		this.delta_time_list = delta_time_list;
	}

	public double getTotal_delta_time() {
		return total_delta_time;
	}

	public void setTotal_delta_time(double total_delta_time) {
		this.total_delta_time = total_delta_time;
	}

	public double getTotal_delta_absorbance_o2() {
		return total_delta_absorbance_o2;
	}

	public void setTotal_delta_absorbance_o2(double total_delta_absorbance_o2) {
		this.total_delta_absorbance_o2 = total_delta_absorbance_o2;
	}

	public double getTotal_delta_absorbance_co2() {
		return total_delta_absorbance_co2;
	}

	public void setTotal_delta_absorbance_co2(double total_delta_absorbance_co2) {
		this.total_delta_absorbance_co2 = total_delta_absorbance_co2;
	}

	public double getAbsorbance_co2_init() {
		return absorbance_co2_init;
	}

	public void setAbsorbance_co2_init(double absorbance_co2_init) {
		this.absorbance_co2_init = absorbance_co2_init;
	}

	public int getIndex_initial() {
		return index_initial;
	}

	public void setIndex_initial(int index_initial) {
		this.index_initial = index_initial;
	}

	public ArrayList<SavedRawData> getSavedRawData_list() {
		return savedRawData_list;
	}

	public void setSavedRawData_list(ArrayList<SavedRawData> savedRawData_list) {
		this.savedRawData_list = savedRawData_list;
	}

	public ArrayList<SavedAbsorbData> getSavedAbsorbData_list() {
		return savedAbsorbData_list;
	}

	public void setSavedAbsorbData_list(
			ArrayList<SavedAbsorbData> savedAbsorbData_list) {
		this.savedAbsorbData_list = savedAbsorbData_list;
	}

	/**
	 * calculate every cycle's corrected absorbance data and time
	 * 
	 */
	public AbsorbanceCalc calcAbsor(DataTranslation data, int index,
			AbsorbanceCalc delta_data) {

		ArrayList<SavedRawData> savedRawData_list = delta_data
				.getSavedRawData_list();
		ArrayList<SavedAbsorbData> savedAbsorbData_list = delta_data
				.getSavedAbsorbData_list();

		SavedRawData savedRawData = new SavedRawData();
		SavedAbsorbData savedAbsorbData = new SavedAbsorbData();

		ArrayList<Double> volume_list = data.getVolume_list();
		ArrayList<Double> pd1_list = data.getPd1_list();
		ArrayList<Double> pd2_list = data.getPd2_list();
		ArrayList<Double> pd3_list = data.getPd3_list();
		ArrayList<Double> pd4_list = data.getPd4_list();

		// check cycle information
		if (index >= 2) {
			if (Math.round(volume_list.get(index)) == Math.round(volume_list
					.get(index - 1))) {
				// this condition means increasing volume starts to be flat
				if (increasing_flag == true) {
					// this situation is first cycle
					if (cycle_index_i == 0) {
						if (cycle_index_j < 4) {
							// ignore the increasing last cycle as one cycle
							// when
							// the plot
							// number is less than 4, so that discard 2 data,
							// still
							// have 2 to calculate delta
							delta_absorbance_co2_list.add(0, 0.0);
							delta_absorbance_o2_list.add(0, 0.0);
							delta_time_list.add(0, 0.0);

							// save all data as 0
							delta_data
									.setDelta_absorbance_co2_list(delta_absorbance_co2_list);
							delta_data
									.setDelta_absorbance_o2_list(delta_absorbance_o2_list);
							delta_data.setDelta_time_list(delta_time_list);

							/******************* csv file part **********************/
							// save absorbance data into savedAbsorbData
							// savedAbsorbData.setBreathSignal(0);
							// savedAbsorbData.setAbsorbance_O2(0);
							// savedAbsorbData.setAbsorbance_CO2(0);
							// savedAbsorbData.setDataPoints(0);
							// savedAbsorbData.setTime(0);
							// savedAbsorbData.setAbs_o2_int(0);
							// savedAbsorbData.setAbs_o2_final(0);
							// savedAbsorbData.setAbs_co2_int(0);
							// savedAbsorbData.setAbs_co2_final(0);
							// savedAbsorbData_list.add(cycle_index_i,
							// savedAbsorbData);
							// delta_data
							// .setSavedAbsorbData_list(savedAbsorbData_list);
							/******************* csv file part **********************/

							cycle_index_i++;
							cycle_index_j = 0;
							increasing_flag = false;
							abs_co2_X_list = new ArrayList<Double>();
							abs_o2_X_list = new ArrayList<Double>();

							init_delta_all_zero_flag = true;
						}
						else {
							// calculate delta time and delta absorbance data in
							// one
							// cycle (ingore the last two data)
							delta_abosrbance_co2_data = (abs_co2_X_list
									.get(cycle_index_j - 2) - absorbance_co2_init)
									/ absorbance_co2_init;
							delta_abosrbance_o2_data = abs_o2_X_list
									.get(cycle_index_j - 2)
									- absorbance_o2_init;
							delta_time_data = 0.25 * ((index - 2)
									- index_initial - 2);

							// add those delta data into arraylist which is
							// related
							// to different cycles
							delta_absorbance_co2_list.add(0,
									delta_abosrbance_co2_data);
							delta_absorbance_o2_list.add(0,
									delta_abosrbance_o2_data);
							delta_time_list.add(0, delta_time_data);

							delta_data
									.setDelta_absorbance_co2_list(delta_absorbance_co2_list);
							delta_data
									.setDelta_absorbance_o2_list(delta_absorbance_o2_list);
							delta_data.setDelta_time_list(delta_time_list);

							/******************* csv file part **********************/
							// // save absorbance data into savedAbsorbData
							// savedAbsorbData.setBreathSignal(0);
							// savedAbsorbData
							// .setAbsorbance_O2(delta_abosrbance_o2_data);
							// savedAbsorbData
							// .setAbsorbance_CO2(delta_abosrbance_co2_data);
							// savedAbsorbData.setDataPoints((index - 2)
							// - index_initial - 2);
							// savedAbsorbData.setTime(delta_time_data);
							// savedAbsorbData.setAbs_o2_int(absorbance_o2_init);
							// savedAbsorbData.setAbs_o2_final(abs_o2_X_list
							// .get(cycle_index_j - 2));
							// savedAbsorbData.setAbs_co2_int(absorbance_co2_init);
							// savedAbsorbData.setAbs_co2_final(abs_co2_X_list
							// .get(cycle_index_j - 2));
							// savedAbsorbData_list.add(cycle_index_i,
							// savedAbsorbData);
							// delta_data
							// .setSavedAbsorbData_list(savedAbsorbData_list);
							/******************* csv file part **********************/

							cycle_index_i++;
							cycle_index_j = 0;
							increasing_flag = false;
							abs_co2_X_list = new ArrayList<Double>();
							abs_o2_X_list = new ArrayList<Double>();
						}
					}
					// this situation is regular cycle
					else if (cycle_index_j < 5) {
						// ignore the increasing part as one cycle when the plot
						// number is less than 6, so that discard 4 data, still
						// have 2 to calculate delta
						cycle_index_j = 0;
						increasing_flag = false;
						abs_co2_X_list = new ArrayList<Double>();
						abs_o2_X_list = new ArrayList<Double>();
					}
					else {
						// calculate delta time and delta absorbance data in
						// one
						// cycle (ingore the first and last data)
						delta_abosrbance_co2_data = (abs_co2_X_list
								.get(cycle_index_j - 2) - abs_co2_X_list.get(2))
								/ absorbance_co2_init;
						delta_abosrbance_o2_data = abs_o2_X_list
								.get(cycle_index_j - 2) - abs_o2_X_list.get(2);
						delta_time_data = 0.25 * ((cycle_index_j - 2) - 2);

						// add those delta data into arraylist which is
						// related
						// to different cycles
						delta_absorbance_co2_list.add(cycle_index_i,
								delta_abosrbance_co2_data);
						delta_absorbance_o2_list.add(cycle_index_i,
								delta_abosrbance_o2_data);
						delta_time_list.add(cycle_index_i, delta_time_data);

						delta_data
								.setDelta_absorbance_co2_list(delta_absorbance_co2_list);
						delta_data
								.setDelta_absorbance_o2_list(delta_absorbance_o2_list);
						delta_data.setDelta_time_list(delta_time_list);

						/******************* csv file part **********************/
						// // save absorbance data into savedAbsorbData
						// savedAbsorbData.setBreathSignal(cycle_index_i);
						// savedAbsorbData
						// .setAbsorbance_O2(delta_abosrbance_o2_data);
						// savedAbsorbData
						// .setAbsorbance_CO2(delta_abosrbance_co2_data);
						// savedAbsorbData.setDataPoints(cycle_index_j - 2 - 2);
						// savedAbsorbData.setTime(delta_time_data);
						// savedAbsorbData.setAbs_o2_int(abs_o2_X_list.get(2));
						// savedAbsorbData.setAbs_o2_final(abs_o2_X_list
						// .get(cycle_index_j - 2));
						// savedAbsorbData.setAbs_co2_int(abs_co2_X_list.get(2));
						// savedAbsorbData.setAbs_co2_final(abs_co2_X_list
						// .get(cycle_index_j - 2));
						// savedAbsorbData_list
						// .add(cycle_index_i, savedAbsorbData);
						// delta_data
						// .setSavedAbsorbData_list(savedAbsorbData_list);
						/******************* csv file part **********************/

						cycle_index_i++;
						cycle_index_j = 0;
						increasing_flag = false;
						abs_co2_X_list = new ArrayList<Double>();
						abs_o2_X_list = new ArrayList<Double>();
					}
				}
			}
			else {
				// consider the last cycle's situation, set last status = 0 as
				// the last point, if this cycle is less than 4， set the
				// existing
				// list as empty;
				if (data.getStatus() == 1) {
					int length = abs_co2_X_list.size();
					if (length < 4) {
						abs_co2_X_list = new ArrayList<Double>();
						abs_o2_X_list = new ArrayList<Double>();
						cycle_index_j = 0;
					}
					// make a record on last group of data
					// PAY ATTENTION: here is cycle_index_j - 3, due to we
					// already use status= 1 this point as the final, we must
					// use last status = 0 point as final one, so we minus 1
					// more
					else {
						// add two more data in last cycle. don't know why all
						// the time miss two point in the last cycle. Just use
						// this way to solve it.
						abs_co2_X_list.add(
								length,
								Math.log10(pd4_list.get(index - 1)
										/ pd2_list.get(index - 1)));
						abs_o2_X_list.add(
								length,
								Math.log10(pd1_list.get(index - 1)
										/ pd3_list.get(index - 1)));
						abs_co2_X_list.add(
								length + 1,
								Math.log10(pd4_list.get(index)
										/ pd2_list.get(index)));
						abs_o2_X_list.add(
								length + 1,
								Math.log10(pd1_list.get(index)
										/ pd3_list.get(index)));
						length = abs_o2_X_list.size();

						// calculate delta data now.
						delta_abosrbance_co2_data = (abs_co2_X_list
								.get(length - 2) - abs_co2_X_list.get(2))
								/ absorbance_co2_init;
						delta_abosrbance_o2_data = abs_o2_X_list
								.get(length - 2) - abs_o2_X_list.get(2);
						delta_time_data = 0.25 * ((length - 1) - 2);

						// add those delta data into arraylist which is
						// related
						// to different cycles
						delta_absorbance_co2_list.add(cycle_index_i,
								delta_abosrbance_co2_data);
						delta_absorbance_o2_list.add(cycle_index_i,
								delta_abosrbance_o2_data);
						delta_time_list.add(cycle_index_i, delta_time_data);

						delta_data
								.setDelta_absorbance_co2_list(delta_absorbance_co2_list);
						delta_data
								.setDelta_absorbance_o2_list(delta_absorbance_o2_list);
						delta_data.setDelta_time_list(delta_time_list);

						/******************* csv file part **********************/
						// save last group of absorbance data into
						// savedAbsorbData
						// savedAbsorbData.setBreathSignal(cycle_index_i);
						// savedAbsorbData
						// .setAbsorbance_O2(delta_abosrbance_o2_data);
						// savedAbsorbData
						// .setAbsorbance_CO2(delta_abosrbance_co2_data);
						// savedAbsorbData.setDataPoints(length - 1 - 2);
						// savedAbsorbData.setTime(delta_time_data);
						// savedAbsorbData.setAbs_o2_int(abs_o2_X_list.get(2));
						// savedAbsorbData.setAbs_o2_final(abs_o2_X_list
						// .get(length - 2));
						// savedAbsorbData.setAbs_co2_int(abs_co2_X_list.get(2));
						// savedAbsorbData.setAbs_co2_final(abs_co2_X_list
						// .get(length - 2));
						// savedAbsorbData_list
						// .add(cycle_index_i, savedAbsorbData);
						// delta_data
						// .setSavedAbsorbData_list(savedAbsorbData_list);
						/******************* csv file part **********************/
					}
				}
				else {
					// the first point should be the first volume larger than 5k
					if (Math.round(volume_list.get(index)) >= 5000
							&& Math.round(volume_list.get(index - 1)) < 5000) {

						// mark the initial index and absorbance_co2_init only
						// first time
						absorbance_co2_init = Math.log10(pd4_list
								.get(index - 1) / pd2_list.get(index - 1));
						index_initial = index - 1;
						absorbance_o2_init = Math.log10(pd1_list.get(index - 1)
								/ pd3_list.get(index - 1));
						delta_data.setAbsorbance_co2_init(absorbance_co2_init);
						delta_data.setIndex_initial(index_initial);
						Log.d(tag, "absorbance_o2_init = " + absorbance_o2_init);
						Log.d(tag, "absorbance_co2_init = "
								+ absorbance_co2_init);
						Log.d(tag, "index = " + index_initial);
					}

					abs_co2_X_list.add(
							cycle_index_j,
							Math.log10(pd4_list.get(index - 2)
									/ pd2_list.get(index - 2)));
					abs_o2_X_list.add(
							cycle_index_j,
							Math.log10(pd1_list.get(index - 2)
									/ pd3_list.get(index - 2)));

					cycle_index_j++;
					increasing_flag = true;
				}
			}
		}

		savedRawData.setIndex(index);
		savedRawData.setTime(getDateTime());
		savedRawData.setPd1(data.getPd1());
		savedRawData.setPd2(data.getPd2());
		savedRawData.setPd3(data.getPd3());
		savedRawData.setPd4(data.getPd4());
		savedRawData.setPresure(data.getPressure());
		savedRawData.setResolution(data.getResolution());
		savedRawData.setStatus(data.getStatus());
		savedRawData.setBreathFreq("NA");
		savedRawData.setAbsorbanceCO2(Math.log10(pd4_list.get(index)
				/ pd2_list.get(index)));
		savedRawData.setAbsorbanceO2(Math.log10(pd1_list.get(index)
				/ pd3_list.get(index)));
		savedRawData.setVolume(data.getVolume());
		savedRawData.setThermistor(data.getThermistor());

		savedRawData_list.add(index - 1, savedRawData);
		delta_data.setSavedRawData_list(savedRawData_list);

		return delta_data;
	}

	/**
	 * This function is used to calculate the total delta time, absorbance_o2
	 * and absorbance_co2
	 * 
	 * @return
	 */
	public AbsorbanceCalc calcDeltaData(DataTranslation data,
			AbsorbanceCalc absorbanceCalc, Context ctx) {

		AbsorbanceCalc total_delta_data = new AbsorbanceCalc();

		double status = data.getStatus();
		ArrayList<Double> delta_absorbance_co2_list = absorbanceCalc
				.getDelta_absorbance_co2_list();
		ArrayList<Double> delta_absorbance_o2_list = absorbanceCalc
				.getDelta_absorbance_o2_list();
		ArrayList<Double> delta_time_list = absorbanceCalc.getDelta_time_list();

		if (status == 1) {
			for (int i = 0; i < delta_time_list.size(); i++) {
				total_delta_time += delta_time_list.get(i);
				Log.d("aaaaaaaaaaa", delta_time_list.get(i).toString());
				total_delta_absorbance_co2 += delta_absorbance_co2_list.get(i);
				total_delta_absorbance_o2 += delta_absorbance_o2_list.get(i);
			}
		}

		total_delta_data
				.setTotal_delta_absorbance_co2(total_delta_absorbance_co2);
		total_delta_data
				.setTotal_delta_absorbance_o2(total_delta_absorbance_o2);
		total_delta_data.setTotal_delta_time(total_delta_time);

		/*********************** csv file ***********************/
		// SaveAbsorbDataFile.saveAbsorbanceData(ctx,
		// absorbanceCalc.getSavedAbsorbData_list());
		/*********************** csv file ***********************/

		return total_delta_data;
	}

	/**
	 * Used to time the data in Excel file. Called for every data point received
	 * in function readBytes()
	 */
	private static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
