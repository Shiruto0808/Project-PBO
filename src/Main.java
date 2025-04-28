import java.util.*;

abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
}

class Customer extends User {
    Map<Saham, Integer> sahamDimiliki = new HashMap<>();
    Map<SBN, Double> sbnDimiliki = new HashMap<>();

    public Customer(String username, String password) {
        super(username, password);
    }
}

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

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Saham> daftarSaham = new ArrayList<>();
    static List<SBN> daftarSBN = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static Customer currentCustomer;
    static Admin currentAdmin;

    public static void main(String[] args) {
        initDummyData();
        menuAwal();
    }

    static void initDummyData() {
        users.add(new Admin("admin", "admin123"));
        users.add(new Customer("cus1", "cus123"));
        daftarSaham.add(new Saham("BBCA", "Bank BCA", 9500));
        daftarSBN.add(new SBN("SBN Seri A", 6.5, 12, "2026-04-01", 10000000));
    }

    static void menuAwal() {
        while (true) {
            System.out.println("\n===== MENU AWAL =====");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Pilih: ");
            int pilih = sc.nextInt();
            sc.nextLine();
            if (pilih == 1) {
                login();
            } else if (pilih == 2) {
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void login() {
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        boolean found = false;
        for (User u : users) {
            if (u.authenticate(user, pass)) {
                found = true;
                if (u instanceof Admin) {
                    currentAdmin = (Admin) u;
                    menuAdmin();
                } else if (u instanceof Customer) {
                    currentCustomer = (Customer) u;
                    menuCustomer();
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Login gagal. Username atau password salah.");
        }
    }

    static void menuAdmin() {
        while (true) {
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1. Saham");
            System.out.println("2. SBN");
            System.out.println("3. Logout");
            System.out.print("Pilih: ");
            int pilih = sc.nextInt();
            sc.nextLine();
            if (pilih == 1) {
                menuAdminSaham();
            } else if (pilih == 2) {
                menuAdminSBN();
            } else if (pilih == 3) {
                currentAdmin = null;
                return;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void menuAdminSaham() {
        System.out.println("\n===== MANAJEMEN SAHAM =====");
        System.out.println("1. Tambah Saham");
        System.out.println("2. Ubah Harga Saham");
        System.out.println("3. Kembali");
        System.out.print("Pilih: ");
        int pilih = sc.nextInt();
        sc.nextLine();
        if (pilih == 1) {
            System.out.print("Kode: ");
            String kode = sc.nextLine();
            System.out.print("Nama: ");
            String nama = sc.nextLine();
            System.out.print("Harga: ");
            double harga = sc.nextDouble();
            sc.nextLine();
            daftarSaham.add(new Saham(kode, nama, harga));
            System.out.println("Saham berhasil ditambahkan!");
        } else if (pilih == 2) {
            for (int i = 0; i < daftarSaham.size(); i++) {
                System.out.println(i + ". " + daftarSaham.get(i));
            }
            System.out.print("Pilih indeks saham: ");
            int idx = sc.nextInt();
            sc.nextLine();
            if (idx >= 0 && idx < daftarSaham.size()) {
                System.out.print("Harga baru: ");
                double hargaBaru = sc.nextDouble();
                sc.nextLine();
                daftarSaham.get(idx).harga = hargaBaru;
                System.out.println("Harga saham berhasil diubah!");
            } else {
                System.out.println("Indeks tidak valid.");
            }
        } else if (pilih == 3) {
            return;
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    static void menuAdminSBN() {
        System.out.println("\n===== TAMBAH SBN =====");
        System.out.print("Nama: ");
        String nama = sc.nextLine();
        System.out.print("Bunga (%): ");
        double bunga = sc.nextDouble();
        sc.nextLine();
        System.out.print("Jangka waktu (bulan): ");
        int waktu = sc.nextInt();
        sc.nextLine();
        System.out.print("Tanggal Jatuh Tempo: ");
        String tgl = sc.nextLine();
        System.out.print("Kuota Nasional: ");
        double kuota = sc.nextDouble();
        sc.nextLine();
        daftarSBN.add(new SBN(nama, bunga, waktu, tgl, kuota));
        System.out.println("SBN berhasil ditambahkan!");
    }

    static void menuCustomer() {
        while (true) {
            System.out.println("\n===== MENU CUSTOMER =====");
            System.out.println("1. Beli Saham");
            System.out.println("2. Jual Saham");
            System.out.println("3. Beli SBN");
            System.out.println("4. Simulasi SBN");
            System.out.println("5. Portofolio");
            System.out.println("6. Logout");
            System.out.print("Pilih: ");
            int pilih = sc.nextInt();
            sc.nextLine();
            if (pilih == 1) {
                beliSaham();
            } else if (pilih == 2) {
                jualSaham();
            } else if (pilih == 3) {
                beliSBN();
            } else if (pilih == 4) {
                simulasiSBN();
            } else if (pilih == 5) {
                lihatPortofolio();
            } else if (pilih == 6) {
                currentCustomer = null;
                return;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void beliSaham() {
        if (daftarSaham.isEmpty()) {
            System.out.println("Belum ada saham tersedia.");
            return;
        }
        for (int i = 0; i < daftarSaham.size(); i++) {
            System.out.println(i + ". " + daftarSaham.get(i));
        }
        System.out.print("Pilih indeks saham: ");
        int idx = sc.nextInt();
        sc.nextLine();
        if (idx >= 0 && idx < daftarSaham.size()) {
            System.out.print("Jumlah lembar: ");
            int jumlah = sc.nextInt();
            sc.nextLine();
            Saham saham = daftarSaham.get(idx);
            currentCustomer.sahamDimiliki.put(saham, currentCustomer.sahamDimiliki.getOrDefault(saham, 0) + jumlah);
            System.out.println("Saham berhasil dibeli!");
        } else {
            System.out.println("Indeks tidak valid.");
        }
    }

    static void jualSaham() {
        if (currentCustomer.sahamDimiliki.isEmpty()) {
            System.out.println("Anda belum memiliki saham.");
            return;
        }
        int i = 0;
        List<Saham> list = new ArrayList<>(currentCustomer.sahamDimiliki.keySet());
        for (Saham s : list) {
            System.out.println(i + ". " + s + " - " + currentCustomer.sahamDimiliki.get(s) + " lembar");
            i++;
        }
        System.out.print("Pilih indeks: ");
        int idx = sc.nextInt();
        sc.nextLine();
        if (idx >= 0 && idx < list.size()) {
            Saham saham = list.get(idx);
            System.out.print("Jumlah lembar dijual: ");
            int jumlah = sc.nextInt();
            sc.nextLine();
            int dimiliki = currentCustomer.sahamDimiliki.get(saham);
            if (jumlah > dimiliki) {
                System.out.println("Gagal: jumlah melebihi kepemilikan.");
            } else {
                if (jumlah == dimiliki) {
                    currentCustomer.sahamDimiliki.remove(saham);
                } else {
                    currentCustomer.sahamDimiliki.put(saham, dimiliki - jumlah);
                }
                System.out.println("Saham berhasil dijual!");
            }
        } else {
            System.out.println("Indeks tidak valid.");
        }
    }

    static void beliSBN() {
        if (daftarSBN.isEmpty()) {
            System.out.println("Belum ada SBN tersedia.");
            return;
        }
        for (int i = 0; i < daftarSBN.size(); i++) {
            System.out.println(i + ". " + daftarSBN.get(i));
        }
        System.out.print("Pilih indeks SBN: ");
        int idx = sc.nextInt();
        sc.nextLine();
        if (idx >= 0 && idx < daftarSBN.size()) {
            System.out.print("Nominal pembelian: ");
            double nominal = sc.nextDouble();
            sc.nextLine();
            SBN sbn = daftarSBN.get(idx);
            if (nominal > sbn.kuotaNasional) {
                System.out.println("Kuota tidak mencukupi.");
            } else {
                sbn.kuotaNasional -= nominal;
                currentCustomer.sbnDimiliki.put(sbn, currentCustomer.sbnDimiliki.getOrDefault(sbn, 0.0) + nominal);
                System.out.println("SBN berhasil dibeli!");
            }
        } else {
            System.out.println("Indeks tidak valid.");
        }
    }

    static void simulasiSBN() {
        if (currentCustomer.sbnDimiliki.isEmpty()) {
            System.out.println("Anda belum memiliki SBN.");
            return;
        }
        for (Map.Entry<SBN, Double> entry : currentCustomer.sbnDimiliki.entrySet()) {
            double bungaBulanan = entry.getKey().bunga / 12 / 100 * 0.9 * entry.getValue();
            System.out.println(entry.getKey().nama + " - Rp" + bungaBulanan + "/bulan");
        }
    }

    static void lihatPortofolio() {
        System.out.println("\n===== PORTOFOLIO =====");
        System.out.println("Saham:");
        if (currentCustomer.sahamDimiliki.isEmpty()) {
            System.out.println("Tidak ada saham.");
        } else {
            for (Map.Entry<Saham, Integer> entry : currentCustomer.sahamDimiliki.entrySet()) {
                double totalBeli = entry.getKey().harga * entry.getValue();
                System.out.println(entry.getKey() + ", Jumlah: " + entry.getValue() + " lembar, Total: Rp" + totalBeli);
            }
        }

        System.out.println("\nSBN:");
        if (currentCustomer.sbnDimiliki.isEmpty()) {
            System.out.println("Tidak ada SBN.");
        } else {
            for (Map.Entry<SBN, Double> entry : currentCustomer.sbnDimiliki.entrySet()) {
                double bungaBulanan = entry.getKey().bunga / 12 / 100 * 0.9 * entry.getValue();
                System.out.println(entry.getKey().nama + ", Nominal: Rp" + entry.getValue() + ", Bunga/bulan: Rp" + bungaBulanan);
            }
        }
    }
}
