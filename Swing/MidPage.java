import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MidPage extends JFrame implements ActionListener{
     JButton jb,jb1,jb2,jb3;
	 JLabel bgr;
	 
    public MidPage(String title){
		super(title);
		setLayout(null);
		
		jb=new JButton("STUDENT");
	    jb1=new JButton("COMPANY");
		jb2=new JButton("Search");
		jb3=new JButton("Logout");
		
		jb.setBounds(100,300,120,50);
		jb1.setBounds(300,300,120,50);
		jb2.setBounds(500,300,120,50);
		jb3.setBounds(300,400,120,50);
		
		  ImageIcon img1=new ImageIcon("midpage.png");
		  bgr=new JLabel("",img1,JLabel.CENTER);
		  bgr.setBounds(0,0,720,900);
		
		add(jb);
		add(jb1);
		add(jb2);
		add(jb3);
		add(bgr);
		jb.addActionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
	}
    public void actionPerformed(ActionEvent e){
		
		 if(e.getSource()==jb){
			  
			  Student std=new Student ("Student Record");
		      std.setSize(700,900);
	          std.setVisible(true);
		      std.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  this.setVisible(false);
			  
		    }
		 else if(e.getSource()==jb1){
			 
		     Company cp=new Company("Company Record");
		     cp.setSize(700,900);
		     cp.setVisible(true);
		     cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 this.setVisible(false);
			 
		    }
		 else if(e.getSource()==jb3){
			 
			 Admin ad=new Admin("Login");
	         ad.setSize(700,900);
	         ad.setVisible(true);
	         ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 this.setVisible(false);
		    }
		 else if(e.getSource()==jb2){
			 
			 Search se=new Search("Search");
	         se.setSize(1000,900);
	         se.setVisible(true);
			 this.setVisible(false);
		 }
		
	}



 public static void main(String []arr){
	  MidPage mp= new MidPage("Select page");
	  mp.setSize(720,900);
	  mp.setVisible(true);
	  mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
    }

}