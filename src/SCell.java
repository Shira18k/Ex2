/**SCell is the class that representing the most of Ex2's part one, what right**/
import javax.management.StringValueExp;
import java.util.Stack;

public class SCell implements Cell {
    private String line;
    private int type;
    private Ex2Sheet sheet; //because the functions not static


    public SCell(String s, Ex2Sheet sheet) { // shows in the table
        this.sheet = sheet;
        if (isText(s)) {
            setType(1);
            setData(s);
        }
        if (isNumber(s)) {
            setType(2);
            setData(s);
        }
        if (isForm(s)) {
            setType(3);
            setData(computeForm(s) + "");
        }
        setData(s);
    }

    @Override
    public int getOrder() {
        // Add your code here
        return 0;
        // ///////////////////
    }

    //@Override
    @Override
    public String toString() {
        return getData();
    }

    @Override
    public void setData(String s) {
        // Add your code here
        line = s;

    }

    @Override
    public String getData() {
        return line;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int t) {
        type = t;
    }

    @Override
    public void setOrder(int t) {
        // Add your code here

    }

public static String replacePattern(String input) { // Replace cell by num

    return input.replaceAll("(?i)[a-z][0-9]{1,2}\\b", "0");
}

    public boolean isNumber(String a) { // if is a num
        try {
            Double.parseDouble(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isText(String a) { //if is a text
        if (isNumber(a) || isForm(a)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isForm(String a) { // right formula (include cells)

        if (a == null || a.isEmpty()) {
            return false;
        }
        if (replacePattern(a) != null) {
            a = replacePattern(a);
        }

        int equalsCount = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '=') {
                equalsCount++;
                if (equalsCount != 1) {
                    return false;
                }
            }
        }
        if (a.startsWith("=")) {
            a = a.substring(1);

            a = a.replaceAll(" ", "");

            // Correctness of "(" ")"
            if (a.contains("(") || a.contains(")")) {
                int openCount = 0;
                for (int i = 0; i < a.length(); i++) {
                    char A = a.charAt(i);

                    if (A == '(') {
                        openCount++;
                        if (i < a.length() - 1) { // legal chars after "("
                            char next = a.charAt(i + 1);
                            if (!Character.isDigit(next) && next != '(' && next != '-') {
                                return false;
                            }
                        }
                    } else if (A == ')') {
                        openCount--;
                        if (openCount < 0) {
                            return false;
                        }
                        if (i > 0) { // legal chars Before ")"
                            char B = a.charAt(i - 1);
                            if (!Character.isDigit(B) && B != ')') {
                                return false;
                            }
                        }
                    }
                }
                if (openCount != 0) {
                    return false;
                }
            }

            if (!a.matches("[\\d()+\\-*/\\s.]*")) {
                return false;
            }

            for (int i = 0; i < a.length(); i++) {
                char current = a.charAt(i);
                if (current == '*' || current == '/' || current == '+' || current == '-') {
                    char before = i > 0 ? a.charAt(i - 1) : '\0';
                    char after = i < a.length() - 1 ? a.charAt(i + 1) : '\0';

                    // for case like (-x)
                    if (current == '-') {
                        if (i == 0 || before == '(') {
                            if (!Character.isDigit(after) && after != '(') {
                                return false;
                            }
                            continue;
                        }
                    }


                    if (!Character.isDigit(before) && before != ')') {
                        return false;
                    }
                    if (!Character.isDigit(after) && after != '(') {
                        return false;
                    }
                }
            }

            return true;
        }

        return false;
    }

    public double computeForm(String formula) {

        if (formula == null || formula.isEmpty()) {
            System.out.println("Error");
            return -1;
        }
        if (formula.charAt(0) == '=') {
            formula = formula.substring(1);
        }

        formula = formula.replaceAll(" ", "");
        String result = "";

        if (formula.contains("(")) {
            int start = formula.indexOf("(") + 1;
            int end = findBracket(formula, start - 1);

            String insideBrackets = formula.substring(start, end);
            String beforeBrackets = formula.substring(0, start - 1);
            String afterBrackets = formula.substring(end + 1);

            result = beforeBrackets + computeForm(insideBrackets) + afterBrackets;
            formula = result;
        }

        if (!containsOperator(formula)) { // is a cell
            if (Character.isLetter(formula.charAt(0))) {
                CellEntry cell = new CellEntry(formula);
                int x = cell.getX();
                int y = cell.getY();
                return this.computeForm(this.sheet.get(x, y).getData()); //compute value of cell
            }
            return Double.parseDouble(formula);
        }

        if (formula.contains("+")) {
            int index = formula.indexOf('+');
            return computeForm(formula.substring(0, index)) + computeForm(formula.substring(index + 1));
        }

        if (formula.contains("-")) {
            if (formula.charAt(0)=='-'&& isNumber(formula)){
                return Double.parseDouble(formula);// לבדוק אם זה עובד
            }
            int index = formula.indexOf('-');
            return computeForm(formula.substring(0, index)) - computeForm(formula.substring(index + 1));
        }

        if (formula.contains("*")) {
            int index = formula.indexOf('*');
            return computeForm(formula.substring(0, index)) * computeForm(formula.substring(index + 1));
        }

        if (formula.contains("/")) {
            int index = formula.indexOf('/');
            return computeForm(formula.substring(0, index)) / computeForm(formula.substring(index + 1));
        }

        return Double.parseDouble(result);
    }

    public static boolean containsOperator(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '*' || c == '+' || c == '/' || c == '-') {
                return true;
            }
        }
        return false;
    }
    private int findBracket(String str, int openIndex) {
        int openCount = 1;
        for (int i = openIndex + 1; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                openCount++;
            } else if (str.charAt(i) == ')') {
                openCount--;
                if (openCount == 0) {
                    return i;
                }
            }
        }
        throw new IllegalArgumentException("No matching closing bracket found.");
    }
}