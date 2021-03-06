package com.philips.lighting.data;

import java.awt.Point;

import javax.swing.ImageIcon;

public class Room {
	private final int ICON_SIZE = Constants.ICON_SIZE;
	public String name;
	public Light light;
	public Sensor sensor;
	public int[] roomCoord;

	public Point fieldCoord;

	public int X;
	public int Y;
	public int W;
	public int H;

	private ImageIcon icon_on;
	private ImageIcon icon_off;

	private ImageIcon icon_grund_on;
	private ImageIcon icon_grund_off;

	public Room(String name) {
		this.name = name;
		icon_on = new ImageIcon("resources/" + name + "_on_" + ICON_SIZE + ".png");
		icon_off = new ImageIcon("resources/" + name + "_off_" + ICON_SIZE + ".png");
		icon_grund_on = new ImageIcon("resources/" + name + "_grund_on.png");
		icon_grund_off = new ImageIcon("resources/" + name + "_grund_off.png");
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

	public ImageIcon getIcon_grund_on() {
		return icon_grund_on;
	}

	public ImageIcon getIcon_grund_off() {
		return icon_grund_off;
	}
}
