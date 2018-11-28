package bismillahpata.Model;

public class HistoryModel {
    private String nmPerusahaan;
    private String idProduk;
    private String namaProduk;
    private long jumlah;
    private String satuanProduk;
    private long ppn;
    private long margin;
    private long hasilHPP;
    private long hasilBEP;
    private long hasilPBP;
    private long totalPengeluaran;

    public String getSatuanProduk() {
        return satuanProduk;
    }

    public void setSatuanProduk(String satuanProduk) {
        this.satuanProduk = satuanProduk;
    }
    
    

    public long getTotalPengeluaran() {
        return totalPengeluaran;
    }

    public void setTotalPengeluaran(long totalPengeluaran) {
        this.totalPengeluaran = totalPengeluaran;
    }

    public String getNmPerusahaan() {
        return nmPerusahaan;
    }

    public void setNmPerusahaan(String nmPerusahaan) {
        this.nmPerusahaan = nmPerusahaan;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public long getJumlah() {
        return jumlah;
    }

    public void setJumlah(long jumlah) {
        this.jumlah = jumlah;
    }

    public long getPpn() {
        return ppn;
    }

    public void setPpn(long ppn) {
        this.ppn = ppn;
    }

    public long getMargin() {
        return margin;
    }

    public void setMargin(long margin) {
        this.margin = margin;
    }

    public long getHasilHPP() {
        return hasilHPP;
    }

    public void setHasilHPP(long hasilHPP) {
        this.hasilHPP = hasilHPP;
    }

    public long getHasilBEP() {
        return hasilBEP;
    }

    public void setHasilBEP(long hasilBEP) {
        this.hasilBEP = hasilBEP;
    }

    public long getHasilPBP() {
        return hasilPBP;
    }

    public void setHasilPBP(long hasilPBP) {
        this.hasilPBP = hasilPBP;
    }    
}
