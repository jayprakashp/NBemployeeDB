/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empmgmt.dao;

import empmgmt.dbutil.DBConnection;
import empmgmt.pojo.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class EmployeeDAO {
   
    public static boolean addEmployee(Employee e)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("insert into employee(empid,empname,sal) values(?,?,?)");
        ps.setInt(1,e.getEmpNo());
        ps.setString(2,e.getEname());
        ps.setDouble(3,e.getSal());
        int ans=ps.executeUpdate();
        if(ans==1){
            return true;
        }
        return false;
    }
    
    public static Employee findEmployeeById(int id)throws SQLException{
          Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select * from employee where empid=?");
        ps.setInt(1,id);
       ResultSet rs=ps.executeQuery();
       Employee e=null;
       if(rs.next()){
           e=new Employee();
          e.setEmpNo(id);
          e.setEname(rs.getString("empname"));
          e.setSal(rs.getDouble("sal"));
          return e;
       }
       return e;
    }
    

public static ArrayList<Employee> getAllEmployee()throws SQLException{
Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select * from employee");
       ResultSet rs=ps.executeQuery();
       ArrayList<Employee> list=new ArrayList<>();
       while(rs.next()){
       Employee e=new Employee();
          e.setEmpNo(rs.getInt("empid"));
          e.setEname(rs.getString("empname"));
          e.setSal(rs.getDouble("sal"));
         list.add(e);
       }
       return list;
}


public static boolean updateEmployee(Employee e) throws SQLException{
    Connection con=DBConnection.getConnection();
    PreparedStatement ps=con.prepareStatement("update employee set empname=?,sal=? where empid=?");
    ps.setString(1,e.getEname());
    ps.setDouble(2, e.getSal());
    ps.setInt(3,e.getEmpNo());
    int result=ps.executeUpdate();
    if(result==1){
        return true;
    }
  return false;
}
public static boolean deleteEmployee(int id) throws SQLException{
    Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("delete from employee where empid=?");
        ps.setInt(1,id);
       int ans=ps.executeUpdate();
      
       if(ans!=0){
           return true;
         
       }
       return false;
}
}
