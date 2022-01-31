/**
 * Ashwin Krishnamurthi (ak8ae)
 * HW7
 * Code for all sorts directly from slides
https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array <- Shuffling the array
https://stackoverflow.com/questions/15422100/animating-graphical-sort <- Displaying the rectangles 
Help from Kevin Le
Questions answered on bottom of this
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConcurrencyGUI extends JFrame
{
	public static JFrame frame;  
	public int count = 0;

	public JPanel panel;
	public  JPanel panel2;
	public JPanel panel3;
	
	protected static ReentrantLock lock;

	public static int[] dArray;

	public static int[] insertion; //for future deep copies of the array
	public static int[] selection;
	public static int[] bubble;
	public ConcurrencyGUI()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500,900);
		frame.setTitle("Sorting Algorithms"); 
		frame.setLayout(new GridLayout(1,0));     

		frame.getContentPane().setBackground(Color.pink); 
		frame.setLocationRelativeTo(null);	

	}

	public static void shuffleArray(int[] array) //to randomly shuffle the array
	{
		ArrayList<Integer> list = new ArrayList<>();
		for (int i : array) {
			list.add(i);
		}

		Collections.shuffle(list);

		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		} 
	}
	public static void main(String[] args)
	{
		lock = new ReentrantLock();
		dArray = new int[50];
		for(int i = 0; i < 50; i++)
		{
			dArray[i] = i + 1;
		}
		shuffleArray(dArray); //Shuffles the values
		insertion = dArray.clone(); //deep copies of the array
		bubble = dArray.clone();
		selection = dArray.clone();

		InsertionRunnable i = new InsertionRunnable(); 
		SelectionRunnable s = new SelectionRunnable();
		BubbleRunnable b = new BubbleRunnable();

		Thread t = new Thread(i); //creating the threads
		t.setPriority(10);
		Thread t2 = new Thread(s);
		t2.setPriority(5);
		Thread t3 = new Thread(b);
		t3.setPriority(1);

		t.start(); //starting them concurrently
		t2.start();
		t3.start();
		
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
