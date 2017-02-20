package com.philips.lighting.data;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.sensor.PHSensor;

public class Wohnung {
	ControllerCustom controller;

	private Room flur;
	private Room badezimmer;
	private Room wc;
	private Room schlafzimmer;
	private Room wohnzimmer;
	private Room kueche;
	private Room eingang;
	private Room abstellkammerl;

	private List<Room> rooms;

	final private int masterX = -10;
	final private int masterY = -10;
	final private int sensorButtonWidth = 50;
	final private int sensorButtonAbstand = 100;

	private ImageIcon motionSensorIcon;
	private ImageIcon motionSensorClickedIcon;

	private List<PHSensor> sensors;
	private List<PHLight> lights;

	public Wohnung(ControllerCustom controller) {
		this.controller = controller;

		motionSensorIcon = new ImageIcon("resources/motion_sensor.png");
		motionSensorClickedIcon = new ImageIcon("resources/motion_sensor_clicked.png");

		if (controller != null) {
			sensors = controller.getCache().getAllSensors();
			lights = controller.getCache().getAllLights();
		}

		// initialize rooms
		initializeFlur();
		initializeBadezimmer();
		initializeWC();
		initializeSchlafzimmer();
		initializeWohnzimmer();
		initializeKueche();
		initializeEingang();
		initializeAbstellkammerl();

		rooms = new LinkedList<Room>();
		rooms.add(badezimmer);
		rooms.add(wc);
		rooms.add(schlafzimmer);
		rooms.add(flur);
		rooms.add(eingang);
		rooms.add(abstellkammerl);
	}

	private void initializeSensorButton(JButton button) {
		button.setEnabled(true);
		button.setIcon(motionSensorIcon);
		button.setSelectedIcon(motionSensorClickedIcon);
		button.setBackground(new Color(0, 0, 0, 0));
		button.setFocusable(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		button.setBorder(emptyBorder);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(false);
	}

	public void switchLightOnState(Room room) {
		room.light.isOn = !room.light.isOn;
	}

	public Room getFlur() {
		return flur;
	}

	public Room getBadezimmer() {
		return badezimmer;
	}

	public Room getWc() {
		return wc;
	}

	public Room getSchlafzimmer() {
		return schlafzimmer;
	}

	public Room getWohnzimmer() {
		return wohnzimmer;
	}

	public Room getKueche() {
		return kueche;
	}

	public Room getEingang() {
		return eingang;
	}

	public Room getAbstellkammerl() {
		return abstellkammerl;
	}

	private void initializeFlur() {
		flur = new Room("Flur");
		flur.light = new Light(lights, 1, 2);
		flur.sensor = new Sensor(sensors, 3, 13);

		flur.X = masterX + 400;
		flur.Y = masterY + 200;
		flur.W = 200;
		flur.H = 70;

		flur.setFeld(Constants.FRAME_BORDER_HORIZONTAL, Constants.FRAME_BORDER_VERTICAL);
	}

	private void initializeBadezimmer() {
		badezimmer = new Room("Badezimmer");
		badezimmer.light = new Light(lights, 3, 4);
		badezimmer.sensor = new Sensor(sensors, 15, 9);

		badezimmer.W = 125;
		badezimmer.H = 125;
		badezimmer.X = flur.X + flur.W - badezimmer.W;
		badezimmer.Y = flur.Y - badezimmer.H;

		badezimmer.setFeld(Constants.FRAME_BORDER_HORIZONTAL + Constants.FIELD_SIZE + Constants.FIELD_BORDER_HORIZONTAL,
				Constants.FRAME_BORDER_VERTICAL);
	}

	private void initializeWC() {
		wc = new Room("WC");
		wc.light = new Light(lights, 0, 1);
		wc.sensor = new Sensor(sensors, 12, 5);

		wc.W = flur.W - badezimmer.W;
		wc.H = 75;
		wc.X = flur.X;
		wc.Y = badezimmer.Y + badezimmer.H - wc.H;

		wc.setFeld(Constants.FRAME_BORDER_HORIZONTAL,
				Constants.FRAME_BORDER_VERTICAL + 2 * Constants.FIELD_SIZE + 2 * Constants.FIELD_BORDER_VERTICAL);
	}

	private void initializeSchlafzimmer() {
		schlafzimmer = new Room("Schlafzimmer");
		schlafzimmer.light = new Light(lights, 2, 3);

		schlafzimmer.W = flur.W;
		schlafzimmer.H = 150;
		schlafzimmer.X = flur.X;
		schlafzimmer.Y = flur.Y + flur.H;

		schlafzimmer.setFeld(
				Constants.FRAME_BORDER_HORIZONTAL + 2 * Constants.FIELD_SIZE + 2 * Constants.FIELD_BORDER_HORIZONTAL,
				Constants.FRAME_BORDER_VERTICAL);
	}

	private void initializeWohnzimmer() {
		wohnzimmer = new Room("Wohnzimmer");

		wohnzimmer.W = 270;
		wohnzimmer.H = flur.H + schlafzimmer.H;
		wohnzimmer.X = flur.X - wohnzimmer.W;
		wohnzimmer.Y = flur.Y;
	}

	private void initializeKueche() {
		kueche = new Room("Kueche");
		kueche.W = 100;
		kueche.H = badezimmer.H;
		kueche.X = wohnzimmer.X + wohnzimmer.W - kueche.W;
		kueche.Y = badezimmer.Y;
	}

	private void initializeEingang() {
		eingang = new Room("Eingang");
		eingang.light = new Light(lights, 4, 5);
		eingang.sensor = new Sensor(sensors, 7, 18);

		eingang.W = 100;
		eingang.H = kueche.H;
		eingang.X = kueche.X - eingang.W;
		eingang.Y = kueche.Y;

		eingang.setFeld(Constants.FRAME_BORDER_HORIZONTAL + Constants.FIELD_SIZE + Constants.FIELD_BORDER_HORIZONTAL,
				Constants.FRAME_BORDER_VERTICAL + 2 * Constants.FIELD_SIZE + 2 * Constants.FIELD_BORDER_VERTICAL);
	}

	private void initializeAbstellkammerl() {
		abstellkammerl = new Room("Abstellkammerl");
		abstellkammerl.light = new Light(lights, 5, 6);

		abstellkammerl.W = wohnzimmer.W - kueche.W - eingang.W;
		abstellkammerl.H = kueche.H;
		abstellkammerl.X = wohnzimmer.X;
		abstellkammerl.Y = kueche.Y;

		abstellkammerl.setFeld(
				Constants.FRAME_BORDER_HORIZONTAL + 2 * Constants.FIELD_SIZE + 2 * Constants.FIELD_BORDER_HORIZONTAL,
				Constants.FRAME_BORDER_VERTICAL + 2 * Constants.FIELD_SIZE + 2 * Constants.FIELD_BORDER_VERTICAL);
	}

	public ControllerCustom getController() {
		return controller;
	}

	public List<Room> getRooms() {
		return rooms;
	}

}
