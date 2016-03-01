package co.breezing.module.three.adaptionSuggestion;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import co.breezing.module.eight.flowcalc.FlowCalculation;
import co.breezing.module.five.adaptionReference.AdaptionReference;

public final class AdaptionSuggestion {

	private static String tag = "adaption_algorithm";

	//algorithm

	/*
	 * based on the input values, determine the suggestion of user breathing
	 * rhythm. 27 different suggestions in total.
	 */
	public String adaptionAlgorithm(FlowCalculation adaption_data,
			AdaptionReference adaption_ref) {
		//algorithm
		String adaptionSuggestion = null;
		Log.d(tag, adaptionSuggestion);
		return adaptionSuggestion;
	}

	public String adaptConcluAlgorithm(FlowCalculation adaption_data,
			AdaptionReference adaption_ref, Context ctx,
			FlowCalculation bf_ve_data) {
		// algorithm
		String msg = null;
		return msg;
	}
}
