package javagames.sound;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javagames.util.SimpleFramework;
import javagames.util.Utility;

public class SoundPlayerExample extends SimpleFramework {

	private SoundEngine SE = new SoundEngine();

	public SoundPlayerExample() {
		appWidth = 340;
		appHeight = 340;
		appSleep = 10L;
		appTitle = "Sound Player Example";
		appBackground = Color.WHITE;
		appFPSColor = Color.BLACK;
	}

	@Override
	protected void initialize() {
		super.initialize();
		SE.initialize();

	}

	@Override
	protected void processInput(float delta) {
		super.processInput(delta);
		if (keyboard.keyDownOnce(KeyEvent.VK_F1)) {
			SE.LoadClip(0);
		}
		if (keyboard.keyDownOnce(KeyEvent.VK_F2)) {
			SE.LoadClip(1);
		}
		if (keyboard.keyDownOnce(KeyEvent.VK_1)) {
			SE.FireClip();
		}
		if (keyboard.keyDownOnce(KeyEvent.VK_2)) {
			SE.ClipEnd();
		}
		if (keyboard.keyDownOnce(KeyEvent.VK_3)) {
			SE.FireLoop();
		}
		if (keyboard.keyDownOnce(KeyEvent.VK_4)) {
			SE.LoopEnd();
		}
		/*
		 * if (keyboard.keyDownOnce(KeyEvent.VK_5)) { restartClip.fire(); } if
		 * (keyboard.keyDownOnce(KeyEvent.VK_6)) { oneShotStream.fire(); } if
		 * (keyboard.keyDownOnce(KeyEvent.VK_7)) { oneShotStream.done(); } if
		 * (keyboard.keyDownOnce(KeyEvent.VK_8)) { loopStream.fire(); } if
		 * (keyboard.keyDownOnce(KeyEvent.VK_9)) { loopStream.done(); } if
		 * (keyboard.keyDownOnce(KeyEvent.VK_0)) { restartStream.fire(); }
		 */
	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);
		textPos = Utility.drawString(g, 20, textPos, "", "(F1) Load Weapon", "(F2) Load Rain", " loaded!", "",
				"(1) Fire Weapon (clip)", "(2) Cancel Weapon (clip)", "(3) Loop Rain (clip)", "(4) Stop Loop (clip)");
		// "(5) Reusable (clip)", "", "(6) Fire One Shot (stream)",
		// "(7) Cancel One Shot (stream)", "(8) Start Loop (stream)", "(9) Stop Loop
		// (stream)",
		// "(0) Reusable (stream)"
	}

	@Override
	protected void terminate() {
		super.terminate();
		SE.shutDownClips();
	}

	public static void main(String[] args) {
		launchApp(new SoundPlayerExample());
	}
}