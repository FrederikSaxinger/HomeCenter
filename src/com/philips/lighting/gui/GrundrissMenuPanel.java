package com.philips.lighting.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.List;

import javax.swing.JPanel;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.data.Constants;
import com.philips.lighting.data.Room;
import com.philips.lighting.data.Wohnung;

public class GrundrissMenuPanel extends JPanel {
	private static final long serialVersionUID = 3613199379092577405L;

	private Wohnung wohnung;
	private ControllerCustom controller;
	private List<Room> rooms;

	private Color passiv = new Color(0, 71, 152, 150);
	private Color aktiv = new Color(0, 100, 200, 150);
	private Color background = new Color(0, 25, 51, 255);

	public GrundrissMenuPanel(ControllerCustom controller, Wohnung wohnung) {
		this.controller = controller;
		this.wohnung = wohnung;
		this.rooms = wohnung.getRooms();
		Color background = new Color(0, 25, 51, 255);
		this.setPreferredSize(new Dimension(Constants.FRAME_WIDTH - Constants.MENU_WIDTH, Constants.FRAME_HIGHT));
		this.setLayout(null);
		this.setBounds(Constants.MENU_WIDTH, 0, Constants.FRAME_WIDTH - Constants.MENU_WIDTH, Constants.FRAME_HIGHT);
		this.setBackground(background);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				Point point = me.getPoint();
				for (Room room : rooms) {
					if (roomClicked(room, point)) {
						if (controller != null) {
							controller.switchLight(room);
						} else {
							room.light.isOn = !room.light.isOn;
						}
						repaint();
					}
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawRoom(wohnung.getFlur(), g);
		drawRoom(wohnung.getBadezimmer(), g);
		drawRoom(wohnung.getWc(), g);
		drawRoom(wohnung.getEingang(), g);
		drawRoom(wohnung.getSchlafzimmer(), g);
		drawRoom(wohnung.getAbstellkammerl(), g);
		drawEmptyRoom(wohnung.getWohnzimmer(), g);
		drawEmptyRoom(wohnung.getKueche(), g);

		drawGrundriss(g);

		drawIcons(g);
	}

	private void drawRoom(Room room, Graphics g) {
		if (room.light.isOn) {
			g.setColor(aktiv);
			g.fillRect(room.X, room.Y, room.W, room.H);
		} else {
			g.setColor(passiv);
			g.fillRect(room.X, room.Y, room.W, room.H);
		}
	}

	private void drawEmptyRoom(Room room, Graphics g) {
		g.drawRect(room.X, room.Y, room.W, room.H);
	}

	private Boolean roomClicked(Room room, Point point) {
		if (room.X <= point.getX() && point.getX() <= room.X + room.W && room.Y <= point.getY()
				&& point.getY() <= room.Y + room.H) {
			return true;
		} else {
			return false;
		}
	}

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

	private void drawGrundriss(Graphics g) {
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(8));
		g2.draw(new Line2D.Float(wohnung.getAbstellkammerl().X, wohnung.getAbstellkammerl().Y, wohnung.getWc().X,
				wohnung.getAbstellkammerl().Y));
		g2.draw(new Line2D.Float(wohnung.getWc().X, wohnung.getAbstellkammerl().Y, wohnung.getWc().X,
				wohnung.getWc().Y));
		g2.draw(new Line2D.Float(wohnung.getWc().X, wohnung.getWc().Y, wohnung.getBadezimmer().X, wohnung.getWc().Y));
		g2.draw(new Line2D.Float(wohnung.getBadezimmer().X, wohnung.getFlur().Y, wohnung.getBadezimmer().X,
				wohnung.getBadezimmer().Y));
		g2.draw(new Line2D.Float(wohnung.getBadezimmer().X, wohnung.getBadezimmer().Y,
				wohnung.getBadezimmer().X + wohnung.getBadezimmer().W, wohnung.getBadezimmer().Y));
		g2.draw(new Line2D.Float(wohnung.getBadezimmer().X + wohnung.getBadezimmer().W, wohnung.getBadezimmer().Y,
				wohnung.getBadezimmer().X + wohnung.getBadezimmer().W,
				wohnung.getSchlafzimmer().Y + wohnung.getSchlafzimmer().H));
		g2.draw(new Line2D.Float(wohnung.getBadezimmer().X + wohnung.getBadezimmer().W,
				wohnung.getSchlafzimmer().Y + wohnung.getSchlafzimmer().H, wohnung.getWohnzimmer().X,
				wohnung.getWohnzimmer().Y + wohnung.getWohnzimmer().H));
		g2.draw(new Line2D.Float(wohnung.getWohnzimmer().X, wohnung.getWohnzimmer().Y + wohnung.getWohnzimmer().H,
				wohnung.getAbstellkammerl().X, wohnung.getAbstellkammerl().Y));
		g2.draw(new Line2D.Float(wohnung.getWohnzimmer().X, wohnung.getWohnzimmer().Y,
				wohnung.getFlur().X + wohnung.getFlur().W, wohnung.getFlur().Y));
		g2.draw(new Line2D.Float(wohnung.getWohnzimmer().X, wohnung.getWohnzimmer().Y,
				wohnung.getFlur().X + wohnung.getFlur().W, wohnung.getFlur().Y));
		g2.draw(new Line2D.Float(wohnung.getWc().X, wohnung.getWc().Y, wohnung.getFlur().X,
				wohnung.getSchlafzimmer().Y + wohnung.getSchlafzimmer().H));
		g2.draw(new Line2D.Float(wohnung.getSchlafzimmer().X, wohnung.getSchlafzimmer().Y,
				wohnung.getSchlafzimmer().X + wohnung.getSchlafzimmer().W, wohnung.getSchlafzimmer().Y));
		g2.draw(new Line2D.Float(wohnung.getEingang().X, wohnung.getEingang().Y, wohnung.getEingang().X,
				wohnung.getFlur().Y));
		g2.draw(new Line2D.Float(wohnung.getKueche().X, wohnung.getKueche().Y, wohnung.getKueche().X,
				wohnung.getFlur().Y));

	}

	private void drawIcons(Graphics g) {
		for (Room room : wohnung.getRooms()) {
			if (room.getIcon_grund_off() != null && room.getIcon_grund_on() != null) {
				int iconH = room.getIcon_grund_on().getIconHeight();
				int iconW = room.getIcon_grund_on().getIconWidth();
				if (room.light.isOn) {
					room.getIcon_grund_on().paintIcon(this, g, room.X + ((room.W - iconW) >> 1),
							room.Y + ((room.H - iconH) >> 1));
				} else {
					room.getIcon_grund_off().paintIcon(this, g, room.X + ((room.W - iconW) >> 1),
							room.Y + ((room.H - iconH) >> 1));
				}
			}
		}
	}
}