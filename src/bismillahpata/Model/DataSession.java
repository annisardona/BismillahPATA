package bismillahpata.Model;

public class DataSession {
    public static String idProduk, namaProduk;
    public static String idPerusahaan;
    public static String idDataTransaksi;
    public static int increment;
    public static long totalHPP1, totalHPP2, totalBEP, totalPBP;
    public static double totalProduk, persentaseBahan;
    public static Produk mProduk;

    public static Produk getmProduk() {
        return mProduk;
    }

    public static void setmProduk(Produk mProduk) {
        DataSession.mProduk = mProduk;
    }
        
    public static long getTotalPBP() {
        return totalPBP;
    }

    public static void setTotalPBP(long totalPBP) {
        DataSession.totalPBP = totalPBP;
    }

    public static int getIncrement() {
        return increment;
    }  
    
    public static void resetIncrement() {
        increment = 0;
    } 
    
    public static void doIncrement(){
        increment++;
    }

    public static String getIdDataTransaksi() {
        return idDataTransaksi;
    }

    public static void setIdDataTransaksi(String idDataTransaksi) {
        DataSession.idDataTransaksi = idDataTransaksi;
    }

    public static double getPersentaseBahan() {
        return persentaseBahan;
    }

    public static void setPersentaseBahan(double persentaseBahan) {
        DataSession.persentaseBahan = persentaseBahan;
    }

    public static String getNamaProduk() {
        return namaProduk;
    }

    public static void setNamaProduk(String namaProduk) {
        DataSession.namaProduk = namaProduk;
    }
    
    public static long getTotalBEP() {
        return totalBEP;
    }

    public static void setTotalBEP(long totalBEP) {
        DataSession.totalBEP = totalBEP;
    }

    public static double getTotalProduk() {
        return totalProduk;
    }

    public static void setTotalProduk(double totalProduk) {
        DataSession.totalProduk = totalProduk;
    }

    public static long getTotalHPP1() {
        return totalHPP1;
    }

    public static void setTotalHPP1(long totalHPP1) {
        DataSession.totalHPP1 = totalHPP1;
    }

    public static long getTotalHPP2() {
        return totalHPP2;
    }

    public static void setTotalHPP2(long totalHPP2) {
        DataSession.totalHPP2 = totalHPP2;
    }

    public static String getIdProduk() {
        return idProduk;
    }

    public static void setIdProduk(String idProduk) {
        DataSession.idProduk = idProduk;
    }

    public static String getIdPerusahaan() {
        return idPerusahaan;
    }

    public static void setIdPerusahaan(String idPerusahaan) {
        DataSession.idPerusahaan = idPerusahaan;
    }
}
