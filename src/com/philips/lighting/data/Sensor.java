package com.philips.lighting.data;

import java.util.List;

import com.philips.lighting.model.sensor.PHSensor;

public class Sensor {

	public int sensorId;
	public int battery;
	public Boolean isOn;
	public int cacheId;
	private PHSensor phsensor;

	public Sensor(List<PHSensor> sensors, int cacheId, int sensorId) {
		this.cacheId = cacheId;
		this.sensorId = sensorId;
		if (sensors != null) {
			this.phsensor = sensors.get(cacheId);
			this.isOn = phsensor.getBaseConfiguration().getOn();
			this.battery = phsensor.getBaseConfiguration().getBattery();
		} else {
			this.isOn = false;
			this.battery = 0;
		}
	}
}
