package ccc.interaction.global;

import javax.swing.JOptionPane;

import ccc.interaction.internalFeatures.soundBoard;
import ccc.mainComponent.globalID;

public class popUpBox extends globalInteractionService{

	public static boolean readLine = true;
	
	public static void infoBox(String stringText,Class<?> classObject, String title) {
			String currentMethod = new Throwable().getStackTrace()[0].getMethodName(); 
			javax.swing.JOptionPane defaultsPane = new javax.swing.JOptionPane();
			JOptionPane.showMessageDialog(defaultsPane, stringText, "Class : " + classObject.getName() + "."+ currentMethod + "." + title, javax.swing.JOptionPane.INFORMATION_MESSAGE);
	}
	
	@SuppressWarnings("static-access")
	public static void alertError(String alertMessage, String alertTitle) {
		javax.swing.JOptionPane alertPane = new javax.swing.JOptionPane();
		alertPane.showMessageDialog(alertPane, alertMessage, alertTitle, javax.swing.JOptionPane.ERROR_MESSAGE);
		alertPane.setVisible(true);
	}
	
	public static void alertError(String alertMessage, String alertTitle, boolean playSound) {
		globalID.pendSoundAlert();
		soundBoard.playBeepDefault(1);
		javax.swing.JOptionPane alertPane = new javax.swing.JOptionPane();
		alertPane.showMessageDialog(alertPane, alertMessage, alertTitle, javax.swing.JOptionPane.ERROR_MESSAGE);
		alertPane.setVisible(true);
	}
	
	@SuppressWarnings("static-access")
	public static void alertError(String alertMessage, String alertTitle, boolean playSound , Exception e) {
		globalID.pendSoundAlert();
		soundBoard.playBeepDefault(1);
		javax.swing.JOptionPane alertPane = new javax.swing.JOptionPane();
		alertPane.showMessageDialog(alertPane, e.toString(), alertTitle, javax.swing.JOptionPane.ERROR_MESSAGE);
		alertPane.setVisible(true);
		globalID.errorPopC++;
		globalID.addErrorCount(e);
	}
	
	@SuppressWarnings("static-access")
	public static void warningBox(String warningMessage, String warnTitle) {
		javax.swing.JOptionPane warningPane = new javax.swing.JOptionPane();
		warningPane.showMessageDialog(warningPane, warningMessage, warnTitle, javax.swing.JOptionPane.WARNING_MESSAGE);
		warningPane.setVisible(true);
	}

}
