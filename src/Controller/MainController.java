package Controller;

import Commons.FunFileCSV;
import Moderm.Vocabulary;

import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    private static ArrayList<Vocabulary> listVocabulary = new ArrayList<Vocabulary>();
    private static Scanner input = new Scanner(System.in);
    public static void displayMainMenu(){
        System.out.println("\n1. Add New Vocabulary." +
                "\n2. Show Information Vocabulary." +
                "\n3. Search Vocabulary." +
                "\n4. Delete Vocabulary." +
                "\n5. Edit Vocabulary." +
                "\n6. Exit." +
                "\nPlease select one function below: ");
        String choose = input.nextLine();
        switch (choose){
            case "1":
                addNewVocabulary();
                break;
            case "2":
                showInformationVocabulary();
                break;
            case "3":
                searchVocabulary();
                break;
            case "4":
                deleteVocabulary();
                break;
            case "5":
                editVocabulary();
                break;
            case "6":
                System.exit(0);
                break;
            default:
                System.out.println("\nFail! Please choose again! Enter to continue...");
                input.nextLine();
                displayMainMenu();
                break;
        }
    }

    private static void addNewVocabulary() {
        listVocabulary = FunFileCSV.readFileCSVToListVocabulary();
        Vocabulary vocabulary = new Vocabulary();

        System.out.println("Enter word: ");
        vocabulary.setWord(input.nextLine());

        System.out.println("Enter mean: ");
        vocabulary.setMean(input.nextLine());
        listVocabulary.add(vocabulary);
        FunFileCSV.writeVocabularyToFileCSV(listVocabulary);

        System.out.println("Add new vocabulary completed! Enter to continue...");
        input.nextLine();
        displayMainMenu();
    }

    private static void showInformationVocabulary() {
        getAllInfomationVocabulary();

        System.out.println("Enter to continue...");
        input.nextLine();
        displayMainMenu();
    }

    private static void getAllInfomationVocabulary(){
        listVocabulary = FunFileCSV.readFileCSVToListVocabulary();
        for (Vocabulary vocabulary: listVocabulary){
            System.out.println("-------------------");
            System.out.println("Word: " + vocabulary.getWord());
            System.out.println("Mean: " + vocabulary.getMean());
            System.out.println("-------------------");
        }
    }

    private static void searchVocabulary(){
        getAllInfomationVocabulary();
        System.out.println("Please enter word you want search: ");
        String chooseWordSearch = input.nextLine();
        for (int i = 0; i < listVocabulary.size(); i++) {
            if (listVocabulary.get(i).getWord().equals(chooseWordSearch)) {
                System.out.println("Word: " + listVocabulary.get(i).getWord());
                System.out.println("Mean: " + listVocabulary.get(i).getMean());
            }
        }
        System.out.println("Enter to continue...");
        input.nextLine();
        displayMainMenu();
    }

    private static void deleteVocabulary(){
        getAllInfomationVocabulary();
        System.out.println("Please enter word you want delete: ");
        String chooseWordDelete = input.nextLine();
        for (int i = 0; i < listVocabulary.size(); i++){
            if (listVocabulary.get(i).getWord().equals(chooseWordDelete)){
                listVocabulary.remove(i);
                FunFileCSV.writeVocabularyToFileCSV(listVocabulary);
                System.out.println("Delete completed!!!");
                break;
            }
        }
        System.out.println("Enter to continue...");
        input.nextLine();
        displayMainMenu();
    }
    private static void editVocabulary(){
        getAllInfomationVocabulary();
        System.out.println("Please enter word you want edit information: ");
        String chooseWordEdit = input.nextLine();
        for (int i = 0; i < listVocabulary.size(); i++){
            if (listVocabulary.get(i).getWord().equals(chooseWordEdit)){
                System.out.println("1." + listVocabulary.get(i).getWord());
                System.out.println("2." + listVocabulary.get(i).getMean());
                System.out.println("Please choose property of vocabulary you want edit: ");
                String chooseProperty = input.nextLine();
                System.out.println("Please enter data you want change");
                String valueProperty = input.nextLine();
                switch (chooseProperty){
                    case "1":
                        listVocabulary.get(i).setWord(valueProperty);
                        break;
                    case "2":
                        listVocabulary.get(i).setMean(valueProperty);
                        break;
                }
                FunFileCSV.writeVocabularyToFileCSV(listVocabulary);
                System.out.println("Edit Completed!!!");
                System.out.println("Enter to continue...");
                input.nextLine();
                displayMainMenu();
            }
        }
    }
}
