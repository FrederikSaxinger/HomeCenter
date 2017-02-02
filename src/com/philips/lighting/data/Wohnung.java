package com.philips.lighting.data;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

	final private int masterX = 100;
	final private int masterY = 25;
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

		sensors = controller.getCache().getAllSensors();
		lights = controller.getCache().getAllLights();

		// initialize rooms
		initializeFlur();
		initializeBadezimmer();
		initializeWC();
		initializeSchlafzimmer();
		initializeWohnzimmer();
		initializeKueche();
		initializeEingang();
		initializeAbstellkammerl();

		// String lastUsername = HueProperties.getUsername();
		// String lastConnectedIPStr = HueProperties.getLastConnectedIP();
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
		room.lightOn = !room.lightOn;
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
		flur = new Room();
		flur.name = "Flur";

		flur.setSensorCache(sensors.get(3));
		flur.setLightCache(lights.get(1));
		flur.sensorIdInCache = 3;
		flur.lightIdInCache = 1;

		flur.sensorID = 13;
		flur.lightID = 2;
		flur.sensorBattery = flur.getSensorCache().getBaseConfiguration().getBattery();
		flur.sensorOn = flur.getSensorCache().getBaseConfiguration().getOn();
		flur.lightOn = flur.getLightCache().getLastKnownLightState().isOn();
		flur.X = masterX + 400;
		flur.Y = masterY + 200;
		flur.W = 200;
		flur.H = 70;

		flur.setFeld(350, 50);

		flur.sensorButtonX = 800;
		flur.sensorButtonY = 100;
		flur.sensorButton = new JButton();
		flur.sensorButton.setSelected(!flur.sensorOn);
		initializeSensorButton(flur.sensorButton);
		flur.sensorButton.setBounds(flur.sensorButtonX, flur.sensorButtonY, sensorButtonWidth, sensorButtonWidth);
		flur.sensorButton.removeMouseListener(flur.sensorButton.getMouseListeners()[0]);
		flur.sensorButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				controller.switchSensorState(flur);

			}
		});

		// flur.sensorButton.addActionListener(new ActionListener() {
		//
		// public void actionPerformed(ActionEvent arg0) {
		// controller.switchFlur();
		// }
		// });
	}

	private void initializeBadezimmer() {
		badezimmer = new Room();
		badezimmer.name = "Badezimmer";

		badezimmer.setSensorCache(sensors.get(15));
		badezimmer.setLightCache(lights.get(3));
		badezimmer.sensorIdInCache = 15;
		badezimmer.lightIdInCache = 3;

		badezimmer.sensorID = 9;
		badezimmer.lightID = 4;
		badezimmer.sensorBattery = badezimmer.getSensorCache().getBaseConfiguration().getBattery();
		badezimmer.sensorOn = badezimmer.getSensorCache().getBaseConfiguration().getOn();
		badezimmer.lightOn = badezimmer.getLightCache().getLastKnownLightState().isOn();
		badezimmer.W = 125;
		badezimmer.H = 125;
		badezimmer.X = flur.X + flur.W - badezimmer.W;
		badezimmer.Y = flur.Y - badezimmer.H;

		badezimmer.setFeld(50, 50);

		badezimmer.sensorButtonX = flur.sensorButtonX;
		badezimmer.sensorButtonY = flur.sensorButtonY + sensorButtonAbstand;
		badezimmer.sensorButton = new JButton();
		initializeSensorButton(badezimmer.sensorButton);
		badezimmer.sensorButton.setBounds(badezimmer.sensorButtonX, badezimmer.sensorButtonY, sensorButtonWidth,
				sensorButtonWidth);
		badezimmer.sensorButton.removeMouseListener(badezimmer.sensorButton.getMouseListeners()[0]);
		badezimmer.sensorButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				controller.switchSensorState(badezimmer);
			}
		});

	}

	private void initializeWC() {
		wc = new Room();
		wc.name = "WC";

		wc.setSensorCache(sensors.get(12));
		wc.setLightCache(lights.get(0));
		wc.sensorIdInCache = 12;
		wc.lightIdInCache = 0;

		wc.sensorID = 5;
		wc.lightID = 1;
		wc.sensorBattery = wc.getSensorCache().getBaseConfiguration().getBattery();
		wc.sensorOn = wc.getSensorCache().getBaseConfiguration().getOn();
		wc.lightOn = wc.getLightCache().getLastKnownLightState().isOn();
		wc.W = flur.W - badezimmer.W;
		wc.H = 75;
		wc.X = flur.X;
		wc.Y = badezimmer.Y + badezimmer.H - wc.H;

		wc.setFeld(350, 325);

		wc.sensorButtonX = flur.sensorButtonX;
		wc.sensorButtonY = flur.sensorButtonY + 2 * sensorButtonAbstand;
		wc.sensorButton = new JButton();
		initializeSensorButton(wc.sensorButton);
		wc.sensorButton.setBounds(wc.sensorButtonX, wc.sensorButtonY, sensorButtonWidth, sensorButtonWidth);
		wc.sensorButton.removeMouseListener(wc.sensorButton.getMouseListeners()[0]);
		wc.sensorButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				controller.switchSensorState(wc);

			}
		});
	}

	private void initializeSchlafzimmer() {
		schlafzimmer = new Room();
		schlafzimmer.name = "Schlafzimmer";

		schlafzimmer.setLightCache(lights.get(2));
		schlafzimmer.lightIdInCache = 2;

		schlafzimmer.lightOn = schlafzimmer.getLightCache().getLastKnownLightState().isOn();

		schlafzimmer.lightID = 3;

		schlafzimmer.W = flur.W;
		schlafzimmer.H = 150;
		schlafzimmer.X = flur.X;
		schlafzimmer.Y = flur.Y + flur.H;

		schlafzimmer.setFeld(50, 325);

		// TODO Button
	}

	private void initializeWohnzimmer() {
		wohnzimmer = new Room();
		wohnzimmer.name = "Wohnzimmer";

		wohnzimmer.W = 270;
		wohnzimmer.H = flur.H + schlafzimmer.H;
		wohnzimmer.X = flur.X - wohnzimmer.W;
		wohnzimmer.Y = flur.Y;
	}

	private void initializeKueche() {
		kueche = new Room();
		kueche.name = "Kueche";
		kueche.W = 100;
		kueche.H = badezimmer.H;
		kueche.X = wohnzimmer.X + wohnzimmer.W - kueche.W;
		kueche.Y = badezimmer.Y;
	}

	private void initializeEingang() {
		eingang = new Room();
		eingang.name = "Eingang";

		eingang.setSensorCache(sensors.get(7));
		eingang.setLightCache(lights.get(4));
		eingang.sensorIdInCache = 7;
		eingang.lightIdInCache = 4;

		eingang.sensorID = 18;
		eingang.lightID = 5;
		eingang.sensorBattery = eingang.getSensorCache().getBaseConfiguration().getBattery();
		eingang.sensorOn = eingang.getSensorCache().getBaseConfiguration().getOn();
		eingang.lightOn = eingang.getLightCache().getLastKnownLightState().isOn();
		eingang.W = 100;
		eingang.H = kueche.H;
		eingang.X = kueche.X - eingang.W;
		eingang.Y = kueche.Y;

		eingang.setFeld(650, 50);

		eingang.sensorButtonX = flur.sensorButtonX;
		eingang.sensorButtonY = flur.sensorButtonY + 3 * sensorButtonAbstand;
		eingang.sensorButton = new JButton();
		initializeSensorButton(eingang.sensorButton);
		eingang.sensorButton.setBounds(eingang.sensorButtonX, eingang.sensorButtonY, sensorButtonWidth,
				sensorButtonWidth);
		eingang.sensorButton.removeMouseListener(eingang.sensorButton.getMouseListeners()[0]);
		eingang.sensorButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				controller.switchSensorState(eingang);

			}
		});
	}

	private void initializeAbstellkammerl() {
		abstellkammerl = new Room();
		abstellkammerl.name = "Abstellkammerl";

		abstellkammerl.setLightCache(lights.get(5));
		abstellkammerl.lightIdInCache = 5;

		abstellkammerl.lightOn = abstellkammerl.getLightCache().getLastKnownLightState().isOn();

		abstellkammerl.lightID = 3;

		abstellkammerl.W = wohnzimmer.W - kueche.W - eingang.W;
		abstellkammerl.H = kueche.H;
		abstellkammerl.X = wohnzimmer.X;
		abstellkammerl.Y = kueche.Y;

		abstellkammerl.setFeld(650, 325);

		// TODO Button
	}

	public ControllerCustom getController() {
		return controller;
	}

}
