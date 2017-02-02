package com.philips.lighting;

import com.philips.lighting.data.HueProperties;
import com.philips.lighting.gui.DesktopView;
import com.philips.lighting.gui.OfflineFrame;
import com.philips.lighting.hue.sdk.PHHueSDK;

/**
 * HueDesktop.java An example Java/Swing Desktop application illustrating how to
 * connect to a bridge and change your Hue lights using a Java Desktop
 * Application.
 * 
 * For more information on programming for Hue see:
 * http://developers.meethue.com
 * 
 * Username: G0p3mmup0EkBfW9x8KiO7HZRR0I2FHrs7XAEmS9q
 *
 */
class OfflineMainFile {

	public static void main(String args[]) {
		new OfflineMainFile();
	}

	public OfflineMainFile() {

		// Set Up the View (A JFrame, MenuBar and Console).
		OfflineFrame desktopView = new OfflineFrame(null);

	}

}