package com.philips.lighting;

import java.awt.geom.Point2D;

import org.json.hue.JSONObject;

import com.philips.lighting.data.Constants;
import com.philips.lighting.data.HueProperties;
import com.philips.lighting.data.Room;
import com.philips.lighting.gui.GrundrissMenuPanel;
import com.philips.lighting.gui.LightMenuPanel;
import com.philips.lighting.hue.listener.PHHTTPListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;

public class ControllerCustom {
	private LightMenuPanel lightPanel;
	private GrundrissMenuPanel grundrissPanel;

	private PHHueSDK phHueSDK;

	private String username;
	private String bridgeIP;

	private PHBridge bridge;
	PHBridgeResourcesCache cache;

	public ControllerCustom() {
		this.phHueSDK = PHHueSDK.getInstance();

		this.lightPanel = null;
		this.grundrissPanel = null;

		bridge = phHueSDK.getSelectedBridge();
		cache = bridge.getResourceCache();

		this.username = HueProperties.getUsername();
		this.bridgeIP = bridge.getResourceCache().getBridgeConfiguration().getIpAddress();

	}

	// TODO alle consolen ausgaben überarbeiten
	public void switchSensorState(Room room) {
		String url = "http://" + bridgeIP + "/api/" + username + "/sensors/" + room.sensor.sensorId;

		String json = "{\"on\": " + !room.sensor.isOn + "}";
		bridge.doHTTPPut(url + "/config", json, new PHHTTPListener() {

			@Override
			public void onHTTPResponse(String jsonResponse) {
				System.out.println(room.name + " RESPONSE : " + jsonResponse);
			}
		});

		room.sensor.isOn = !room.sensor.isOn;

		if (room.sensor.isOn) {
			switchOffLight(room);
		} else {
			switchOnLight(room);
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
				room.light.isOn = true;
				System.out.println("light on");
				lightPanel.repaint();
				grundrissPanel.repaint();
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
				room.light.isOn = false;
				room.light.brightness = 254;
				room.light.reglerbreite = Constants.REGLER_BREITE_INNEN;
				System.out.println("light off");
				lightPanel.repaint();
				grundrissPanel.repaint();
				bridge.updateLight(room.light.phlight, null);
			}
		};

		bridge.doHTTPPut(url, json, switchListener);
	}

	public void switchLight(Room room) {
		boolean on = room.light.isOn;
		if (on) {
			switchOffLight(room);
		} else {
			switchOnLight(room);
		}
	}

	public void getLightState(Room room) {
		String url = "http://" + bridgeIP + "/api/" + username + "/lights/" + room.light.lightId;

		PHHTTPListener switchListener = new PHHTTPListener() {
			@Override
			public void onHTTPResponse(String jsonResponse) {
				JSONObject object = new JSONObject(jsonResponse);
				object = object.getJSONObject("state");
				Boolean lightstate = object.get("on").toString() == "true";
				room.light.isOn = lightstate;
				lightPanel.repaint();
				System.out.println("GUI updaten - Verursacher: " + room.name);
			}
		};

		bridge.doHTTPGet(url, switchListener);
	}

	public void getSensorState(Room room) {
		String url = "http://" + bridgeIP + "/api/" + username + "/sensors/" + room.sensor.sensorId;

		PHHTTPListener switchListener = new PHHTTPListener() {
			@Override
			public void onHTTPResponse(String jsonResponse) {
				JSONObject object = new JSONObject(jsonResponse);
				object = object.getJSONObject("config");
				Boolean sensorstate = object.get("on").toString() == "true";
				room.sensor.isOn = sensorstate;
				System.out.println("systems reports sensor in " + room.name + " is " + room.sensor.isOn);
				grundrissPanel.repaint();
				System.out.println("GUI updaten - Verursacher: " + room.name);
			}
		};

		bridge.doHTTPGet(url, switchListener);
	}

	public void setLightBrightness(Room room, int brightness) {
		String url = "http://" + bridgeIP + "/api/" + username + "/lights/" + room.light.lightId + "/state";
		String json = "{\"on\": true, \"bri\": " + brightness + "}";

		PHHTTPListener switchListener = new PHHTTPListener() {

			@Override
			public void onHTTPResponse(String jsonResponse) {
				System.out.println("regler hat verstellt");
			}
		};

		bridge.doHTTPPut(url, json, switchListener);
	}

	public void setSceneWhite(String id, int lightId, int brightness, int whitness) {
		String url = "http://" + bridgeIP + "/api/" + username + "/scenes/" + id + "/lightstates/" + lightId;
		String json = "{\"on\": true, \"bri\": " + brightness + ",\"ct\": " + whitness + "}";

		PHHTTPListener switchListener = new PHHTTPListener() {

			@Override
			public void onHTTPResponse(String jsonResponse) {
				System.out.println(jsonResponse);
			}
		};

		bridge.doHTTPPut(url, json, switchListener);
	}

	public void setSceneColor(String id, int lightId, int brightness, Point2D.Double color) {
		String url = "http://" + bridgeIP + "/api/" + username + "/scenes/" + id + "/lightstates/" + lightId;
		String json = "{\"on\": true, \"bri\": " + brightness + ",\"xy\":[" + color.getX() + "," + color.getY() + "]}";

		PHHTTPListener switchListener = new PHHTTPListener() {

			@Override
			public void onHTTPResponse(String jsonResponse) {
				System.out.println(jsonResponse);
			}
		};

		bridge.doHTTPPut(url, json, switchListener);
	}

	public PHBridgeResourcesCache getCache() {
		return cache;
	}

	public void setLightPanel(LightMenuPanel lightPanel) {
		this.lightPanel = lightPanel;
	}

	public void setGrundrissPanel(GrundrissMenuPanel grundrissPanel) {
		this.grundrissPanel = grundrissPanel;
	}

}
