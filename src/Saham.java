import java.util.Objects;

class Saham {
    String kode;
    String namaPerusahaan;
    double harga;

    public Saham(String kode, String namaPerusahaan, double harga) {
        this.kode = kode;
        this.namaPerusahaan = namaPerusahaan;
        this.harga = harga;
    }

    @Override
    public String toString() {
        return kode + ": " + namaPerusahaan + " - Rp" + harga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Saham)) return false;
        Saham saham = (Saham) o;
        return Objects.equals(kode, saham.kode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kode);
    }
}
