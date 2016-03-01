package co.breezing.math.round;

import java.text.DecimalFormat;

public class RoundData {
	
	/** Convert to X.Y */
	public static double roundOneDecimals(double d) {
		DecimalFormat newFormat = new DecimalFormat("#.#");
		return Double.valueOf(newFormat.format(d));
	}
	
	/** Convert to X.YY */
	public static double roundTwoDecimals(double d) {
		DecimalFormat newFormat = new DecimalFormat("#.##");
		return Double.valueOf(newFormat.format(d));
	}
	
	/** Converts to X.YYY */
	public static double roundThreeDecimals(double d) {
		DecimalFormat newFormat = new DecimalFormat("#.###");
		return Double.valueOf(newFormat.format(d));
	}
	
	/** Convert to X.YYYY */
	public static double roundFourDecimals(double d) {
		DecimalFormat newFormat = new DecimalFormat("#.####");
		return Double.valueOf(newFormat.format(d));
	}
}
