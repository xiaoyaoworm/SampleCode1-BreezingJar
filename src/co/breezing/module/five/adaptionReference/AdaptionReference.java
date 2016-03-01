package co.breezing.module.five.adaptionReference;

import android.util.Log;

public class AdaptionReference {

	private static AdaptionReference instance = new AdaptionReference();
	// adaption -- breath frequency reference minmum and maxmum value
	private double adaption_bf_ref_min;
	private double adaption_bf_ref_max;
	// adaption -- volume reference minmum and maxmum value
	private double adaption_etv_ref_min;
	private double adaption_etv_ref_max;
	// adaption -- ve reference minmum and maxmum value
	private double adaption_ve_ref_min;
	private double adaption_ve_ref_max;

	private static String tag = "AdaptionReference";

	public double getAdaption_bf_ref_min() {
		return adaption_bf_ref_min;
	}

	public void setAdaption_bf_ref_min(double adaption_bf_ref_min) {
		this.adaption_bf_ref_min = adaption_bf_ref_min;
	}

	public double getAdaption_bf_ref_max() {
		return adaption_bf_ref_max;
	}

	public void setAdaption_bf_ref_max(double adaption_bf_ref_max) {
		this.adaption_bf_ref_max = adaption_bf_ref_max;
	}

	public double getAdaption_etv_ref_min() {
		return adaption_etv_ref_min;
	}

	public void setAdaption_etv_ref_min(double adaption_etv_ref_min) {
		this.adaption_etv_ref_min = adaption_etv_ref_min;
	}

	public double getAdaption_etv_ref_max() {
		return adaption_etv_ref_max;
	}

	public void setAdaption_etv_ref_max(double adaption_etv_ref_max) {
		this.adaption_etv_ref_max = adaption_etv_ref_max;
	}

	public double getAdaption_ve_ref_min() {
		return adaption_ve_ref_min;
	}

	public void setAdaption_ve_ref_min(double adaption_ve_ref_min) {
		this.adaption_ve_ref_min = adaption_ve_ref_min;
	}

	public double getAdaption_ve_ref_max() {
		return adaption_ve_ref_max;
	}

	public void setAdaption_ve_ref_max(double adaption_ve_ref_max) {
		this.adaption_ve_ref_max = adaption_ve_ref_max;
	}

	public static AdaptionReference getInstance() {
		return instance;
	}

	public static void setInstance(AdaptionReference instance) {
		AdaptionReference.instance = instance;
	}

	/**
	 * Breathing frequency (bpm): Minimum = 5, Maximum = 20 ETV: Use table based
	 * on Gender and Age Ve (flow/min): Minimum = 2692 (male), 2220 (female)
	 * Maximum = [(66.5 + 13.8 * weight_kg) + (5.0 * height_cm) - (6.8 *
	 * age_year)]*2/0.30 (male) [(665.1 + 9.6 * weight_kg) + (1.8 * height_cm) -
	 * (4.7 * age_year)]*2/0.30 (female)
	 */

	public AdaptionReference adaptionRefAlgo(double weight_kg, double height_cm,
			String gender, int age) {
		
		AdaptionReference adaptionReference = new AdaptionReference();
		
		// breathing frequency reference: 5~20
		adaption_bf_ref_min = 5;
		adaption_bf_ref_max = 20;
		Log.d(tag, "Normal range of bf is: " + adaption_bf_ref_min + " ~ "
				+ adaption_bf_ref_max);

		// etv reference:
		int gender_index = 0;
		int age_index = 0;
		int[][] etv_ref_min_table = {
				{ 360, 400, 370, 400, 480, 520, 510, 560, 440, 370, 520, 490,
						390, 490, 430 },
				{ 360, 350, 410, 270, 450, 370, 340, 400, 450, 390, 450, 310,
						280, 400, 320 } };
		int[][] etv_ref_max_table = {
				{ 670, 750, 880, 900, 960, 1160, 1030, 1200, 1230, 1140, 1180,
						1100, 1100, 1400, 780 },
				{ 640, 470, 800, 650, 670, 900, 570, 670, 690, 930, 1300, 750,
						820, 860, 750 } };

		if (gender.equals("male")) {
			gender_index = 0;
		}
		else if (gender.equals("female")) {
			gender_index = 1;
		}
		if (age == 11) {
			age_index = 0;
		}
		else if (age == 12) {
			age_index = 1;
		}
		else if (age == 13) {
			age_index = 2;
		}
		else if (age == 14) {
			age_index = 3;
		}
		else if (age == 15) {
			age_index = 4;
		}
		else if (age == 16) {
			age_index = 5;
		}
		else if (age == 17) {
			age_index = 6;
		}
		else if (age == 18) {
			age_index = 7;
		}
		else if (age == 19) {
			age_index = 8;
		}
		else if (age >= 20 && age < 30) {
			age_index = 9;
		}
		else if (age >= 30 && age < 40) {
			age_index = 10;
		}
		else if (age >= 40 && age < 50) {
			age_index = 11;
		}
		else if (age >= 50 && age < 60) {
			age_index = 12;
		}
		else if (age >= 60 && age < 70) {
			age_index = 13;
		}
		else if (age >= 70) {
			age_index = 14;
		}
		adaption_etv_ref_min = etv_ref_min_table[gender_index][age_index];
		adaption_etv_ref_max = etv_ref_max_table[gender_index][age_index];
		Log.d(tag, "Normal range of etv is: " + adaption_etv_ref_min + " ~ "
				+ adaption_etv_ref_max);

		// ve reference:
		if (gender.equals("male")) {
			adaption_ve_ref_min = 2692;
			adaption_ve_ref_max = ((66.5 + 13.8 * weight_kg)
					+ (5.0 * height_cm) - (6.8 * age)) * 2 / 0.30;
		}
		else if (gender.equals("female")) {
			adaption_ve_ref_min = 2220;
			adaption_ve_ref_max = ((665.1 + 9.6 * weight_kg)
					+ (1.8 * height_cm) - (4.7 * age)) * 2 / 0.30;
		}
		Log.d(tag, "Normal range of ve is: " + adaption_ve_ref_min + " ~ "
				+ adaption_ve_ref_max);

		adaptionReference.setAdaption_bf_ref_max(adaption_bf_ref_max);
		adaptionReference.setAdaption_bf_ref_min(adaption_bf_ref_min);
		adaptionReference.setAdaption_etv_ref_max(adaption_etv_ref_max);
		adaptionReference.setAdaption_etv_ref_min(adaption_etv_ref_min);
		adaptionReference.setAdaption_ve_ref_max(adaption_ve_ref_max);
		adaptionReference.setAdaption_ve_ref_min(adaption_ve_ref_min);
		
		return adaptionReference;
	}
	
}
