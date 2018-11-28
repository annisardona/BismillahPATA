/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bismillahpata.View;

import bismillahpata.Controller.DataTransaksiController;
import bismillahpata.Controller.ProdukController;
import bismillahpata.Model.DataSession;
import bismillahpata.Model.DataTransaksi;
import bismillahpata.Model.Produk;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
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
public class HitungPBP extends javax.swing.JFrame {
    private Object[] columnsNames = {"ID Investasi", "Nama Investasi", "Jumlah", "Harga Satuan", "Satuan"};
    private final DefaultTableModel tblModel = new DefaultTableModel();
    private long totalSemua = 0, total = 0;
    private String idDTS, id, namaData, jumlah, harga, satuan;
    Produk p = new Produk();
    DataTransaksi dts = new DataTransaksi();
    String realIDPerusahaan, realIDProduk;
    ProdukController pc = new ProdukController();
    DataTransaksiController masukDatabase = new DataTransaksiController();
    /**
     * Creates new form HitungPBP
     */
    public HitungPBP() {
//        DataSession.setIdProduk(p.getIdProduk());
        initComponents();
        initTable();
        System.out.println(DataSession.idPerusahaan);
        System.out.println(DataSession.idProduk);
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
//                    pc.deleteHistory();
                    System.out.println("Keluar!");
                }else{
                    //
                }
            }
        });
        
        simpenTabel();
    }
    
    private void simpenTabel(){
        List<DataTransaksi> listDTSPBP = pc.getListDetailDTSPBP();
        for(DataTransaksi dts : listDTSPBP){
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
        tblModel.setColumnIdentifiers(columnsNames);
        tableView4.setModel(tblModel);
        tableView4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public String generate(){
        String prefix = "DTM";
//        int rowCount = tableView4.getRowCount()+1;
//        String generatedId = prefix+""+rowCount;
        DataSession.doIncrement();
        String generatedId = prefix+""+(DataSession.getIncrement());
        System.out.println("Increment "+DataSession.getIncrement());
        
        return generatedId;
    }
    
    public void populatedTabel(String id, String namaPengeluaran, String jumlah, String harga, String jenis) {       
        Object[] obj = new Object[columnsNames.length];
        
        obj[0] = id;
        obj[1] = namaPengeluaran;
        obj[2] = jumlah;
        obj[3] = harga;
        obj[4] = jenis;
                
        tblModel.addRow(obj);
        
        namaBahan1.setText("");
        jumlahBahan1.setText("");
        hargaBahan1.setText("");
        jenisBahan1.setText("");
        
        namaBahan1.requestFocus();
    }
    
    private double hitungTotal(){
        double total = 0, subtotal = 0;
        int rowCount = tableView4.getRowCount();
        
        System.out.println("Jumlah baris pada tabel : "+rowCount);
        
        for(int i = 0; i<rowCount; i++){
            subtotal = Double.parseDouble((String) tableView4.getValueAt(i, 2)) * Double.parseDouble((String) tableView4.getValueAt(i, 3));
            total += subtotal;
        }
        
        System.out.println("Total Perhitungan adalah : "+total);
        
        return total;
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
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        totalLabelProduk = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        namaLabelProduk = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        SubmitTabel = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        namaBahan1 = new javax.swing.JTextField();
        jumlahBahan1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        hargaBahan1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jenisBahan1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableView4 = new javax.swing.JTable();
        btnNext = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
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
                    .addComponent(btnHitung, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnListData, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(695, 547));

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Nama Produk");

        totalLabelProduk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        totalLabelProduk.setText("Total Produk");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Total Produk");

        namaLabelProduk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        namaLabelProduk.setText("Nama Produk");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(47, 47, 47)
                .addComponent(namaLabelProduk)
                .addGap(102, 102, 102)
                .addComponent(jLabel8)
                .addGap(81, 81, 81)
                .addComponent(totalLabelProduk)
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(namaLabelProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(totalLabelProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Hitung Payback Periode");

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));

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

        namaBahan1.setMargin(null);

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

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Jumlah");

        hargaBahan1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
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

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Harga Satuan ");

        jenisBahan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisBahan1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Satuan");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Nama Pengeluaran");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hargaBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jenisBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(SubmitTabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(namaBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(SubmitTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnUbah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(hargaBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jumlahBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jenisBahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tableView4.setModel(new javax.swing.table.DefaultTableModel(
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
        tableView4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableView4MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableView4);

        btnNext.setBackground(new java.awt.Color(0, 153, 153));
        btnNext.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNext.setText("Selanjutnya");
        btnNext.setToolTipText("Halaman Selanjutnya");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel11.setText("Pengeluaran Investasi atau Modal Awal");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel13))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel11))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void SubmitTabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitTabelActionPerformed
        String nmBahan = namaBahan1.getText();
        String jumlah = jumlahBahan1.getText();
        String hSatuan = hargaBahan1.getText();
        String jenis = jenisBahan1.getText();
        
        idDTS = masukDatabase.generate();

        populatedTabel(idDTS, nmBahan, jumlah, hSatuan, jenis);
        
        DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(JLabel.RIGHT);
        tableView4.getColumnModel().getColumn(3).setCellRenderer((rightRender));

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
    }//GEN-LAST:event_SubmitTabelActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        int baris = tableView4.getSelectedRow();
        if(baris >= 0){
            //tbModel.setValueAt(idSementara, baris, 0);
            tblModel.setValueAt(namaBahan1.getText(), baris, 1);
            tblModel.setValueAt(jumlahBahan1.getText(), baris, 2);
            tblModel.setValueAt(hargaBahan1.getText(), baris,3);
            tblModel.setValueAt(jenisBahan1.getText(), baris, 4);
            
            dts.setNamaData(namaBahan1.getText());
            dts.setJumlah(jumlahBahan1.getText());
            dts.setHargaSatuan(hargaBahan1.getText());
            dts.setSatuan(jenisBahan1.getText());

            namaBahan1.setText("");
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
        int baris = tableView4.getSelectedRow();
        if(baris != -1){
            String data = tableView4.getValueAt(baris, 0).toString();
            
            int status = 1;
            if(status == 1){
                tblModel.removeRow(baris);
                namaBahan1.setText("");
                jumlahBahan1.setText("");
                hargaBahan1.setText("");
                jenisBahan1.setText("");
                
                masukDatabase.deleteData(dts);
                JOptionPane.showMessageDialog(this, "Data Bahan Berhasil di Hapus");
            }else{
                JOptionPane.showMessageDialog(this, "Data Gagal di Hapus");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Pilih Baris Data Dahulu");
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void jumlahBahan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahBahan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahBahan1ActionPerformed

    private void jumlahBahan1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jumlahBahan1FocusLost

    }//GEN-LAST:event_jumlahBahan1FocusLost

    private void jumlahBahan1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahBahan1KeyTyped
        char karakter = evt.getKeyChar();
        if (!(Character.isDigit(karakter) || karakter == KeyEvent.VK_BACK_SPACE)&& karakter != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_jumlahBahan1KeyTyped

    private void hargaBahan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaBahan1ActionPerformed
        //jumlahBahan1.setText(String.valueOf(hitungJumlah()));
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

    private void tableView4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableView4MouseClicked
        int i = tableView4.getSelectedRow();
        TableModel model = tableView4.getModel();
        DataSession.setIdDataTransaksi(model.getValueAt(i, 0).toString());
        namaBahan1.setText(model.getValueAt(i, 1).toString());
        jumlahBahan1.setText(model.getValueAt(i, 2).toString());
        hargaBahan1.setText(model.getValueAt(i, 3).toString());
        jenisBahan1.setText(model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_tableView4MouseClicked

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
                            new Outpuuut().setVisible(true);
                            this.dispose();
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

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        namaBahan1.setText("");
        jumlahBahan1.setText("");
        hargaBahan1.setText("");
        jenisBahan1.setText("");
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnListDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListDataActionPerformed
        new LihatDetailData().setVisible(true);
    }//GEN-LAST:event_btnListDataActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new HitungBEP().setVisible(true);
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
            java.util.logging.Logger.getLogger(HitungPBP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HitungPBP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HitungPBP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HitungPBP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HitungPBP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SubmitTabel;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnListData;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnUbah;
    private javax.swing.JTextField hargaBahan1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jenisBahan1;
    private javax.swing.JTextField jumlahBahan1;
    private javax.swing.JTextField namaBahan1;
    private javax.swing.JLabel namaLabelProduk;
    private javax.swing.JTable tableView4;
    private javax.swing.JLabel totalLabelProduk;
    // End of variables declaration//GEN-END:variables
}
