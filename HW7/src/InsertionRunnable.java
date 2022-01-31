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

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InsertionRunnable extends ConcurrencyGUI implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //auto gen
	private DrawInsertion di;
	public int x = 0;
	public int y = 800;
	private int check1 = 0;
	private int check2 = -1;
	public class DrawInsertion extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; //auto gen

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); 
			y = 800; 
			this.setBackground(Color.CYAN);
			g.setColor(Color.orange);
			// Loop through the Array and display series of Rectangular Strips
			for (int i = 0 ; i < insertion.length ; i++)
			{
				// Fill out the Rectangle
				g.drawRect (x, y, insertion[i], 12);
				g.fillRect(x, y, insertion[i], 12);
				y = y - 15;
			}

			if (check2 >= 0)
			{
				g.setColor(Color.RED); //Rectangle being moved
				g.fillRect(x, 800 - check2 * 15, insertion[check2], 12);
			}
			g.setColor(Color.BLACK);   //Place its moved to
			g.fillRect(x, 800 - check1 * 15, insertion[check1], 12);
			if (check1 < 49) 
			{
				check1++;
			}

		}
	}
	@Override
	public void run() 
	{
		lock.lock(); // Locked for addition of JFrame components
		try {
			panel2 = new JPanel();
			JLabel label2 = new JLabel("<html><font color ='orange'>Insertion Sort</font></html>");
			panel2.add(label2);
			panel2.setBackground(Color.CYAN);
			frame.add(panel2);
			di = new DrawInsertion();
			frame.add(di);
			frame.setVisible(true);
		} finally {
			lock.unlock();
		}
		for(int i = 1; i < insertion.length; i++) //algorithm from lab
		{
			int target = insertion[i];
			int j = i - 1;
			while(j >= 0 && insertion[j] > target)
			{
				insertion[j+1] = insertion[j];
				j--;
			}
			check2 = j + 1;
			insertion[j+1] = target;
			di.repaint();
			try {
				Thread.sleep(150); //time between each frame reload
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
