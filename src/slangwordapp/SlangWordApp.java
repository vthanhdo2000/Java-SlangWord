/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangwordapp;

import static java.lang.System.exit;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class SlangWordApp {

    /**
     * @param args the command line arguments
     */
    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        String[] options = {"1- Search by slang word",
                            "2- Search by definition",
                            "3- Display history, list of searched slang words",
                            "4- Add a new slang words",
                            "5- Edit a slang words",
                            "6- Delete a slang words. Confirm before deleting",
                            "7- Reset the original slang words list",
                            "8- Random 1 slang word (On this day slang word)",
                            "9- Quiz game, the program displays 1 random slang word, with 4 answers for user selects.",
                            "10- Quiz game, the program displays 1 definition, with 4 slang words the answer for the user to choose",
                            "11- Exit",
        };
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option!=11){
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option){
                    case 1: option1(); break;
                    case 2: option2(); break;
                    case 3: option3(); break;
                    case 4: option4(); break;
                    case 5: option5(); break;
                    case 6: option6(); break;
                    case 7: option7(); break;
                    case 8: option8(); break;
                    case 9: option9(); break;
                    case 10: option10(); break;
                    case 11: exit(0);
                }
            }
            catch (Exception ex){
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
        }
    }
    
    private static void option1() {
        System.out.println("Thanks for choosing option 1");
    }
    private static void option2() {
        System.out.println("Thanks for choosing option 2");
    }
    private static void option3() {
        System.out.println("Thanks for choosing option 3");
    }
    private static void option4() {
        System.out.println("Thanks for choosing option 4");
    }
    private static void option5() {
        System.out.println("Thanks for choosing option 5");
    }
    private static void option6() {
        System.out.println("Thanks for choosing option 6");
    }
    private static void option7() {
        System.out.println("Thanks for choosing option 7");
    }
    private static void option8() {
        System.out.println("Thanks for choosing option 8");
    }
    private static void option9() {
        System.out.println("Thanks for choosing option 9");
    }
    private static void option10() {
        System.out.println("Thanks for choosing option 10");
    }
    
}
