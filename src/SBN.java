import java.util.Objects;

class SBN {
    String nama;
    double bunga;
    int jangkaWaktu;
    String tanggalJatuhTempo;
    double kuotaNasional;

    public SBN(String nama, double bunga, int jangkaWaktu, String tanggalJatuhTempo, double kuotaNasional) {
        this.nama = nama;
        this.bunga = bunga;
        this.jangkaWaktu = jangkaWaktu;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.kuotaNasional = kuotaNasional;
    }

    @Override
    public String toString() {
        return nama + ", Bunga: " + bunga + "%, Kuota: Rp" + kuotaNasional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SBN)) return false;
        SBN sbn = (SBN) o;
        return Objects.equals(nama, sbn.nama);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nama);
    }
}
