 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */ 
package dao;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import model.Bill;
/**
 *
 * @author asus
 */
public class BillDao {
        public static String getId(){
            int id = 1;
            try{
                ResultSet rs = DbOperations.getData("select max(id) from bill");
                if(rs.next()){
                    id = rs.getInt(1);
                    id = id + 1;
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            return String.valueOf(id);
        }
        
        public static void save(Bill bill){
            String query = "insert into Bill values('"+bill.getId()+"','"+bill.getName()+"','"+bill.getMobilNumber()+"','"+bill.getEmail()+"','"+bill.getDate()+"','"+bill.getTotal()+"','"+bill.getCreatedBy()+"')";
            DbOperations.setDataOrDelete(query, "Bill Details Added Successfully");
        }
        
        public static ArrayList<Bill> gettAllRecordsByInc(String date){
            ArrayList<Bill> arraylist = new ArrayList<>();
            try{
                ResultSet rs = DbOperations.getData("select *from bill where date like '%"+date+"%'");
                while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setName(rs.getString("Name"));
                bill.setMobilNumber(rs.getString("MobileNumber"));
                bill.setEmail(rs.getString("Email"));
                bill.setDate(rs.getString("Date"));
                bill.setTotal(rs.getString("Total"));
                bill.setCreatedBy(rs.getString("CreatedBy"));
                arraylist.add(bill);
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            return arraylist;
        }
        
        public static ArrayList<Bill> gettAllRecordsByDesc(String date){
            ArrayList<Bill> arraylist = new ArrayList<>();
            try{
                ResultSet rs = DbOperations.getData("select *from bill where date like '%"+date+"%' order By id DESC");
                while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setName(rs.getString("Name"));
                bill.setMobilNumber(rs.getString("MobileNumber"));
                bill.setEmail(rs.getString("Email"));
                bill.setDate(rs.getString("Date"));
                bill.setTotal(rs.getString("Total"));
                bill.setCreatedBy(rs.getString("CreatedBy"));
                arraylist.add(bill);
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            return arraylist;
        }
}
