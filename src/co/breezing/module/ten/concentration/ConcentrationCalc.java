package co.breezing.module.ten.concentration;

import co.breezing.module.nine.absorbcalc.AbsorbanceCalc;
import co.breezing.module.nine.absorbcalc.SavedAbsorbData;
import co.breezing.module.one.qrcode.QRcodeParse;

public class ConcentrationCalc {

	private static String tag = "Module 10: concentration data calculation";

	private double concen_o2;
	private double concen_co2;
	private SavedAbsorbData savedAbsorbData;

	public double getConcen_o2() {
		return concen_o2;
	}

	public void setConcen_o2(double concen_o2) {
		this.concen_o2 = concen_o2;
	}

	public double getConcen_co2() {
		return concen_co2;
	}

	public void setConcen_co2(double concen_co2) {
		this.concen_co2 = concen_co2;
	}

	public SavedAbsorbData getSavedAbsorbData() {
		return savedAbsorbData;
	}

	public void setSavedAbsorbData(SavedAbsorbData savedAbsorbData) {
		this.savedAbsorbData = savedAbsorbData;
	}

	/**
	 * calculate concentrate_o2 and concentrate_co2
	 * 
	 * @param qrData
	 * @param total_delta_data
	 * @return
	 */
	public ConcentrationCalc calcConcenData(QRcodeParse qrData,
			AbsorbanceCalc total_delta_data) {

		ConcentrationCalc concenData = new ConcentrationCalc();
		// algorithm
		return concenData;
	}

}
