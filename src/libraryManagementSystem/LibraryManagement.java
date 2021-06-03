package libraryManagementSystem;

import libraryManagementSystem.service.LibraryManagementService;

import java.util.Scanner;

public class LibraryManagement {

    LibraryManagementService libraryManagementService;
    public static void main(String[] args) {
        LibraryManagement libraryManagement = new LibraryManagement();
        libraryManagement.libraryManagementService = new LibraryManagementService();
        libraryManagement.startStimulation();
    }

    void startStimulation() {
        Scanner in = new Scanner(System.in);
        String[] input;
        while (true) {
            input = in.nextLine().split(" ");
            if (input[0].equals("exit")) {
                break;
            } else if (input[0].equals("create_library")) {
                libraryManagementService.createLibrary(Integer.parseInt(input[1]));
                System.out.println("Created library with " + input[1] + " racks");
            } else if (input[0].equals("add_book")) {
                String addStatus = libraryManagementService.addBookEntries(input);
                System.out.println(addStatus);
            } else if (input[0].equals("search")) {
                if (input[1].equals("book_id")) {
                    String searchStatus = libraryManagementService.searchBookId(Integer.parseInt(input[2]));
                    System.out.println(searchStatus);
                }
            } else if (input[0].equals("remove_book_copy")) {
                String removeBookStatus = libraryManagementService.removeBookCopy(input[1]);
                System.out.println(removeBookStatus);
            }
        }
    }
}
