package co.breezing.module.four.guidance;

import java.util.HashMap;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

/**
 * This class provides musical guidance for breathing and adapting the tempo of
 * music in accordance to breathing frequency
 */
public class Biofeedback {

	private SoundPool mSoundPool;
	private HashMap<Integer, Integer> mSoundPoolMap;
	private AudioManager mAudioManager;
	private Context mContext;
	public static int streamID;
	private static String tag = "Biofeedback";

	public Biofeedback() {

	}

	/** Initializes the soundpool */
	public void initSounds(Context theContext) {
		mContext = theContext;
		mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		mSoundPoolMap = new HashMap<Integer, Integer>();
		mAudioManager = (AudioManager) mContext
				.getSystemService(Context.AUDIO_SERVICE);
	}

	public void addSound(int Index, int SoundID) {
		mSoundPoolMap.put(Index, mSoundPool.load(mContext, SoundID, 1));
	}

	/**
	 * For playing the music continuously until stopSound() function is called
	 * Volume input is in the range 0-10, index is for the music id number and
	 * rate is for the frequency at which the music file is played.
	 * 
	 * @param index
	 * @param rate
	 * @param volume
	 */
	public void playSound(int index, float rate, int volume) {
		float streamVolume = (float) (volume * 0.1); // Range 0 to 1 -> max vol
														// at 1
		streamID = mSoundPool.play(mSoundPoolMap.get(index), streamVolume,
				streamVolume, 1, -1, rate);
		Log.d(tag, "Stream ID : " + streamID);
	}

	/** For playing only one loop of the music file */
	public void playSoundOnce(int index, float rate, int volume) {
		float streamVolume = mAudioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamID = mSoundPool.play(mSoundPoolMap.get(index), streamVolume,
				streamVolume, 1, 1, rate);
		Log.d(tag, "Stream ID : " + streamID);
	}

	/** Changes the tempo of music */
	public void changePlayRate(float rate) {
		Log.d(tag, "Stream ID : " + streamID + ",  Rate :" + rate);
		mSoundPool.setRate(streamID, rate);
	}

	/** Stops the music */
	public void stopSound() {
		mSoundPool.stop(streamID);
		mSoundPool.release();
	}

}