package challenge;

import java.io.IOException;
import java.util.ArrayList;

public interface ReceiptGenerator {
    void generatePaymentReceipt(int totalQty, int totalPrice, ArrayList<Menu> menus) throws IOException;
    void readPaymentReceiptFromFile();
}
