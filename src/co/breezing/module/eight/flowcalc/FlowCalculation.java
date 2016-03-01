package co.breezing.module.eight.flowcalc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import android.content.Context;
import android.util.Log;
import co.breezing.metabolism.parameter.Constant;
import co.breezing.module.nine.absorbcalc.AbsorbanceCalc;
import co.breezing.module.nine.absorbcalc.SavePressureDataFile;
import co.breezing.module.seven.dataTranslation.DataTranslation;

public class FlowCalculation {

	// algorithm
	public boolean checkCycle(DataTranslation data, int index) {
		// algorithm
		boolean whetherCycle = true;
		return whetherCycle;
	}

	public FlowCalculation calcRealSuggData() {
		// algorithm
		return null;
	}

	public FlowCalculation flowCalcuProcess(DataTranslation data,
			AbsorbanceCalc delta_data, int index, Context ctx, int time) {
		//algorithm
		FlowCalculation bf_Ve_Data = new FlowCalculation();
		return bf_Ve_Data;
	}

	private double getActualVolumeFactor(double min_thermistor) {
		// algorithm
		return 0;
	}
}
