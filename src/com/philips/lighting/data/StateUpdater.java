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
		wohnung.getFlur().lightOn = lights.get(wohnung.getFlur().lightIdInCache).getLastKnownLightState().isOn();
		wohnung.getWc().lightOn = lights.get(wohnung.getWc().lightIdInCache).getLastKnownLightState().isOn();
		wohnung.getBadezimmer().lightOn = lights.get(wohnung.getBadezimmer().lightIdInCache).getLastKnownLightState()
				.isOn();
		wohnung.getEingang().lightOn = lights.get(wohnung.getEingang().lightIdInCache).getLastKnownLightState().isOn();
		wohnung.getAbstellkammerl().lightOn = lights.get(wohnung.getAbstellkammerl().lightIdInCache)
				.getLastKnownLightState().isOn();
		wohnung.getSchlafzimmer().lightOn = lights.get(wohnung.getSchlafzimmer().lightIdInCache)
				.getLastKnownLightState().isOn();
	}

	private void updateSensorsOn() {
		wohnung.getFlur().sensorOn = sensors.get(wohnung.getFlur().sensorIdInCache).getBaseConfiguration().getOn();
		wohnung.getBadezimmer().sensorOn = sensors.get(wohnung.getBadezimmer().sensorIdInCache).getBaseConfiguration()
				.getOn();
		wohnung.getWc().sensorOn = sensors.get(wohnung.getWc().sensorIdInCache).getBaseConfiguration().getOn();
		wohnung.getEingang().sensorOn = sensors.get(wohnung.getEingang().sensorIdInCache).getBaseConfiguration()
				.getOn();
	}
}
