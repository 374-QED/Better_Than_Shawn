package implementation;

import java.io.*;
import java.util.*;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI4{
	private static nrLib test = new nrLib();
	public void start4(String temp_crn,String time, String room, String choice, String days)
	{
		try{

			JFrame frame = new JFrame();
			JPanel panel = new JPanel();

			JLabel title = new JLabel(" You moved this "+temp_crn+" to "+time+" in room "+room+". ");
			//JLabel verbLabel1 = new JLabel("484323 James White ");
			//JLabel adjLabel = new JLabel("Junior");

			

			frame.getContentPane().add(BorderLayout.NORTH, title);

			panel.setLayout(new GridBagLayout());
			panel.setBackground(Color.white);
			frame.getContentPane().add(panel);

			GridBagConstraints left = new GridBagConstraints();
			left.anchor = GridBagConstraints.EAST;
					        
			GridBagConstraints right = new GridBagConstraints();
			right.weightx = 2.0;
			right.fill = GridBagConstraints.HORIZONTAL;
			right.gridwidth = GridBagConstraints.REMAINDER;

			JLabel[] verbLabel = new JLabel[test.number_of_student(temp_crn)];
			JLabel[] adjLabel = new JLabel[test.number_of_student(temp_crn)];
			List<String> student = test.getStudentFromCourse_CRN(temp_crn);
			for(int x = 0; x < test.number_of_student(temp_crn); x++)
			{
				if(test.in_this_class(student.get(x),time,days))
				{
					verbLabel[x] = new JLabel(student.get(x)+" ");
					adjLabel[x] = new JLabel(test.findStudentname(student.get(x))+" "+test.classification(student.get(x)));
					panel.add(verbLabel[x], left);
					panel.add(adjLabel[x], right);
				} 
			}
			/*
			panel.add(verbLabel1, left);
			panel.add(adjLabel, right);
			panel.add(verbLabel2, left);
			panel.add(adjLabel2, right);
			*/
			//panel.add(list1, right);
			
			/*
			if(room_solve1.size() == 0)
			    panel.add(list3, right);
			else
			        panel.add(list2,right);
			*/
			   
			panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			frame.pack();
			frame.setVisible(true);
			frame.setTitle("Move Class Project");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);          // Center window.
		}
		catch(SQLException e)
		{

		}
				

	}
}