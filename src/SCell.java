// Add your documentation below:

public class SCell implements Cell {
    private String line;
    private int type;  // להעתיק מUTILS כל סוג מה המספר


    public SCell(String s) {
        if (isText(s)) {
            setType(1);
            setData(s);
        }
        if (isNumber(s)) {
            setType(2); // מה הצבע
            setData(s); // מה יוצא בסוף
        }
        if (isForm(s)) {
            setType(3); // מה הצבע
            setData(computeForm(s));
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

    public static boolean isNumber(String a) {
        try {
            Double.parseDouble(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isText(String a) {
        if (!isNumber(a) || !isForm(a)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isForm(String a) { //

        if (a == null || a.isEmpty()) {
            return false;
        }

        //?? cells
        if (a.matches("[A-Z][1-9][0-9]*")) {
            return true;
        }

        if (a.startsWith("=")) {
            a = a.substring(1);

            a = a.replaceAll(" ", "");

            // Correctness of "(" ")"
            int openCount = 0;
            for (int i = 0; i < a.length(); i++) {
                char current = a.charAt(i);

                if (current == '(') {
                    openCount++;
                    if (i < a.length() - 1) { // legal chars after "("
                        char next = a.charAt(i + 1);
                        if (!Character.isDigit(next) && next != '(' && next != '-') {
                            return false;
                        }
                    }
                } else if (current == ')') {
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

            // legal chars in the formula
            if (!a.matches("[\\d()+\\-*/\\s.]*")) {
                return false;
            }

            //if the next or before the Op illegal
            for (int i = 0; i < a.length(); i++) {
                char current = a.charAt(i); // תו נוכחי בלולאה
                if (current == '*' || current == '/' || current == '+' || current == '-') {
                    char before = i > 0 ? a.charAt(i - 1) : '\0'; // תו לפני האופרטור או מספר או (
                    char after = i < a.length() - 1 ? a.charAt(i + 1) : '\0'; // מספר או ) תו אחרי האופרטור

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

    public static double computeForm(String s) {
        if (isForm(s)) {
            s = s.substring(1);
            //step 1 - if contains "( )" check what inside.
            if (s.contains("(")) {

                int start = s.lastIndexOf("("); // the last "(" = start
                int end = s.indexOf(")", start); // the ")" in correlation to start

                double middle = computeForm(s.substring(start + 1, end)); //the value of the inside of start & end
                s = s.substring(0, start) + middle + s.substring(end + 1); // the new s with the middle
            }
        }


        //step 2 - check what kind of Op the inside contain
        if (s.contains("*") || s.contains("/")) {
            return calculateOperation(s, "*/");
        }

        if (s.contains("+") || s.contains("-")) {
            return calculateOperation(s, "+-");
        }


        return Double.parseDouble(s.trim()); //if left just a num- return num.
    }

    //step 3- if s contains Op- do some act
    public static double calculateOperation(String s, String operators) { //if s contains Op- do some act.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (operators.indexOf(c) != -1) {
                double left = computeForm(s.substring(0, i)); //
                double right = computeForm(s.substring(i + 1)); //

                switch (c) {                //actions for each Op
                    case '*':
                        return left * right;
                    case '/':
                        return left / right;
                    case '+':
                        return left + right;
                    case '-':
                        return left - right;
                }
            }
        }
        throw new IllegalArgumentException("Unexpected error in formula computation.");
    }
}



