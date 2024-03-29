package ccc.interaction.internalFeatures;

import java.awt.Toolkit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.global.addons.external_freeTTS;
import ccc.mainComponent.globalID;

public class soundBoard{

	private static external_freeTTS readLine = new external_freeTTS();
	private external_freeTTS readLineExpr;
	private static boolean freeTTSRun = false;
	
	public static void playBeepDefault(int i) {
		if(globalID.alertCool>0) {
			if(i==1) {Toolkit.getDefaultToolkit().beep();}
			
			globalID.alertCool--;}}

	public static float RATE = 8000f;
	
	public static void tone(int hz, int msecs, double vol)
	{
		byte[] buf = new byte[1];
		AudioFormat audioSB = new AudioFormat(RATE,8,1,true,false);
				
		try {
			SourceDataLine sdl = AudioSystem.getSourceDataLine(audioSB);
		} catch (LineUnavailableException e) {
			autoNotificationPusher.notPush("ERROR", "Error Exception - Catched", e.toString(), Class.class.getClass(), "AUTO");
			e.printStackTrace();
		}
	}
	
	@Deprecated(since="08152019-undocumented")
	public static void readLineDefault(String text) {
		external_freeTTS.readLineDefault(text);
	}
	
	public void readLineDefaultExpr(String text) {
		readLineExpr = new external_freeTTS();
		readLineExpr.setText(text);
		readLineExpr.readText(text);
		;
	}
	
}
