package com.philips.lighting.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GrundrissMenuPanel extends JPanel {
	private static final long serialVersionUID = 3613199379092577405L;
	private static final int PREF_W = 924;
	private static final int PREF_H = 600;

	public GrundrissMenuPanel() {
		Color backgroud = new Color(0, 25, 51, 255);

		this.setPreferredSize(new Dimension(924, 600));
		this.setLayout(null);
		this.setBounds(100, 0, 924, 600);
		this.setBackground(backgroud);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color sidebar = new Color(0, 51, 102, 255);

		g.setColor(sidebar);
		g.fillRect(10, 210, 50, 50);

	}
}
// im constructor
// addMouseListener(new MouseAdapter() {
// @Override
// public void mouseClicked(MouseEvent me) {
// Point point = me.getPoint();
// if (roomClicked(wohnung.getFlur(), point)) {
// controller.switchLight(wohnung.getFlur());
// repaint();
// } else if (roomClicked(wohnung.getBadezimmer(), point)) {
// controller.switchLight(wohnung.getBadezimmer());
// repaint();
// } else if (roomClicked(wohnung.getWc(), point)) {
// controller.switchLight(wohnung.getWc());
// repaint();
// } else if (roomClicked(wohnung.getSchlafzimmer(), point)) {
// controller.switchLight(wohnung.getSchlafzimmer());
// repaint();
// } else if (roomClicked(wohnung.getEingang(), point)) {
// controller.switchLight(wohnung.getEingang());
// repaint();
// } else if (roomClicked(wohnung.getAbstellkammerl(), point)) {
// controller.switchLight(wohnung.getAbstellkammerl());
// repaint();
// }
//
// }
// });

// in der paint

// drawSensorBatterie(wohnung.getFlur(), g);
// drawSensorBatterie(wohnung.getBadezimmer(), g);
// drawSensorBatterie(wohnung.getEingang(), g);
// drawSensorBatterie(wohnung.getWc(), g);
//
// drawRoom(wohnung.getFlur(), g);
// drawRoom(wohnung.getBadezimmer(), g);
// drawRoom(wohnung.getWc(), g);
// drawRoom(wohnung.getEingang(), g);
// drawRoom(wohnung.getSchlafzimmer(), g);
// drawRoom(wohnung.getAbstellkammerl(), g);
// drawEmptyRoom(wohnung.getWohnzimmer(), g);
// drawEmptyRoom(wohnung.getKueche(), g);
//
// g.setFont(new Font("Arial", Font.BOLD, 17));
// writeRoomName(wohnung.getFlur(), g);
// writeRoomName(wohnung.getBadezimmer(), g);
// writeRoomName(wohnung.getEingang(), g);
// writeRoomName(wohnung.getWc(), g);
//
// g.drawLine(750, 0, 750, 600);
//
// drawGrundriss(g);

// Methoden:
// private void writeRoomName(Room room, Graphics g) {
// g.drawString(room.name, room.sensorButtonX, room.sensorButtonY - 5);
// }
//
// private void drawRoom(Room room, Graphics g) {
// if (room.light.lightOn) {
// g.setColor(Color.white);
// g.fillRect(room.X, room.Y, room.W, room.H);
// // g.drawImage(ambientBirneOn, room.X + 210, room.Y + 110, 55, 55,
// // null);
// } else {
// g.setColor(Color.white);
// g.drawRect(room.X, room.Y, room.W, room.H);
// // g.drawImage(ambientBirneOff, room.X + 210, room.Y + 110, 55, 55,
// // null);
// }
//
// if (room.sensor.sensorOn != null) {
// room.sensorButton.setSelected(!room.sensor.sensorOn);
// }
// }
//
// private void drawEmptyRoom(Room room, Graphics g) {
// g.drawRect(room.X, room.Y, room.W, room.H);
// }
//
// private Boolean roomClicked(Room room, Point point) {
// if (room.X <= point.getX() && point.getX() <= room.X + room.W && room.Y <=
// point.getY()
// && point.getY() <= room.Y + room.H) {
// return true;
// } else {
// return false;
// }
// }
//
// private void drawSensorBatterie(Room room, Graphics g) {
// int x = room.sensorButtonX + 55;
// int y = room.sensorButtonY + 20;
// g.setColor(Color.white);
// g.drawRoundRect(x, y, 12, 26, 4, 4);
// g.fillRect(x + 4, y - 4, 4, 4);
//
// g.setColor(Color.green);
// if (room.sensor.battery > 10) {
// g.fillRect(x + 2, y + 20, 9, 5);
// }
// if (room.sensor.battery > 25) {
// g.fillRect(x + 2, y + 14, 9, 5);
// }
// if (room.sensor.battery > 50) {
// g.fillRect(x + 2, y + 8, 9, 5);
// }
// if (room.sensor.battery > 75) {
// g.fillRect(x + 2, y + 2, 9, 5);
// }
// }
//
// private void drawGrundriss(Graphics g) {
// g.setColor(Color.gray);
// Graphics2D g2 = (Graphics2D) g;
// g2.setStroke(new BasicStroke(4));
// g2.draw(new Line2D.Float(wohnung.getAbstellkammerl().X,
// wohnung.getAbstellkammerl().Y, wohnung.getWc().X,
// wohnung.getAbstellkammerl().Y));
// g2.draw(new Line2D.Float(wohnung.getWc().X, wohnung.getAbstellkammerl().Y,
// wohnung.getWc().X,
// wohnung.getWc().Y));
// g2.draw(new Line2D.Float(wohnung.getWc().X, wohnung.getWc().Y,
// wohnung.getBadezimmer().X, wohnung.getWc().Y));
// g2.draw(new Line2D.Float(wohnung.getBadezimmer().X, wohnung.getFlur().Y,
// wohnung.getBadezimmer().X,
// wohnung.getBadezimmer().Y));
// g2.draw(new Line2D.Float(wohnung.getBadezimmer().X,
// wohnung.getBadezimmer().Y,
// wohnung.getBadezimmer().X + wohnung.getBadezimmer().W,
// wohnung.getBadezimmer().Y));
// g2.draw(new Line2D.Float(wohnung.getBadezimmer().X +
// wohnung.getBadezimmer().W, wohnung.getBadezimmer().Y,
// wohnung.getBadezimmer().X + wohnung.getBadezimmer().W,
// wohnung.getSchlafzimmer().Y + wohnung.getSchlafzimmer().H));
// g2.draw(new Line2D.Float(wohnung.getBadezimmer().X +
// wohnung.getBadezimmer().W,
// wohnung.getSchlafzimmer().Y + wohnung.getSchlafzimmer().H,
// wohnung.getWohnzimmer().X,
// wohnung.getWohnzimmer().Y + wohnung.getWohnzimmer().H));
// g2.draw(new Line2D.Float(wohnung.getWohnzimmer().X, wohnung.getWohnzimmer().Y
// + wohnung.getWohnzimmer().H,
// wohnung.getAbstellkammerl().X, wohnung.getAbstellkammerl().Y));
// g2.draw(new Line2D.Float(wohnung.getWohnzimmer().X,
// wohnung.getWohnzimmer().Y,
// wohnung.getFlur().X + wohnung.getFlur().W, wohnung.getFlur().Y));
// g2.draw(new Line2D.Float(wohnung.getWohnzimmer().X,
// wohnung.getWohnzimmer().Y,
// wohnung.getFlur().X + wohnung.getFlur().W, wohnung.getFlur().Y));
// g2.draw(new Line2D.Float(wohnung.getWc().X, wohnung.getWc().Y,
// wohnung.getFlur().X,
// wohnung.getSchlafzimmer().Y + wohnung.getSchlafzimmer().H));
// g2.draw(new Line2D.Float(wohnung.getSchlafzimmer().X,
// wohnung.getSchlafzimmer().Y,
// wohnung.getSchlafzimmer().X + wohnung.getSchlafzimmer().W,
// wohnung.getSchlafzimmer().Y));
// g2.draw(new Line2D.Float(wohnung.getEingang().X, wohnung.getEingang().Y,
// wohnung.getEingang().X,
// wohnung.getFlur().Y));
// g2.draw(new Line2D.Float(wohnung.getKueche().X, wohnung.getKueche().Y,
// wohnung.getKueche().X,
// wohnung.getFlur().Y));
//
// }