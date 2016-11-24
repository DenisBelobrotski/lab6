package by.sem3.lab6;

import java.io.FileNotFoundException;

class ProcessLogger {
    public void solve() {
        try {
            Run run = new Run();
            run.fillCompanies("resources/input.csv");
            run.outputXML();
            run.outputJSON();
            run.printSearchResultOfShortTitle("StiTlE3");
            run.printSearchResultOfShortTitle("sTitle1");
            run.printSearchResultOfActivity("ACTIVITY3");
            run.printSearchResultOfActivity("activity0");
            run.printSearchResultOfBranch("braNCH1");
            run.printSearchResultOfBranch("branch5");
            run.printSearchResultOfDateOfFoundation(Check.checkDate("01/01/2015", true), Check.checkDate("01/01/2016", true));
            run.printSearchResultOfDateOfFoundation(Check.checkDate("01/01/2000", true), Check.checkDate("01/01/2004", true));
            run.printSearchResultOfCountOfEmployees(100, 1200);
            run.printSearchResultOfCountOfEmployees(2, 10);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }
    }
}
