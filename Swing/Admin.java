import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class Admin extends JFrame implements ActionListener{
	
	 JLabel jl,jl1,background;
	 JTextField jt;
	 JPasswordField jp;
	 JButton b;
	 
	
	 public Admin(String title){
		 super(title);
		 setLayout(null);
		 
		 jl=new JLabel("Username");
		 jl1=new JLabel("Password");
		 
		 jl.setBounds(200,200,80,50);
		 jl1.setBounds(200,250,80,50);
		 
		 jt=new JTextField(20);
		 jp=new JPasswordField(20);
		 
		 jt.setBounds(270,210,180,30);
		 jp.setBounds(270,260,180,30);
		 
		 b=new JButton("Login");
		 b.setBounds(270,320,100,40);
		 
		 
		 ImageIcon img=new ImageIcon("admin.png");
		 background=new JLabel("",img,JLabel.CENTER);
		 background.setBounds(-140,180,800,900);
		 add(background);
		 
		 add(jl);
		 add(jl1);
		 add(jt);
		 add(jp);
		 add(b);
	     b.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		
		try{
			
			     Class.forName("com.mysql.jdbc.Driver");
			     Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
				 Statement st=cn.createStatement();
				 
				
				 String username=jt.getText();
				 String pass=jp.getText();
				
			     ResultSet rs=st.executeQuery("select password from admin where username='"+username+"'");
				
				if(rs.next()){
					if(rs.getString("password").equals(pass)){
						
					  MidPage mp= new MidPage("Select page");
	                  mp.setSize(720,900);
	                  mp.setVisible(true);
	                  mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					  this.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null,"Invalid Password");
					}
					
				}
				else{
					
					JOptionPane.showMessageDialog(null,"Invalid Username");
					
				}
				
			
			
		
			
		} 
		catch(ClassNotFoundException en){
           	
             JOptionPane.showMessageDialog(null,en.getMessage());
	    }
	    catch(SQLException en){
           	
		     JOptionPane.showMessageDialog(null,en.getMessage());
	    }
		
		
		
		
	} 
	
	
	
	
   public static void main(String [] arr){
	   Admin ad=new Admin("Login");
	   ad.setSize(700,900);
	   ad.setVisible(true);
	   ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	  
   }	
	
	
}