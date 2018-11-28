package bismillahpata.Model;

public class Produk{
    private String idProduk;
    private String namaProduk;
    private double jumlah;
    private String satuanProduk;
    private double ppn;
    private double margin;

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

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getSatuanProduk() {
        return satuanProduk;
    }

    public void setSatuanProduk(String satuanProduk) {
        this.satuanProduk = satuanProduk;
    }

    public double getPpn() {
        return ppn;
    }

    public void setPpn(double ppn) {
        this.ppn = ppn;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }
}
