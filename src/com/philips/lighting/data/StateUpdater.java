package com.philips.lighting.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.gui.MenuPanel;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.sensor.PHSensor;

//läuft alle x sekunden und datet die wohnung ab... kein zugriff auf gui
public class StateUpdater {
	private int SECONDS = 7; // The delay in minutes
	private Timer timer;
	private TimerTask updater;

	private PHBridgeResourcesCache cache;
	private Wohnung wohnung;
	private ControllerCustom controller;

	private List<PHLight> lights;
	private List<PHSensor> sensors;
	private List<Room> rooms;

	private Boolean newstate;

	public StateUpdater(ControllerCustom controller, Wohnung wohnung, MenuPanel menuPanel) {
		this.wohnung = wohnung;
		this.cache = PHHueSDK.getInstance().getSelectedBridge().getResourceCache();
		this.controller = controller;

		rooms = new LinkedList<Room>();
		rooms.add(wohnung.getBadezimmer());
		rooms.add(wohnung.getWc());
		rooms.add(wohnung.getSchlafzimmer());
		rooms.add(wohnung.getFlur());
		rooms.add(wohnung.getEingang());
		rooms.add(wohnung.getAbstellkammerl());

		timer = new Timer();

		updater = new TimerTask() {
			@Override
			public void run() {
				System.out.println("updating STATES");
				lights = cache.getAllLights();
				sensors = cache.getAllSensors();

				updateLightsOn();
				updateSensorsOn();
			}
		};

		timer.schedule(updater, 0, 1000 * SECONDS);
	}

	public void updateLightsOn() {
		for (Room room : rooms) {
			newstate = lights.get(room.light.cacheId).getLastKnownLightState().isOn();
			if (newstate != room.light.lightOn) {
				controller.getLightState(room);
			}
		}
	}

	public void updateSensorsOn() {
		wohnung.getFlur().sensor.sensorOn = sensors.get(wohnung.getFlur().sensor.cacheId).getBaseConfiguration()
				.getOn();
		wohnung.getBadezimmer().sensor.sensorOn = sensors.get(wohnung.getBadezimmer().sensor.cacheId)
				.getBaseConfiguration().getOn();
		wohnung.getWc().sensor.sensorOn = sensors.get(wohnung.getWc().sensor.cacheId).getBaseConfiguration().getOn();
		wohnung.getEingang().sensor.sensorOn = sensors.get(wohnung.getEingang().sensor.cacheId).getBaseConfiguration()
				.getOn();
	}
}
