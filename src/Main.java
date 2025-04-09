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

    public String toString() {
        return kode + ": " + namaPerusahaan + " - Rp" + harga;
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

    public String toString() {
        return nama + ", Bunga: " + bunga + "%" + ", Kuota: Rp" + kuotaNasional;
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
            if (pilih == 1) login();
            else break;
        }
    }

    static void login() {
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        for (User u : users) {
            if (u.authenticate(user, pass)) {
                if (u instanceof Admin) {
                    currentAdmin = (Admin) u;
                    menuAdmin();
                } else if (u instanceof Customer) {
                    currentCustomer = (Customer) u;
                    menuCustomer();
                }
                return;
            }
        }
        System.out.println("Login gagal.");
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
            switch (pilih) {
                case 1:
                    menuAdminSaham();
                    break;
                case 2:
                    menuAdminSBN();
                    break;
                case 3:
                    currentAdmin = null;
                    return;
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
        } else if (pilih == 2) {
            for (int i = 0; i < daftarSaham.size(); i++) {
                System.out.println(i + ". " + daftarSaham.get(i));
            }
            System.out.print("Pilih indeks saham: ");
            int idx = sc.nextInt();
            sc.nextLine();
            System.out.print("Harga baru: ");
            double hargaBaru = sc.nextDouble();
            sc.nextLine();
            daftarSaham.get(idx).harga = hargaBaru;
        }
    }

    static void menuAdminSBN() {
        System.out.println("\n===== MANAJEMEN SBN =====");
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
            switch (pilih) {
                case 1:
                    beliSaham();
                    break;
                case 2:
                    jualSaham();
                    break;
                case 3:
                    beliSBN();
                    break;
                case 4:
                    simulasiSBN();
                    break;
                case 5:
                    lihatPortofolio();
                    break;
                case 6:
                    currentCustomer = null;
                    return;
            }
        }
    }

    static void beliSaham() {
        for (int i = 0; i < daftarSaham.size(); i++) {
            System.out.println(i + ". " + daftarSaham.get(i));
        }
        System.out.print("Pilih indeks saham: ");
        int idx = sc.nextInt();
        sc.nextLine();
        System.out.print("Jumlah lembar: ");
        int jumlah = sc.nextInt();
        sc.nextLine();
        Saham saham = daftarSaham.get(idx);
        currentCustomer.sahamDimiliki.put(saham, currentCustomer.sahamDimiliki.getOrDefault(saham, 0) + jumlah);
    }

    static void jualSaham() {
        int i = 0;
        List<Saham> list = new ArrayList<>(currentCustomer.sahamDimiliki.keySet());
        for (Saham s : list) {
            System.out.println(i + ". " + s + " - " + currentCustomer.sahamDimiliki.get(s) + " lembar");
            i++;
        }
        System.out.print("Pilih indeks: ");
        int idx = sc.nextInt();
        sc.nextLine();
        Saham saham = list.get(idx);
        System.out.print("Jumlah lembar dijual: ");
        int jumlah = sc.nextInt();
        sc.nextLine();
        int dimiliki = currentCustomer.sahamDimiliki.get(saham);
        if (jumlah > dimiliki) {
            System.out.println("Gagal: jumlah melebihi kepemilikan.");
        } else {
            if (jumlah == dimiliki) currentCustomer.sahamDimiliki.remove(saham);
            else currentCustomer.sahamDimiliki.put(saham, dimiliki - jumlah);
        }
    }

    static void beliSBN() {
        for (int i = 0; i < daftarSBN.size(); i++) {
            System.out.println(i + ". " + daftarSBN.get(i));
        }
        System.out.print("Pilih indeks SBN: ");
        int idx = sc.nextInt();
        sc.nextLine();
        System.out.print("Nominal pembelian: ");
        double nominal = sc.nextDouble();
        sc.nextLine();
        SBN sbn = daftarSBN.get(idx);
        if (nominal > sbn.kuotaNasional) {
            System.out.println("Kuota tidak mencukupi.");
            return;
        }
        sbn.kuotaNasional -= nominal;
        currentCustomer.sbnDimiliki.put(sbn, currentCustomer.sbnDimiliki.getOrDefault(sbn, 0.0) + nominal);
    }

    static void simulasiSBN() {
        for (Map.Entry<SBN, Double> entry : currentCustomer.sbnDimiliki.entrySet()) {
            double bungaBulanan = entry.getKey().bunga / 12 / 100 * 0.9 * entry.getValue();
            System.out.println(entry.getKey().nama + " - Rp" + bungaBulanan + "/bulan");
        }
    }

    static void lihatPortofolio() {
        System.out.println("\n===== PORTOFOLIO =====");
        System.out.println("Saham:");
        for (Map.Entry<Saham, Integer> entry : currentCustomer.sahamDimiliki.entrySet()) {
            double totalBeli = entry.getKey().harga * entry.getValue();
            System.out.println(entry.getKey() + ", Jumlah: " + entry.getValue() + " lembar, Total: Rp" + totalBeli);
        }
        System.out.println("\nSBN:");
        for (Map.Entry<SBN, Double> entry : currentCustomer.sbnDimiliki.entrySet()) {
            double bungaBulanan = entry.getKey().bunga / 12 / 100 * 0.9 * entry.getValue();
            System.out.println(entry.getKey().nama + ", Nominal: Rp" + entry.getValue() + ", Bunga/bulan: Rp" + bungaBulanan);
        }
    }
}
