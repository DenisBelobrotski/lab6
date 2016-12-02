package by.sem3.lab6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

class ProcessLogger {
    private FileWriter logWriter;
    private Run run;
    private Scanner consoleScanner;

    public ProcessLogger() throws IOException {
        logWriter = new FileWriter("resources/logfile.txt", true);
        run = new Run();
        consoleScanner = new Scanner(System.in);
    }

    public void runProcess() {
        try {
            String inputFilePath = "resources/input.csv";
            logWriter.write("\n" + new Date(System.currentTimeMillis()) + "\n");
            logWriter.write("Input file: " + inputFilePath + "\n");
            run.fillCompanies(inputFilePath);
            logWriter.write("Output files: \"" + run.outputJSON() + "\", \"" + run.outputXML() + "\"\n");
            userMainMenu();
            logWriter.write("Writing file: \"requests.txt\"\n");
            logWriter.write("\tSuccess!\n");
            logWriter.close();
        } catch (Exception e) {
            StackTraceElement[] elements = e.getStackTrace();
            try {
                logWriter.write(e.getMessage() + "\n");
                for (StackTraceElement item : elements) {
                    logWriter.write("\t" + item.toString() + "\n");
                }
                logWriter.close();
                System.out.println("\nRuntime error! Check \"logfile.txt\" for details.\n");
            } catch (IOException ex) {
                System.out.println("Input/Output error!");
            }
        }
    }

    private void userMainMenu() throws InputException, SQLException, IOException {
        String menu = "1. Find by short title.\n2. Find by branch. \n3. Find by activity. \n4. Find by date of " +
                "foundation (fromDate, toDate)(excluding these dates)(Formats: \"dd.mm.yyyy\" or \"mm/dd/yyyy\"). " +
                "\n5. Find by count of employees (from,to).\n6. Read SQL requests(Please, write requests in " +
                "\"SQLRequests.txt\").\n7. Exit program.\n";
        System.out.println(menu);
        byte choice;
        do {
            System.out.println("Your choice: ");
            choice = consoleScanner.nextByte();
            switch (choice) {
                case 1: {
                    findShortTitle();
                    System.out.println(menu);
                    break;
                }
                case 2: {
                    findBranch();
                    System.out.println(menu);
                    break;
                }
                case 3: {
                    findActivity();
                    System.out.println(menu);
                    break;
                }
                case 4: {
                    findDateOfFoundation();
                    System.out.println(menu);
                    break;
                }
                case 5: {
                    findAmountOfEmployees();
                    System.out.println(menu);
                    break;
                }
                case 6: {
                    logWriter.write("Cleaning directory \"SQLRequests\"...\n");
                    clearSQLRequests();
                    logWriter.write("Cleaning complete!\n");
                    logWriter.write("Writing SQL requests...\n");
                    processQuery();
                    logWriter.write("Writing complete!\n");
                    System.out.println(menu);
                    break;
                }
                case 7: {
                    System.out.println("\nProgram has been already closed.");
                    break;
                }
                default: {
                    System.out.println("\nIncorrect choice. Try again.\n");
                    break;
                }
            }
        } while (choice != 7);
    }

    private void findShortTitle() {
        System.out.print("\nEnter short title of company:\n");
        run.printSearchResultOfShortTitle(consoleScanner.next());
        System.out.println("\nYou can find this company in \"requests.txt\"\n");
    }

    private void findBranch() {
        System.out.print("\nEnter branch of company:\n");
        run.printSearchResultOfBranch(consoleScanner.next());
        System.out.println("\nYou can find this companies in \"requests.txt\"\n");
    }

    private void findActivity() {
        System.out.print("\nEnter activity of company:\n");
        run.printSearchResultOfActivity(consoleScanner.next());
        System.out.println("\nYou can find this companies in \"requests.txt\"\n");
    }

    private void findDateOfFoundation() throws InputException {
        System.out.print("\nEnter date of foundation of company:\nfrom ");
        String fromDate = consoleScanner.next();
        System.out.print("to ");
        String toDate = consoleScanner.next();
        run.printSearchResultOfDateOfFoundation(Check.checkDate(fromDate, true), Check.checkDate(toDate, true));
        System.out.println("\nYou can find this company in \"requests.txt\"\n");
    }

    private void findAmountOfEmployees() throws InputException {
        System.out.print("\nEnter amount of employees of company:\nfrom ");
        int fromCount = consoleScanner.nextInt();
        System.out.print("to ");
        int toCount = consoleScanner.nextInt();
        run.printSearchResultOfCountOfEmployees(fromCount, toCount);
        System.out.println("\nYou can find this company in \"requests.txt\"\n");
    }

    private void processQuery() throws SQLException, IOException {
        logWriter.write("\"SQLRequests.txt\" processing...\n");
        run.processSQLRequests();
        System.out.println("\nSQL requests complete! You can find output files in \"Resources/SQLRequests\".\n");
        logWriter.write("SQL requests complete!\n");
    }

    private void clearDirectory(String path) throws SQLException{
        File file = new File(path);
        if(!file.exists())
            throw new SQLException("Directory \"" + path + "\" does not exist.");
        if(file.isDirectory())
        {
            for(File f : file.listFiles())
                f.delete();
        }
        else
        {
            throw new SQLException("\"" + path + "\" is not directory.");
        }
    }

    private void clearSQLRequests() throws SQLException {
        clearDirectory("Resources/SQLRequests/JSON");
        clearDirectory("Resources/SQLRequests/XML");
    }
}
