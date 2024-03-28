package org.example;

import java.io.*;
import java.util.*;

import java.util.*;

public class Main {

    private static final LinkedHashMap<String, Integer> menus = new LinkedHashMap<String, Integer>() {{
        put("Nasi goreng", 20000);
        put("Mie goreng", 15000);
        put("Gado-gado", 10000);
        put("Bubur ayam", 12000);
        put("Soto ayam", 16000);
    }};

    private static final HashSet<OrderItem> order = new HashSet<>();

    private static final Scanner sc = new Scanner(System.in);

    private static void showOrderMenu() {
        System.out.println("==========================");
        System.out.println("Selamat datang di BinarFud");
        System.out.println("==========================\n");
        System.out.println("Silahkan pilih makanan : ");
        int index = 1;
        for(Map.Entry<String, Integer> menu : menus.entrySet()) {
            System.out.println(index + ". " + menu.getKey() + "\t|\t" + menu.getValue());
            index++;
        }
        System.out.println("99. Pesan dan bayar");
        System.out.println("0. Keluar aplikasi\n");

        while(true) {
            System.out.print("=> ");
            if (sc.hasNextInt()) {
                int selectedMenu = sc.nextInt();
                if (selectedMenu == 99) {
                    if (!order.isEmpty()) {
                        showOrderConfirmationMenu();
                        break;
                    } else {
                        System.out.println("Pilih menu terlebih dahulu!");
                    }
                } else if (selectedMenu > menus.size() || selectedMenu < 0) {
                    System.out.println("Silahkan pilih menu 1 - " + menus.size());
                } else if(selectedMenu == 0){
                    break;
                } else {
                    String menu = menuAt(selectedMenu);
                    if (isItemInOrder(menu)) {
                        System.out.println("Menu ini sudah ada di pesanan Anda. Silahkan pesan menu yang lain.");
                    } else {
                        showOrderQuantity(selectedMenu);
                        break;
                    }
                }
            } else {
                System.out.println("Masukkan input yang benar");
                sc.next();
            }
        }
    }

    private static boolean isItemInOrder(String menu) {
        for (OrderItem item : order) {
            if (item.getItem().equals(menu)) {
                return true;
            }
        }
        return false;
    }

    private static void showOrderQuantity(int selectedMenu) {
        System.out.println("==========================");
        System.out.println("Berapa pesanan anda");
        System.out.println("==========================\n");
        String menu = menuAt(selectedMenu);
        System.out.println(menu + "\t| " + menus.get(menu) + "\n");
        while(true) {
            System.out.print("qty => ");
            if (sc.hasNextInt()) {
                int qty = sc.nextInt();
                if (qty < 1) {
                    System.out.println("Masukkan angka lebih dari 1");
                } else {
                    OrderItem orderItem = new OrderItem(menu, qty);
                    order.add(orderItem);
                    break;
                }
            } else {
                System.out.println("Masukkan input yang benar");
                sc.next();
            }
        }
        System.out.println();
        showOrderMenu();
    }

    private static void showOrderConfirmationMenu() {
        System.out.println("\n==========================");
        System.out.println("Konfirmasi pembayaran");
        System.out.println("==========================\n");
        int totalQty = 0;
        int totalPrice = 0;
        for(OrderItem orderedItem : order) {
            String menu = orderedItem.getItem();
            int quantity = orderedItem.getQuantity();
            int totalPricePerMenu = menus.get(menu) * quantity;
            System.out.println(menu + "\t" + quantity + "\t" + totalPricePerMenu);
            totalQty += quantity;
            totalPrice += totalPricePerMenu;
        }
        System.out.println("----------------------+");
        System.out.println("Total" + "\t\t" + totalQty + "\t" + totalPrice + "\n");

        System.out.println("1. Konfirmasi pembayaran");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi\n");

        while(true) {
            System.out.print("=> ");
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                if (input == 0) break;
                else if (input == 1) {
                    generatePaymentReceipt(totalQty, totalPrice);
                    break;
                } else if (input == 2) {
                    showOrderMenu();
                    break;
                } else {
                    System.out.println("Pilih angka 0 - 2");
                }
            } else {
                System.out.println("Masukkan input yang benar");
                sc.next();
            }
        }
    }

    private static void generatePaymentReceipt(int totalQty, int totalPrice) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("payment_receipt.txt"))) {
            writer.write("==========================\n");
            writer.write("BinarFud\n");
            writer.write("==========================\n\n");
            writer.write("Terima kasih sudah memesan\ndi BinarFud\n\n");
            writer.write("Dibawah ini adalah pesanan anda\n\n");
            for (OrderItem orderedItem : order) {
                String menu = orderedItem.getItem();
                int quantity = orderedItem.getQuantity();
                int totalPricePerMenu = menus.get(menu) * quantity;
                writer.write(menu + "\t" + quantity + "\t" + totalPricePerMenu + "\n");
            }
            writer.write("----------------------+\n");
            writer.write("Total" + "\t\t" + totalQty + "\t" + totalPrice + "\n\n");
            writer.write("Pembayaran : BinarCash\n\n");
            writer.write("==========================\n");
            writer.write("Simpan struk ini sebagai\nbukti pembayaran\n");
            writer.write("==========================\n\n");
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menyimpan struk pembayaran: " + e.getMessage());
        }
        System.out.println();
        readPaymentReceiptFromFile();
    }

    private static void readPaymentReceiptFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("payment_receipt.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca struk pembayaran: " + e.getMessage());
        }
    }

    private static String menuAt(int index) {
        int count = 1;
        for (String menu : menus.keySet()) {
            if (count == index) {
                return menu;
            }
            count++;
        }
        return null;
    }

    public static void main(String[] args) {
        showOrderMenu();
        sc.close();
    }
}
