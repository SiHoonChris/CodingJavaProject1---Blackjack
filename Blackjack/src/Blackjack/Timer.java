package Blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Timer extends JFrame implements Runnable {
	String sec;
	JLabel time = new JLabel();
	JPanel panel = new JPanel();
	
	Timer() {
		time.setFont(new Font("MS Gothic", Font.BOLD, 40));
		time.setBackground(Color.BLACK);
		time.setForeground(Color.RED);
		time.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.BLACK);
		panel.add(time, BorderLayout.CENTER);
		
		setBounds(600, 600, 150, 100);
		add(panel);
		setBackground(Color.BLACK);
		setResizable(false);
		setVisible(true);
	} // END - Timer()
	
	// Thread 참고
	// https://kim-jong-hyun.tistory.com/101
	// https://aileen93.tistory.com/105
	public void run() {
		
		while(true) {
			try {
				for(int i=60; ; i--) {
					sec=String.format("%02d", i);
					if(i>=0) time.setText(sec);
					else	 time.setText("00"); // 60초 끝나면 00으로 대기
					TimeUnit.SECONDS.sleep(1);
				}
			}
			catch(Exception e) {e.printStackTrace();}
		}
		
	} // END - public void run()
	
	public void ClockOff() { // 시계 윈도우(프레임) 없앰
		dispose();
	} // END - public void ClockOff()
	
	
} // END - public class Timer extends JFrame implements Runnable {}