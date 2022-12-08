package Blackjack;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Timer extends JFrame implements Runnable {
	String sec;
	JLabel time = new JLabel();
	JPanel panel = new JPanel();
	InGame IG;
	static boolean timeOver;
	
	Timer() {
		time.setFont(new Font("MS Gothic", Font.BOLD, 40));
		time.setBackground(Color.BLACK);
		time.setForeground(Color.RED);
		time.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.BLACK);
		panel.add(time, BorderLayout.CENTER);
		
		setBounds(0, 0, 200, 150);
		add(panel);
		setBackground(Color.BLACK);
		setResizable(false);
		setVisible(true);
	} // END - Timer()
	
	// Thread 참고
	// https://kim-jong-hyun.tistory.com/101
	// https://aileen93.tistory.com/105
	public void run() { // Thread는 PlayBlackjack 내에서만 작동해야 한다.
		
		while(true) {
			try {
				for(int i=60; ; i--) {
					if(i>0)       { sec=String.format("%02d", i); }
					else if(i==0) { sec="00";
									timeOver=true;
									// Player에게 Time-Penalty 안내
									System.out.println("\n[Dealer] : Penalty [Time-Over : 60sec.]");
									System.out.printf("           [Player] (-%,d), [Dealer] (+%,d)\n", 
													   Betting.minimumBet, Betting.minimumBet);
									}
					else          { sec="00"; }
					
					time.setText(sec);
					TimeUnit.SECONDS.sleep(1);
				}
			}
			catch(Exception e) {e.printStackTrace();}
		}
				
	} // END - public void run()
	
	public void ClockOff() { // 시계 윈도우(프레임) 없앰
		dispose();
		TimeOverPenalty();
	} // END - public void ClockOff()
	
	private void TimeOverPenalty() { // 시간 초과에 대한 패널티 지불 (베팅 규칙)
	//- 60초 안에 한 Turn이 끝나지 않으면, Player가 Dealer에게 패널티(최소 베팅 금액) 지급(게임 결과-승무패 에 상관없이)
		if(timeOver==true) {
			Betting.playerWallet-=Betting.minimumBet;
			Betting.dealerWallet+=Betting.minimumBet;
		}
		timeOver=false;
	} // END - private void TimeOverPenalty()
	
	
} // END - public class Timer extends JFrame implements Runnable {}