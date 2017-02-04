package com.philips.lighting.data;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Room {
	private final int ICON_SIZE = 70;
	public String name;
	public Light light;
	public Sensor sensor;
	public int[] roomCoord;

	public JButton sensorButton;
	public int sensorButtonX;
	public int sensorButtonY;

	public Point fieldCoord;

	public int X;
	public int Y;
	public int W;
	public int H;

	private ImageIcon icon_on;
	private ImageIcon icon_off;

	public Room(String name) {
		this.name = name;
		icon_on = new ImageIcon("resources/" + name + "_on_" + ICON_SIZE + ".png");
		icon_off = new ImageIcon("resources/" + name + "_off_" + ICON_SIZE + ".png");
	}

	public void setFeld(int x, int y) {
		fieldCoord = new Point(x, y);
	}

	public ImageIcon getIcon_on() {
		return icon_on;
	}

	public ImageIcon getIcon_off() {
		return icon_off;
	}

}
