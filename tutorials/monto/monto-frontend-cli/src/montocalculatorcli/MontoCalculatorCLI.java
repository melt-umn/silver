/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montocalculatorcli;

import java.util.Scanner;

/**
 *
 * @author charlie
 */
public class MontoCalculatorCLI {
    private final Scanner scanner;
    private final MontoManager monto;
    
    public MontoCalculatorCLI() {
        scanner = new Scanner(System.in);
        monto = new MontoManager();
    }
    
    public void run() {
        System.out.print("Connecting to Monto...");
        monto.connect();
        System.out.println("Connected");
        // Scan for input from the user and output the product from Monto
        System.out.print("-> ");
        String message = scanner.nextLine();
        while(!"done".equals(message)) {
            String product = nextProduct(message);
            System.out.println(product);
            System.out.print("-> ");
            message = scanner.nextLine();
        }
        System.out.print("Closing connection to monto...");
        monto.disconnect();
        System.out.println("Connection closed");
    }
    
    private String nextProduct(String message) {
        monto.sendMessage(message);
        return monto.receiveProduct();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MontoCalculatorCLI calc = new MontoCalculatorCLI();
        calc.run();
    }
}
