package com.philips.lighting.gui;

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
import com.philips.lighting.SceneController;
import com.philips.lighting.data.Constants;
import com.philips.lighting.data.Room;
import com.philips.lighting.data.Wohnung;

public class SceneMenuPanel extends JPanel {

	private Wohnung wohnung;
	private ControllerCustom controller;
	private SceneController sceneController;
	private List<Room> rooms;

	private JButton hell;
	private JButton dunkel;
	private JButton normal;

	private List<ImageIcon> icons;

	private List<Point> coords;

	private Border emptyBorder;

	private int selected = 1;

	public SceneMenuPanel(ControllerCustom controller, Wohnung wohnung) {
		this.controller = controller;
		this.sceneController = new SceneController(controller);
		this.wohnung = wohnung;
		this.rooms = wohnung.getRooms();
		this.setPreferredSize(new Dimension(Constants.FRAME_WIDTH - Constants.MENU_WIDTH, Constants.FRAME_HIGHT));
		this.setLayout(null);
		this.setBounds(Constants.MENU_WIDTH, 0, Constants.FRAME_WIDTH - Constants.MENU_WIDTH, Constants.FRAME_HIGHT);
		this.setBackground(Constants.COLOR_BACKGROUND);

		coords = new ArrayList<>();
		coords.add(new Point(70, 170));
		coords.add(new Point(280, 170));
		coords.add(new Point(490, 170));

		hell = new JButton();
		normal = new JButton();
		dunkel = new JButton();

		hell.setBounds(coords.get(0).x, coords.get(0).y, Constants.FIELD_SIZE, Constants.FIELD_SIZE);
		normal.setBounds(coords.get(1).x, coords.get(1).y, Constants.FIELD_SIZE, Constants.FIELD_SIZE);
		dunkel.setBounds(coords.get(2).x, coords.get(2).y, Constants.FIELD_SIZE, Constants.FIELD_SIZE);

		icons = new LinkedList<>();
		icons.add(new ImageIcon("resources/hell_on_100.png"));
		icons.add(new ImageIcon("resources/hell_off_100.png"));
		icons.add(new ImageIcon("resources/normal_on_100.png"));
		icons.add(new ImageIcon("resources/normal_off_100.png"));
		icons.add(new ImageIcon("resources/dunkel_on_100.png"));
		icons.add(new ImageIcon("resources/dunkel_off_100.png"));

		initializeButton(hell);
		initializeButton(normal);
		initializeButton(dunkel);

		hell.addMouseListener(new MouseListener() {

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
				selected = 0;
				sceneController.setAwakeMode();
				System.out.println("repaint because pressed Button");
				repaint();
			}
		});

		normal.addMouseListener(new MouseListener() {

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
				selected = 1;
				sceneController.setNormalScenes();
				System.out.println("repaint because pressed Button");
				repaint();
			}
		});

		dunkel.addMouseListener(new MouseListener() {

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
				selected = 2;
				sceneController.setSleepMode();
				System.out.println("repaint because pressed Button");
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Constants.COLOR_PASSIV_BUTTON);
		for (int i = 0; i < 3; i++) {
			if (i != selected) {
				g.fillRoundRect(coords.get(i).x, coords.get(i).y, Constants.FIELD_SIZE, Constants.FIELD_SIZE,
						Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
				icons.get(2 * i + 1).paintIcon(this, g, coords.get(i).x + 20, coords.get(i).y + 20);

			}
		}
		g.setColor(Constants.COLOR_AKTIV_BUTTON);
		g.fillRoundRect(coords.get(selected).x, coords.get(selected).y, Constants.FIELD_SIZE, Constants.FIELD_SIZE,
				Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
		icons.get(2 * selected).paintIcon(this, g, coords.get(selected).x + 20, coords.get(selected).y + 20);
	}

	private void initializeButton(JButton button) {
		// button = new JButton();
		button.setContentAreaFilled(false);
		button.setBorder(emptyBorder);
		button.setFocusable(false);
		add(button);
	}
}
