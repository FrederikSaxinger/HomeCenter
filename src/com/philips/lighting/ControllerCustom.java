package com.philips.lighting;

import com.philips.lighting.data.HueProperties;
import com.philips.lighting.data.Room;
import com.philips.lighting.hue.listener.PHHTTPListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;

public class ControllerCustom {

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

		bridge = phHueSDK.getSelectedBridge();
		cache = bridge.getResourceCache();

		this.username = HueProperties.getUsername();
		this.bridgeIP = bridge.getResourceCache().getBridgeConfiguration().getIpAddress();

	}

	public void switchSensorState(Room room) {
		String url = "http://" + bridgeIP + "/api/" + username + "/sensors/" + room.sensorID;

		String json = "{\"on\": " + !room.sensorOn + "}";
		bridge.doHTTPPut(url + "/config", json, new PHHTTPListener() {

			@Override
			public void onHTTPResponse(String jsonResponse) {
				System.out.println(room.name + " RESPONSE : " + jsonResponse);
			}
		});

		room.sensorOn = !room.sensorOn;

		if (room.sensorOn) {
			switchOffLight(room);
			room.sensorButton.setSelected(false);
		} else {
			switchOnLight(room);
			room.sensorButton.setSelected(true);
		}
	}

	public void switchOnLight(Room room) {
		String url = "http://" + bridgeIP + "/api/" + username + "/lights/" + room.lightID + "/state";
		String json = "{\"on\": true}";

		PHHTTPListener switchListener = new PHHTTPListener() {

			@Override
			public void onHTTPResponse(String jsonResponse) {
				System.out.println(room.name + " RESPONSE : " + jsonResponse);
			}
		};

		bridge.doHTTPPut(url, json, switchListener);

		room.lightOn = true;
	}

	public void switchOffLight(Room room) {
		String url = "http://" + bridgeIP + "/api/" + username + "/lights/" + room.lightID + "/state";
		String json = "{\"on\": false}";

		PHHTTPListener switchListener = new PHHTTPListener() {

			@Override
			public void onHTTPResponse(String jsonResponse) {
				System.out.println(room.name + " RESPONSE : " + jsonResponse);
			}
		};

		bridge.doHTTPPut(url, json, switchListener);

		room.lightOn = false;
	}

	public void switchLight(Room room) {
		boolean on = room.lightOn;
		if (on) {
			switchOffLight(room);
		} else {
			switchOnLight(room);
		}
	}

	public PHBridgeResourcesCache getCache() {
		return cache;
	}

}
