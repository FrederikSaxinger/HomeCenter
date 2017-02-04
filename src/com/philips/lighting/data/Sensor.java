package com.philips.lighting.data;

import java.util.List;

import com.philips.lighting.model.sensor.PHSensor;

public class Sensor {

	public int sensorId;
	public int battery;
	public Boolean sensorOn;
	public int cacheId;
	private PHSensor phsensor;

	public Sensor(List<PHSensor> sensors, int cacheId, int sensorId) {
		this.phsensor = sensors.get(cacheId);
		this.cacheId = cacheId;
		this.sensorId = sensorId;
		this.sensorOn = phsensor.getBaseConfiguration().getOn();
		this.battery = phsensor.getBaseConfiguration().getBattery();
	}
}
