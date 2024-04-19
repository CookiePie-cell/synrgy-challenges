package org.example;

import java.io.IOException;
import java.util.List;

public interface ReceiptGenerator {
    void generatePaymentReceipt(int totalQty, int totalPrice, List<Menu> menus) throws IOException;

    void readPaymentReceiptFromFile();
}
