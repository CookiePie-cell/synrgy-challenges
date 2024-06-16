package challenge;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReceiptGenerator receiptGenerator = new PaymentReceiptGenerator(new File("ChallengeThree/payment_receipt.txt"));
        Restaurant restaurant = new Restaurant(receiptGenerator, sc);
        restaurant.showOrderMenu();
        sc.close();
    }
}