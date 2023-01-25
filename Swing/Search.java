import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Search extends JFrame implements ActionListener{
	  JLabel jl,jl1,jl2;
	  JTextField jt,jt1,jt2,jt3;
	  JComboBox jc;
	  JButton jb,jb1;
	  //table
      JTable t;
	  JScrollPane jsp; 
	  //GroupLayout gl;
	  //String st;
	  DefaultTableModel dtm=null;
	  
      public Search(String title){
	   super(title);
	   setLayout(null);
	   //Table
	   t=new JTable(); 
	   dtm=new DefaultTableModel();
	   String arr[]=new String[]{"student_id","name","moblie","email","gender","branch","10th","12th","back","btech"};
	   
	   dtm.setColumnIdentifiers(arr);
	   t.setModel(dtm);
	   jsp=new JScrollPane(t);
	   jsp.setBounds(60,0,850,450);
	   add(jsp);
	   
	   jl= new JLabel("NO OF BACK");
	   jl.setBounds(100,550,80,50);
	   jt=new JTextField(20);
	   jt.setBounds(180,555,180,40);
	   
	   jl1=new JLabel("Min. Agg");
	   jl1.setBounds(400,550,80,50);
	   jt1=new JTextField(20);
	   jt1.setBounds(500,555,180,40);
	   //list
	   jl2=new JLabel("Company");
	   jl2.setBounds(720,550,80,40);
	   jc=new JComboBox();
	   jc.setBounds(800,550,100,40);
	   
	   //button
	   jb=new JButton("Search");
	   jb.setBounds(430,700,120,50);
	   jb1=new JButton("<");
	   jb1.setBounds(10,15,50,30);
	    add(jb);
	    add(jc);
	    add(jl);
	    add(jt);
	    add(jl1);
	    add(jt1);
	    add(jl2);
		add(jb1);
		
	    jb.addActionListener(this);  
	    jb1.addActionListener(this);
	   
	    
	    try{
	     
			 Class.forName("com.mysql.jdbc.Driver");
		     Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
             Statement st=cn.createStatement();
	         ResultSet rs=st.executeQuery("select * from company");
			 
			 while(rs.next()){
				 
				 jc.addItem(rs.getString("company_name"));
				 
			 }
		
		    }
		 catch(ClassNotFoundException en){
           	
             JOptionPane.showMessageDialog(null,en.getMessage());
	        }
	     catch(SQLException en){
           	
		     JOptionPane.showMessageDialog(null,en.getMessage());
	        }
	
	} 
	 
      public void actionPerformed(ActionEvent e){ 
	     String branch=null;
	     try{
	     
			 Class.forName("com.mysql.jdbc.Driver");
		     Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
             
			 String company_name=jc.getSelectedItem() + "";
             Statement st=cn.createStatement();
			 ResultSet rs=st.executeQuery("select * from company where company_name='"+company_name+"'");
	
			 if(rs.next()){
				 
				 jt.setText(rs.getInt("back")+"");
				 jt1.setText(rs.getInt("btech")+"");
				 
				  branch=rs.getString("branch");
				
				 
			 }
			 
			 if(e.getSource()==jb){
				 
                 /*int rowCount = dtm.getRowCount();
                 for (int i = rowCount - 1; i >= 0; i--) {
					
                     dtm.removeRow(i);
                 }*/
				 String a[]=branch.split(",");
				 dtm.setRowCount(0);
				 int back=Integer.parseInt(jt.getText());
				 int btech=Integer.parseInt(jt1.getText());
				 for(int i=0;i<a.length;i++){
						rs=st.executeQuery("select * from stud_info where back<='"+back+"' AND btech>='"+btech+"'   AND branch='"+a[i]+"' ");
						while(rs.next()){
						
							dtm.addRow(new Object[]{rs.getString("student_id"),rs.getString("name"),rs.getString("mobile"),rs.getString("email"),rs.getString("gender"),rs.getString("branch"),rs.getString("10th"),rs.getString("12th"),rs.getString("back"),rs.getString("btech")});
						}
				    }
             
			    }
					
		        else if(e.getSource()==jb1){
			
			     MidPage mp= new MidPage("Select page");
	             mp.setSize(720,900);
	             mp.setVisible(true);
			     this.setVisible(false);
	             mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
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
      Search se=new Search("Search");
	  se.setSize(1000,900);
	  se.setVisible(true);
  }
}