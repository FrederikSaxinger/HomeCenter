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
		this.cacheId = cacheId;
		this.sensorId = sensorId;
		if (sensors != null) {
			this.phsensor = sensors.get(cacheId);
			this.sensorOn = phsensor.getBaseConfiguration().getOn();
			this.battery = phsensor.getBaseConfiguration().getBattery();
		} else {
			this.sensorOn = false;
			this.battery = 0;
		}
	}
}
