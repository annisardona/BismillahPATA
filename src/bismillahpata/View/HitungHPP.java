/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bismillahpata.View;

import bismillahpata.Controller.DataTransaksiController;
import bismillahpata.Controller.ProdukController;
import bismillahpata.Model.ClassUmum;
import bismillahpata.Model.DataSession;
import bismillahpata.Model.DataTransaksi;
import bismillahpata.Model.Produk;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Suryadi
 */
public class HitungHPP extends javax.swing.JFrame {
    private Object[] columnsNames = {"ID Bahan", "Nama Bahan", "Jumlah", "Harga Satuan", "Satuan"};
    private final DefaultTableModel tbModel = new DefaultTableModel();
    private long totalSemua = 0;
    private long total = 0;
    private String idDTS, id, namaData, jumlah, harga, satuan ;
    Produk p = new Produk();
    ProdukController pc = new ProdukController();
    DataTransaksi dts = new DataTransaksi();
    DataTransaksiController masukDatabase = new DataTransaksiController();
    
    public HitungHPP() {
        initComponents();
        initTable();
//        System.out.println(DataSession.idPerusahaan);
//        DataSession.setIdProduk(null);
        
        if(DataSession.idProduk != null){
            namaBahan1.setEnabled(true);
            hargaBahan1.setEnabled(true);
            jenisBahan1.setEnabled(true);
            jumlahBahan1.setEnabled(true);
            btnTambahProduk.setEnabled(false);
        }else{
            namaBahan1.setEnabled(false);
            hargaBahan1.setEnabled(false);
            jenisBahan1.setEnabled(false);
            jumlahBahan1.setEnabled(false);
        }
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
               int simpanTidakYa = JOptionPane.showConfirmDialog(null, "Jika Anda keluar, data akan hilang.\n Apa Anda yakin keluar?", "Keluar", JOptionPane.YES_NO_OPTION);
                if (simpanTidakYa == JOptionPane.YES_OPTION){
                    pc.deleteHistory();
                    DataSession.setIdPerusahaan(null);
                    System.out.println("Data Session dikeluarkan "+DataSession.idPerusahaan);
                    e.getWindow().dispose();
                    System.out.println("Keluar!");
                }else{
                    //
                }
            }
        });
        namaLabelProduk.setText(DataSession.getNamaProduk());
        totalLabelProduk.setText(String.valueOf(DataSession.getTotalProduk())); 
        simpenTabel();
    }
    
    private void simpenTabel(){
        List<DataTransaksi> listDTSHPP1 = pc.getListDetailDTSHPP1();
        for(DataTransaksi dts : listDTSHPP1){
            Object[] his = {
                id = dts.getIdData(),
                namaData = dts.getNamaData(),
                jumlah = dts.getJumlah(),
                harga = dts.getHargaSatuan(),
                satuan = dts.getSatuan()
            };
            populatedTabel(id, namaData, jumlah, harga, satuan);
        }
    }
        
    private void initTable(){
        tbModel.setColumnIdentifiers(columnsNames);
        tableView.setModel(tbModel);
        tableView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public String generate(){
        String prefix = "DTB";
//        int rowCount = tableView.getRowCount()+1;
//        String generatedId = prefix+""+rowCount;
        DataSession.doIncrement();
        String generatedId = prefix+""+(DataSession.getIncrement());
        System.out.println("Increment "+DataSession.getIncrement());
        
        return generatedId;
    }
    
    public void populatedTabel(String id, String namaBahan, String jumlah, String harga, String jenis) {       
        Object[] obj = new Object[columnsNames.length];
        
        obj[0] = id;
//        dts.setIdData(idDTS);
//        DataSession.setIdDataTransaksi(idDTS);
        obj[1] = namaBahan;
        obj[2] = jumlah;
        obj[3] = harga;
        obj[4] = jenis;
        
        tbModel.addRow(obj);
        
        namaBahan1.setText("");
//        persentaseBahan1.setText("");
        jumlahBahan1.setText("");
        hargaBahan1.setText("");
        jenisBahan1.setText("");
        
        namaBahan1.requestFocus();
    }
    
//    private double hitungJumlah(){ 
//        double jumlah = 0;
//        double persentase, totalProduk;
//        if(persentaseBahan1.getText().equals("")){
//            persentase = 0;
//        } else {
//            persentase = Double.parseDouble(persentaseBahan1.getText())/ 100;
//            DataSession.setPersentaseBahan(persentase);
//            System.out.println("Session Persentase "+DataSession.getPersentaseBahan());
//        }
//        
//        if(fieldTotalProduk.getText().isEmpty()){
//            totalProduk = 0;
//        } else {
//            totalProduk = Double.parseDouble(fieldTotalProduk.getText());
//        }
//        
//        jumlah = persentase * totalProduk;
//        System.out.println("Hitung Jumlah dijalankan");
//        System.out.println("Nilai Persentase : "+persentase);
//        System.out.println("Nilai Harga Satuan : "+totalProduk);
//        System.out.println("Jumlah "+jumlah);
//        return jumlah;
//    }
    
//    private double hitungTotal(){
//        double total = 0, subtotal = 0;
//        int rowCount = tabelView.getRowCount();
//        
//        System.out.println("Jumlah baris pada tabel : "+rowCount);
//        
//        for(int i = 0; i<rowCount; i++){
//            subtotal = Double.parseDouble((String) tabelView.getValueAt(i, 2)) * Double.parseDouble((String) tabelView.getValueAt(i, 3));
//            total += subtotal;
//        }
//        
//        System.out.println("Total Perhitungan adalah : "+total);
//        
//        return total;
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnHitung = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnListData = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        fieldNamaProduk = new javax.swing.JTextField();
        fieldTotalProduk = new javax.swing.JTextField();
        fieldSatuanProduk = new javax.swing.JTextField();
        btnTambahProduk = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        namaBahan1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jumlahBahan1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        hargaBahan1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jenisBahan1 = new javax.swing.JTextField();
        SubmitTabel = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();
        btnNext = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        totalLabelProduk = new javax.swing.JLabel();
        namaLabelProduk = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 4, 31));

        btnHitung.setIcon(new javax.swing.ImageIcon("E:\\Yang ini Tugas\\Semt 6\\BismillahPATA\\asset\\calculator-1.png")); // NOI18N
        btnHitung.setToolTipText("Hitung");
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });

        btnHistory.setIcon(new javax.swing.ImageIcon("E:\\Yang ini Tugas\\Semt 6\\BismillahPATA\\asset\\stopwatch-2.png")); // NOI18N
        btnHistory.setToolTipText("History");
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        btnHome.setIcon(new javax.swing.ImageIcon("E:\\Yang ini Tugas\\Semt 6\\BismillahPATA\\asset\\home.png")); // NOI18N
        btnHome.setToolTipText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnListData.setIcon(new javax.swing.ImageIcon("E:\\Yang ini Tugas\\Semt 6\\BismillahPATA\\asset\\list.png")); // NOI18N
        btnListData.setToolTipText("List Data");
        btnListData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHitung, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnListData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHitung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnListData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHistory)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Nama Produk");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Total Produk");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Satuan Produk");

        fieldNamaProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNamaProdukActionPerformed(evt);
            }
        });

        fieldTotalProduk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldTotalProdukFocusLost(evt);
            }
        });
        fieldTotalProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldTotalProdukKeyTyped(evt);
            }
        });

        fieldSatuanProduk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldSatuanProdukFocusLost(evt);
            }
        });
        fieldSatuanProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldSatuanProdukKeyTyped(evt);
            }
        });

        btnTambahProduk.setText("Tambah");
        btnTambahProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahProdukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldNamaProduk)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldTotalProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(fieldSatuanProduk))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTambahProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldTotalProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldSatuanProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addComponent(btnTambahProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Hitung Harga Pokok Penjualan");

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));

        namaBahan1.setMargin(null);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Bahan");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Jumlah Bahan");

        jumlahBahan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahBahan1ActionPerformed(evt);
            }
        });
        jumlahBahan1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jumlahBahan1FocusLost(evt);
            }
        });
        jumlahBahan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlahBahan1KeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Harga ");

        hargaBahan1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        hargaBahan1.setToolTipText("harga per satuan barang");
        hargaBahan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaBahan1ActionPerformed(evt);
            }
        });
        hargaBahan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hargaBahan1KeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Satuan");

        jenisBahan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisBahan1ActionPerformed(evt);
            }
        });

        SubmitTabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SubmitTabel.setText("Tambah");
        SubmitTabel.setToolTipText("Tambah Data");
        SubmitTabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitTabelActionPerformed(evt);
            }
        });

        btnUbah.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.setToolTipText("Ubah Data");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setToolTipText("Hapus Data");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancel.setText("Batal");
        btnCancel.setToolTipText("Batalkan");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(namaBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jumlahBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hargaBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubmitTabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jenisBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SubmitTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(hargaBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jenisBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Bahan", "Jumlah", "Harga", "Jenis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableViewMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableView);

        btnNext.setBackground(new java.awt.Color(0, 153, 153));
        btnNext.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNext.setText("Selanjutnya");
        btnNext.setToolTipText("Halaman Selanjutnya");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel10.setText(" Pengeluaran Bahan Baku");

        jPanel10.setBackground(new java.awt.Color(204, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(267, 130));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Nama Produk");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Total Produk");

        totalLabelProduk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        totalLabelProduk.setText("Total Produk");

        namaLabelProduk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        namaLabelProduk.setText("Nama Produk");

        btnReset.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.setToolTipText("Ubah Data");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(58, 58, 58)
                .addComponent(namaLabelProduk)
                .addGap(72, 72, 72)
                .addComponent(jLabel19)
                .addGap(75, 75, 75)
                .addComponent(totalLabelProduk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(btnReset)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(totalLabelProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namaLabelProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(233, 233, 233)
                                .addComponent(jLabel10))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel13))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 486, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
        int simpanTidakYa = JOptionPane.showConfirmDialog(null, "Jika Anda keluar, data akan hilang.\n Apa Anda yakin keluar?", "Keluar", JOptionPane.YES_NO_OPTION);
                if (simpanTidakYa == JOptionPane.YES_OPTION){
                    pc.deleteHistory();
                    new HitungHPP().setVisible(true);
                    this.dispose();
                }else{
                    //
                }
    }//GEN-LAST:event_btnHitungActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        int simpanTidakYa = JOptionPane.showConfirmDialog(null, "Jika Anda keluar, data akan hilang.\n Apa Anda yakin keluar?", "Keluar", JOptionPane.YES_NO_OPTION);
                if (simpanTidakYa == JOptionPane.YES_OPTION){
                    pc.deleteHistory();
                    new PilihAction().setVisible(true);
                    this.dispose();
                }else{
                    //
                }
    }//GEN-LAST:event_btnHomeActionPerformed

    private void fieldTotalProdukFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldTotalProdukFocusLost
//        jumlahBahan1.setText(String.valueOf(hitungJumlah()));
    }//GEN-LAST:event_fieldTotalProdukFocusLost

    private void fieldTotalProdukKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldTotalProdukKeyTyped
        char karakter = evt.getKeyChar();
        if (!(Character.isDigit(karakter) || karakter == KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldTotalProdukKeyTyped

    private void fieldSatuanProdukFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldSatuanProdukFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldSatuanProdukFocusLost

    private void fieldSatuanProdukKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldSatuanProdukKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldSatuanProdukKeyTyped

    private void jumlahBahan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahBahan1ActionPerformed
//        char karakter = '.';
//        if (!(Character.isDigit(karakter) || karakter == KeyEvent.VK_BACK_SPACE )&& Character.isDefined('.')) {
//            dts.setJumlah(Character.toString(karakter));
//        }
    }//GEN-LAST:event_jumlahBahan1ActionPerformed

    private void jumlahBahan1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jumlahBahan1FocusLost
//        jumlahBahan1.setText(String.valueOf(hitungJumlah()));
    }//GEN-LAST:event_jumlahBahan1FocusLost

    private void hargaBahan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaBahan1ActionPerformed
        
    }//GEN-LAST:event_hargaBahan1ActionPerformed

    private void hargaBahan1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaBahan1KeyTyped
        char karakter = evt.getKeyChar();
        if (!(Character.isDigit(karakter) || karakter == KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_hargaBahan1KeyTyped

    private void jenisBahan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisBahan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenisBahan1ActionPerformed

    private void SubmitTabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitTabelActionPerformed
        String nmBahan = namaBahan1.getText();
//        String persen = persentaseBahan1.getText();
        String jumlah = jumlahBahan1.getText();
        String hSatuan = hargaBahan1.getText();
        String jenis = jenisBahan1.getText();
        
        idDTS = masukDatabase.generate();
        
        populatedTabel(idDTS, nmBahan, jumlah, hSatuan, jenis);
        
        DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(JLabel.RIGHT);
        tableView.getColumnModel().getColumn(3).setCellRenderer((rightRender));
        
        String id_pro = pc.getProdukBy(p);
        System.out.println("id produuuuuuuuk "+pc.getProdukBy(p));
//        String id_per = DataSession.idPerusahaan;
        
        p.setIdProduk(pc.getProdukBy(p));
                            //masuk datasession
        DataSession.setIdProduk(p.getIdProduk());
        System.out.println(p.getIdProduk());
                      
//        dts.setIdPerusahaan(id_per);
        dts.setIdProduk(id_pro);
                                //dts.setIdData(id_per);
        dts.setIdData(idDTS);
        dts.setNamaData(nmBahan);
        dts.setJumlah(jumlah);
        dts.setHargaSatuan(hSatuan);
        dts.setSatuan(jenis);
        dts.setKet(generate());
                                //System.out.println(dts);

        System.out.println("Simpan : "+dts.getIdPerusahaan());
        System.out.print(" / Simpan : "+dts.getIdProduk());
        System.out.print(" / Simpan : "+dts.getIdData());
        System.out.print(" / Simpan : "+dts.getNamaData());

        masukDatabase.inputNamaData(dts);
        
//        dts.setNamaData(nmBahan);
//        dts.setJumlah(jumlah);
//        dts.setSatuan(jenis);
//        dts.setHargaSatuan(hSatuan);  
//        String realIDPerusahaan = DataSession.idPerusahaan;
//        String realIDProduk = DataSession.idProduk;
//        dts.setIdPerusahaan(realIDPerusahaan);
//        dts.setIdProduk(realIDProduk + 1);
//        
//        System.out.println(realIDPerusahaan + " Ini real");
//      
        total = (long) (Double.parseDouble(jumlah) * Double.parseDouble(hSatuan));
        
        totalSemua += total;
        DataSession.setTotalHPP1(totalSemua);
        System.out.println(total);
        System.out.println("total Semua Harga "+totalSemua);  
    }//GEN-LAST:event_SubmitTabelActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        int baris = tableView.getSelectedRow();
        if(baris >= 0){
           //tbModel.setValueAt(idSementara, baris, 0);
            
           tbModel.setValueAt(namaBahan1.getText(), baris, 1);
           tbModel.setValueAt(jumlahBahan1.getText(), baris, 2);
           tbModel.setValueAt(hargaBahan1.getText(), baris,3);
           tbModel.setValueAt(jenisBahan1.getText(), baris, 4); 
           
           dts.setNamaData(namaBahan1.getText());
           dts.setJumlah(jumlahBahan1.getText());
           dts.setHargaSatuan(hargaBahan1.getText());
           dts.setSatuan(jenisBahan1.getText());
           
           
            namaBahan1.setText("");
//            persentaseBahan1.setText("");
            jumlahBahan1.setText("");
            hargaBahan1.setText("");
            jenisBahan1.setText("");
           
            masukDatabase.updateDataTransaksi(dts);
            JOptionPane.showMessageDialog(this, "Berhasil!");
//            hitungTotal(); 
        }else{
            JOptionPane.showMessageDialog(this, "Gagal Update");
            System.out.println("Gagal Update");
        }       
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int baris = tableView.getSelectedRow();
        if(baris != -1){
            String data = tableView.getValueAt(baris, 0).toString();
            
            int status = 1;
            if(status == 1){
                tbModel.removeRow(baris);
                namaBahan1.setText("");
//                persentaseBahan1.setText("");
                jumlahBahan1.setText("");
                hargaBahan1.setText("");
                jenisBahan1.setText("");
                
                masukDatabase.deleteData(dts);
                JOptionPane.showMessageDialog(this, "Data Bahan Berhasil di Hapus");
            }else{
                JOptionPane.showMessageDialog(this, "Data Gagal di Hapus");
            }
//            hitungTotal();
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris Data Dahulu");
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void tableViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableViewMouseClicked
        int i = tableView.getSelectedRow();
        TableModel model = tableView.getModel();
        DataSession.setIdDataTransaksi(model.getValueAt(i, 0).toString());
        namaBahan1.setText(model.getValueAt(i, 1).toString());
//        persentaseBahan1.setText(String.valueOf(DataSession.getPersentaseBahan()));
        jumlahBahan1.setText(model.getValueAt(i, 2).toString());
        hargaBahan1.setText(model.getValueAt(i, 3).toString());
        jenisBahan1.setText(model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_tableViewMouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        Object[] option = {"Sudah","Belum"};
                int selanjutnya = JOptionPane.showOptionDialog(this, "Apakah Anda sudah mengisi seluruh data?\nCek kembali sebelum ke halaman berikutnya",
                        "Konfirmasi Selanjutnya", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
                if (selanjutnya == JOptionPane.YES_OPTION){
                    int res = 1;
                    if (res > 0){
                        try{
                            this.dispose();
                            new HitungHPP2().setVisible(true);
                            p.setIdProduk(pc.getProdukBy(p));
                            DataSession.resetIncrement();
                            DataSession.setIdProduk(p.getIdProduk());
                        }
                        catch(Exception e){
                             System.out.println(e);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Perintah Gagal, silahkan isi ulang!");
                    }
                }else{
                    //
                }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        int simpanTidakYa = JOptionPane.showConfirmDialog(null, "Jika Anda keluar, data akan hilang.\n Apa Anda yakin keluar?", "Keluar", JOptionPane.YES_NO_OPTION);
                if (simpanTidakYa == JOptionPane.YES_OPTION){
                    pc.deleteHistory();
                    new History().setVisible(true);
                    this.dispose();
                }else{
                    //
                }
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void fieldNamaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNamaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNamaProdukActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        //hapus isi
        namaBahan1.setText("");
//        persentaseBahan1.setText("");
        jumlahBahan1.setText("");
        hargaBahan1.setText("");
        jenisBahan1.setText("");
        
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnListDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListDataActionPerformed
        new LihatDetailData().setVisible(true);
    }//GEN-LAST:event_btnListDataActionPerformed

    private void btnTambahProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahProdukActionPerformed
        String namaProduk = this.fieldNamaProduk.getText();
        double totalProduk = Double.parseDouble(fieldTotalProduk.getText());
        String satuanProduk = this.fieldSatuanProduk.getText();
        
        fieldNamaProduk.setText("");
        fieldSatuanProduk.setText("");
        fieldTotalProduk.setText("");
        
        DataSession.setTotalProduk(totalProduk);
        DataSession.setNamaProduk(namaProduk);

        namaLabelProduk.setText(DataSession.getNamaProduk());
        totalLabelProduk.setText(String.valueOf(DataSession.getTotalProduk()));
        
        p.setNamaProduk(namaProduk);
        p.setJumlah(totalProduk);
        p.setSatuanProduk(satuanProduk);

        pc.insertProduct(p); 
        JOptionPane.showMessageDialog(null, "Data Sudah Masuk");
        
        btnTambahProduk.setEnabled(false);
        namaBahan1.setEnabled(true);
        jumlahBahan1.setEnabled(true);
        hargaBahan1.setEnabled(true);
        jenisBahan1.setEnabled(true);
    }//GEN-LAST:event_btnTambahProdukActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        pc.deleteProduk();
        
        namaLabelProduk.setText("Nama Produk");
        totalLabelProduk.setText("Total Produk");
        
        btnTambahProduk.setEnabled(true);
    }//GEN-LAST:event_btnResetActionPerformed

    private void jumlahBahan1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahBahan1KeyTyped
        char karakter = evt.getKeyChar();
        if (!(Character.isDigit(karakter) || karakter == KeyEvent.VK_BACK_SPACE)&& karakter != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_jumlahBahan1KeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HitungHPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HitungHPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HitungHPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HitungHPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HitungHPP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SubmitTabel;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnListData;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambahProduk;
    private javax.swing.JButton btnUbah;
    private javax.swing.JTextField fieldNamaProduk;
    private javax.swing.JTextField fieldSatuanProduk;
    private javax.swing.JTextField fieldTotalProduk;
    private javax.swing.JTextField hargaBahan1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jenisBahan1;
    private javax.swing.JTextField jumlahBahan1;
    private javax.swing.JTextField namaBahan1;
    private javax.swing.JLabel namaLabelProduk;
    private javax.swing.JTable tableView;
    private javax.swing.JLabel totalLabelProduk;
    // End of variables declaration//GEN-END:variables
}
