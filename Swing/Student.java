import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Student extends JFrame implements ActionListener{
        JLabel l,l1,l2,l3,l4,l5,l6,l7,l8,l9,bgr;
		JTextField t,t1,t2,t3,t4,t5,t6,t7;
		JRadioButton c,c1;
		ButtonGroup bg,bg1;
		JCheckBox jb,jb1,jb2,jb3;
		JButton b,b1,b2,b3;
		int flag=1;
		
		public Student(String title){
		  super(title);
		  setLayout(null);
		  l=new JLabel("Student_Id");
		  l.setBounds(200,40,70,20);
		  t=new JTextField();
		  t.setBounds(270,40,190,30);
		  l1=new JLabel("Name");
		  l1.setBounds(210,80,50,20);
		  t1=new JTextField();
		  t1.setBounds(270,80,190,30);
		  l2=new JLabel("Mobile");
		  l2.setBounds(210,120,50,20);
		  t2=new JTextField();
		  t2.setBounds(270,120,190,30);
		  l3=new JLabel("Email Id");
		  l3.setBounds(210,160,50,20);
		  t3=new JTextField();
		  t3.setBounds(270,160,190,30);
		  l4=new JLabel("Gender");
		  l4.setBounds(210,200,50,20);
		  
		  c=new JRadioButton("Male");
		  c.setBounds(270,200,60,20);
		  c1=new JRadioButton("Female");
		  c1.setBounds(330,200,90,20);
		  bg=new ButtonGroup();
		  
		  bg.add(c);
		  bg.add(c1);
		  l5=new JLabel("Branch");
		  l5.setBounds(210,240,50,20);
		  jb=new JCheckBox("CSE");
		  jb.setBounds(270,240,50,20);
		  jb1=new JCheckBox("ME");
		  jb1.setBounds(320,240,50,20);
		  jb2=new JCheckBox("EC");
		  jb2.setBounds(370,240,50,20);
		  jb3=new JCheckBox("CE");
		  jb3.setBounds(420,240,50,20);
		  
		  l6=new JLabel("Secondary");
		  l6.setBounds(210,280,80,20);
		  t4=new JTextField();
		  t4.setBounds(280,280,180,30);
		  l7=new JLabel("High_Secondary");
		  l7.setBounds(210,320,120,20);
		  t5=new JTextField();
		  t5.setBounds(310,320,150,30);
		  l8=new JLabel("Back");
		  l8.setBounds(210,360,50,20);
		  t6=new JTextField();
		  t6.setBounds(270,360,190,30);
		  l9=new JLabel("Btech");
		  l9.setBounds(210,400,50,20);
		  t7=new JTextField();
		  t7.setBounds(270,400,190,30);
		  
		  b=new JButton ("submit");
		  b.setBounds(220,440,230,30);
		  b1=new JButton("edit");
		  b1.setBounds(220,480,110,30);
		  b2=new JButton("delete");
		  b2.setBounds(340,480,110,30);
		  b3=new JButton("<");
		  b3.setBounds(20,15,50,30);
		  add(l);
		  add(t);
		  add(l1);
		  add(t1);
		  add(l2);
		  add(t2);
		  add(l3);
		  add(t3);
		  add(l4);
		  add(c);
		  add(c1);
		  add(l5);
		  add(jb);
		  add(jb1);
		  add(jb2);
		  add(jb3);
		  add(l6);
		  add(t4);
		  add(l7);
		  add(t5);
		  add(l8);
		  add(t6);
		  add(l9);
		  add(t7);
		  
		  ImageIcon img1=new ImageIcon("student.png");
		  bgr=new JLabel("",img1,JLabel.CENTER);
		  bgr.setBounds(-130,0,850,900);
		  
		  b.addActionListener(this);
		  b1.addActionListener(this);
		  b2.addActionListener(this);
		  b3.addActionListener(this);
		  add(b);
		  add(b1);
		  add(b2);
		  add(b3);
		  
		  add(bgr);
        }
		public void actionPerformed(ActionEvent ae){
			try{
				
				 Class.forName("com.mysql.jdbc.Driver");
		         Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
                 Statement st=cn.createStatement();
				
			    if(ae.getSource()==b){	
					String stud=t.getText();
					String name=t1.getText();
					String mobile=t2.getText();
					String email=t3.getText();
					String gender="";
					
					if(c.isSelected()){
					
						gender="Male";
					
					}
					else if(c1.isSelected()){
					
						gender="Female";
					}	
					//branch
					String branch="";
					String pt="";
					if(jb.isSelected()){
					  branch=branch+pt+"CSE";
					  pt=",";
					}	
					if(jb1.isSelected()){
					 branch=branch+pt+"ME";
					 pt=",";
					}	
					if(jb2.isSelected()){
					 branch=branch+pt+"EC";
					 pt=",";
					}	
					if(jb3.isSelected()){
					 branch=branch+pt+"CE";
					
					}	
					int secondary=Integer.parseInt(t4.getText());
					int highsecondary=Integer.parseInt(t5.getText());
					int back=Integer.parseInt(t6.getText());
					int btech=Integer.parseInt(t7.getText());
				  
		            if(flag==1){
						
					    if(st.executeUpdate("insert into stud_info values('"+stud+"','"+name+"','"+mobile+"','"+email+"','"+gender+"','"+branch+"',"+secondary+","+highsecondary+","+back+","+btech+")")>0){
						
						     JOptionPane.showMessageDialog(null,"Record Inserted");
						     Student std=new Student ("Student Record");
		                     std.setSize(700,900);
		                     std.setVisible(true);
				             this.setVisible(false);
						}
						else{
						
						     JOptionPane.showMessageDialog(null,"Try again");
						} 
					}
                    else if(flag==2){
						
						if(st.executeUpdate("update stud_info set student_id='"+stud+"',name='"+name+"',mobile='"+mobile+"',email='"+email+"',gender='"+gender+"',branch='"+branch+"',10th="+secondary+",12th="+highsecondary+",back="+back+",btech="+btech+" where student_id='"+stud+"'")>0){
						
						     JOptionPane.showMessageDialog(null,"Record Updated");
						     Student std=new Student ("Student Record");
		                     std.setSize(700,900);
		                     std.setVisible(true);
				             this.setVisible(false);
						}
						else{
						
						     JOptionPane.showMessageDialog(null,"Try again");
						} 
						
						
						
					}					
			    }	
		        else if(ae.getSource()==b1){
					
					 String stud=t.getText();
			         flag=2;
					/* "rahul_123  */
					
					 ResultSet rs=st.executeQuery("select * from stud_info where student_id='"+stud+"'");
					 
					 if(rs.next()){
				    
				     t1.setText(rs.getString("name"));
				     t2.setText(rs.getString("mobile"));
					 t3.setText(rs.getString("email"));
				     //radio button
				     if(rs.getString("gender").equals("Male")){
					 
					     c.setSelected(true);
				        } 
				     else{
					 
					     c1.setSelected(true);
				        }
					 //checkbox
               	     String str=rs.getString("branch");
				     str=str.trim();
				     String a[]=str.split(",");
				     for(int i=0;i<a.length;i++){
					
					     if(a[i].equals("CSE")){
						
						     jb.setSelected(true); 
					        }
					     else if(a[i].equals("ME")){
						 
						     jb1.setSelected(true);
					        }
					     else if(a[i].equals("EC")){
						 
						     jb2.setSelected(true);
					        }
					     else if(a[i].equals("CE")){
						 
						     jb3.setSelected(true);
					        }
				        }
					 t4.setText(rs.getInt("10th")+"");
					 t5.setText(rs.getInt("12th")+"");
					 t6.setText(rs.getInt("back")+"");
					 t7.setText(rs.getInt("btech")+"");
					
					}
					else{
						 JOptionPane.showMessageDialog(null,"No Record");
						
					}
				}
			  
			  //delete
			  else if(ae.getSource()==b2){
					 String stud=t.getText();
			
			         if(st.executeUpdate("delete from stud_info where student_id='"+stud+"'")>0){
				
				     JOptionPane.showMessageDialog(null,"Record deleted");	
					 Student std=new Student ("Student Record");
		             std.setSize(700,900);
		             std.setVisible(true);
				     this.setVisible(false);
			        }
			         else{
				
				     JOptionPane.showMessageDialog(null,"No Record For Delete");
			        }
					
					
				}
			}	
			
		    catch(ClassNotFoundException en){
           	
                 JOptionPane.showMessageDialog(null,en.getMessage());
	        }
	        catch(SQLException en){
           	
		         JOptionPane.showMessageDialog(null,en.getMessage());
	        }
		    if(ae.getSource()== b3){
				 MidPage mp= new MidPage("Select page");
	             mp.setSize(720,900);
	             mp.setVisible(true);
	             mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 this.setVisible(false);
			}
	    }
    public static void main (String []arr){
	    Student std=new Student ("Student Record");
		std.setSize(700,900);
		std.setVisible(true);
		std.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}