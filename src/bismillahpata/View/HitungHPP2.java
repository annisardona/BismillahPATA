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
public class HitungHPP2 extends javax.swing.JFrame {
    private Object[] columnsNames = {"ID Bahan", "Nama Bahan", "Jumlah", "Harga Satuan", "Satuan"};
    private final DefaultTableModel tbModel = new DefaultTableModel();
    private long totalSemua = 0;
    private long total = 0;
    private String idDTS, id, namaData, jumlah, harga, satuan ;
    String realIDPerusahaan, realIDProduk;
    Produk p = new Produk();
    ProdukController pc = new ProdukController();
    DataTransaksi dts = new DataTransaksi();
    DataTransaksiController masukDatabase = new DataTransaksiController();
    
    public HitungHPP2() {
//        DataSession.setIdProduk(p.getIdProduk());
        initComponents();
        initTable();
        System.out.println(DataSession.idPerusahaan);
//        windowFrame();
        namaLabelProduk.setText(DataSession.getNamaProduk());
        totalLabelProduk.setText(String.valueOf(DataSession.getTotalProduk()));
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
        
        simpenTabel();
    }

//    public void windowFrame(){
//        ClassUmum cu = new ClassUmum();
//        cu.windowFrame();
//        setTitle("HitungHPP");
//        dc.SaveState(getTitle());
//    }
    private void simpenTabel(){
        List<DataTransaksi> listDTSHPP2 = pc.getListDetailDTSHPP2();
        for(DataTransaksi dts : listDTSHPP2){
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
        tableView2.setModel(tbModel);
        tableView2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public String generate(){
        String prefix = "DTO";
        //int rowCount = tableView2.getRowCount()+1;
        DataSession.doIncrement();
        String generatedId = prefix+""+(DataSession.getIncrement());
        System.out.println("Increment "+DataSession.getIncrement());
        
        return generatedId;
    }
    
    private double hitungTotal(){
        double total = 0, subtotal = 0;
        int rowCount = tableView2.getRowCount();
        
        System.out.println("Jumlah baris pada tabel : "+rowCount);
        
        for(int i = 0; i<rowCount; i++){
            subtotal = Double.parseDouble((String) tableView2.getValueAt(i, 2)) * Double.parseDouble((String) tableView2.getValueAt(i, 3));
            total += subtotal;
        }
        
        System.out.println("Total Perhitungan adalah : "+total);
        
        return total;
    }
    
    public void populatedTabel(String id, String namaBahan, String jumlah, String harga, String jenis) {       
        Object[] obj = new Object[columnsNames.length];
        
        obj[0] = id;
        obj[1] = namaBahan;
        obj[2] = jumlah;
        obj[3] = harga;
        obj[4] = jenis;
        
        tbModel.addRow(obj);
        
        namaBahan2.setText("");
        jumlahBahan2.setText("");
        hargaBahan2.setText("");
        jenisBahan2.setText("");
        
        namaBahan2.requestFocus();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnHitung = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnListData = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        namaBahan2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jumlahBahan2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        hargaBahan2 = new javax.swing.JTextField();
        jenisBahan2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        submitTabel = new javax.swing.JButton();
        btnHapus1 = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        totalLabelProduk = new javax.swing.JLabel();
        namaLabelProduk = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableView2 = new javax.swing.JTable();
        btnNext1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

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
                    .addComponent(btnListData, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHitung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnListData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHistory)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(695, 547));

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));

        namaBahan2.setMargin(null);
        namaBahan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaBahan2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Nama Pengeluaran");

        jumlahBahan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahBahan2ActionPerformed(evt);
            }
        });
        jumlahBahan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlahBahan2KeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Jumlah");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Harga Satuan ");

        hargaBahan2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        hargaBahan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaBahan2ActionPerformed(evt);
            }
        });
        hargaBahan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hargaBahan2KeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Satuan");

        submitTabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        submitTabel.setText("Tambah");
        submitTabel.setToolTipText("Tambah Data");
        submitTabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitTabelActionPerformed(evt);
            }
        });

        btnHapus1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnHapus1.setText("Hapus");
        btnHapus1.setToolTipText("Hapus Data");
        btnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapus1ActionPerformed(evt);
            }
        });

        ubah.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ubah.setText("Ubah");
        ubah.setToolTipText("Ubah Data");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        btnCancel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancel1.setText("Batal");
        btnCancel1.setToolTipText("Batalkan");
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(namaBahan2)
                    .addComponent(hargaBahan2, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jumlahBahan2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jenisBahan2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitTabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(submitTabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namaBahan2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jumlahBahan2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jenisBahan2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(hargaBahan2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)))))
                .addGap(6, 6, 6))
        );

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Hitung Harga Pokok Penjualan");

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(267, 130));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Nama Produk");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Total Produk");

        totalLabelProduk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        totalLabelProduk.setText("Total Produk");

        namaLabelProduk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        namaLabelProduk.setText("Nama Produk");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(58, 58, 58)
                .addComponent(namaLabelProduk)
                .addGap(71, 71, 71)
                .addComponent(jLabel12)
                .addGap(76, 76, 76)
                .addComponent(totalLabelProduk)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(totalLabelProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(namaLabelProduk)
                .addComponent(jLabel11))
        );

        tableView2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pengeluaran", "Nama Pengeluaran", "Jumlah", "Harga", "Jenis"
            }
        ));
        tableView2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableView2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableView2);

        btnNext1.setBackground(new java.awt.Color(0, 153, 153));
        btnNext1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNext1.setText("Selanjutnya");
        btnNext1.setToolTipText("Halaman Selanjutnya");
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText(" Pengeluaran Overhead, Listrik, dan Tenaga Kerja Langsung");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel9))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void submitTabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitTabelActionPerformed
        String nmBahan = namaBahan2.getText();
        String jumlah = jumlahBahan2.getText();
        String hSatuan = hargaBahan2.getText();
        String jenis = jenisBahan2.getText();
        
        idDTS = masukDatabase.generate();

        populatedTabel(idDTS, nmBahan, jumlah, hSatuan, jenis);
        
        DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(JLabel.RIGHT);
        tableView2.getColumnModel().getColumn(3).setCellRenderer((rightRender));

        String id_pro = pc.getProdukBy(p);
        System.out.println("id produuuuuuuuk "+pc.getProdukBy(p));
        String id_per = DataSession.idPerusahaan;
        
        p.setIdProduk(pc.getProdukBy(p));
                            //masuk datasession
        DataSession.setIdProduk(p.getIdProduk());
        System.out.println(p.getIdProduk());
                      
        dts.setIdPerusahaan(id_per);
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
    }//GEN-LAST:event_submitTabelActionPerformed

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        int baris = tableView2.getSelectedRow();
        if(baris >= 0){
            //tbModel.setValueAt(idSementara, baris, 0);
            tbModel.setValueAt(namaBahan2.getText(), baris, 1);
            tbModel.setValueAt(jumlahBahan2.getText(), baris, 2);
            tbModel.setValueAt(hargaBahan2.getText(), baris,3);
            tbModel.setValueAt(jenisBahan2.getText(), baris, 4);
            
            dts.setNamaData(namaBahan2.getText());
            dts.setJumlah(jumlahBahan2.getText());
            dts.setHargaSatuan(hargaBahan2.getText());
            dts.setSatuan(jenisBahan2.getText());

            namaBahan2.setText("");
            jumlahBahan2.setText("");
            hargaBahan2.setText("");
            jenisBahan2.setText("");
            
            masukDatabase.updateDataTransaksi(dts);
            JOptionPane.showMessageDialog(this, "Berhasil!");
        }else{
            JOptionPane.showMessageDialog(this, "Gagal Update");
            System.out.println("Gagal Update");
        }
    }//GEN-LAST:event_ubahActionPerformed

    private void btnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapus1ActionPerformed
        int baris = tableView2.getSelectedRow();
        if(baris != -1){
            String data = tableView2.getValueAt(baris, 0).toString();

            int status = 1;
            if(status == 1){
                tbModel.removeRow(baris);
                namaBahan2.setText("");
                jumlahBahan2.setText("");
                hargaBahan2.setText("");
                jenisBahan2.setText("");
                
                masukDatabase.deleteData(dts);
                JOptionPane.showMessageDialog(this, "Data Bahan Berhasil di Hapus");
            }else{
                JOptionPane.showMessageDialog(this, "Data Gagal di Hapus");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris Data Dahulu");
        }
//        hitungTotal();
    }//GEN-LAST:event_btnHapus1ActionPerformed

    private void hargaBahan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaBahan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaBahan2ActionPerformed

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        Object[] option = {"Sudah","Belum"};
                int selanjutnya = JOptionPane.showOptionDialog(this, "Apakah Anda sudah mengisi seluruh data?\nCek kembali sebelum ke halaman berikutnya",
                        "Konfirmasi Selanjutnya", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
                if (selanjutnya == JOptionPane.YES_OPTION){
                    int res = 1;
                    if (res > 0){
                        try{
                            new HitungBEP().setVisible(true);
                            this.dispose();
                            p.setIdProduk(pc.getProdukBy(p));
                            DataSession.setIdProduk(p.getIdProduk());
                            DataSession.resetIncrement();
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
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void namaBahan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaBahan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaBahan2ActionPerformed

    private void jumlahBahan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahBahan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahBahan2ActionPerformed

    private void hargaBahan2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaBahan2KeyTyped
        char karakter = evt.getKeyChar();
        if (!(Character.isDigit(karakter) || karakter == KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_hargaBahan2KeyTyped

    private void jumlahBahan2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahBahan2KeyTyped
        char karakter = evt.getKeyChar();
        if (!(Character.isDigit(karakter) || karakter == KeyEvent.VK_BACK_SPACE)&& karakter != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_jumlahBahan2KeyTyped

    private void tableView2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableView2MouseClicked
        int i = tableView2.getSelectedRow();
        TableModel model = tableView2.getModel();
        DataSession.setIdDataTransaksi(model.getValueAt(i, 0).toString());
        namaBahan2.setText(model.getValueAt(i, 1).toString());
        jumlahBahan2.setText(model.getValueAt(i, 2).toString());
        hargaBahan2.setText(model.getValueAt(i, 3).toString());
        jenisBahan2.setText(model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_tableView2MouseClicked

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

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        namaBahan2.setText("");
        jumlahBahan2.setText("");
        hargaBahan2.setText("");
        jenisBahan2.setText("");
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void btnListDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListDataActionPerformed
        new LihatDetailData().setVisible(true);
    }//GEN-LAST:event_btnListDataActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new HitungHPP().setVisible(true);
        p.setIdProduk(pc.getProdukBy(p));
        DataSession.setIdProduk(p.getIdProduk());
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(HitungHPP2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HitungHPP2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HitungHPP2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HitungHPP2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HitungHPP2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnHapus1;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnListData;
    private javax.swing.JButton btnNext1;
    private javax.swing.JTextField hargaBahan2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jenisBahan2;
    private javax.swing.JTextField jumlahBahan2;
    private javax.swing.JTextField namaBahan2;
    private javax.swing.JLabel namaLabelProduk;
    private javax.swing.JButton submitTabel;
    private javax.swing.JTable tableView2;
    private javax.swing.JLabel totalLabelProduk;
    private javax.swing.JButton ubah;
    // End of variables declaration//GEN-END:variables
}
