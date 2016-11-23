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
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }
    }
}
