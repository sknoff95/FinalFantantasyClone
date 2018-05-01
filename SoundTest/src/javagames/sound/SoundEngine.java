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
	private byte[] travelBytes;
	private byte[] battleBytes;
	private byte[] townBytes;
	private byte[] bottleBytes;
	private byte[] dpsBytes;
	private byte[] tankBytes;
	private byte[] fireballBytes;
	private byte[] goblinBytes;
	private byte[] healBytes;
	private byte[] reviveBytes;
	private byte[] slimeBytes;
	private byte[] wolfBytes;
	private byte[] coinBytes;
	private byte[] defensiveBytes;
	private String loaded;

	public void initialize() {
		InputStream in = ResourceLoader.load(SoundPlayerExample.class, "./resources/battle.wav", "asdf");
		battleBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/bottle.wav", "asdf");
		bottleBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/dpsswing.wav", "asdf");
		dpsBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/fireball.wav", "asdf");
		fireballBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/goblin.wav", "asdf");
		goblinBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/heal.wav", "asdf");
		healBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/lightning.wav", "asdf");
		reviveBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/slime.wav", "asdf");
		slimeBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/tankswing.wav", "asdf");
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/travel.wav", "asdf");
		travelBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/town.wav", "asdf");
		townBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/wolf.wav", "asdf");
		wolfBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/coin.wav", "asdf");
		coinBytes = readBytes(in);
		in = ResourceLoader.load(SoundPlayerExample.class, "./resources/warcry.wav", "asdf");
		defensiveBytes = readBytes(in);
		loaded = "loaded";
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
			loadStreamFile(travelBytes);
		}
		if (clip == 1) {
			loadStreamFile(battleBytes);
		}
		if (clip == 2) {
			loadStreamFile(townBytes);
		}
		if (clip == 3) {
			loadWaveFile(bottleBytes);
		}
		if (clip == 4) {
			loadWaveFile(dpsBytes);
		}
		if (clip == 5) {
			loadWaveFile(tankBytes);
		}
		if (clip == 6) {
			loadWaveFile(fireballBytes);
		}
		if (clip == 7) {
			loadWaveFile(goblinBytes);
		}
		if (clip == 8) {
			loadWaveFile(healBytes);
		}
		if (clip == 9) {
			loadWaveFile(reviveBytes);
		}
		if (clip == 10) {
			loadWaveFile(slimeBytes);
		}
		if (clip == 11) {
			loadWaveFile(wolfBytes);
		}
		if (clip == 12) {
			loadWaveFile(coinBytes);
		}
		if (clip == 13) {
		    loadWaveFile(defensiveBytes);
		}
	}

	public void PlayClip(int clip) {

	}

	public static void main(String[] args) {
	}
}
