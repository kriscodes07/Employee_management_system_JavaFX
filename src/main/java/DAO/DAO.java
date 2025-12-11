package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Employee_model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {



    public  void adddata(Employee_model model) throws SQLException {

        Connection con=DBconnection.Connect();

        String query="insert into emp(name,department,mail,em_code) values(?,?,?,?)";


try {
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setString(1, model.getName());
    pstmt.setString(2, model.getDepartment());
    pstmt.setString(3, model.getMail());
    pstmt.setString(4, model.getEmcode());


    int affeceted= pstmt.executeUpdate();
    if(affeceted>0){
        System.out.println("data inserted successfully");
    }else{
        System.out.println("unable to insert data");
    }



} catch (Exception e) {
    throw new RuntimeException(e);
}

    }



    public boolean delete_data(String empcode){


        Connection connection=DBconnection.Connect();

        String query="delete from emp where em_code=?";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,empcode);
            int executed= preparedStatement.executeUpdate();
            if(executed>0){
                return true;
            }

        } catch (SQLException e) {

                           e.printStackTrace();
        }

return false;

    }



    public ObservableList<Employee_model> table() {

        ObservableList<Employee_model> list = FXCollections.observableArrayList();
        String query = "select id,name,department,mail,em_code from emp";

        try {
            Connection con = DBconnection.Connect();
            PreparedStatement pstm = con.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Employee_model em = new Employee_model();
                em.setId(String.valueOf(rs.getInt("id")));
                em.setName(rs.getString("name"));
                em.setDepartment(rs.getString("department"));
                em.setMail(rs.getString("mail"));
                em.setEmcode(rs.getString("em_code"));

                list.add(em);  // ✔ add to list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;   // ✔ return list
    }


    public  void update(String name,String dept,String emcpde){


        Connection con=DBconnection.Connect();
        String query="update emp set name=?,department=? where em_code=? ";


        try{

            PreparedStatement pstm= con.prepareStatement(query);
            pstm.setString(1,name);
            pstm.setString(2,dept);
            pstm.setString(3,emcpde);

            int rows=pstm.executeUpdate();
            if(rows>0){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Data updated successfully");
                alert.show();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Unable to update");
               alert.show();
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public  void  delete(String emcode){

        Connection c=DBconnection.Connect();
        String q="delete from emp where em_code=?";


        try{
            PreparedStatement p= c.prepareStatement(q);
            p.setString(1,emcode);

            int r=p.executeUpdate();
            if(r>0){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Data Deleted successfully");
                alert.show();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Unable to Delete");
                alert.show();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }



}
