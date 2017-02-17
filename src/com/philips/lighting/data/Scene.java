package com.philips.lighting.data;

import java.awt.Point;

public class Scene {

	public String id;
	public int brightness;
	public int lightId;
	public Point.Double color;

	public Scene(String id, int lightid, int brightness, Point.Double color) {
		this.id = id;
		this.brightness = brightness;
		this.lightId = lightid;
		this.color = color;
	}
}
