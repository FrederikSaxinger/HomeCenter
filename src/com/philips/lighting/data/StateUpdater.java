package com.philips.lighting.data;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.gui.MenuPanel;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.sensor.PHSensor;

//l�uft alle x sekunden und datet die wohnung ab... kein zugriff auf gui
public class StateUpdater {
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

		this.rooms = wohnung.getRooms();

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

		timer.schedule(updater, 0, 1000 * Constants.SECONDS_STATEUPDATER);
	}

	public void updateLightsOn() {
		for (Room room : rooms) {
			newstate = lights.get(room.light.cacheId).getLastKnownLightState().isOn();
			if (newstate != room.light.isOn) {
				controller.getLightState(room);
			}
		}
	}

	public void updateSensorsOn() {
		// TODO wie bei licht eine abfrage einbauen
		for (Room room : rooms) {
			if (room.sensor != null) {
				newstate = sensors.get(room.sensor.cacheId).getBaseConfiguration().getOn();
				if (newstate != room.sensor.isOn) {
					controller.getSensorState(room);
				}
			}
		}
		wohnung.getFlur().sensor.isOn = sensors.get(wohnung.getFlur().sensor.cacheId).getBaseConfiguration().getOn();
		wohnung.getBadezimmer().sensor.isOn = sensors.get(wohnung.getBadezimmer().sensor.cacheId).getBaseConfiguration()
				.getOn();
		wohnung.getWc().sensor.isOn = sensors.get(wohnung.getWc().sensor.cacheId).getBaseConfiguration().getOn();
		wohnung.getEingang().sensor.isOn = sensors.get(wohnung.getEingang().sensor.cacheId).getBaseConfiguration()
				.getOn();
	}
}
