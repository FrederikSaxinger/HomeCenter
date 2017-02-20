package com.philips.lighting.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.data.Constants;
import com.philips.lighting.data.Room;
import com.philips.lighting.data.Wohnung;

public class SensorMenuPanel extends JPanel {
	private static final long serialVersionUID = -8384979822638777947L;

	private ControllerCustom controller;
	private Wohnung wohnung;

	private Border emptyBorder;

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;

	public SensorMenuPanel(ControllerCustom controller, Wohnung wohnung) {
		this.controller = controller;
		this.wohnung = wohnung;

		this.setPreferredSize(new Dimension(Constants.FRAME_WIDTH - Constants.MENU_WIDTH, Constants.FRAME_HIGHT));
		this.setLayout(null);
		this.setBounds(Constants.MENU_WIDTH, 0, Constants.FRAME_WIDTH - Constants.MENU_WIDTH, Constants.FRAME_HIGHT);
		this.setBackground(Constants.COLOR_BACKGROUND);

		initializeButton(button1, wohnung.getFlur());
		initializeButton(button2, wohnung.getBadezimmer());
		initializeButton(button3, wohnung.getWc());
		initializeButton(button4, wohnung.getEingang());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawFeld(g, wohnung.getBadezimmer());
		drawFeld(g, wohnung.getWc());
		drawFeld(g, wohnung.getEingang());
		drawFeld(g, wohnung.getFlur());

	}

	private void drawFeld(Graphics g, Room room) {
		if (room.sensor.isOn) {
			g.setColor(Constants.COLOR_AKTIV_BUTTON);
			g.fillRoundRect(room.fieldCoord.x, room.fieldCoord.y, Constants.FIELD_SIZE, Constants.FIELD_SIZE,
					Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
			room.getIcon_on().paintIcon(this, g, room.fieldCoord.x + Constants.ICON_BORDER,
					room.fieldCoord.y + Constants.ICON_BORDER);
		} else {
			g.setColor(Constants.COLOR_PASSIV_BUTTON);
			g.fillRoundRect(room.fieldCoord.x, room.fieldCoord.y, Constants.FIELD_SIZE, Constants.FIELD_SIZE,
					Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
			room.getIcon_off().paintIcon(this, g, room.fieldCoord.x + Constants.ICON_BORDER,
					room.fieldCoord.y + Constants.ICON_BORDER);
		}

	}

	private void initializeButton(JButton button, Room room) {
		button = new JButton();
		button.setBounds(room.fieldCoord.x, room.fieldCoord.y, Constants.FIELD_SIZE, Constants.FIELD_SIZE);
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
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				controller.switchSensorState(room);
			}
		});
		add(button);
	}
}
