package challenge;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PaymentReceiptGenerator receiptGenerator = new PaymentReceiptGenerator(new File("ChallengeTwo/payment_receipt.txt"));
        Restaurant restaurant = new Restaurant(receiptGenerator, sc);
        restaurant.showOrderMenu();
        sc.close();
    }
}