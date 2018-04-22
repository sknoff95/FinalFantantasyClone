package javagames.sound;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javagames.util.ResourceLoader;

public class SoundEngine {

	private OneShotEvent oneShotClip;
	private LoopEvent loopClip;
	private RestartEvent restartClip;
	private OneShotEvent oneShotStream;
	private LoopEvent loopStream;
	private RestartEvent restartStream;
	private byte[] weaponBytes;
	private byte[] rainBytes;
	private String loaded;
	private String[] sound = { "weaponBytes", "rainBytes" };

	public void initialize() {
		InputStream in = ResourceLoader.load(SoundPlayerExample.class, "./res/assets/sound/WEAPON_scifi_fire_02.wav",
				"asdf");
		weaponBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./res/assets/sound/WEATHER_rain_medium_5k.wav", "asdf");
		rainBytes = readBytes(in);
		loadWaveFile(weaponBytes);
		loaded = "weapon";
	}

	private byte[] readBytes(InputStream in) {
		try {
			BufferedInputStream buf = new BufferedInputStream(in);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int read;
			while ((read = buf.read()) != -1) {
				out.write(read);
			}
			in.close();
			return out.toByteArray();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private void loadWaveFile(byte[] rawData) {
		shutDownClips();
		oneShotClip = new OneShotEvent(new BlockingClip(rawData));
		oneShotClip.initialize();
		loopClip = new LoopEvent(new BlockingClip(rawData));
		loopClip.initialize();
		restartClip = new RestartEvent(new BlockingClip(rawData));
		restartClip.initialize();
	}

	private void loadStreamFile(byte[] rawData) {
		oneShotStream = new OneShotEvent(new BlockingDataLine(rawData));
		oneShotStream.initialize();
		loopStream = new LoopEvent(new BlockingDataLine(rawData));
		loopStream.initialize();
		restartStream = new RestartEvent(new BlockingDataLine(rawData));
		restartStream.initialize();
	}

	public void shutDownClips() {
		if (oneShotClip != null)
			oneShotClip.shutDown();
		if (loopClip != null)
			loopClip.shutDown();
		if (restartClip != null)
			restartClip.shutDown();
		if (oneShotStream != null)
			oneShotStream.shutDown();
		if (loopStream != null)
			loopStream.shutDown();
		if (restartStream != null)
			restartStream.shutDown();
	}

	public void FireClip() {
		oneShotClip.fire();
	}

	public void ClipEnd() {
		oneShotClip.done();
	}

	public void FireLoop() {
		loopStream.fire();
	}

	public void LoopEnd() {
		loopStream.done();
	}

	public void LoadClip(int clip) {
		if (clip == 0) {
			loadWaveFile(weaponBytes);
		}
		if (clip == 1) {
			loadStreamFile(rainBytes);
		}
	}

	public void PlayClip(int clip) {

	}

	public static void main(String[] args) {
	}
}