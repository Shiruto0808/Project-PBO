
# Investasi Saham dan SBN App

Aplikasi ini merupakan aplikasi simulasi untuk membeli dan menjual saham serta Surat Berharga Negara (SBN). Didesain untuk memudahkan pengguna dalam melakukan transaksi saham dan investasi SBN.

## Fitur Utama

- **Login**: Pengguna dapat login sebagai admin atau customer.
- **Admin**: Admin memiliki akses untuk menambah saham dan SBN, serta mengubah harga saham.
- **Customer**: Customer dapat membeli dan menjual saham, membeli SBN, serta melihat portofolio mereka.
- **Simulasi SBN**: Customer dapat melakukan simulasi perhitungan kupon bulanan dari SBN yang dibeli.

## Diagram UML

Berikut adalah diagram UML yang menggambarkan hubungan antar kelas dalam aplikasi ini: "https://app.diagrams.net/#G1EsNqU3JrVMN0-nZKqYldx8z-oNI07rPh#%7B%22pageId%22%3A%22X4EWLqRm33CRsZ2IQbLl%22%7D"


- **User** adalah kelas dasar yang digunakan oleh semua pengguna, dengan atribut seperti `username`, `password`, dan `role`.
- **Customer** mewarisi kelas **User** dan memiliki fitur untuk membeli saham dan SBN, serta menyimpan portofolio.
- **Saham** dan **SuratBerhargaNegara** adalah entitas yang dapat dibeli oleh customer. Setiap saham memiliki informasi seperti kode, nama perusahaan, dan harga, sementara SBN memiliki nama, bunga, jangka waktu, dan kuota nasional.
- **PortofolioSBN** menyimpan informasi mengenai pembelian SBN oleh customer, termasuk nominal dan perhitungan kupon bulanan.

## Instalasi

Untuk menjalankan aplikasi ini, pastikan Anda memiliki Java 8 atau lebih baru terinstal pada sistem Anda.

1. Clone repositori ini ke komputer Anda.
2. Buka terminal dan navigasikan ke folder proyek.
3. Jalankan aplikasi dengan perintah:
   ```bash
   javac InvestasiApp.java
   java InvestasiApp
   ```

## Penggunaan

1. **Login**: Masukkan username dan password untuk masuk sebagai admin atau customer.
2. **Admin Menu**:
   - **Tambah Saham**: Menambahkan saham baru ke dalam sistem.
   - **Ubah Harga Saham**: Mengubah harga saham yang ada.
   - **Tambah SBN**: Menambahkan Surat Berharga Negara baru.
3. **Customer Menu**:
   - **Beli Saham**: Membeli saham dari daftar yang tersedia.
   - **Jual Saham**: Menjual saham yang dimiliki.
   - **Beli SBN**: Membeli Surat Berharga Negara.
   - **Simulasi SBN**: Melihat simulasi perhitungan kupon bulanan dari investasi SBN.
   - **Portofolio**: Melihat portofolio saham dan SBN yang dimiliki.

## Contributing

Jika Anda ingin berkontribusi pada proyek ini, silakan lakukan langkah-langkah berikut:
1. Fork repositori ini.
2. Buat cabang baru (`git checkout -b fitur-anda`).
3. Lakukan perubahan yang diperlukan.
4. Commit perubahan Anda (`git commit -am 'Menambahkan fitur baru'`).
5. Push ke cabang Anda (`git push origin fitur-anda`).
6. Buat pull request.
