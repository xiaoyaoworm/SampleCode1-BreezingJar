package co.breezing.module.four.guidance;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import co.breezing.module.R;

public class Guidance{

	private static AnimationDrawable animation;

	private static String tag = "Guidance";

	public static Biofeedback biofeedback;

	/**
	 * Initializes the frames of animation. Each frame corresponds to a
	 * different image present in the drawable folder in resources. 3 cycles of
	 * breath can be shown in one screen.
	 */
	public void startAnimation(AnimationDrawable animation, Resources resources) {
		try {
			/* To setup the animation based on breathing frequency */
			String freq_str = BfAndTimeFromFile.getBfAndTimeFromFile()[3];
			int frequency = (int) Math.round((Double.valueOf(freq_str)));
			if (frequency == 0) {
				frequency = 15;
			}
			int t = (int) Math.ceil((60 * 3 * 1000) / (frequency * 31));
			animation.addFrame(resources.getDrawable(R.drawable.f1), t);
			animation.addFrame(resources.getDrawable(R.drawable.f2), t);
			animation.addFrame(resources.getDrawable(R.drawable.f3), t);
			animation.addFrame(resources.getDrawable(R.drawable.f4), t);
			animation.addFrame(resources.getDrawable(R.drawable.f5), t);
			animation.addFrame(resources.getDrawable(R.drawable.f6), t);
			animation.addFrame(resources.getDrawable(R.drawable.f7), t);
			animation.addFrame(resources.getDrawable(R.drawable.f8), t);
			animation.addFrame(resources.getDrawable(R.drawable.f9), t);
			animation.addFrame(resources.getDrawable(R.drawable.f10), t);
			animation.addFrame(resources.getDrawable(R.drawable.f11), t);
			animation.addFrame(resources.getDrawable(R.drawable.f12), t);
			animation.addFrame(resources.getDrawable(R.drawable.f13), t);
			animation.addFrame(resources.getDrawable(R.drawable.f14), t);
			animation.addFrame(resources.getDrawable(R.drawable.f15), t);
			animation.addFrame(resources.getDrawable(R.drawable.f16), t);
			animation.addFrame(resources.getDrawable(R.drawable.f17), t);
			animation.addFrame(resources.getDrawable(R.drawable.f18), t);
			animation.addFrame(resources.getDrawable(R.drawable.f19), t);
			animation.addFrame(resources.getDrawable(R.drawable.f20), t);
			animation.addFrame(resources.getDrawable(R.drawable.f21), t);
			animation.addFrame(resources.getDrawable(R.drawable.f22), t);
			animation.addFrame(resources.getDrawable(R.drawable.f24), t);
			animation.addFrame(resources.getDrawable(R.drawable.f25), t);
			animation.addFrame(resources.getDrawable(R.drawable.f26), t);
			animation.addFrame(resources.getDrawable(R.drawable.f27), t);
			animation.addFrame(resources.getDrawable(R.drawable.f28), t);
			animation.addFrame(resources.getDrawable(R.drawable.f29), t);
			animation.addFrame(resources.getDrawable(R.drawable.f30), t);
			animation.addFrame(resources.getDrawable(R.drawable.f31), t);

			animation.setOneShot(false);
		}
		catch (Exception e) {
			Log.d(tag, "Exception in Animation " + e);
		}
	}

	/** To start the feather animation */
	public class Starter implements Runnable {
		public void run() {
			animation.start();
		}
	}

	/**
	 * Set the rate of music based on breathing frequency of user obtained from
	 * database before starting the test
	 */
	public void startMusicalGuidance(Biofeedback biofeedback) {
		String freq_str = BfAndTimeFromFile.getBfAndTimeFromFile()[3];
		int frequency = (int) Math.round((Double.valueOf(freq_str)));
		if (frequency == 0) {
			frequency = 15;
		}
		try {
			int music_volume = 10;

			switch (frequency) {
			case 5:
				biofeedback.playSound(1, 0.33f, music_volume);
				break;
			case 6:
				biofeedback.playSound(1, 0.4f, music_volume);
				break;
			case 7:
				biofeedback.playSound(1, 0.47f, music_volume);
				break;
			case 8:
				biofeedback.playSound(1, 0.53f, music_volume);
				break;
			case 9:
				biofeedback.playSound(1, 0.6f, music_volume);
				break;
			case 10:
				biofeedback.playSound(1, 0.66f, music_volume);
				break;
			case 11:
				biofeedback.playSound(1, 0.73f, music_volume);
				break;
			case 12:
				biofeedback.playSound(1, 0.8f, music_volume);
				break;
			case 13:
				biofeedback.playSound(1, 0.87f, music_volume);
				break;
			case 14:
				biofeedback.playSound(1, 0.93f, music_volume);
				break;
			case 15:
				biofeedback.playSound(1, 1f, music_volume);
				break;
			case 16:
				biofeedback.playSound(1, 1.07f, music_volume);
				break;
			case 17:
				biofeedback.playSound(1, 1.14f, music_volume);
				break;
			case 18:
				biofeedback.playSound(1, 1.2f, music_volume);
				break;
			case 19:
				biofeedback.playSound(1, 1.27f, music_volume);
				break;
			case 20:
				biofeedback.playSound(1, 1.34f, music_volume);
				break;
			default:
				biofeedback.playSound(1, 1, music_volume);
				break;
			}
		}
		catch (Exception e) {
			Log.d(tag, "Exception in providing musical guidance for "
					+ frequency + " bpm " + e);
		}
	}
}
