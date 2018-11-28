/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bismillahpata.View;

import bismillahpata.Controller.ProdukController;
import bismillahpata.Model.DataSession;
import bismillahpata.Model.HistoryModel;
import bismillahpata.Model.Produk;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Suryadi
 */
public class Outpuuut extends javax.swing.JFrame {
    private Object[] columnsNames = {"Total Pengeluaran","HPP", "Harga + Pajak", "Harga Jual", "BEP", "Tahun PBP", "ROI"};
    private final DefaultTableModel tblModel = new DefaultTableModel();
    ProdukController pc = new ProdukController();
    Produk p = new Produk();
    long hasilHPP = 0, hargaSetelahPajakPertambahanNilai = 0, hargaJual = 0, pendapatanSetelahPPN = 0, pendapatanPerTahun = 0, untungPerUnit = 0, untungPerProduksi = 0, untungPerTahun = 0;
    long totalPengeluaran = 0, hasilBEP = 0, hasilPBP = 0, hasilROI = 0;
    long jumlah = 0, ppn = 0, margin = 0, totalHitungan = 0;
    String namaProduk, nmPerusahaan, idProduk;
    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
    /**
     * Creates new form Outpuuut
     */
    public Outpuuut() {
        initComponents();
        initTable();
    }

    private void initTable(){
        tblModel.setColumnIdentifiers(columnsNames);
        tableViewOutput.setModel(tblModel);
        tableViewOutput.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void populatedTabel(String totalPengeluaran, String hpp, String hargaPajak, String hargaJual, String bep, String thPBP, String roi) {       
        Object[] obj = new Object[columnsNames.length];
        
        obj[0] = totalPengeluaran;
        obj[1] = hpp;
        obj[2] = hargaPajak;
        obj[3] = hargaJual;
        obj[4] = bep;
        obj[5] = thPBP;
        obj[6] = roi;
//        obj[7] = roi;
                
        tblModel.addRow(obj);
    }
    
    public void output(){
        List<HistoryModel> listHistory = pc.selectProductBy(DataSession.idPerusahaan);
        for(HistoryModel history : listHistory){
                Object[] his = {
//                                nmPerusahaan = history.getNmPerusahaan(),
                                idProduk = history.getIdProduk(),
                                namaProduk = history.getNamaProduk(),
                                jumlah = history.getJumlah(),
                                ppn = history.getPpn(),
                                margin = history.getMargin(),
                                totalPengeluaran = history.getTotalPengeluaran(),
                                hasilHPP = history.getHasilHPP(),
                                hasilBEP = history.getHasilBEP(),
                                hasilPBP = history.getHasilPBP()
                             };
                String outputPengeluaran = "Rp. "+totalPengeluaran;
                System.out.println("hasil HPP "+hasilHPP);
                String outputHPP = "Rp. "+hasilHPP;
                System.out.println("convert to double "+(double) hasilBEP);
                
                hargaSetelahPajakPertambahanNilai = hasilHPP+ hasilHPP *  ppn /100;
                String outputHargaPajak = "Rp. "+hargaSetelahPajakPertambahanNilai;
                System.out.println("harga stlh pajak pertambahan nilai "+hargaSetelahPajakPertambahanNilai);
                
                hargaJual = hargaSetelahPajakPertambahanNilai + hargaSetelahPajakPertambahanNilai * margin/100; 
                String outputHargaJual = "Rp. "+hargaJual;
                System.out.println("harga Jual "+hargaJual);

                pendapatanSetelahPPN = hargaJual * jumlah;
                System.out.println("hasil pendapatan setelah PPN "+pendapatanSetelahPPN);

                pendapatanPerTahun = pendapatanSetelahPPN * 12;
                System.out.println("hasil pendapatan per tahun "+pendapatanPerTahun);

                untungPerUnit = hargaJual - hasilHPP;
                System.out.println("untung per unit "+untungPerUnit);
                
                untungPerProduksi = untungPerUnit * jumlah;
                System.out.println("untung per produksi "+untungPerProduksi);

                untungPerTahun = untungPerProduksi * 12;
        //        DataSession.setTotalPBP(untungPerTahun);
                System.out.println("untung per tahun "+untungPerTahun);

                DecimalFormat df = new DecimalFormat("#.##");
                double outputBEP = (double) hasilBEP / (double) untungPerUnit;
                System.out.println("hasil BEP "+df.format(outputBEP));

                System.out.println("hasil PBP "+hasilPBP);
                
                double outputPBP = (double) hasilPBP/ (double) untungPerTahun;
                System.out.println("output perhitungan PBP "+df.format(outputPBP));
                String akhirPBP = null;
                if(outputPBP <= (0.08334)){
                    akhirPBP = "1 bulan";
                }else if (outputPBP > (0.08334) && outputPBP <= (0.1666)){
                    akhirPBP = "2 bulan";
                }else if (outputPBP > (0.1666) && outputPBP <= (0.25)){
                    akhirPBP = "3 bulan";
                }else if (outputPBP > (0.25) && outputPBP <= (0.3333)){
                    akhirPBP = "4 bulan";
                }else if (outputPBP > (0.3333) && outputPBP <= (0.4166)){
                    akhirPBP = "5 bulan";
                }else if (outputPBP > (0.4166) && outputPBP <= (0.5)){
                    akhirPBP = "6 bulan";
                }else if (outputPBP > (0.5) && outputPBP <= (0.5833)){
                    akhirPBP = "7 bulan";
                }else if (outputPBP > (0.5833) && outputPBP <= (0.6666)){
                    akhirPBP = "8 bulan";
                }else if (outputPBP > (0.6666) && outputPBP <= (0.75)){
                    akhirPBP = "9 bulan";
                }else if (outputPBP > (0.75) && outputPBP <= (0.8333)){
                    akhirPBP = "10 bulan";
                }else if (outputPBP > (0.8333) && outputPBP <= (0.9166)){
                    akhirPBP = "11 bulan";
                }else if (outputPBP > (0.9166) && outputPBP <= (1)){
                    akhirPBP = "12 bulan";
                }else if (outputPBP > (1) && outputPBP <= (1.0833)){
                    akhirPBP = "13 bulan";
                }else if (outputPBP > (1.0833) && outputPBP <= (1.1666)){
                    akhirPBP = "14 bulan";
                }else if (outputPBP > (1.1666) && outputPBP <= (1.25)){
                    akhirPBP = "15 bulan";
                }else if (outputPBP > (1.25) && outputPBP <= (1.3333)){
                    akhirPBP = "16 bulan";
                }else if (outputPBP > (1.3333) && outputPBP <= (1.4166)){
                    akhirPBP = "17 bulan";
                }else if (outputPBP > (1.4166) && outputPBP <= (1.5)){
                    akhirPBP = "18 bulan";
                }else if (outputPBP > (1.5) && outputPBP <= (1.5833)){
                    akhirPBP = "19 bulan";
                }else if (outputPBP > (1.5833) && outputPBP <= (1.6666)){
                    akhirPBP = "20 bulan";
                }else if (outputPBP > (1.6666) && outputPBP <= (1.75)){
                    akhirPBP = "21 bulan";
                }else if (outputPBP > (1.75) && outputPBP <= (1.8333)){
                    akhirPBP = "22 bulan";
                }else if (outputPBP > (1.8333) && outputPBP <= (1.9166)){
                    akhirPBP = "23 bulan";
                }else if (outputPBP > (1.9166) && outputPBP <= (2)){
                    akhirPBP = "24 bulan";
                }else if (outputPBP > (2) && outputPBP <= (2.0833)){
                    akhirPBP = "25 bulan";
                }else if (outputPBP > (2.0833) && outputPBP <= (2.1666)){
                    akhirPBP = "26 bulan";
                }else if (outputPBP > (2.1666) && outputPBP <= (2.25)){
                    akhirPBP = "27 bulan";
                }else if (outputPBP > (2.25) && outputPBP <= (2.3333)){
                    akhirPBP = "28 bulan";
                }else if (outputPBP > (2.3333) && outputPBP <= (2.4166)){
                    akhirPBP = "29 bulan";
                }else if (outputPBP > (2.4166) && outputPBP <= (2.5)){
                    akhirPBP = "30 bulan";
                }else if (outputPBP > (2.5) && outputPBP <= (2.5833)){
                    akhirPBP = "31 bulan";
                }else if (outputPBP > (2.5833) && outputPBP <= (2.6666)){
                    akhirPBP = "32 bulan";
                }else if (outputPBP > (2.6666) && outputPBP <= (2.75)){
                    akhirPBP = "33 bulan";
                }else if (outputPBP > (2.75) && outputPBP <= (2.8333)){
                    akhirPBP = "34 bulan";
                }else if (outputPBP > (2.8333) && outputPBP <= (2.9166)){
                    akhirPBP = "35 bulan";
                }else if (outputPBP > (2.9166) && outputPBP <= (3)){
                    akhirPBP = "36 bulan";
                }else if (outputPBP > (3) && outputPBP <= (3.0833)){
                    akhirPBP = "37 bulan";
                }else if (outputPBP > (3.0833) && outputPBP <= (3.1666)){
                    akhirPBP = "38 bulan";
                }else if (outputPBP > (3.1666) && outputPBP <= (3.25)){
                    akhirPBP = "39 bulan";
                }else if (outputPBP > (3.25) && outputPBP <= (3.3333)){
                    akhirPBP = "40 bulan";
                }else if (outputPBP > (3.3333) && outputPBP <= (3.4166)){
                    akhirPBP = "41 bulan";
                }else if (outputPBP > (3.4166) && outputPBP <= (3.5)){
                    akhirPBP = "42 bulan";
                }else if (outputPBP > (3.5) && outputPBP <= (3.5833)){
                    akhirPBP = "43 bulan";
                }else if (outputPBP > (3.5833) && outputPBP <= (3.6666)){
                    akhirPBP = "44 bulan";
                }else if (outputPBP > (3.6666) && outputPBP <= (3.75)){
                    akhirPBP = "45 bulan";
                }else if (outputPBP > (3.75) && outputPBP <= (3.8333)){
                    akhirPBP = "46 bulan";
                }else if (outputPBP > (3.8333) && outputPBP <= (3.9166)){
                    akhirPBP = "47 bulan";
                }else if (outputPBP > (3.9166) && outputPBP <= (4)){
                    akhirPBP = "48 bulan";
                }else if (outputPBP > (4) && outputPBP <= (4.0833)){
                    akhirPBP = "49 bulan";
                }else if (outputPBP > (4.0833) && outputPBP <= (4.1666)){
                    akhirPBP = "50 bulan";
                }else if (outputPBP > (4.1666) && outputPBP <= (4.25)){
                    akhirPBP = "51 bulan";
                }else if (outputPBP > (4.25) && outputPBP <= (4.3333)){
                    akhirPBP = "52 bulan";
                }else if (outputPBP > (4.3333) && outputPBP <= (4.4166)){
                    akhirPBP = "53 bulan";
                }else if (outputPBP > (4.4166) && outputPBP <= (4.5)){
                    akhirPBP = "54 bulan";
                }else if (outputPBP > (4.5) && outputPBP <= (4.5833)){
                    akhirPBP = "55 bulan";
                }else if (outputPBP > (4.5833) && outputPBP <= (4.6666)){
                    akhirPBP = "56 bulan";
                }else if (outputPBP > (4.6666) && outputPBP <= (4.75)){
                    akhirPBP = "57 bulan";
                }else if (outputPBP > (4.75) && outputPBP <= (4.8333)){
                    akhirPBP = "58 bulan";
                }else if (outputPBP > (4.8333) && outputPBP <= (4.9166)){
                    akhirPBP = "59 bulan";
                }else if (outputPBP > (4.9166) && outputPBP <= (5)){
                    akhirPBP = "60 bulan";
                }else{
                    akhirPBP = "lebih dari 5 th";
                }
                

//                belajar lagi rumus PBP say
//
                double hasilROI = (double) untungPerTahun / hasilPBP * 100;
                String outputROI = df.format(hasilROI)+"% /th";
                System.out.println("hasil ROI "+df.format(hasilROI));
//                System.out.println("hasil BEP "+Math.ceil(hasilROI));
                populatedTabel(outputPengeluaran, outputHPP, outputHargaPajak, outputHargaJual, df.format(outputBEP), akhirPBP, outputROI);        
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnHitung = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnListData = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableViewOutput = new javax.swing.JTable();
        close = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fieldPPN = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fieldMargin = new javax.swing.JTextField();
        btnHitung1 = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();

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
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHitung, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListData, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
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
        jPanel4.setPreferredSize(new java.awt.Dimension(688, 492));

        tableViewOutput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Produk", "Jumlah Produk", "HPP", "Harga Jual", "BEP", "PBP", "ROI"
            }
        ));
        tableViewOutput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableViewOutputMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableViewOutput);

        close.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        close.setText("Tutup");
        close.setToolTipText("OK, Tutup");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Hasil Perhitungan");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("PPN");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Margin");

        btnHitung1.setIcon(new javax.swing.ImageIcon("E:\\Yang ini Tugas\\Semt 6\\BismillahPATA\\asset\\equal-1.png")); // NOI18N
        btnHitung1.setToolTipText("Mulai Menghitung");
        btnHitung1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitung1ActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(289, 289, 289))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(71, 71, 71)
                                .addComponent(fieldPPN, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(jLabel4)
                                .addGap(55, 55, 55)
                                .addComponent(fieldMargin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addComponent(btnHitung1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnPrint)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(close)))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(fieldPPN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(fieldMargin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHitung1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableViewOutputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableViewOutputMouseClicked
        int i = tableViewOutput.getSelectedRow();
        TableModel model = tableViewOutput.getModel();
        DataSession.setIdProduk(model.getValueAt(i, 0).toString());
        System.out.println("model at value i, 1 "+DataSession.getIdProduk());
    }//GEN-LAST:event_tableViewOutputMouseClicked

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        new PilihAction().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void btnHitung1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitung1ActionPerformed
        Double ppn = Double.parseDouble(fieldPPN.getText());
        p.setPpn(ppn);
        System.out.println("PPN "+ppn);

        Double margin = Double.parseDouble(fieldMargin.getText());
        p.setMargin(margin);
        System.out.println("margin "+margin);

        pc.updateProduct(p);
        
        output();
        
        btnHitung1.setEnabled(false);

//        hasilHPP = totalPengeluaran / (long) DataSession.getTotalProduk();
//        System.out.println("hasil HPP "+hasilHPP);
//
//        hargaSetelahPajakPertambahanNilai = hasilHPP + hasilHPP * (long) ppn/100;
//        System.out.println("harga stlh pajak pertambahan nilai "+hargaSetelahPajakPertambahanNilai);
//
//        hargaJual = hargaSetelahPajakPertambahanNilai + hargaSetelahPajakPertambahanNilai * (long) margin/100;
//        System.out.println("harga Jual "+hargaJual);
//
//        pendapatanSetelahPPN = hargaJual * (long) DataSession.getTotalProduk();
//        System.out.println("hasil pendapatan setelah PPN "+pendapatanSetelahPPN);
//
//        pendapatanPerTahun = pendapatanSetelahPPN * 12;
//        System.out.println("hasil pendapatan per tahun "+pendapatanPerTahun);
//
//        untungPerUnit = hargaJual - hasilHPP;
//        System.out.println("untung per unit "+untungPerUnit);
//
//        untungPerProduksi = untungPerUnit * (long) DataSession.getTotalProduk();
//        System.out.println("untung per produksi "+untungPerProduksi);
//
//        untungPerTahun = untungPerProduksi * 12;
//        DataSession.setTotalPBP(untungPerTahun);
//        System.out.println("untung per tahun "+untungPerTahun);
//
//        hasilBEP = DataSession.getTotalBEP()/(hargaJual-hasilHPP);
//        System.out.println("hasil BEP "+hasilBEP);
//
//        hasilPBP = DataSession.getTotalPBP()/untungPerTahun;
//        System.out.println("hasil PBP "+hasilPBP);
//        //belajar lagi rumus PBP say
//
//        hasilROI = untungPerTahun / DataSession.getTotalPBP() * 100;
//        System.out.println("hasil BEP "+hasilROI);
//        populatedTabel(totalPengeluaran, hasilHPP, hargaSetelahPajakPertambahanNilai, hargaJual, hasilBEP, hasilPBP, hasilROI);
    }//GEN-LAST:event_btnHitung1ActionPerformed

    private void btnListDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListDataActionPerformed
        new LihatDetailData().setVisible(true);
    }//GEN-LAST:event_btnListDataActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        new PilihAction().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        new History().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
        new HitungHPP().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHitungActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try{
            tableViewOutput.print();
        }catch (PrinterException ex){
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

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
            java.util.logging.Logger.getLogger(Outpuuut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Outpuuut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Outpuuut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Outpuuut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Outpuuut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnHitung1;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnListData;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton close;
    private javax.swing.JTextField fieldMargin;
    private javax.swing.JTextField fieldPPN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableViewOutput;
    // End of variables declaration//GEN-END:variables
}
