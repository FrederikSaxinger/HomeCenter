package com.philips.lighting.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.data.StateUpdater;
import com.philips.lighting.data.Wohnung;

public class OfflineFrame extends JFrame {

	private static final long serialVersionUID = -7469471678945429320L;
	private MenuPanel menuPanel;

	JButton lightMenuButton;
	JButton sensorMenuButton;
	JButton grundrissMenuButton;

	LightMenuPanel lightPanel;
	SensorMenuPanel sensorPanel;
	GrundrissMenuPanel grundrissPanel;

	public OfflineFrame(Wohnung wohnung) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Home Center by Frederik Saxinger");

		menuPanel = new MenuPanel(wohnung);
		menuPanel.setPreferredSize(new Dimension(1024, 600));

		// Format stuff
		Color background = new Color(0, 25, 51, 255);
		menuPanel.setBackground(background);
		menuPanel.setLayout(null);
		getContentPane().add(menuPanel, BorderLayout.CENTER);

		lightPanel = new LightMenuPanel(null, wohnung);
		menuPanel.add(lightPanel);
		lightPanel.setPreferredSize(new Dimension(954, 600));
		lightPanel.setLocation(70, 0);
		sensorPanel = new SensorMenuPanel();
		sensorPanel.setPreferredSize(new Dimension(954, 600));
		grundrissPanel = new GrundrissMenuPanel();
		grundrissPanel.setPreferredSize(new Dimension(954, 600));

		lightMenuButton = new JButton();
		sensorMenuButton = new JButton();
		grundrissMenuButton = new JButton();

		setUpMenuButton(lightMenuButton);
		setUpLightsMenuButton();

		setUpMenuButton(sensorMenuButton);
		setUpSensorsMenuButton();

		setUpMenuButton(grundrissMenuButton);
		setUpGrundrissMenuButton();

		menuPanel.add(lightMenuButton);
		menuPanel.add(sensorMenuButton);
		menuPanel.add(grundrissMenuButton);

		// 4. Size the frame.
		pack();
		setLocationRelativeTo(null); // Centre the window.
		setVisible(true);

	}

	private void setUpMenuButton(JButton button) {
		button.setSelected(false);
		button.setBackground(null);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		button.setBorder(emptyBorder);
		button.setFocusable(false);
		button.removeMouseListener(button.getMouseListeners()[0]);
	}

	private void setUpLightsMenuButton() {
		lightMenuButton.setSelected(true);
		ImageIcon birne = new ImageIcon("resources/birne_off.png");
		ImageIcon birneOff = new ImageIcon("resources/birne.png");
		lightMenuButton.setIcon(birne);
		lightMenuButton.setSelectedIcon(birneOff);
		lightMenuButton.setBounds(5, 55, 60, 60);
		lightMenuButton.addMouseListener(new MouseListener() {

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
				if (lightMenuButton.isSelected() == false) {
					lightMenuButton.setSelected(true);
					sensorMenuButton.setSelected(false);
					grundrissMenuButton.setSelected(false);

					menuPanel.remove(grundrissPanel);
					menuPanel.remove(sensorPanel);
					menuPanel.add(lightPanel);

					menuPanel.selected = 1;
					menuPanel.repaint();
				}
			}
		});
	}

	private void setUpSensorsMenuButton() {
		// TODO on/off name selected...
		ImageIcon sensor = new ImageIcon("resources/motion_sensor_off.png");
		ImageIcon sensorOff = new ImageIcon("resources/motion_sensor_on.png");
		sensorMenuButton.setIcon(sensor);
		sensorMenuButton.setSelectedIcon(sensorOff);
		sensorMenuButton.setBounds(5, 155, 60, 60);
		sensorMenuButton.addMouseListener(new MouseListener() {

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
				if (sensorMenuButton.isSelected() == false) {
					sensorMenuButton.setSelected(true);
					lightMenuButton.setSelected(false);
					grundrissMenuButton.setSelected(false);

					menuPanel.remove(lightPanel);
					menuPanel.remove(grundrissPanel);
					menuPanel.add(sensorPanel);

					menuPanel.selected = 2;
					menuPanel.repaint();
				}
			}
		});
	}

	private void setUpGrundrissMenuButton() {
		ImageIcon switchOn = new ImageIcon("resources/grundriss_on.png");
		ImageIcon switchOff = new ImageIcon("resources/grundriss_off.png");
		grundrissMenuButton.setIcon(switchOff);
		grundrissMenuButton.setSelectedIcon(switchOn);
		grundrissMenuButton.setBounds(5, 255, 60, 60);
		grundrissMenuButton.addMouseListener(new MouseListener() {

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
				if (grundrissMenuButton.isSelected() == false) {
					grundrissMenuButton.setSelected(true);
					lightMenuButton.setSelected(false);
					sensorMenuButton.setSelected(false);

					menuPanel.remove(sensorPanel);
					menuPanel.remove(lightPanel);
					menuPanel.add(grundrissPanel);

					menuPanel.selected = 3;
					menuPanel.repaint();
				}
			}
		});
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}
}
