package com.philips.lighting.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SensorMenuPanel extends JPanel {
	private static final long serialVersionUID = -8384979822638777947L;
	private static final int PREF_W = 924;
	private static final int PREF_H = 600;

	public SensorMenuPanel() {
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
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("UNDER CONSTRUCTION", 100, 100);

	}
}
