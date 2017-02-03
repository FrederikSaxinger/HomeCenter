package com.philips.lighting.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.data.Room;
import com.philips.lighting.data.Wohnung;

public class LightMenuPanel extends JPanel {

	private final int SCALE = 1;
	private final int FRAME_WIDTH = 480 * SCALE;
	private final int FRAME_HIGHT = 320 * SCALE;
	private final int MENU_WIDTH = 60 * SCALE;
	private final int FIELD_SIZE = 90 * SCALE;
	private final int ICON_SIZE = 70 * SCALE;
	private final int ICON_BORDER = 10 * SCALE;

	private ControllerCustom controller;
	private Wohnung wohnung;

	private ImageIcon birne = new ImageIcon("resources/birne_off_button.png");
	private ImageIcon birneleuchtend = new ImageIcon("resources/birne_on_button.png");

	private Color passiv = new Color(0, 71, 152, 150);
	private Color aktiv = new Color(0, 100, 200, 150);
	private Color background = new Color(0, 25, 51, 255);
	private Color blaugrau = new Color(102, 143, 175, 100);
	private Color blaugrau2 = new Color(55, 90, 118, 255);

	public LightMenuPanel(ControllerCustom controller, Wohnung wohnung) {
		this.controller = controller;
		this.wohnung = wohnung;

		Color backgroud = new Color(0, 25, 51, 255);

		this.setPreferredSize(new Dimension(FRAME_WIDTH - MENU_WIDTH, FRAME_HIGHT));
		this.setLayout(null);
		this.setBounds(MENU_WIDTH, 0, FRAME_WIDTH - MENU_WIDTH, FRAME_HIGHT);
		this.setBackground(backgroud);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				Point point = me.getPoint();
				if (feldclicked(point, wohnung.getBadezimmer())) {
					controller.switchLight(wohnung.getBadezimmer());
				} else if (feldclicked(point, wohnung.getFlur())) {
					controller.switchLight(wohnung.getFlur());
				} else if (feldclicked(point, wohnung.getWc())) {
					controller.switchLight(wohnung.getWc());
				} else if (feldclicked(point, wohnung.getSchlafzimmer())) {
					controller.switchLight(wohnung.getSchlafzimmer());
				} else if (feldclicked(point, wohnung.getEingang())) {
					controller.switchLight(wohnung.getEingang());
				} else if (feldclicked(point, wohnung.getAbstellkammerl())) {
					controller.switchLight(wohnung.getAbstellkammerl());
				}
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("Arial", Font.BOLD, 15));

		// if (wohnung.getBadezimmer().lightOn) {
		// g.setColor(aktiv);
		// g.fillRect(50, 50, 225, 225);
		// badezimmerOn.paintIcon(this, g, 87, 100);
		// g.setColor(Color.white);
		// g.drawString("Badezimmer", 80, 80);
		// } else {
		// g.setColor(passiv);
		// g.fillRect(50, 50, 225, 225);
		// badezimmerOff.paintIcon(this, g, 87, 100);
		// g.setColor(Color.black);
		// g.drawString("Badezimmer", 80, 80);
		// }

		drawFeld(g, wohnung.getBadezimmer());
		drawFeld(g, wohnung.getWc());
		drawFeld(g, wohnung.getSchlafzimmer());
		drawFeld(g, wohnung.getEingang());
		drawFeld(g, wohnung.getAbstellkammerl());
		drawFeld(g, wohnung.getFlur());

	}

	private void drawFeld(Graphics g, Room room) {
		if (room.lightOn) {
			g.setColor(aktiv);
			g.fillRect(room.feld[0], room.feld[1], FIELD_SIZE, FIELD_SIZE);
			room.getIcon_on().paintIcon(this, g, room.feld[0] + ICON_BORDER, room.feld[1] + ICON_BORDER);
		} else {
			g.setColor(passiv);
			g.fillRect(room.feld[0], room.feld[1], FIELD_SIZE, FIELD_SIZE);
			room.getIcon_off().paintIcon(this, g, room.feld[0] + ICON_BORDER, room.feld[1] + ICON_BORDER);
		}

	}

	private Boolean feldclicked(Point point, Room room) {
		if (room.feld[0] <= point.getX() && point.getX() <= room.feld[0] + FIELD_SIZE && room.feld[1] <= point.getY()
				&& point.getY() <= room.feld[1] + FIELD_SIZE) {
			return true;
		} else {
			return false;
		}
	}

}
