/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bismillahpata.View;

import bismillahpata.Controller.ProdukController;
import bismillahpata.Model.ClassUmum;
import bismillahpata.Model.DataSession;
import bismillahpata.Model.HistoryModel;
import bismillahpata.Model.Produk;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Suryadi
 */
public class History extends javax.swing.JFrame {
    long hasilHPP = 0, hargaSetelahPajakPertambahanNilai = 0, hargaJual = 0, pendapatanSetelahPPN = 0, pendapatanPerTahun = 0, untungPerUnit = 0, untungPerProduksi = 0, untungPerTahun = 0;
    long totalPengeluaran = 0, hasilBEP = 0, hasilPBP = 0, hasilROI = 0;
    long jumlah = 0, ppn = 0, margin = 0, totalHitungan = 0;
    String namaProduk, nmPerusahaan, idProduk, satuanProduk, id, hppPilihan, bepPilihan;
    private Object[] columnsNames = {"ID Produk","Nama Produk", "Jumlah Produk", "PPN(%)", "Margin(%)", "HPP(Rp)", "Harga Jual(Rp)", "BEP(pcs)", "Tahun PBP", "ROI"};
    private final DefaultTableModel tblModel = new DefaultTableModel();
    ProdukController pc = new ProdukController();
    Produk p = new Produk();
    List<HistoryModel> listHistory = pc.selectAll();
    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
    
    public History() {
        initComponents();
        initTable();
        lihatHistory();
        WindowFrame();
    }
    
    public void WindowFrame(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("History");
//        PerusahaanController prshC = new PerusahaanController();
    }
    
    private void initTable(){
        tblModel.setColumnIdentifiers(columnsNames);
        tableHistory.setModel(tblModel);
        tableHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public void populatedTabel(String IDProduk, String namaProduk, String jumlahProduk, long ppn, long margin, long hpp, String harga_jual, String bep, String thPBP, String roi) {       
        Object[] obj = new Object[columnsNames.length];
        
        obj[0] = IDProduk;
        obj[1] = namaProduk;
        obj[2] = jumlahProduk;
        obj[3] = ppn;
        obj[4] = margin;
        obj[5] = hpp;
        obj[6] = harga_jual;
        obj[7] = bep;
        obj[8] = thPBP;
        obj[9] = roi;
                
        tblModel.addRow(obj);
    }
    
    public void lihatHistory(){
        for(HistoryModel history : listHistory){
                Object[] his = {
//                                nmPerusahaan = history.getNmPerusahaan(),
                                idProduk = history.getIdProduk(),
                                namaProduk = history.getNamaProduk(),
                                jumlah = history.getJumlah(),
                                satuanProduk = history.getSatuanProduk(),
                                ppn = history.getPpn(),
                                margin = history.getMargin(),
                                hasilHPP = history.getHasilHPP(),
                                hasilBEP = history.getHasilBEP(),
                                hasilPBP = history.getHasilPBP()
                             };
                System.out.println("hasil HPP "+hasilHPP);
                String outputHPP = "Rp. "+hasilHPP;
                String outputJumlah = jumlah+" "+satuanProduk;
                System.out.println("convert to double "+(double) hasilBEP);
                
                hargaSetelahPajakPertambahanNilai = hasilHPP+ hasilHPP *  ppn /100;
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

                DecimalFormat dfBEP = new DecimalFormat("#");
                double outputBEP = (double) hasilBEP / (double) untungPerUnit;
                String keluaranBEP = dfBEP.format(outputBEP);
                System.out.println("hasil BEP "+dfBEP.format(outputBEP));

                System.out.println("hasil PBP "+hasilPBP);
                
                double outputPBP = (double) hasilPBP/ (double) untungPerTahun;
                DecimalFormat df = new DecimalFormat("#.##");
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
                populatedTabel(idProduk, namaProduk, outputJumlah , ppn, margin, hasilHPP, outputHargaJual, keluaranBEP, akhirPBP, outputROI);        
                
                DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
                rightRender.setHorizontalAlignment(JLabel.RIGHT);
                tableHistory.getColumnModel().getColumn(5).setCellRenderer((rightRender));
                tableHistory.getColumnModel().getColumn(6).setCellRenderer((rightRender));
        }
//        System.out.println(" "+namaProduk+" "+hasilHPP);
          
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnHitung = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHistory = new javax.swing.JTable();
        chartHPP = new javax.swing.JButton();
        chartBEP = new javax.swing.JButton();
        close = new javax.swing.JButton();
        deleteData = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        grafikPilihan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnGrafikHPPPilihan = new javax.swing.JButton();
        btnGrafikBEPPIlihan = new javax.swing.JButton();
        btnPrintPilihan = new javax.swing.JButton();

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHitung, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(btnHistory)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        tableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Produk", "Jumlah Produk", "HPP", "Harga Jual", "BEP", "PBP", "ROI"
            }
        ));
        tableHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHistoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHistory);

        chartHPP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chartHPP.setText("Lihat Grafik HPP");
        chartHPP.setToolTipText("Lihat Grafik HPP");
        chartHPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartHPPActionPerformed(evt);
            }
        });

        chartBEP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chartBEP.setText("Lihat Grafik BEP");
        chartBEP.setToolTipText("Lihat Grafik BEP");
        chartBEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartBEPActionPerformed(evt);
            }
        });

        close.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        close.setText("Tutup");
        close.setToolTipText("OK, Tutup");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        deleteData.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        deleteData.setText("Hapus");
        deleteData.setToolTipText("Hapus");
        deleteData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDataActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("History");

        btnPrint.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        grafikPilihan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        grafikPilihan.setText("Pilih Data");
        grafikPilihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grafikPilihanActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Produk", "Nama", "Jumlah", "PPN(%)", "Margin(%)", "HPP(Rp)", "Harga Jual(Rp)", "BEP(pcs)", "Tahun", "ROI"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        btnGrafikHPPPilihan.setText("Lihat Grafik HPP Pilihan");
        btnGrafikHPPPilihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikHPPPilihanActionPerformed(evt);
            }
        });

        btnGrafikBEPPIlihan.setText("Lihat Grafik BEP Pilihan");
        btnGrafikBEPPIlihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikBEPPIlihanActionPerformed(evt);
            }
        });

        btnPrintPilihan.setText("Print Data Pilihan");
        btnPrintPilihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintPilihanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(grafikPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGrafikHPPPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGrafikBEPPIlihan, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrintPilihan)))
                .addGap(0, 266, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(chartHPP, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chartBEP, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(close)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(grafikPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chartHPP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartBEP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteData, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrintPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGrafikHPPPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGrafikBEPPIlihan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
        DataSession.setIdProduk(null);
        DataSession.setNamaProduk(null);
        DataSession.setTotalProduk(0);
        System.out.println("ID PRODUK HISTORY "+DataSession.getIdProduk());
        new HitungHPP().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHitungActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        new History().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        new PilihAction().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void tableHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHistoryMouseClicked
        int i = tableHistory.getSelectedRow();
        TableModel model = tableHistory.getModel();
        DataSession.setIdProduk(model.getValueAt(i, 0).toString());
        System.out.println("model at value i, 1 "+DataSession.getIdProduk());
    }//GEN-LAST:event_tableHistoryMouseClicked

    private void chartHPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartHPPActionPerformed
        for(HistoryModel history : listHistory){
            String idNama = history.getIdProduk()+" "+history.getNamaProduk();
            dcd.setValue(history.getHasilHPP(), "ID dan Nama Produk", idNama);
        }
        JFreeChart jfc = ChartFactory.createBarChart("Hasil HPP", "ID Produk", "Total HPP", dcd, PlotOrientation.VERTICAL, true, true, true);
        ChartFrame cf = new ChartFrame("Hasil Perhitungan HPP", jfc);

        cf.setSize(1000,500);
        cf.setVisible(true);
        cf.setLocationRelativeTo(null);
    }//GEN-LAST:event_chartHPPActionPerformed

    private void chartBEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartBEPActionPerformed
        for(HistoryModel history : listHistory){
            Object[] his = {
                namaProduk = history.getNamaProduk(),
                jumlah = history.getJumlah(),
                ppn = history.getPpn(),
                margin = history.getMargin(),
                hasilHPP = history.getHasilHPP(),
                hasilBEP = history.getHasilBEP(),
                hasilPBP = history.getHasilPBP()
            };
            hargaSetelahPajakPertambahanNilai = hasilHPP+ hasilHPP *  ppn /100;
            System.out.println("harga stlh pajak pertambahan nilai "+hargaSetelahPajakPertambahanNilai);

            hargaJual = hargaSetelahPajakPertambahanNilai + hargaSetelahPajakPertambahanNilai * margin/100;
            System.out.println("harga Jual "+hargaJual);

            untungPerUnit = hargaJual - hasilHPP;
            System.out.println("untung per unit "+untungPerUnit);

            hasilBEP = hasilBEP/untungPerUnit;
            System.out.println(" hasil BEP "+hasilBEP);
            System.out.println(" untung per unit "+untungPerUnit);
            String idNama = history.getIdProduk()+" "+history.getNamaProduk();
            dcd.setValue(hasilBEP, "ID dan Nama Produk", idNama);
        }
        JFreeChart jfc = ChartFactory.createBarChart("Hasil BEP", "ID Produk", "Total BEP", dcd, PlotOrientation.VERTICAL, true, true, true);
        ChartFrame cf = new ChartFrame("Hasil Perhitungan BEP", jfc);

        cf.setSize(1000,500);
        cf.setVisible(true);
        cf.setLocationRelativeTo(null);
    }//GEN-LAST:event_chartBEPActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        new PilihAction().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void deleteDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDataActionPerformed
        int baris = tableHistory.getSelectedRow();
        if(baris != -1){
            String data = tableHistory.getValueAt(baris, 0).toString();

            int status = 1;
            if(status == 1){
                tblModel.removeRow(baris);
                pc.deleteHistory();
                DataSession.setIdProduk(null);
                JOptionPane.showMessageDialog(this, "History Berhasil di Hapus");
            }else{
                JOptionPane.showMessageDialog(this, "History Gagal di Hapus");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris Data Dahulu");
        }
    }//GEN-LAST:event_deleteDataActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try{
            tableHistory.print();
        }catch (PrinterException ex){
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void grafikPilihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grafikPilihanActionPerformed
//        tableHistory.setRowSelectionAllowed(true);
//        tableHistory.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TableModel model1 = tableHistory.getModel();
        int index[] = tableHistory.getSelectedRows();
                
        Object[] row = new Object[10];
        
//        CobaDulu cbdl = new CobaDulu();
        DefaultTableModel model2 = (DefaultTableModel)jTable1.getModel();
        
        for(int i = 0; i < index.length; i++){
            row[0] = model1.getValueAt(index[i], 0);
            row[1] = model1.getValueAt(index[i], 1);
            row[2] = model1.getValueAt(index[i], 2);
            row[3] = model1.getValueAt(index[i], 3);
            row[4] = model1.getValueAt(index[i], 4);
            row[5] = model1.getValueAt(index[i], 5);
            row[6] = model1.getValueAt(index[i], 6);
            row[7] = model1.getValueAt(index[i], 7);
            row[8] = model1.getValueAt(index[i], 8);
            row[9] = model1.getValueAt(index[i], 9);
//            row[10] = model1.getValueAt(index[i], 10);
//            row[11] = model1.getValueAt(index[i], 11);            
            model2.addRow(row);
        }
                
        DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(JLabel.RIGHT);
        jTable1.getColumnModel().getColumn(5).setCellRenderer((rightRender));
        jTable1.getColumnModel().getColumn(6).setCellRenderer((rightRender));
        
//        for(int i = 0; i< index.length; i++){
//            idNama[0] = model1.getValueAt(index[i], 1)+" "+model1.getValueAt(index[i], 2);
////            String nama = 
//            idNama[1] = model1.getValueAt(index[i], 6);
//            double hasilHPP = Number(idNama[1]);
//            dcd.setValue(hasilHPP, "ID dan Nama Produk", (Comparable) idNama[0]);
//        }
//        JFreeChart jfc = ChartFactory.createBarChart("Hasil HPP", "ID Produk", "Total HPP", dcd, PlotOrientation.VERTICAL, true, true, true);
//        ChartFrame cf = new ChartFrame("Hasil Perhitungan HPP", jfc);
//
//        cf.setSize(1000,500);
//        cf.setVisible(true);
//        cf.setLocationRelativeTo(null);
    }//GEN-LAST:event_grafikPilihanActionPerformed

    private void btnGrafikHPPPilihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikHPPPilihanActionPerformed
        System.out.println("Rooow "+jTable1.getRowCount()); 
        for(int i = 0; i < jTable1.getRowCount(); i++){
            namaProduk = jTable1.getValueAt(i, 1).toString();
            id = jTable1.getValueAt(i, 0).toString();
            hppPilihan = jTable1.getValueAt(i, 5).toString();
            
//            long hppLong = (long) hppPilihan;
                       
            System.out.print(hppPilihan);
            
            String idNama = id+" "+namaProduk;
            dcd.setValue(Long.parseLong(hppPilihan), "ID dan Nama Produk", idNama);
        }
        JFreeChart jfc = ChartFactory.createBarChart("Hasil HPP", "ID Produk", "Total HPP", dcd, PlotOrientation.VERTICAL, true, true, true);
        ChartFrame cf = new ChartFrame("Hasil Perhitungan HPP", jfc);

        cf.setSize(1000,500);
        cf.setVisible(true);
        cf.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnGrafikHPPPilihanActionPerformed

    private void btnGrafikBEPPIlihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikBEPPIlihanActionPerformed
        System.out.println("Rooow "+jTable1.getRowCount()); 
        for(int i = 0; i < jTable1.getRowCount(); i++){
            namaProduk = jTable1.getValueAt(i, 1).toString();
            id = jTable1.getValueAt(i, 0).toString();
            bepPilihan = jTable1.getValueAt(i, 7).toString();
            
//            long hppLong = (long) hppPilihan;
                       
            System.out.print(bepPilihan);
            
            String idNama = id+" "+namaProduk;
            dcd.setValue(Long.parseLong(bepPilihan), "ID dan Nama Produk", idNama);
        }
        JFreeChart jfc = ChartFactory.createBarChart("Hasil HPP", "ID Produk", "Total BEP", dcd, PlotOrientation.VERTICAL, true, true, true);
        ChartFrame cf = new ChartFrame("Hasil Perhitungan BEP", jfc);

        cf.setSize(1000,500);
        cf.setVisible(true);
        cf.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnGrafikBEPPIlihanActionPerformed

    private void btnPrintPilihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintPilihanActionPerformed
        try{
            jTable1.print();
        }catch (PrinterException ex){
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintPilihanActionPerformed

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
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new History().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGrafikBEPPIlihan;
    private javax.swing.JButton btnGrafikHPPPilihan;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrintPilihan;
    private javax.swing.JButton chartBEP;
    private javax.swing.JButton chartHPP;
    private javax.swing.JButton close;
    private javax.swing.JButton deleteData;
    private javax.swing.JButton grafikPilihan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tableHistory;
    // End of variables declaration//GEN-END:variables

    private double Number(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
