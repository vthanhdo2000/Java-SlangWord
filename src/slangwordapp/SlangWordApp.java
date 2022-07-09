/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangwordapp;

import static java.lang.System.exit;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class SlangWordApp {

    /**
     * @param args the command line arguments
     */
    private static Service service;

    //private Service service;
    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }

    public static void main(String[] args) {

        Service.getInstance();

        // TODO code application logic here
        String[] options = {
            "1- Search by slang word",
            "2- Search by definition",
            "3- Display history, list of searched slang words",
            "4- Add a new slang words",
            "5- Edit a slang words",
            "6- Delete a slang words. Confirm before deleting",
            "7- Reset the original slang words list",
            "8- Random 1 slang word (On this day slang word)",
            "9- Quiz game, the program displays 1 random slang word, with 4 answers for user selects.",
            "10- Quiz game, the program displays 1 definition, with 4 slang words the answer for the user to choose",
            "11- Exit",};
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option != 11) {
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        option1();
                        break;
                    case 2:
                        option2();
                        break;
                    case 3:
                        option3();
                        break;
                    case 4:
                        option4();
                        break;
                    case 5:
                        option5();
                        break;
                    case 6:
                        option6();
                        break;
                    case 7:
                        option7();
                        break;
                    case 8:
                        option8();
                        break;
                    case 9:
                        option9();
                        break;
                    case 10:
                        option10();
                        break;
                    case 11:
                        exit(0);
                }
            } catch (Exception ex) {
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
        }
    }

    public static void option1() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter key slang word: ");

        String[][] result = null;
        String slangString = myObj.nextLine();

        result = Service.searchSlangWord(slangString);
        if (result != null) {
            for (int i = 0; i < result.length; i++) {
                System.out.println(Arrays.toString(result[i]));
            }
        } else {
            System.out.println("Not found slang!");
        }

    }

    private static void option2() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter key definition: ");

        String[][] result = null;
        String slangString = myObj.nextLine();

        result = Service.searchDefinition(slangString);
        if (result != null) {
            for (int i = 0; i < result.length; i++) {
                System.out.println(Arrays.toString(result[i]));
            }
        } else {
            System.out.println("Not found!");
        }
    }

    private static void option3() {
        String[][] result = null;
        result = Service.readHistory();
        if (result != null) {
            for (int i = 0; i < result.length; i++) {
                System.out.println(Arrays.toString(result[i]));
            }
        } else {
            System.out.println("Not found!");
        }
        
    }

    private static void option4() {
       Scanner myObj = new Scanner(System.in);
       System.out.println("Input key: ");
       String slag = myObj.nextLine();
       System.out.println("Input mean: ");
       String meaning = myObj.nextLine();
       Service.addSlangWord(slag, meaning);
    }

    private static void option5() {
       Service.updateSlangWords();
    }

    private static void option6() {
        Service.deleteSlangWord();
    }

    private static void option7() {
        try {
           Service.randomSlangWords();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void option8() {
        try {
           Service.randomSlangWords();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void option9() {
        System.out.println("Thanks for choosing option 9");
    }

    private static void option10() {
        System.out.println("Thanks for choosing option 10");
    }

}
