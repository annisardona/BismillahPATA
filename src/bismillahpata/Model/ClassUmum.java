package bismillahpata.Model;

import bismillahpata.Controller.ProdukController;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JFrame;

public class ClassUmum {
    long jumlah = 0, ppn = 0, margin = 0, totalHitungan = 0;
    String namaProduk, nmPerusahaan, idProduk;
    long hasilHPP = 0, hargaSetelahPajakPertambahanNilai = 0, hargaJual = 0, pendapatanSetelahPPN = 0, pendapatanPerTahun = 0, untungPerUnit = 0, untungPerProduksi = 0, untungPerTahun = 0;
    long totalPengeluaran = 0, hasilBEP = 0, hasilPBP = 0, hasilROI = 0;
    ProdukController pc = new ProdukController();
    List<HistoryModel> listHistory = pc.selectAll();
    
    //fungsi logout
    
    //fungsi windowFrame
    public void windowFrame(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //fungsi perhitungan
    public void perhitungan(){
        for(HistoryModel history : listHistory){
                Object[] his = {
//                                nmPerusahaan = history.getNmPerusahaan(),
                                idProduk = history.getIdProduk(),
                                namaProduk = history.getNamaProduk(),
                                jumlah = history.getJumlah(),
                                ppn = history.getPpn(),
                                margin = history.getMargin(),
                                hasilHPP = history.getHasilHPP(),
                                hasilBEP = history.getHasilBEP(),
                                hasilPBP = history.getHasilPBP()
                             };
                System.out.println("hasil HPP "+hasilHPP);
                System.out.println("convert to double "+(double) hasilBEP);
                
                hargaSetelahPajakPertambahanNilai = hasilHPP+ hasilHPP *  ppn /100;
                System.out.println("harga stlh pajak pertambahan nilai "+hargaSetelahPajakPertambahanNilai);
                
                hargaJual = hargaSetelahPajakPertambahanNilai + hargaSetelahPajakPertambahanNilai * margin/100;  
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
                String akhirPBP;
                if(outputPBP <= (0.08)){
                    akhirPBP = "1 bulan";
                }else if (outputPBP >= (0.08) && (0.16) <= outputPBP){
                    akhirPBP = "2 bulan";
                }else if (outputPBP <= (0.16) && (0.25) <= outputPBP){
                    akhirPBP = "3 bulan";
                }else if (outputPBP <= (0.25) && (0.34) <= outputPBP){
                    akhirPBP = "4 bulan";
                }else //if (5/12 >= outputPBP)//
                {
                    akhirPBP = "5 bulan";
                }
//                }else if (6/12 >= outputPBP){
//                    akhirPBP = "6 bulan";
//                }else if (7/12 >= outputPBP){
//                    akhirPBP = "7 bulan";
//                }else if (8/12 >= outputPBP){
//                    akhirPBP = "8 bulan";
//                }else if (9/12 >= outputPBP){
//                    akhirPBP = "9 bulan";
//                }else if (10/12 >= outputPBP){
//                    akhirPBP = "10 bulan";
//                }else if (11/12 >= outputPBP){
//                    akhirPBP = "11 bulan";
//                }else if (12/12 >= outputPBP){
//                    akhirPBP = "1 tahun";
//                }else if (24/12 >= outputPBP){
//                    akhirPBP = "2 tahun";
//                }else if (36/12 >= outputPBP){
//                    akhirPBP = "3 tahun";
//                }else{
//                    akhirPBP = "lebih 4 tahun";
//                }
//                belajar lagi rumus PBP say
//
                double hasilROI = (double) untungPerTahun / hasilPBP * 100;
                System.out.println("hasil ROI "+df.format(hasilROI));
                
//                populatedTabel(idProduk, namaProduk, jumlah, ppn, margin, hasilHPP, hargaJual,df.format(outputBEP), akhirPBP, df.format(outputPBP), df.format(hasilROI));        
        }
          
    }
}
