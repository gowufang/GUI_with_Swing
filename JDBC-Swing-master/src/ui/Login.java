package ui;
import javax.swing.*;
import dao.IUserDao;
import dao.impl.UserDaoImpl;
import util.DBCon;
import entity.User;
import java.awt.*;   
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Login extends JFrame{
	static boolean flag = true;
    JTextField jTextField ;
    JPasswordField jPasswordField;
    JLabel jLabel1,jLabel2;
    JPanel jp1,jp2,jp3;
    JButton jb1,jb2; 
    public Login(){
        jTextField = new JTextField(12);
        jPasswordField = new JPasswordField(13);
        jLabel1 = new JLabel("用户");
        jLabel2 = new JLabel("密码");
        jb1 = new JButton("确认");
        jb2 = new JButton("取消");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        
        jb1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub
				String user = jTextField.getText().toString();
				String pass = new String(jPasswordField.getPassword());
				
				Connection con = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql:///test","root","123456");
					System.out.println("ssd");
					Statement stat = con.createStatement();
					
					String sql = "select * from user where name = '" + user +"'and birth = '" + pass+"'";
					ResultSet res = stat.executeQuery(sql);
//					while(res.next()){
						Login.this.setVisible(false);
						MainFrame mainframe = new MainFrame();
						mainframe.setVisible(true);
//					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		
        });
        this.setLayout(new GridLayout(3,1));
        
        jp1.add(jLabel1); 
        jp1.add(jTextField);
        
        jp2.add(jLabel2);
        jp2.add(jPasswordField);
        
        jp3.add(jb1);
        jp3.add(jb2); 
        
        this.add(jp1);
        this.add(jp2);
        this.add(jp3); 
       
        this.setSize(300, 200);
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("登录");
         
    }
    protected User User(int userid) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
