// Add your documentation below:

import java.util.Date;

public class SCell implements Cell {
    private String line;
    private int type;
    // Add your code here

    public SCell(String s) {
        // Add your code here
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
        /////////////////////
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

    //main
    public static char[] numOp() {
        return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '/'};
    }

    public boolean isNumber(String a) {
        try {
            Double.parseDouble(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isText(String a) {
        if (!isNumber(a) || !isForm(a)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isForm(String a) {
        // 1. בדיקה אם המחרוזת ריקה או null
        if (a == null || a.isEmpty()) {
            return false;
        }

        // 2. בדיקה אם המחרוזת היא שם תא חוקי
        if (a.matches("[A-Z][1-9][0-9]*")) {
            return true;
        }

        // 3. בדיקה אם המחרוזת מתחילה ב-"="
        if (a.startsWith("=")) {
            String formula = a.substring(1);

            // 4. בדיקת סוגריים תקינים
            int openCount = 0;
            for (char c : formula.toCharArray()) {
                if (c == '(') {
                    openCount++;
                } else if (c == ')') {
                    openCount--;
                    if (openCount < 0) {
                        return false; // סוגר סוגר לפני פתיחה
                    }
                }
            }
            if (openCount != 0) {
                return false; // סוגריים לא מאוזנים
            }

            // 5. בדיקת תקינות הביטוי הכללי
            return formula.matches("[A-Z][1-9][0-9]*|\\d+(\\.\\d+)?|[()+\\-*/]+");
        }


        return false; // לא עומד בתנאים

    }
    public double computeForm (String s) {
        // שלב ראשון: אם הנוסחה מכילה סוגריים, חישוב מה שבפנים
        if (s.contains("(")) {
            int start = s.lastIndexOf("("); // סוגריים פתוחים הכי פנימיים
            int end = s.indexOf(")", start); // סוגריים סגורים שלהם

            // חישוב החלק שבתוך הסוגריים והחלפתו
            double middle = computeForm(s.substring(start + 1, end));
            return computeForm(s.substring(0, start) + middle + s.substring(end + 1));
        }

        // שלב שני: פירוק לפי סדר פעולות חשבון - קודם כפל וחילוק
        if (s.contains("*") || s.contains("/")) {
            return calculateOperation(s, "*/");
        }

        // שלב שלישי: טיפול בחיבור וחיסור
        if (s.contains("+") || s.contains("-")) {
            return calculateOperation(s, "+-");
        }

        // אם נשאר רק מספר, מחזירים אותו
        return Double.parseDouble(s.trim());
    }

    // פונקציה שמבצעת חישוב על פי אופרטורים
    private double calculateOperation(String s, String operators) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (operators.indexOf(c) != -1) { // אם התו הוא אופרטור מתוך הרשימה
                double left = computeForm(s.substring(0, i)); // חישוב החלק השמאלי
                double right = computeForm(s.substring(i + 1)); // חישוב החלק הימני

                // ביצוע הפעולה בהתאם לאופרטור
                switch (c) {
                    case '*': return left * right;
                    case '/': return left / right;
                    case '+': return left + right;
                    case '-': return left - right;
                }
            }
        }
        throw new IllegalArgumentException("Unexpected error in formula computation.");
    }

}



