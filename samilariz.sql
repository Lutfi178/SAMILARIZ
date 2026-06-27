DROP DATABASE IF EXISTS samilariz;
CREATE DATABASE samilariz;
USE samilariz;
CREATE TABLE konsumen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    harga DOUBLE NOT NULL,
    jumlah_barang INT NOT NULL,
    total_bayar DOUBLE NOT NULL,
    bonus VARCHAR(100) NOT NULL
);
INSERT INTO konsumen (nama, harga, jumlah_barang, total_bayar, bonus)
VALUES ('Andi', 50000, 2, 100000, 'Voucher');