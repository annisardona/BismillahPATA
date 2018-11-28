/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bismillahpata.Controller;

import bismillahpata.Model.DataSession;
import bismillahpata.Model.DataTransaksi;
import bismillahpata.Model.Produk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import koneksi.Koneksi;

/**
 *
 * @author Suryadi
 */
public class DataTransaksiController extends DataTransaksi {
    Koneksi db;
    Connection con;
    PreparedStatement pst;
    
    public DataTransaksiController() {
        super();
        db = new Koneksi();
        con = db.getConnection();
        /**namaBahan = new ArrayList<JTextField>();
        persentaseBahan = new ArrayList<JTextField>();
        **/
    }
    public String generate(){
        String prefix = "DT";
        String generatedId = null;
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(RIGHT (id,2)) as id from data_transaksi");
            while(rs.next()){
                if(rs.first() == false){
                    generatedId = "DT01";
                }else{
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int noLong = no.length();
                        for(int a = 0; a<2-noLong; a++){
                            no = "0" + no;
                        }
                        generatedId = prefix + no;
                }
                System.out.println(generatedId);
//                DataSession.setIdProduk(generatedId);
                return generatedId;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return generatedId;
//        String prefix = "DT";
//        String generatedId = null;
//        try{
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT COUNT(id) as id from data_transaksi");
//            if(rs.next()){
//                int id = rs.getInt(1)+1;
//                generatedId = prefix + new Integer(id).toString();
//                checkData(generatedId);
//                System.out.println(generatedId);
//                return generatedId;
//            }
//                       
//        }catch(SQLException e){
//            System.out.println(e.getMessage());
//        }
//        return generatedId;
    }
    
//    public boolean checkData(String generatedId){
//        String sql = "";
//        String idData = null;
//        String prefix = "DT";
////        String generatedId = null;
//        ResultSet rs = null;
//        try{
//            sql = "SELECT * FROM data_transaksi WHERE id = ?";
//            pst = con.prepareStatement(sql);
//            
//            pst.setString(1, generatedId);
//            
//            rs = pst.executeQuery();
//            while (rs.next()){
//                idData = rs.getString("id");
//                System.out.println("id Dataaaaaaaaaa "+idData);
//                return true;
//            }rs.last();
//            
//            if(rs.getRow() == 1){
////                System.out.println("id Data "+idData);
////                int id = rs.getInt(1)+1;
////                generatedId = prefix + new Integer(id).toString();
////                System.out.println(generatedId);
////                return generatedId;
//                JOptionPane.showMessageDialog(null, "Data Sudah Ada");
//                //masukin halaman baru
//            }else{
////                JOptionPane.showMessageDialog(null, "Username dan Password Salah");
//            }
//        }catch(SQLException e){
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }
    
    public int inputNamaData(DataTransaksi dts){
        int res = 0;
        //String id = generate();
        ResultSet rs = null;
        
        String sql = "";      
        try{
            sql = "INSERT INTO data_transaksi(id, nmDataTransaksi, JumlahBarang, Satuan, HargaSatuan, idProduk, ket)"
                    + " VALUES (?,?,?,?,?,?,?)";
            
            pst = con.prepareStatement(sql);
            
            pst.setString(1, dts.getIdData());
            pst.setString(2, dts.getNamaData());
            pst.setString(3, dts.getJumlah());
            pst.setString(4, dts.getSatuan());
            pst.setString(5, dts.getHargaSatuan());
            pst.setString(6, dts.getIdProduk());
            pst.setString(7, dts.getKet());
            
            res = pst.executeUpdate();
                            
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }
    
    public int updateDataTransaksi(DataTransaksi dts){
        int res = 0;
        String sql = "";
        try{
            sql = "UPDATE data_transaksi SET nmDataTransaksi =?, JumlahBarang =?, Satuan =?, HargaSatuan =? WHERE id = '"+DataSession.getIdDataTransaksi()+"' ";
           
            pst = con.prepareStatement(sql);
            
            //pst.setString(1, prsh.getIdPer());
            pst.setString(1, dts.getNamaData());
            pst.setString(2, dts.getJumlah());
            pst.setString(3, dts.getSatuan());
            pst.setString(4, dts.getHargaSatuan());
            
            
            res = pst.executeUpdate();
                    
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }
    
    public void deleteData(DataTransaksi dts){
        String sql = "";
        try{
            sql = "UPDATE data_transaksi SET isActive = 0 WHERE id = '"+DataSession.getIdDataTransaksi()+"'";
            
            pst = con.prepareStatement(sql);
          
            System.out.println("id Data untuk di Delete "+DataSession.getIdDataTransaksi());
            pst.executeUpdate();   
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
   
    public int getDataBy(String idProduk){
        String sql = "";
        ResultSet rs = null;
        try{   
            sql = "SELECT COUNT(idDataTransaksi) as id FROM data_transaksi WHERE idProduk = ?";
            pst = con.prepareStatement(sql);
                      
            pst.setString(1, DataSession.getIdProduk());
            System.out.println("id Produk "+DataSession.getIdProduk());
            rs = pst.executeQuery();
            
            if(rs.next()){
                int id = rs.getInt("id");
                System.out.println("iniiiiiiii ID "+id);
                return id;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<DataTransaksi> SelectAll(String idProduk){
        List<DataTransaksi> ListDTS = null;
        ResultSet rs;
        try {
            ListDTS = new ArrayList<DataTransaksi>();
            
            String query =  "SELECT id, nmDataTransaksi, JumlahBarang, HargaSatuan, Satuan FROM data_transaksi WHERE idProduk = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, DataSession.getIdProduk());
            System.out.println("id Produk "+DataSession.getIdProduk());
            rs =ps.executeQuery();
            
            while(rs.next()){
                DataTransaksi ListData = new DataTransaksi();
                ListData.setIdData(rs.getString("id"));
                ListData.setNamaData(rs.getString("nmDataTransaksi"));
                ListData.setJumlah(rs.getString("JumlahBarang"));
                ListData.setHargaSatuan(rs.getString("HargaSatuan"));
                ListData.setSatuan(rs.getString("Satuan"));
                ListDTS.add(ListData);
                //BMg.setDiameter(rs.getString("Diameter"));
                
            }
        }catch(SQLException ex){
            System.out.println("Error PS View: " + ex.getMessage());
        } 
        return ListDTS;
    }
    
    /**public void delete(){
        try{
            String sql = "delete from data_transaksi where idDataTransaksi = ?";
            pst = con.prepareStatement(sql);
            
            pst.setString(1, idProduk);
            pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Error : "+ex.getMessage());
        }
    }**/
}
