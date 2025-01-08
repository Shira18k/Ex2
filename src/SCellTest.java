import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SCellTest {

    @Test
    void isNumbertrue() {
        String[] good = {"1.0", "23.98", "99.0", "-92.5", "-1.44"};
        for (int i = 0; i < good.length; i = i + 1) {
            boolean ok = SCell.isNumber(good[i]);
            assertTrue(ok);
        }
    }

    @Test
    void isNumberfalse() {
        String[] bad = {"23.-98", "aa",};
        for (int i = 0; i < bad.length; i = i + 1) {
            boolean notOk = SCell.isNumber(bad[i]);
            assertFalse(notOk);
        }
    }

    @Test
    void isTextTrue() {
        String[] good = {"@hjcbjhbcc", "abba", "aa!",};
        for (int i = 0; i < good.length; i = i + 1) {
            boolean ok = SCell.isText(good[i]);
            assertTrue(ok);
        }
    }

    @Test
    void isTextFalse() {
        String[] bad = {"23.-98", "(4+4)", "(3+"};
        for (int i = 0; i < bad.length; i = i + 1) {
            boolean notOk = SCell.isText(bad[i]);
            assertFalse(notOk);
        }
    }

    @Test
    void isFormTrue() {
        String[] good = {"=( (4+5) * (3+2 * (27/2)))", "=(-8)" , "=(3+4) + (-5)"}; // נוסחה תקינה
        for (int i = 0; i < good.length; i++) {
            boolean ok = SCell.isForm(good[i]);
            assertTrue(ok);
        }
    }

    @Test
    void isFormFalse() {
        String[] bad = {"=(((4+5) * (3+2 * (27/2)))", "=((8)", "=(-+)"}; // נוסחה תקינה
        for (int i = 0; i < bad.length; i++) {
            boolean notOk = SCell.isForm(bad[i]);
            assertFalse(notOk);
        }
    }

    @Test
    void computFormTest() {
        String formula = "=(8-9)"; // נוסחה מורכבת עם סוגריים וכמה סוגי אופרטורים
        double result = SCell.computeForm(formula);
        System.out.println("Result: " + result); // מצפה לתוצאה מדויקת לפי פעולות חשבון
    }
}

