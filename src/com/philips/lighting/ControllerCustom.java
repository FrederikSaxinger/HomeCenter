package com.philips.lighting;

import com.philips.lighting.data.HueProperties;
import com.philips.lighting.data.Room;
import com.philips.lighting.gui.LightMenuPanel;
import com.philips.lighting.hue.listener.PHHTTPListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;

public class ControllerCustom {
	private LightMenuPanel lightPanel;

	private PHHueSDK phHueSDK;
	private static final int MAX_HUE = 65535;
	private ControllerCustom instance;

	private String username;
	private String bridgeIP;

	private PHBridge bridge;
	PHBridgeResourcesCache cache;

	public ControllerCustom() {
		this.phHueSDK = PHHueSDK.getInstance();
		this.instance = this;

		this.lightPanel = null;

		bridge = phHueSDK.getSelectedBridge();
		cache = bridge.getResourceCache();

		this.username = HueProperties.getUsername();
		this.bridgeIP = bridge.getResourceCache().getBridgeConfiguration().getIpAddress();

	}

	public void switchSensorState(Room room) {
		String url = "http://" + bridgeIP + "/api/" + username + "/sensors/" + room.sensor.sensorId;

		String json = "{\"on\": " + !room.sensor.sensorOn + "}";
		bridge.doHTTPPut(url + "/config", json, new PHHTTPListener() {

			@Override
			public void onHTTPResponse(String jsonResponse) {
				System.out.println(room.name + " RESPONSE : " + jsonResponse);
			}
		});

		room.sensor.sensorOn = !room.sensor.sensorOn;

		if (room.sensor.sensorOn) {
			switchOffLight(room);
			room.sensorButton.setSelected(false);
		} else {
			switchOnLight(room);
			room.sensorButton.setSelected(true);
		}
	}

	public void switchOnLight(Room room) {
		String url = "http://" + bridgeIP + "/api/" + username + "/lights/" + room.light.lightId + "/state";
		String json = "{\"on\": true, \"bri\": 254}";

		PHHTTPListener switchListener = new PHHTTPListener() {

			@Override
			public void onHTTPResponse(String jsonResponse) {
				// System.out.println(room.name + " RESPONSE : " +
				// jsonResponse);
				room.light.lightOn = true;
				lightPanel.repaint();
			}
		};

		bridge.doHTTPPut(url, json, switchListener);
	}

	public void switchOffLight(Room room) {
		String url = "http://" + bridgeIP + "/api/" + username + "/lights/" + room.light.lightId + "/state";
		String json = "{\"on\": false}";

		PHHTTPListener switchListener = new PHHTTPListener() {

			@Override
			public void onHTTPResponse(String jsonResponse) {
				// System.out.println(room.name + " RESPONSE : " +
				// jsonResponse);
				room.light.lightOn = false;
				lightPanel.repaint();
			}
		};

		bridge.doHTTPPut(url, json, switchListener);
	}

	public void switchLight(Room room) {
		boolean on = room.light.lightOn;
		if (on) {
			switchOffLight(room);
		} else {
			switchOnLight(room);
		}
	}

	public PHBridgeResourcesCache getCache() {
		return cache;
	}

	public void setLightPanel(LightMenuPanel lightPanel) {
		this.lightPanel = lightPanel;
	}

}
