package com.philips.lighting.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.data.Constants;
import com.philips.lighting.data.Wohnung;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = -8072760331082729434L;

	@SuppressWarnings("unused")
	private ControllerCustom controller;

	private final int FRAME_HIGHT = Constants.FRAME_HIGHT;
	private final int MENU_WIDTH = Constants.MENU_WIDTH;
	private final int MENU_BUTTON_BORDER = Constants.MENU_BUTTON_BORDER;

	@SuppressWarnings("unused")
	private Wohnung wohnung;

	public int selected = 1;

	public MenuPanel(Wohnung wohnung) {
		this.wohnung = wohnung;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color sidebar = new Color(0, 51, 102, 255);
		Color backgroud = new Color(0, 25, 51, 255);

		g.setColor(sidebar);
		g.fillRect(0, 0, MENU_WIDTH, FRAME_HIGHT);

		g.setColor(backgroud);
		g.fillRect(0, MENU_BUTTON_BORDER + MENU_WIDTH * (selected - 1), MENU_WIDTH, MENU_WIDTH);

	}

	public void setController(ControllerCustom controller) {
		this.controller = controller;
	}
}
