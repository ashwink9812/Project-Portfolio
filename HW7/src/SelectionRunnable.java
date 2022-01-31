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

public class SelectionRunnable extends ConcurrencyGUI implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawSelection ds;
	public int x = 0;
	public int y = 800; 
	private int check1;
	private int check2;
	
	public class DrawSelection extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g)//draws the rectangles
		{
			super.paintComponent(g); 
			y = 800; 
			this.setBackground(Color.pink);
			g.setColor(Color.blue);
			for (int i = 0 ; i < selection.length ; i++)
			{
			
				g.drawRect (x, y, selection[i], 12);
				g.fillRect(x, y, selection[i], 12);
				y = y - 15;
			}
			g.setColor(Color.RED); 
			g.fillRect(x, 800 - check1 * 15, selection[check1], 12);
			g.fillRect(x, 800 - check2 * 15, selection[check2], 12);
		}
	}
	@Override
	public void run() 
	{

		lock.lock(); // so that i can add frame components
		try {
			panel3 = new JPanel();
			panel3.setBackground(Color.pink);
			JLabel label3 = new JLabel("<html><font color ='blue'>Selection Sort</font></html>");
			panel3.add(label3);
			ds = new DrawSelection();
			frame.add(panel3);

			frame.add(ds);
		} finally {
			lock.unlock();
			frame.setVisible(true);
				}
		int i,j,first,temp;
		for(i = selection.length - 1; i > 0; i--)
		{
			first = 0;
			for(j = 1; j <= i; j++)
			{
				if(selection[j] > selection[first])
				{
					check1 = first; 
					first = j;
				}
			}
			temp = selection[first];
			selection[first] = selection[i];
			selection[i] = temp; 
			check2 = i;
			ds.repaint(); 
			try {
				Thread.sleep(150);
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


