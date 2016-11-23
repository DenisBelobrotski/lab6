package by.sem3.lab6;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Companies {
    private List<Company> list;

    public Companies() {
        list = new ArrayList<>();
    }

    public void add(Company company) {
        list.add(company);
    }

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Company item : list) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }

    public String toXMLString() {
        String head = getClassName(Companies.class.getName());
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(head).append(">\n");
        for (Company item : list) {
            sb.append(item.toXMLString()).append("\n");
        }
        sb.append("</").append(head).append(">");
        return sb.toString();
    }

    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        String head = getClassName(Companies.class.getName());
        sb.append("{\n\t\"").append(head).append("\": [\n");
        for (Company item : list) {
            sb.append(item.toJSONString()).append("\n");
        }
        sb.append("\n\t]\n");
        sb.append("}\n");
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    private String getClassName(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = name.length() - 1; name.charAt(i) != '.'; i--) {
            sb.append(name.charAt(i));
        }
        return sb.reverse().toString();
    }

    public List<Company> findShortTitle(String str, PrintStream ps) {
        List<Company> shortTitle = new ArrayList<>();
        for (Company item : list) {
            if (str.equalsIgnoreCase(item.getShortTitle())) {
                shortTitle.add(item);
            }
        }
        if (shortTitle.size() == 0) {
            ps.println("Companies with this short title aren't found!\n");
        }
        return shortTitle;
    }
}
