package com.philips.lighting.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.data.Room;
import com.philips.lighting.data.StateUpdater;
import com.philips.lighting.data.Wohnung;

public class LightMenuPanel extends JPanel {
	private final Boolean raspberry = false;

	private final int SCALE = 1;
	private final int FRAME_WIDTH = 480 * SCALE;
	private final int FRAME_HIGHT = 320 * SCALE;
	private final int MENU_WIDTH = 60 * SCALE;
	private final int FIELD_SIZE = 90 * SCALE;
	private final int ICON_SIZE = 70 * SCALE;
	private final int ICON_BORDER = 10 * SCALE;

	private ControllerCustom controller;
	private Wohnung wohnung;
	private StateUpdater stateUpdater;

	private Border emptyBorder;

	private Color passiv = new Color(0, 71, 152, 150);
	private Color aktiv = new Color(0, 100, 200, 150);
	private Color background = new Color(0, 25, 51, 255);
	private Color blaugrau = new Color(102, 143, 175, 100);
	private Color blaugrau2 = new Color(55, 90, 118, 255);

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;

	public LightMenuPanel(ControllerCustom controller, Wohnung wohnung) {
		this.controller = controller;
		this.wohnung = wohnung;

		initializeButton(button1, wohnung.getFlur());
		initializeButton(button2, wohnung.getBadezimmer());
		initializeButton(button3, wohnung.getSchlafzimmer());
		initializeButton(button4, wohnung.getEingang());
		initializeButton(button5, wohnung.getWc());
		initializeButton(button6, wohnung.getAbstellkammerl());

		Color backgroud = new Color(0, 25, 51, 255);

		this.setPreferredSize(new Dimension(FRAME_WIDTH - MENU_WIDTH, FRAME_HIGHT));
		this.setLayout(null);
		this.setBounds(MENU_WIDTH, 0, FRAME_WIDTH - MENU_WIDTH, FRAME_HIGHT);
		this.setBackground(backgroud);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("Arial", Font.BOLD, 15));

		drawFeld(g, wohnung.getBadezimmer());
		drawFeld(g, wohnung.getWc());
		drawFeld(g, wohnung.getSchlafzimmer());
		drawFeld(g, wohnung.getEingang());
		drawFeld(g, wohnung.getAbstellkammerl());
		drawFeld(g, wohnung.getFlur());

	}

	private void drawFeld(Graphics g, Room room) {
		if (room.light.lightOn) {
			g.setColor(aktiv);
			g.fillRect((int) room.fieldCoord.getX(), (int) room.fieldCoord.getY(), FIELD_SIZE, FIELD_SIZE);
			room.getIcon_on().paintIcon(this, g, (int) room.fieldCoord.getX() + ICON_BORDER,
					(int) room.fieldCoord.getY() + ICON_BORDER);
		} else {
			g.setColor(passiv);
			g.fillRect((int) room.fieldCoord.getX(), (int) room.fieldCoord.getY(), FIELD_SIZE, FIELD_SIZE);
			room.getIcon_off().paintIcon(this, g, (int) room.fieldCoord.getX() + ICON_BORDER,
					(int) room.fieldCoord.getY() + ICON_BORDER);
		}

	}

	private Boolean feldclicked(Point point, Room room) {
		if (room.fieldCoord.getX() <= point.getX() && point.getX() <= room.fieldCoord.getX() + FIELD_SIZE
				&& room.fieldCoord.getY() <= point.getY() && point.getY() <= room.fieldCoord.getY() + FIELD_SIZE) {
			return true;
		} else {
			return false;
		}
	}

	private void initializeButton(JButton button, Room room) {
		button = new JButton();
		button.setBounds(room.fieldCoord.x, room.fieldCoord.y, FIELD_SIZE, FIELD_SIZE);
		button.setContentAreaFilled(false);
		button.setBorder(emptyBorder);
		button.setFocusable(false);
		button.addMouseListener(new MouseListener() {

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
				if (raspberry) {
					controller.switchLight(room);
					repaint();
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!raspberry) {
					controller.switchLight(room);
					repaint();
				}
			}
		});
		add(button);
	}
}
