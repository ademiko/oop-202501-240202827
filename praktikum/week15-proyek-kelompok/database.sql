-- 1. MEMBERSIHKAN TABEL LAMA (Opsional, agar tidak bentrok)
DROP TABLE IF EXISTS transaction_items;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

-- 2. TABEL PENGGUNA (LOGIN & HAK AKSES)
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL -- 'admin' atau 'kasir'
);

-- 3. TABEL PRODUK (STOK & HARGA)
CREATE TABLE products (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price DOUBLE PRECISION DEFAULT 0, -- Digunakan di tabel admin & kasir
    stock INT DEFAULT 0 -- Dipotong otomatis saat transaksi
);

-- 4. TABEL TRANSAKSI (HEADER)
CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    total_amount DOUBLE PRECISION,
    payment_method VARCHAR(20), -- 'Tunai' atau 'QRIS'
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. TABEL ITEM TRANSAKSI (DETAIL & KUANTITAS)
CREATE TABLE transaction_items (
    id SERIAL PRIMARY KEY,
    transaction_id INT REFERENCES transactions(id) ON DELETE CASCADE,
    product_code VARCHAR(10) REFERENCES products(code),
    quantity INT NOT NULL, -- Mencatat jumlah beli yang diinput kasir
    price_at_transaction DOUBLE PRECISION NOT NULL -- Harga saat dibeli
);

--- DATA AWAL (SEEDER) ---

-- Masukkan akun sesuai permintaanmu
INSERT INTO users (username, password, role) VALUES 
('admin', '123', 'admin'),
('kasir', '123', 'kasir');

-- Masukkan beberapa produk untuk tes
INSERT INTO products (code, name, category, price, stock) VALUES 
('P01', 'Pupuk Urea', 'Pupuk', 150000, 10),
('P02', 'Bibit Jagung', 'Benih', 50000, 25),
('P03', 'Pestisida Cair', 'Obat', 85000, 15);

SELECT * FROM products ORDER BY code ASC;
SELECT * FROM transactions ORDER BY transaction_date DESC;
