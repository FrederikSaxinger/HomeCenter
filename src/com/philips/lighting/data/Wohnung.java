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

	final private int FRAME_BORDER_VERTICAL = Constants.FRAME_BORDER_VERTICAL;
	final private int FRAME_BORDER_HORIZONTAL = Constants.FRAME_BORDER_HORIZONTAL;
	final private int FIELD_SIZE = Constants.FIELD_SIZE;
	final private int FIELD_BORDER_VERTIACAL = Constants.FIELD_BORDER_VERTICAL;
	final private int FIELD_BORDER_HORIZONTAL = Constants.FIELD_BORDER_HORIZONTAL;

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
		room.light.lightOn = !room.light.lightOn;
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

		flur.setFeld(FRAME_BORDER_HORIZONTAL, FRAME_BORDER_VERTICAL);

		flur.sensorButtonX = 800;
		flur.sensorButtonY = 100;
		flur.sensorButton = new JButton();
		flur.sensorButton.setSelected(!flur.sensor.sensorOn);
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
		badezimmer = new Room("Badezimmer");
		badezimmer.light = new Light(lights, 3, 4);
		badezimmer.sensor = new Sensor(sensors, 15, 9);

		badezimmer.W = 125;
		badezimmer.H = 125;
		badezimmer.X = flur.X + flur.W - badezimmer.W;
		badezimmer.Y = flur.Y - badezimmer.H;

		badezimmer.setFeld(FRAME_BORDER_HORIZONTAL + FIELD_SIZE + FIELD_BORDER_HORIZONTAL, FRAME_BORDER_VERTICAL);

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
		wc = new Room("WC");
		wc.light = new Light(lights, 0, 1);
		wc.sensor = new Sensor(sensors, 12, 5);

		wc.W = flur.W - badezimmer.W;
		wc.H = 75;
		wc.X = flur.X;
		wc.Y = badezimmer.Y + badezimmer.H - wc.H;

		wc.setFeld(FRAME_BORDER_HORIZONTAL, FRAME_BORDER_VERTICAL + 2 * FIELD_SIZE + 2 * FIELD_BORDER_VERTIACAL);

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
		schlafzimmer = new Room("Schlafzimmer");
		schlafzimmer.light = new Light(lights, 2, 3);

		schlafzimmer.W = flur.W;
		schlafzimmer.H = 150;
		schlafzimmer.X = flur.X;
		schlafzimmer.Y = flur.Y + flur.H;

		schlafzimmer.setFeld(FRAME_BORDER_HORIZONTAL + 2 * FIELD_SIZE + 2 * FIELD_BORDER_HORIZONTAL,
				FRAME_BORDER_VERTICAL);

		// TODO Button
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

		eingang.setFeld(FRAME_BORDER_HORIZONTAL + FIELD_SIZE + FIELD_BORDER_HORIZONTAL,
				FRAME_BORDER_VERTICAL + 2 * FIELD_SIZE + 2 * FIELD_BORDER_VERTIACAL);

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
		abstellkammerl = new Room("Abstellkammerl");
		abstellkammerl.light = new Light(lights, 5, 6);

		abstellkammerl.W = wohnzimmer.W - kueche.W - eingang.W;
		abstellkammerl.H = kueche.H;
		abstellkammerl.X = wohnzimmer.X;
		abstellkammerl.Y = kueche.Y;

		abstellkammerl.setFeld(FRAME_BORDER_HORIZONTAL + 2 * FIELD_SIZE + 2 * FIELD_BORDER_HORIZONTAL,
				FRAME_BORDER_VERTICAL + 2 * FIELD_SIZE + 2 * FIELD_BORDER_VERTIACAL);

		// TODO Button
	}

	public ControllerCustom getController() {
		return controller;
	}

}
