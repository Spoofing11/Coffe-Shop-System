/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author asus
 */
public class UserDao {
    public static void save(User user){
        String query = "insert into user(name,email,mobilenumber,address,password,securityquestion,answer,status) values('"+user.getName()+"', '"+user.getEmail()+"', '"+user.getMobilenumber()+"', '"+user.getAddress()+"', '"+user.getPassword()+"', '"+user.getSecurityquestion()+"', '"+user.getAnswer()+"','false')";
        DbOperations.setDataOrDelete(query, "Registered Succesfully! Wait for Admin Approval!") ;
    }
    
    public static User login(String email,String password){
        User user = null;
        try{
            ResultSet rs = DbOperations.getData("select * from user where email='"+email+"' and password='"+password+"'");
            while (rs.next()){
                user = new  User();
                user.setStatus(rs.getString("status"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }
    
    public static User getSecurityQuestion(String email){
        User user = null;
        try{
            ResultSet rs = DbOperations.getData("select * from user where email = '" +email+ "'");
                    while(rs.next()){
                        user = new User();
                        user.setSecurityquestion(rs.getString("SecurityQuestion"));
                        user.setAnswer(rs.getString("answer"));
                    }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }
    
    public static void update(String email, String newpassword){
        String query = "Update user set password ='"+newpassword+"' where email = '"+email+"'";
        DbOperations.setDataOrDelete(query, "Password Changed Successfully");
    }
    
    public static ArrayList<User> getAllRecords(String email){
        ArrayList<User> arraylist = new ArrayList<>();
        try{
            ResultSet rs = DbOperations.getData("select * from user where email like '%"+email+"%'");
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobilenumber(rs.getString("mobilenumber"));
                user.setAddress(rs.getString("address"));
                user.setSecurityquestion(rs.getString("securityquestion"));
                user.setStatus(rs.getString("status"));
                arraylist.add(user);
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return arraylist;
    }
    
    public static void changestatus(String email,String status){
        String query = "update user set status='"+status+"' where email='"+email+"'";
        DbOperations.setDataOrDelete(query, "status changed Succesfully");
    }
    
    public static void changePassword(String email, String oldpassword, String newpassword){
        try{
            ResultSet rs = DbOperations.getData("select *from user where email='"+email+"' and password='"+oldpassword+"'");
            if(rs.next()){
                update(email, newpassword);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Old Password is Wrong");
        }
    }
    
    public static void changeSecurityQuestion(String email, String password, String securityQuestion, String answer){
        try{
            ResultSet rs = DbOperations.getData("select *from user where email='"+email+"' and password='"+password+"'");
            if(rs.next()){
                update(email,securityQuestion,answer);
            }
            else{
                JOptionPane.showMessageDialog(null, "Password is Wrong");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void update(String email, String securityQuestion, String answer){
        String query = "update user set securityQuestion='"+securityQuestion+"', answee='"+answer+"' where email='"+email+"'";
        DbOperations.setDataOrDelete(query, "Security Question Changed Succesfully");
    }
}
