package by.sem3.lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class QueryProcessing {
    public void process(Companies companies) throws SQLException, FileNotFoundException {
        Scanner sc = new Scanner(new File("resources/SQLRequests.txt"));
        int counter = 0;
        while (sc.hasNext()) {
            String str = sc.nextLine();
            processQuery(str, companies, counter);
            counter++;
        }
        sc.close();
    }

    private void processQuery(String query, Companies companies, int counter) throws SQLException, FileNotFoundException {
        ++counter;
        PrintStream XMLps = new PrintStream("resources/SQLRequests/XML/request" + counter + ".xml");
        PrintStream JSONps = new PrintStream("resources/SQLRequests/JSON/request" + counter + ".json");
        Check.checkQuery(query);
        Pattern wherePattern = Pattern.compile("where", Pattern.CASE_INSENSITIVE);
        Matcher whereMatcher = wherePattern.matcher(query);
        Pattern betweenPattern = Pattern.compile("between", Pattern.CASE_INSENSITIVE);
        Matcher betweenMatcher = betweenPattern.matcher(query);
        if (!getDataBase(query).equalsIgnoreCase("dataBase")) {
            throw new SQLException("Data base not found!");
        }
        List<String> fieldsList = getFields(query);
        if (!whereMatcher.find()) {
            XMLps.print(companies.toXMLForQuery(fieldsList));
            JSONps.print(companies.toJSONForQuery(fieldsList));
        } else {
            if (betweenMatcher.find()) {
                processWithBetween(query, XMLps, JSONps, companies, fieldsList);
            }
            if (query.contains("=")) {
                processWithStringValues(query, XMLps, JSONps, companies, fieldsList);
            }
        }
        XMLps.close();
        JSONps.close();
    }

    private List<String> getFields(String query) {
        List<String> fields = new ArrayList<>();
        Pattern selectPattern = Pattern.compile("select", Pattern.CASE_INSENSITIVE);
        Pattern fromPattern = Pattern.compile("from", Pattern.CASE_INSENSITIVE);
        Matcher selectMatcher = selectPattern.matcher(query);
        Matcher fromMatcher = fromPattern.matcher(query);
        selectMatcher.find();
        fromMatcher.find();
        String field = query.substring(selectMatcher.end() + 1, fromMatcher.start() - 1);
        Scanner queryScanner = (new Scanner(field)).useDelimiter(",");
        while (queryScanner.hasNext()) {
            fields.add(queryScanner.next().trim().toLowerCase());
        }
        return fields;
    }

    private String getDataBase(String query) throws SQLException {
        Pattern fromPattern = Pattern.compile("from", Pattern.CASE_INSENSITIVE);
        Matcher fromMatcher = fromPattern.matcher(query);
        fromMatcher.find();
        Pattern dataBasePattern = Pattern.compile("from[ ]+\\w+[ ]*", Pattern.CASE_INSENSITIVE);
        Matcher dataBaseMatcher = dataBasePattern.matcher(query);
        dataBaseMatcher.find();
        return query.substring(fromMatcher.end() + 1, dataBaseMatcher.end()).trim();
    }

    private int getLowerBound(String query) {
        Pattern betweenPattern = Pattern.compile("between", Pattern.CASE_INSENSITIVE);
        Matcher betweenMatcher = betweenPattern.matcher(query);
        betweenMatcher.find();
        Pattern lowerBoundPattern = Pattern.compile("between[ ]+\\d+[ ]+", Pattern.CASE_INSENSITIVE);
        Matcher lowerBoundMatcher = lowerBoundPattern.matcher(query);
        lowerBoundMatcher.find();
        return Integer.parseInt(query.substring(betweenMatcher.end() + 1, lowerBoundMatcher.end()).trim());
    }

    private int getUpperBound(String query) {
        Pattern andPattern = Pattern.compile("and", Pattern.CASE_INSENSITIVE);
        Matcher andMatcher = andPattern.matcher(query);
        andMatcher.find();
        Pattern upperBoundPattern = Pattern.compile("and[ ]+\\d+[ ]*", Pattern.CASE_INSENSITIVE);
        Matcher upperBoundMatcher = upperBoundPattern.matcher(query);
        upperBoundMatcher.find();
        return Integer.parseInt(query.substring(andMatcher.end() + 1, upperBoundMatcher.end()).trim());
    }

    private String getSearchingField(String query) {
        Pattern wherePattern = Pattern.compile("where", Pattern.CASE_INSENSITIVE);
        Matcher whereMatcher = wherePattern.matcher(query);
        whereMatcher.find();
        Pattern fieldPattern = Pattern.compile("where[ ]+\\w+[ ]*", Pattern.CASE_INSENSITIVE);
        Matcher fieldMatcher = fieldPattern.matcher(query);
        fieldMatcher.find();
        return query.substring(whereMatcher.end() + 1, fieldMatcher.end()).trim().toLowerCase();
    }

    private String getStringInQuotes(String query) {
        return query.substring(query.indexOf('\'') + 1, query.lastIndexOf('\'')).trim().toLowerCase();
    }

    private void processWithBetween(String query, PrintStream XMLps, PrintStream JSONps, Companies companies,
                                    List<String> fieldsList) throws SQLException {
        String searchingField = getSearchingField(query);
        int lowerBound = getLowerBound(query);
        int upperBound = getUpperBound(query);
        XMLps.print(companies.toXMLForQueryFromBounds(fieldsList, searchingField, lowerBound, upperBound));
        JSONps.print(companies.toJSONForQueryFromBounds(fieldsList, searchingField, lowerBound, upperBound));
    }

    private void processWithStringValues(String query, PrintStream XMLps, PrintStream JSONps, Companies companies,
                                         List<String> fieldsList) throws SQLException {
        String searchingField = getSearchingField(query);
        String variable = getStringInQuotes(query);
        XMLps.print(companies.toXMLForQueryFromQuotes(fieldsList, searchingField, variable));
        JSONps.print(companies.toJSONForQueryFromQuotes(fieldsList, searchingField, variable));
    }
}
