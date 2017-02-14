package com.philips.lighting.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.data.Constants;
import com.philips.lighting.data.Room;
import com.philips.lighting.data.Wohnung;

import javafx.util.Pair;

public class SceneMenuPanel extends JPanel {

	private Wohnung wohnung;
	private ControllerCustom controller;
	private List<Room> rooms;

	private Color passiv = new Color(0, 52, 110, 255);
	private Color aktiv = new Color(0, 69, 139, 255);
	private Color background = new Color(0, 25, 51, 255);

	private JButton hell;
	private JButton dunkel;
	private JButton normal;

	private List<Pair<ImageIcon, ImageIcon>> icons;

	private List<Point> coords;

	private Border emptyBorder;

	private int selected = 0;

	public SceneMenuPanel(ControllerCustom controller, Wohnung wohnung) {
		this.controller = controller;
		this.wohnung = wohnung;
		this.rooms = wohnung.getRooms();
		this.setPreferredSize(new Dimension(Constants.FRAME_WIDTH - Constants.MENU_WIDTH, Constants.FRAME_HIGHT));
		this.setLayout(null);
		this.setBounds(Constants.MENU_WIDTH, 0, Constants.FRAME_WIDTH - Constants.MENU_WIDTH, Constants.FRAME_HIGHT);
		this.setBackground(background);

		coords = new ArrayList<>();
		coords.add(new Point(70, 170));
		coords.add(new Point(280, 170));
		coords.add(new Point(490, 170));

		hell = new JButton();
		dunkel = new JButton();
		normal = new JButton();

		hell.setBounds(coords.get(0).x, coords.get(0).y, Constants.FIELD_SIZE, Constants.FIELD_SIZE);
		dunkel.setBounds(coords.get(1).x, coords.get(1).y, Constants.FIELD_SIZE, Constants.FIELD_SIZE);
		normal.setBounds(coords.get(2).x, coords.get(2).y, Constants.FIELD_SIZE, Constants.FIELD_SIZE);

		icons = new LinkedList<>();
		icons.add(new Pair<>(new ImageIcon("resources/hell_on_100.png"), new ImageIcon("resources/hell_off_100.png")));
		icons.add(new Pair<>(new ImageIcon("resources/normal_on_100.png"),
				new ImageIcon("resources/normal_off_100.png")));
		icons.add(new Pair<>(new ImageIcon("resources/dunkel_on_100.png"),
				new ImageIcon("resources/dunkel_off_100.png")));

		initializeButton(hell, 0);
		initializeButton(dunkel, 1);
		initializeButton(normal, 2);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(passiv);
		for (int i = 0; i < 3; i++) {
			if (i != selected) {
				g.fillRoundRect(coords.get(i).x, coords.get(i).y, Constants.FIELD_SIZE, Constants.FIELD_SIZE,
						Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
				icons.get(i).getValue().paintIcon(this, g, coords.get(i).x + 20, coords.get(i).y + 20);

			}
		}
		g.setColor(aktiv);
		g.fillRoundRect(coords.get(selected).x, coords.get(selected).y, Constants.FIELD_SIZE, Constants.FIELD_SIZE,
				Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
		icons.get(selected).getKey().paintIcon(this, g, coords.get(selected).x + 20, coords.get(selected).y + 20);
	}

	private void initializeButton(JButton button, int pressedButton) {
		// button = new JButton();
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
				selected = pressedButton;
				System.out.println("repaint because pressed Button");
				repaint();
			}
		});
		add(button);
	}
}
