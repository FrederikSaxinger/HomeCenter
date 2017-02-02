package com.philips.lighting.data;

import javax.swing.JButton;

import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.sensor.PHSensor;

public class Room {
	public String name;
	public int sensorID;
	public int lightID;
	public int sensorBattery;
	public Boolean lightOn;
	public Boolean sensorOn;

	public int[] feld;

	public int X;
	public int Y;
	public int W;
	public int H;

	public JButton sensorButton;
	public int sensorButtonX;
	public int sensorButtonY;

	public int sensorIdInCache;
	public int lightIdInCache;

	private PHSensor sensorCache;
	private PHLight lightCache;

	public PHSensor getSensorCache() {
		return sensorCache;
	}

	public void setSensorCache(PHSensor sensorCache) {
		this.sensorCache = sensorCache;
	}

	public PHLight getLightCache() {
		return lightCache;
	}

	public void setLightCache(PHLight lightCache) {
		this.lightCache = lightCache;
	}

	public void setFeld(int x, int y) {
		feld = new int[2];
		feld[0] = x;
		feld[1] = y;
	}
}
