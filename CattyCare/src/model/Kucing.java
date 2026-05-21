package model;

public class Kucing extends Entitas implements Biaya {
    private String namaPemilik;
    private String namaKucing;
    private String nomorTelepon;
    private int lamaPenitipan;
    private int biaya;

    public Kucing() {
        
    }
    public Kucing(int id, String namaPemilik, String namaKucing, String nomorTelepon, int lamaPenitipan) {
        super(id);
        this.namaPemilik = namaPemilik;
        this.namaKucing = namaKucing;
        this.nomorTelepon = nomorTelepon;
        this.lamaPenitipan = lamaPenitipan;
        this.biaya = hitungBiaya();
    }
    public Kucing(String namaPemilik, String namaKucing, String nomorTelepon, int lamaPenitipan) {
        this.namaPemilik = namaPemilik;
        this.namaKucing = namaKucing;
        this.nomorTelepon = nomorTelepon;
        this.lamaPenitipan = lamaPenitipan;
        this.biaya = hitungBiaya();
    }

    @Override
    public int hitungBiaya() { // Menghitung biaya berdasarkan lama penitipan
        if (lamaPenitipan <= 2) {
            return biaya = 40000 * lamaPenitipan;
        }else{
            return biaya = 80000 + (30000 * lamaPenitipan); // Angka 80000 didapatkan dari total biaya pada 2 hari pertama yaitu 40000 + 40000
        }
    }
    public void refreshBiaya() {
        this.biaya = hitungBiaya();
    }
    public String getNamaPemilik() {
        return namaPemilik;
    }
    public void setNamaPemilik(String nama) {
        this.namaPemilik = nama;
    }
    public String getNamaKucing() {
        return namaKucing;
    }
    public void setNamaKucing(String nama) {
        this.namaKucing = nama;
    }
    public String getNomorTelepon() {
        return nomorTelepon;
    }
    public void setNomorTelepon(String nomor) {
        this.nomorTelepon = nomor;
    }
    public int getLamaPenitipan() {
        return lamaPenitipan;
    }
    public void setLamaPenitipan(int waktu) {
        this.lamaPenitipan = waktu;
        hitungBiaya();
    }
    public int getBiaya() {
        return biaya;
    }
}