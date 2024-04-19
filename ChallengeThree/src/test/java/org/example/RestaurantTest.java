package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.configuration.injection.scanner.MockScanner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RestaurantTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Scanner mockScanner;

    private List<Menu> menus;

    private Restaurant restaurant;

    private final ReceiptGenerator mockReceiptGenerator = mock(ReceiptGenerator.class);



    @BeforeEach
    public void setUpStreams() {
        mockScanner = mock(Scanner.class);
        menus = new ArrayList<>();
        menus.add(new FoodItem("Nasi goreng", 20000, false));
        menus.add(new FoodItem("Mie goreng", 15000, false));
        menus.add(new FoodItem("Bubur ayam", 10000, false));

        restaurant = new Restaurant(mockReceiptGenerator, menus, mockScanner);

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testShowOrderMenu() {


        when(mockScanner.hasNextInt())
                .thenReturn(true); // First call to hasNextInt() returns true
        when(mockScanner.nextInt()).thenReturn(0);

        restaurant.showOrderMenu();

        assertTrue(outContent.toString().contains("Selamat datang di BinarFud"));
        assertTrue(outContent.toString().contains("=========================="));
        assertTrue(outContent.toString().contains("\n")); // New line character for formatting

        assertTrue(outContent.toString().contains("Silahkan pilih makanan : "));
        assertTrue(outContent.toString().contains("1. Nasi goreng\t\t20000"));
        assertTrue(outContent.toString().contains("2. Mie goreng\t\t15000"));
        assertTrue(outContent.toString().contains("3. Bubur ayam\t\t10000"));
        assertTrue(outContent.toString().contains("99. Pesan dan bayar"));
        assertTrue(outContent.toString().contains("0. Keluar aplikasi"));

        assertTrue(outContent.toString().contains("=> "));
    }

    @Test
    public void testShowOrderMenuWhenUserInputNonInteger() {
        when(mockScanner.hasNextInt())
                .thenReturn(false)
                .thenReturn(true);
        when(mockScanner.next()).thenReturn("abc");
        when(mockScanner.nextInt()).thenReturn(0);

        restaurant.showOrderMenu();
        verify(mockScanner).next();
        verify(mockScanner, times(2)).hasNextInt();
        assertTrue(outContent.toString().contains("Masukkan input yang benar"));
    }

    @Test
    public void testShowOrderMenuWhenUserInputDontMatchWithMenu() {
        when(mockScanner.hasNextInt())
                .thenReturn(true);
        when(mockScanner.nextInt())
                .thenReturn(menus.size()+1)
                .thenReturn(-1)
                .thenReturn(0);

        restaurant.showOrderMenu();

        verify(mockScanner, times(3)).hasNextInt();
        assertTrue(outContent.toString().contains("Silahkan pilih menu 1 - " + menus.size()));
    }

    @Test
    public void testShowOrderWhenUserEnterValidInput() {
        int selectedMenu = 1;
        when(mockScanner.hasNextInt())
                .thenReturn(true);
        when(mockScanner.nextInt())
                .thenReturn(selectedMenu)
                .thenReturn(0)
                .thenReturn(0);

        restaurant.showOrderMenu();
        verify(mockScanner, times(3)).hasNextInt();
        assertTrue(outContent.toString().contains("=========================="));
        assertTrue(outContent.toString().contains("Berapa pesanan anda"));
        assertTrue(outContent.toString().contains("==========================\n"));
        assertTrue(outContent.toString().contains(menus.get(selectedMenu-1).getFood() + "\t| " + menus.get(selectedMenu-1).getPrice()));
    }

//    @Test
//    public void testOrderQuantityWhenUserInputNonInteger() {
//        when(mockScanner.hasNextInt())
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true);
//        when(mockScanner.next()).thenReturn("abc");
//        when(mockScanner.nextInt())
//                .thenReturn(1)
//                .thenReturn(0);
//
//        restaurant.showOrderMenu();
//        verify(mockScanner).next();
//        verify(mockScanner, times(3)).hasNextInt();
//        assertTrue(outContent.toString().contains("Masukkan input yang benar"));
//    }
}

