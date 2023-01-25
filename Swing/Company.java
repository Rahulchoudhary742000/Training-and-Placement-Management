import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Company extends JFrame implements ActionListener{
	    JLabel l,l1,l2,l3,l4,bgr;
		JTextField t,t1,t2,t3;
		JCheckBox jc,jc1,jc2,jc3;
		JButton b,b1,b2,b3;
		int flag=1;
	    public Company(String title){
		   super(title);
		   setLayout(null);
		   
		   l=new JLabel("Company Name");
		   l1=new JLabel("Package");
		   l2=new JLabel("Branch");
           l3=new JLabel("NO. OF BACK");		  
		   l4=new JLabel("Btech");
		  
           l.setBounds(200,200,100,40);
		   l1.setBounds(200,250,80,40);
		   l2.setBounds(200,300,80,40);
		   l3.setBounds(200,350,80,40);
		   l4.setBounds(200,400,80,40);
		   
		   t=new JTextField(20);
		   t1=new JTextField(20);
		   jc=new JCheckBox("CSE");
		   jc1=new JCheckBox("ME");
		   jc2=new JCheckBox("EC");
		   jc3=new JCheckBox("CE");
		   
		   jc.setBounds(300,300,50,40);
		   jc1.setBounds(350,300,50,40);
		   jc2.setBounds(400,300,50,40);
		   jc3.setBounds(450,300,50,40);
		   
		   t.setBounds(300,205,180,37);
		   t1.setBounds(300,255,180,37);
		   
		   t2=new JTextField(20);
		   t3=new JTextField(20);
		   
		   t2.setBounds(300,355,180,37);
		   t3.setBounds(300,405,180,37);
           
           b=new JButton("Edit");
           b1=new JButton("Submit");
           b2=new JButton("Delete");
           b3=new JButton("<");
		 
           b.setBounds(210,470,120,40);
           b1.setBounds(360,470,120,40);
           b2.setBounds(235,520,220,40);
 		   b3.setBounds(20,15,50,30);
		   
		   ImageIcon img1=new ImageIcon("company.png");
		   bgr=new JLabel("",img1,JLabel.CENTER);
		   bgr.setBounds(0,0,700,900);
		   add(l);
		   add(l1);
		   add(l2);
		   add(l3);
		   add(l4);
		   add(t);
		   add(t1);
		   add(jc);
		   add(jc1);
		   add(jc2);
		   add(jc3);
		   add(t2);
		   add(t3);
		   add(b);
		   add(b1);
		   add(b2);
		   add(b3);
		   add(bgr);
		   
		   b.addActionListener(this);
		   b1.addActionListener(this);
		   b2.addActionListener(this);
		   b3.addActionListener(this);
	    }
	    public void actionPerformed(ActionEvent e){
	        try{
			    
				Class.forName("com.mysql.jdbc.Driver");
		        Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
                PreparedStatement ps=null;
				
			    if(e.getSource()==b1){
				    String company_name=t.getText();
					String pkg=t1.getText();
					//branch
					String branch="";
					String pt="";
					if(jc.isSelected()){
					  branch=branch+pt+"CSE";
					  pt=",";
					}	
					if(jc1.isSelected()){
					 branch=branch+pt+"ME";
					 pt=",";
					}	
					if(jc2.isSelected()){
					 branch=branch+pt+"EC";
					 pt=",";
					}	
					if(jc3.isSelected()){
					 branch=branch+pt+"CE";
					
					}	
					int back=Integer.parseInt(t2.getText());
				    int btech=Integer.parseInt(t3.getText());
					
					
			        
					if(flag==1){    
						ps=cn.prepareStatement("insert into company values(?,?,?,?,?)");
						ps.setString(1,company_name);
						ps.setString(2,pkg);
						ps.setString(3,branch);
						ps.setInt(4,back);
						ps.setInt(5,btech);
			  
						
						if(ps.executeUpdate()>0){
							
							 JOptionPane.showMessageDialog(null,"Record Inserted");
							 Company cp=new Company("Company Record");
		                     cp.setSize(700,900);
		                     cp.setVisible(true);
							 this.setVisible(false);
						}
						else{
							 JOptionPane.showMessageDialog(null,"Try Again");
						}	
					}
                    else if(flag==2){
						
						ps=cn.prepareStatement("update company set pkg=?,branch=?,back=?,btech=? where company_name=?");
						ps.setString(1,pkg);
						ps.setString(2,branch);
						ps.setInt(3,back);
						ps.setInt(4,btech);
						ps.setString(5,company_name);
						
				        if(ps.executeUpdate()>0){
							JOptionPane.showMessageDialog(null,"Record updated");
							
							Company cp=new Company("Company Record");
	                     	cp.setSize(700,900);
		                    cp.setVisible(true);
							this.setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null,"Try Again");
						}
					}	
                    				
  			    }
			    else if(e.getSource()==b){
					String company_name=t.getText();
					flag=2;
					
                    Statement st=cn.createStatement();
					ResultSet rs=st.executeQuery("select * from company where company_name='"+company_name+"'");
					
					if(rs.next()){
				    
				     t1.setText(rs.getString("pkg"));
				     String str=rs.getString("branch");
				     str=str.trim();
				     String a[]=str.split(",");
				     for(int i=0;i<a.length;i++){
					
					     if(a[i].equals("CSE")){
						
						     jc.setSelected(true); 
					        }
					     else if(a[i].equals("ME")){
						 
						     jc1.setSelected(true);
					        }
					     else if(a[i].equals("EC")){
						 
						     jc2.setSelected(true);
					        }
					     else if(a[i].equals("CE")){
						 
						     jc3.setSelected(true);
					        }
				        }
					    t2.setText(rs.getInt("back")+"");
						t3.setText(rs.getInt("btech")+"");
				    }
					else{
						
						JOptionPane.showMessageDialog(null,"Enter Correct Name");
					}
			    }
			    else if(e.getSource()==b2){
					String company_name=t.getText();
			      
                    ps=cn.prepareStatement("delete from company where company_name=?");
					ps.setString(1,company_name);
					
					if(ps.executeUpdate()>0){
						JOptionPane.showMessageDialog(null,"Record Deleted");
						
						Company cp=new Company("Company Record");
	                	cp.setSize(700,900);
		                cp.setVisible(true);
						this.setVisible(false);
					}
					else{
						
						JOptionPane.showMessageDialog(null,"No Records");
					}
				}
		    }
            catch(ClassNotFoundException en){
           	
                 JOptionPane.showMessageDialog(null,en.getMessage());
	        }
	        catch(SQLException en){
           	
		         JOptionPane.showMessageDialog(null,en.getMessage());
	        }
            if(e.getSource()== b3){
				 MidPage mp= new MidPage("Select page");
	             mp.setSize(720,900);
	             mp.setVisible(true);
	             mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 this.setVisible(false);
			} 
		 
	    }
	public static void main(String [] arr){
		
		Company cp=new Company("Company Record");
		cp.setSize(700,900);
		cp.setVisible(true);
		cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}