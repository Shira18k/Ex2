import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SCellTest {

    @Test
    void isNumbertrue() {
        String[] good = {"1.0", "23.98", "99.0", "-92.5", "-1.44"};
        SCell c = new SCell("",new Ex2Sheet());
        for (int i = 0; i < good.length; i = i + 1) {
            boolean ok = c.isNumber(good[i]);
            assertTrue(ok);
        }
    }

    @Test
    void isNumberfalse() {
        String[] bad = {"23.-98", "aa",};
        SCell c = new SCell("",new Ex2Sheet());
        for (int i = 0; i < bad.length; i = i + 1) {
            boolean notOk = c.isNumber(bad[i]);
            assertFalse(notOk);
        }
    }

    @Test
    void isTextTrue() {
        String[] good = {"@hjcbjhbcc", "abba", "aa!",};
        SCell c = new SCell("",new Ex2Sheet());
        for (int i = 0; i < good.length; i = i + 1) {
            boolean ok = c.isText(good[i]);
            assertTrue(ok);
        }
    }

    @Test
    void isTextFalse() {
        String[] bad = {"23.98", "=(4+4)", "-9"};
        SCell c = new SCell("",new Ex2Sheet());
        for (int i = 0; i < bad.length; i = i + 1) {
            boolean notOk = c.isText(bad[i]);
            assertFalse(notOk);
        }
    }

    @Test
    void isFormTrue() {
        String[] good = {"=(4+2*7)"}; // נוסחה תקינה
        SCell c = new SCell("",new Ex2Sheet());
        for (int i = 0; i < good.length; i++) {
            boolean ok = c.isForm(good[i]);
            assertTrue(ok);
        }
    }

    @Test
    void isFormFalse() {
        String[] bad = {"=(((4+5) * (3+2 * (27/2)))", "=((8)", "=(-+)"}; //"}; // נוסחה תקינה;
        SCell c = new SCell("",new Ex2Sheet());
        for (int i = 0; i < bad.length; i++) {
            boolean notOk = c.isForm(bad[i]);
            assertFalse(notOk);
        }
    }

    @Test
    void computFormTest() {
        SCell c = new SCell("",new Ex2Sheet());
        assertEquals(18, c.computeForm("=(4+7*2)"));
        assertEquals(14, c.computeForm("=(2*(9/3)+8)"));
        assertEquals(-1, c.computeForm("=(8-9)"));
    }
}




