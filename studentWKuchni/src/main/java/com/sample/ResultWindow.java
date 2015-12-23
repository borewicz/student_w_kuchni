package com.sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.concurrent.Semaphore;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ResultWindow extends JFrame implements ActionListener {

	private JButton trueButton;
	private JButton falseButton;
	public static Semaphore dataAvailable = new Semaphore(0);

	private String result;
	private String message;
	public Dish d;

	private WindowListener windowListener;

	public ResultWindow(Dish d, String result, String message, String buttonQuestion) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		this.message = message;
		this.d = d;
		this.result = result;

		int windowWidth = 600;
		int windowHeight = 400;
		Border empty = new EmptyBorder(10, 600, 20, 600);

		JLabel label1 = new JLabel(message);
		label1.setBorder(empty);
		add(label1);

		add(new JPanel());

		JPanel buttonArea = new JPanel(new FlowLayout());
		buttonArea.add(new JPanel());

		trueButton = new JButton("Tak");
		trueButton.addActionListener(this);
		falseButton = new JButton("Nie");
		falseButton.addActionListener(this);
		buttonArea.add(trueButton);
		buttonArea.add(falseButton);

		buttonArea.add(new JPanel());

		add(buttonArea);

		setVisible(true);
		setResizable(false);
		setLayout(new FlowLayout());
		setSize(windowWidth, windowHeight);
		setTitle("Student w Kuchni");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		try {
			dataAvailable.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();				
		if (source == trueButton) {
			setVisible(false);
			dataAvailable.release();
			System.exit(0);
		}
		if (source == falseButton) {
			if (d.attributeExist("result")) {
				d.attributes.get("result").add(this.result);
			} else {
				HashSet<String> attributesSet = new HashSet<String>();				
				attributesSet.add(this.result);
				d.attributes.put("result", attributesSet);
			}
			setVisible(false);
			dataAvailable.release();
		}
	}
}