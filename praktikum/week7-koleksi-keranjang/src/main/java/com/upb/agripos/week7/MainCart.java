package com.upb.agripos.week7;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Nama : Ade Miko | NIM : 2420202827");
        System.out.println("====================================");

        Product p1 = new Product("P01", "Benih Padi", 50000);
        Product p2 = new Product("P02", "Pupuk Cair", 30000);

        // Test ArrayList & Search
        ShoppingCart listCart = new ShoppingCart();
        listCart.addProduct(p1);
        Product search = listCart.findProductByCode("P01");
        System.out.println("Hasil Cari P01: " + (search != null ? search.getName() : "Tidak ada"));

        // Test Map & Tax
        ShoppingCartMap mapCart = new ShoppingCartMap();
        mapCart.addProduct(p1);
        mapCart.addProduct(p1);
        mapCart.addProduct(p2);
        mapCart.printCart();
        System.out.println("Total + Pajak 11%: Rp" + mapCart.getTotalWithTax(0.11));

        // Test Set (Customer)
        CustomerList cList = new CustomerList();
        cList.addCustomer("Ade Miko");
        cList.addCustomer("Ade Miko"); // Akan ditolak
        cList.printAll();

        // Test Clear
        mapCart.emptyCart();
        System.out.println("Setelah dikosongkan, Total: Rp" + mapCart.getTotal());
    }
}
