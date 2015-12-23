package com.sample;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;



public class FinalWindow extends JFrame implements ActionListener{
	
	private JButton potwierdzButton;
	public static Semaphore sem = new Semaphore(0);
	
	public FinalWindow (String decyzja){
		
		setLayout(new FlowLayout());
		
		Border empty = new EmptyBorder(30, 1000, 60, 1000);
			
		JLabel label1 = new JLabel(decyzja);
		label1.setBorder(empty);
		add(label1);
		
		potwierdzButton = new JButton("Potwierdü");
        potwierdzButton.addActionListener(this);
        
        add(potwierdzButton);
		
		setSize(400, 200);
		setResizable(false);
        setTitle("G≥odny student, smutny student.");
        setVisible(true);
        setLocationRelativeTo(null);
        try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
 
        if(source == potwierdzButton){
        	setVisible(false);
        	
        	sem.release();
        	
        	System.exit(0);
        }	
	}
	
	
}