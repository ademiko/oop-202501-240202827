-- Menambahkan kolom quantity ke tabel detail transaksi
ALTER TABLE transaction_items 
ADD COLUMN IF NOT EXISTS quantity INT DEFAULT 1;

-- Menambahkan kolom payment_method ke tabel transaksi
ALTER TABLE transactions 
ADD COLUMN IF NOT EXISTS payment_method VARCHAR(20);

-- Tambahan: Pastikan kolom stock ada di tabel produk
ALTER TABLE products 
ADD COLUMN IF NOT EXISTS stock INT DEFAULT 0;