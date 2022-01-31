/**
 * Ashwin Krishnamurthi (ak8ae)
 * HW7
 * Code for all sorts directly from slides
https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array <- Shuffling the array
https://stackoverflow.com/questions/15422100/animating-graphical-sort <- Displaying the rectangles 
Help from Kevin Le
Questions answered on bottom of this
 */
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BubbleRunnable extends ConcurrencyGUI implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawBubble db;
	public int x = 0;
	public int y = 800; 
	private int check1;
	private int check2;
	public class DrawBubble extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); 
			y = 800; 
			this.setBackground(Color.BLACK);
			g.setColor(Color.WHITE);
			// Loop through the Array and display series of Rectangular Strips
			for (int i = 0 ; i < bubble.length ; i++)
			{
				// Fill out the Rectangle
				g.drawRect (x, y, bubble[i], 12);
				g.fillRect(x, y, bubble[i], 12);
				y = y - 15;

			}
			g.setColor(Color.RED); 
			g.fillRect(x, 800 - check1 * 15, bubble[check1], 12); //marking rectangles next to each other
			g.fillRect(x, 800 - check2 * 15, bubble[check2], 12); //marking rectangles next to each other
			
		}
	}
	@Override
	public void run() 
	{
		lock.lock(); // Locked for addition of JFrame components
		try {
			panel = new JPanel();
			panel.setBackground(Color.black);
			JLabel label = new JLabel("<html><font color ='white'>Bubble Sort</font></html>");
			panel.add(label);
			db = new DrawBubble();
			frame.add(panel);
			frame.add(db);
		} finally {
			lock.unlock();
			frame.setVisible(true);
		}
		boolean swapped = true;
		int j = 0;
		int tmp;
		while(swapped)
		{
			swapped = false;
			j++;
			for(int i = 0; i < bubble.length - j; i++) //code for bubble sort from slides
			{
				if(bubble[i] > bubble[i+1])
				{
					tmp = bubble[i];
					bubble[i] = bubble[i + 1];
					bubble[i+1] = tmp;
					swapped = true; 
					check1 = i + 1;  //keeping track of which values are being swapped
					check2 = i;
				}

				db.repaint();
				try {
					Thread.sleep(15); //shorter sleep time since more runs are required
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
 

	}

}
/**
 * Answers to Questions:
 * 1) I used a lock just to make sure that all the essential components of the frame were added
 * to the thread without any interruptions. 
 * 2)If another thread calls notify(), then an await() can be "woken up" while a sleep() still cannot be. 
 * 3) The thread will keep on waiting for a signal to execute. If no other thread calls one of these methods, 
 * it will never execute. 
 * */ 


