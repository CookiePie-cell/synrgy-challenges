package org.example;

import java.io.*;
import java.util.List;

public class PaymentReceiptGenerator implements ReceiptGenerator {

    private final File file;

    public PaymentReceiptGenerator(File file) {
        this.file = file;
    }

    public PaymentReceiptGenerator() {
        this.file = new File("payment_receipt.txt");
    }

    @Override
    public void generatePaymentReceipt(int totalQty, int totalPrice, List<Menu> menus) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("==========================\n");
            writer.write("BinarFud\n");
            writer.write("==========================\n\n");
            writer.write("Terima kasih sudah memesan\ndi BinarFud\n\n");
            writer.write("Dibawah ini adalah pesanan anda\n\n");
            for (Menu menu : menus) {
                FoodItem item = (FoodItem) menu;
                if (item.getIsOrdered()) {
                    String food = item.getFood();
                    int quantity = item.getQuantity();
                    int totalPricePerMenu = item.getTotalPrice();
                    writer.write(food + "\t" + quantity + "\t" + totalPricePerMenu + "\n");
                }
            }
            writer.write("----------------------+\n");
            writer.write("Total" + "\t\t" + totalQty + "\t" + totalPrice + "\n\n");
            writer.write("Pembayaran : BinarCash\n\n");
            writer.write("==========================\n");
            writer.write("Simpan struk ini sebagai\nbukti pembayaran\n");
            writer.write("==========================\n\n");
        }
    }

    @Override
    public void readPaymentReceiptFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca struk pembayaran: " + e.getMessage());
        }
    }
}
