package co.breezing.module.two.pdcheck;

import co.breezing.module.seven.dataTranslation.DataTranslation;
import android.util.Log;

public class PDCheck {

	public static String tag = "PDcheck";

	public static int NO_CARTRIDGE = 0;
	public static int USED_CARTRIDGE = 1;
	public static int FLIPPED_CARTRIDGE = 2;
	public static int GOOD_CARTRIDGE = 3;
	public static int NO_TRAINING_CARTRIDGE = 4;

	private static int cartridge_status;

	public int getCartridge_status() {
		return cartridge_status;
	}

	public void setCartridge_status(int cartridge_status) {
		PDCheck.cartridge_status = cartridge_status;
	}

	/**
	 * Test if the cartridge under use is fresh or not based on the photodiode
	 * values.
	 */
	public static int check_cartridge(DataTranslation data,
			boolean training_flag) {
			//algorithm
			return 0;
	}
}
