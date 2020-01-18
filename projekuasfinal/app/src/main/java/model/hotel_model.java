package model;

public class hotel_model {
    String _id, kode_boking, nama, no_ktp, tanggal_pemesanan,  tanggal_keluar ,  jumlah_kamar, harga;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getKode_boking() {
        return kode_boking;
    }

    public void setKode_boking(String kode_boking) {
        this.kode_boking = kode_boking;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getTanggal_pemesanan() {
        return tanggal_pemesanan;
    }

    public void setTanggal_pemesanan(String tanggal_pemesanan) {
        this.tanggal_pemesanan = tanggal_pemesanan;
    }

    public String getTanggal_keluar() {
        return tanggal_keluar;
    }

    public void setTanggal_keluar(String tanggal_keluar) {
        this.tanggal_keluar = tanggal_keluar;
    }

    public String getJumlah_kamar() {
        return jumlah_kamar;
    }

    public void setJumlah_kamar(String jumlah_kamar) {
        this.jumlah_kamar = jumlah_kamar;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
