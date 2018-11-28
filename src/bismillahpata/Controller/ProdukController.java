/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bismillahpata.Controller;

import bismillahpata.Model.DataSession;
import bismillahpata.Model.DataTransaksi;
import bismillahpata.Model.HistoryModel;
import bismillahpata.Model.Produk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import koneksi.Koneksi;

/**
 *
 * @author Suryadi
 */
public class ProdukController extends Produk{
    Koneksi db;
    Connection con;
    PreparedStatement pst;

    public ProdukController() {
        super();
        db = new Koneksi();
        con = db.getConnection();
    }
        
    public String generate(){
        String prefix = "PDK";
        String generatedId = null;
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(RIGHT (idProduk,2)) as id from produk");
            while(rs.next()){
                if(rs.first() == false){
                    generatedId = "PDK01";
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
                DataSession.setIdProduk(generatedId);
                return generatedId;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return generatedId;
    }
    
    public int insertProduct(Produk p){
        int res = 0;
        String id = generate();
        String sql = "";
        try{
            sql = "INSERT INTO produk(idProduk, nmProduk, satuanProduk, Jumlah, ppn, margin)"
                    + " VALUES ('" + id + "',?,?,?,0,0)";
           
            pst = con.prepareStatement(sql);
            
            //pst.setString(1, prsh.getIdPer());
            pst.setString(1, p.getNamaProduk());
            pst.setString(2, p.getSatuanProduk());
            pst.setDouble(3, p.getJumlah());
            
            res = pst.executeUpdate();
                    
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }
    
    public int updateProduct(Produk p){
        int res = 0;
        String sql = "";
        try{
            sql = "UPDATE produk SET ppn =?, margin =? WHERE idProduk = '"+DataSession.getIdProduk()+"' ";
           
            pst = con.prepareStatement(sql);
            
            //pst.setString(1, prsh.getIdPer());
            pst.setDouble(1, p.getPpn());
            pst.setDouble(2, p.getMargin());
            
            res = pst.executeUpdate();
                    
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }
    
    public String getProdukBy(Produk p){
        String sql = "";
        String realIDProduk = null;
        ResultSet rs = null;
        try{
            sql = "SELECT * FROM PRODUK WHERE idProduk = '"+DataSession.getIdProduk()+"'";
            pst = con.prepareStatement(sql);
                     
            rs = pst.executeQuery();
            while (rs.next()){
                realIDProduk = rs.getString("idProduk");
            }rs.last();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return realIDProduk;
    }
    
    public void deleteHistory(){
        String sql = "";
        try{
            sql = "DELETE p.*, dts.* FROM produk p JOIN data_transaksi dts ON p.idProduk = dts.idProduk WHERE p.idProduk = '"+DataSession.getIdProduk()+"'";
            
            pst = con.prepareStatement(sql);
          
            System.out.println("idProduk untuk di Delete "+DataSession.getIdProduk());
            pst.executeUpdate();   
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteProduk(){
        String sql = "";
        try{
            sql = "DELETE p.* FROM produk p WHERE p.idProduk = '"+DataSession.getIdProduk()+"'";
            
            pst = con.prepareStatement(sql);
          
            System.out.println("idProduk untuk di Delete "+DataSession.getIdProduk());
            pst.executeUpdate();   
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public List<Produk> getDetailProduk() {
       List<Produk> produkDetail = null;
        
        ResultSet rs;
        try {
             produkDetail = new ArrayList<Produk>();
            
            String query = "SELECT p.nmProduk FROM `produk` p JOIN `data_transaksi` dts "
                    + "ON p.idProduk = dts.idProduk GROUP BY dts.idProduk";
            PreparedStatement ps = con.prepareStatement(query);
            
            rs =ps.executeQuery();
            //ps.setString(1, getNamaBuah());
            while(rs.next())
            {
                Produk detailProduk = new Produk();
//                detailProduk.setIdProduk(rs.getString("idProduk"));
                detailProduk.setNamaProduk(rs.getString("nmProduk"));
//                detailProduk.setJumlah(rs.getDouble("Jumlah"));
//                detailProduk.setPpn(rs.getDouble("Jumlah"));
//                detailProduk.setMargin(rs.getDouble("Jumlah"));
                produkDetail.add(detailProduk);
            }
            
            //int jumRow = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error PS Delete: " + ex.getMessage());
        }
    
           return produkDetail;
    }
    
    public List<HistoryModel> selectAll(){
        List<HistoryModel> ListHistory = null;
        ResultSet rs;
        try {
            ListHistory = new ArrayList<HistoryModel>();
            
            String query =  "SELECT p.idProduk, p.nmProduk, p.Jumlah, p.satuanProduk ,p.ppn, p.margin, (" +
"       SELECT SUM(JumlahBarang * HargaSatuan)" +
"       FROM data_transaksi AS DTTT " +
"       WHERE ket LIKE 'DTPB%' && DTTT.idProduk = dts.idProduk AND DTTT.isActive = 1 GROUP BY idProduk) totalBEP, (" +
"        (" +
"            (SELECT SUM(JumlahBarang * HargaSatuan) " +
"             FROM data_transaksi AS DTTB" +
"             WHERE ket LIKE 'DTB%' && DTTB.idProduk = dts.idProduk AND DTTB.isActive = 1 GROUP BY idProduk)" +
"            +" +
"            (SELECT SUM(JumlahBarang * HargaSatuan)" +
"             FROM data_transaksi AS DTTO " +
"             WHERE ket LIKE 'DTO%' && DTTO.idProduk = dts.idProduk AND DTTO.isActive = 1 GROUP BY idProduk)" +
"            )" +
"        )/" +
"        (SELECT p.Jumlah " +
"         FROM produk as p " +
"         WHERE p.idProduk = dts.idProduk) hasilHPP ,"+ 
"       (SELECT SUM(JumlahBarang * HargaSatuan)" +
"          FROM data_transaksi AS DTTM" +
"          WHERE ket LIKE 'DTM%' && DTTM.idProduk = dts.idProduk AND DTTM.isActive = 1 GROUP BY idProduk) totalPBP" +
"         FROM PRODUK p " +
"         JOIN data_transaksi dts on p.idProduk = dts.idProduk"+ 
"         WHERE dts.ket LIKE 'DTPB%' && dts.ket LIKE 'DTM%' && dts.ket LIKE 'DTB%' OR dts.ket LIKE 'DTO%' AND dts.isActive = 1 GROUP BY p.idProduk";
            PreparedStatement ps = con.prepareStatement(query);

//            System.out.println("id Perusahaan di Produk Controller "+DataSession.idPerusahaan);
            rs =ps.executeQuery();
            
            while(rs.next()){
                HistoryModel ListData = new HistoryModel();
//                ListData.setNmPerusahaan(rs.getString("nmPerusahaan"));
                ListData.setIdProduk(rs.getString("idProduk"));
                ListData.setNamaProduk(rs.getString("nmProduk"));
                ListData.setJumlah(rs.getLong("Jumlah"));
                ListData.setSatuanProduk(rs.getString("satuanProduk"));
                ListData.setPpn(rs.getLong("ppn"));
                ListData.setMargin(rs.getLong("margin"));
                ListData.setHasilHPP(rs.getLong("hasilHPP"));
                ListData.setHasilBEP(rs.getLong("totalBEP"));
                ListData.setHasilPBP(rs.getLong("totalPBP"));
                ListHistory.add(ListData);
                //BMg.setDiameter(rs.getString("Diameter"));
                
            }
        }catch(SQLException ex){
            System.out.println("Error PS View: " + ex.getMessage());
        } 
        return ListHistory;
    }
    
     public List<HistoryModel> selectProductBy(String idPerusahaan){
        List<HistoryModel> ListHistory = null;
        ResultSet rs;
        try {
            ListHistory = new ArrayList<HistoryModel>();
            
            String query =  "SELECT p.idProduk, p.nmProduk, p.jumlah, p.ppn, p.margin, ("
                    + "SELECT SUM(JumlahBarang * HargaSatuan) "
                    + "FROM data_transaksi AS DTTB "
                    + "WHERE ket LIKE 'DTB%' && DTTB.idProduk = dts.idProduk "
                    + "&& dts.idProduk = '"+DataSession.idProduk+"' AND DTTB.isActive = 1 "
                    + "GROUP BY idProduk)"
                    + "+"
                    + "("
                    + "SELECT SUM(JumlahBarang * HargaSatuan) "
                    + "FROM data_transaksi AS DTTO "
                    + "WHERE ket LIKE 'DTO%' && DTTO.idProduk = dts.idProduk "
                    + "&& dts.idProduk = '"+DataSession.idProduk+"' AND DTTO.isActive = 1 "
                    + "GROUP BY idProduk) totalPengeluaran,"
                    + "(SELECT SUM(JumlahBarang * HargaSatuan)"
                    + "FROM data_transaksi AS DTTT "
                    + "WHERE ket LIKE 'DTPB%' && DTTT.idProduk = dts.idProduk "
                    + "&& dts.idProduk = '"+DataSession.idProduk+"' AND DTTT.isActive = 1 "
                    + "GROUP BY idProduk) totalBEP, ("
                    + "("
                    + "(SELECT SUM(JumlahBarang * HargaSatuan) "
                    + "FROM data_transaksi AS DTTB "
                    + "WHERE ket LIKE 'DTB%' && DTTB.idProduk = dts.idProduk "
                    + "&& dts.idProduk = '"+DataSession.idProduk+"' AND DTTB.isActive = 1 "
                    + "GROUP BY idProduk)"
                    + "+"
                    + "(SELECT SUM(JumlahBarang * HargaSatuan) "
                    + "FROM data_transaksi AS DTTO "
                    + "WHERE ket LIKE 'DTO%' && DTTO.idProduk = dts.idProduk "
                    + "&& dts.idProduk = '"+DataSession.idProduk+"' AND DTTO.isActive = 1 "
                    + "GROUP BY idProduk) "
                    + ")"
                    + ")"
                    + "/"
                    + "(SELECT p.Jumlah "
                    + "FROM produk as p "
                    + "WHERE p.idProduk = dts.idProduk) hasilHPP ,"
                    + "(SELECT SUM(JumlahBarang * HargaSatuan) "
                    + "FROM data_transaksi AS DTTM "
                    + "WHERE ket LIKE 'DTM%' && DTTM.idProduk = dts.idProduk "
                    + "&& dts.idProduk = '"+DataSession.idProduk+"' AND DTTM.isActive = 1 GROUP BY idProduk) totalPBP "
                    + "FROM produk p "
                    + "JOIN data_transaksi dts on p.idProduk = dts.idProduk "
                    + "WHERE dts.ket LIKE 'DTPB%' && dts.ket LIKE 'DTM%' "
                    + "&& dts.ket LIKE 'DTB%' OR dts.ket LIKE 'DTO%' "
                    + "&& dts.idProduk = '"+DataSession.idProduk+"' AND dts.isActive = 1 GROUP BY p.idProduk";
            PreparedStatement ps = con.prepareStatement(query);

//            System.out.println("id Perusahaan di Produk Controller "+DataSession.idPerusahaan);
            System.out.println("id Produk di Produk Controller "+DataSession.idProduk);
            rs =ps.executeQuery();
            
            while(rs.next()){
                HistoryModel ListData = new HistoryModel();
                ListData.setIdProduk(rs.getString("idProduk"));
                ListData.setNamaProduk(rs.getString("nmProduk"));
                ListData.setJumlah(rs.getLong("Jumlah"));
                ListData.setPpn(rs.getLong("ppn"));
                ListData.setMargin(rs.getLong("margin"));
                ListData.setTotalPengeluaran(rs.getLong("totalPengeluaran"));
                ListData.setHasilHPP(rs.getLong("hasilHPP"));
                ListData.setHasilBEP(rs.getLong("totalBEP"));
                ListData.setHasilPBP(rs.getLong("totalPBP"));
                
                ListHistory.add(ListData);
                //BMg.setDiameter(rs.getString("Diameter"));
                
            }
        }catch(SQLException ex){
            System.out.println("Error PS View: " + ex.getMessage());
        } 
        return ListHistory;
    }
     
    public List<Produk> getListDetailProduk(){
        String sql = "";
        List<Produk> listDetailProduk = null;
        ResultSet rs = null;
        try{
            listDetailProduk = new ArrayList<Produk>();
            
            sql = "SELECT p.* FROM `produk` p JOIN `data_transaksi` dts "
                    + "ON p.idProduk = dts.idProduk WHERE p.nmProduk = '"+DataSession.getNamaProduk()+"' GROUP BY dts.idProduk";
            pst = con.prepareStatement(sql);
            
            System.out.println("Data Session Nama Produk di Controller adalah "+DataSession.getNamaProduk());
            
            rs = pst.executeQuery();
            while (rs.next()){
                Produk listData = new Produk();
                listData.setIdProduk(rs.getString("idProduk"));
                listData.setNamaProduk(rs.getString("nmProduk"));
                listData.setJumlah(rs.getLong("Jumlah"));
                listData.setSatuanProduk(rs.getString("satuanProduk"));
                listData.setPpn(rs.getLong("ppn"));
                listData.setMargin(rs.getLong("margin"));
                
                listDetailProduk.add(listData);
            }rs.last();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return listDetailProduk;
    }
    
    public List<DataTransaksi> getListDetailDTSHPP1(){
        String sql = "";
        List<DataTransaksi> listDetailDTSHPP1 = null;
        ResultSet rs = null;
        try{
            listDetailDTSHPP1 = new ArrayList<DataTransaksi>();
            
            sql = "SELECT dts.* FROM `data_transaksi` dts JOIN `produk` p ON p.idProduk = dts.idProduk WHERE dts.idProduk = '"+DataSession.idProduk+"' AND dts.ket LIKE 'DTB%'";
            pst = con.prepareStatement(sql);
            
            System.out.println("Data Session Id Produk di Controller adalah "+DataSession.idProduk);
            
            rs = pst.executeQuery();
            while (rs.next()){
                DataTransaksi listData = new DataTransaksi();
                listData.setIdData(rs.getString("id"));
                listData.setNamaData(rs.getString("nmDataTransaksi"));
                listData.setJumlah(rs.getString("JumlahBarang"));
                listData.setHargaSatuan(rs.getString("HargaSatuan"));
                listData.setSatuan(rs.getString("Satuan"));
                
                listDetailDTSHPP1.add(listData);
            }rs.last();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return listDetailDTSHPP1;
    }
    
    public List<DataTransaksi> getListDetailDTSHPP2(){
        String sql = "";
        List<DataTransaksi> listDetailDTSHPP2 = null;
        ResultSet rs = null;
        try{
            listDetailDTSHPP2 = new ArrayList<DataTransaksi>();
            
            sql = "SELECT dts.* FROM `data_transaksi` dts JOIN `produk` p ON p.idProduk = dts.idProduk WHERE dts.idProduk = '"+DataSession.idProduk+"' AND dts.ket LIKE 'DTO%'";
            pst = con.prepareStatement(sql);
            
            System.out.println("Data Session Id Produk di Controller adalah "+DataSession.idProduk);
            
            rs = pst.executeQuery();
            while (rs.next()){
                DataTransaksi listData = new DataTransaksi();
                listData.setIdData(rs.getString("id"));
                listData.setNamaData(rs.getString("nmDataTransaksi"));
                listData.setJumlah(rs.getString("JumlahBarang"));
                listData.setHargaSatuan(rs.getString("HargaSatuan"));
                listData.setSatuan(rs.getString("Satuan"));
                
                listDetailDTSHPP2.add(listData);
            }rs.last();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return listDetailDTSHPP2;
    }
    
    public List<DataTransaksi> getListDetailDTSBEP(){
        String sql = "";
        List<DataTransaksi> listDetailDTSBEP = null;
        ResultSet rs = null;
        try{
            listDetailDTSBEP = new ArrayList<DataTransaksi>();
            
            sql = "SELECT dts.* FROM `data_transaksi` dts JOIN `produk` p ON p.idProduk = dts.idProduk WHERE dts.idProduk = '"+DataSession.idProduk+"' AND dts.ket LIKE 'DTPB%'";
            pst = con.prepareStatement(sql);
            
            System.out.println("Data Session Id Produk di Controller adalah "+DataSession.idProduk);
            
            rs = pst.executeQuery();
            while (rs.next()){
                DataTransaksi listData = new DataTransaksi();
                listData.setIdData(rs.getString("id"));
                listData.setNamaData(rs.getString("nmDataTransaksi"));
                listData.setJumlah(rs.getString("JumlahBarang"));
                listData.setHargaSatuan(rs.getString("HargaSatuan"));
                listData.setSatuan(rs.getString("Satuan"));
                
                listDetailDTSBEP.add(listData);
            }rs.last();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return listDetailDTSBEP;
    }
    
    public List<DataTransaksi> getListDetailDTSPBP(){
        String sql = "";
        List<DataTransaksi> listDetailDTSPBP = null;
        ResultSet rs = null;
        try{
            listDetailDTSPBP = new ArrayList<DataTransaksi>();
            
            sql = "SELECT dts.* FROM `data_transaksi` dts JOIN `produk` p ON p.idProduk = dts.idProduk WHERE dts.idProduk = '"+DataSession.idProduk+"' AND dts.ket LIKE 'DTM%'";
            pst = con.prepareStatement(sql);
            
            System.out.println("Data Session Id Produk di Controller adalah "+DataSession.idProduk);
            
            rs = pst.executeQuery();
            while (rs.next()){
                DataTransaksi listData = new DataTransaksi();
                listData.setIdData(rs.getString("id"));
                listData.setNamaData(rs.getString("nmDataTransaksi"));
                listData.setJumlah(rs.getString("JumlahBarang"));
                listData.setHargaSatuan(rs.getString("HargaSatuan"));
                listData.setSatuan(rs.getString("Satuan"));
                
                listDetailDTSPBP.add(listData);
            }rs.last();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return listDetailDTSPBP;
    }
}
