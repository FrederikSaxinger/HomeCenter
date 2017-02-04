package com.philips.lighting.data;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.philips.lighting.gui.MenuPanel;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.sensor.PHSensor;

//läuft alle x sekunden und datet die wohnung ab... kein zugriff auf gui
public class StateUpdater {
	private int SECONDS = 10; // The delay in minutes
	private Timer timer;
	private TimerTask updater;

	private PHBridgeResourcesCache cache;
	private Wohnung wohnung;

	private List<PHLight> lights;
	private List<PHSensor> sensors;

	public StateUpdater(Wohnung wohnung, MenuPanel grundrissPanel) {
		this.wohnung = wohnung;
		this.cache = PHHueSDK.getInstance().getSelectedBridge().getResourceCache();

		timer = new Timer();

		updater = new TimerTask() {
			@Override
			public void run() {
				lights = cache.getAllLights();
				sensors = cache.getAllSensors();

				updateLightsOn();
				updateSensorsOn();
				grundrissPanel.repaint();
			}
		};

		timer.schedule(updater, 0, 1000 * SECONDS);
	}

	private void updateLightsOn() {
		wohnung.getFlur().light.lightOn = lights.get(wohnung.getFlur().light.cacheId).getLastKnownLightState().isOn();
		wohnung.getWc().light.lightOn = lights.get(wohnung.getWc().light.cacheId).getLastKnownLightState().isOn();
		wohnung.getBadezimmer().light.lightOn = lights.get(wohnung.getBadezimmer().light.cacheId)
				.getLastKnownLightState().isOn();
		wohnung.getEingang().light.lightOn = lights.get(wohnung.getEingang().light.cacheId).getLastKnownLightState()
				.isOn();
		wohnung.getAbstellkammerl().light.lightOn = lights.get(wohnung.getAbstellkammerl().light.cacheId)
				.getLastKnownLightState().isOn();
		wohnung.getSchlafzimmer().light.lightOn = lights.get(wohnung.getSchlafzimmer().light.cacheId)
				.getLastKnownLightState().isOn();
	}

	private void updateSensorsOn() {
		wohnung.getFlur().sensor.sensorOn = sensors.get(wohnung.getFlur().sensor.cacheId).getBaseConfiguration()
				.getOn();
		wohnung.getBadezimmer().sensor.sensorOn = sensors.get(wohnung.getBadezimmer().sensor.cacheId)
				.getBaseConfiguration().getOn();
		wohnung.getWc().sensor.sensorOn = sensors.get(wohnung.getWc().sensor.cacheId).getBaseConfiguration().getOn();
		wohnung.getEingang().sensor.sensorOn = sensors.get(wohnung.getEingang().sensor.cacheId).getBaseConfiguration()
				.getOn();
	}
}
