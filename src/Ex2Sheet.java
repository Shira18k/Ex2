import com.sun.source.tree.BreakTree;

import java.io.IOException;
// Add your documentation below:

public class Ex2Sheet implements Sheet {
    private Cell[][] table;
    // Add your code here

    // ///////////////////
    public Ex2Sheet(int x, int y) {
        table = new SCell[x][y];
        for (int i = 0; i < x; i = i + 1) {
            for (int j = 0; j < y; j = j + 1) {
                table[i][j] = new SCell("");
            }
        }
        eval();
    }

    public Ex2Sheet() {
        this(Ex2Utils.WIDTH, Ex2Utils.HEIGHT);
    }

    @Override
    public String value(int x, int y) {  // what in the cell
        String ans = Ex2Utils.EMPTY_CELL;

        Cell c = get(x, y);
        if (c != null) {
            ans = c.toString();
            if (SCell.isNumber(ans) || SCell.isText(ans)) {
                return ans;
            }
            if (SCell.isForm(ans)) {
                ans = String.valueOf(SCell.computeForm(ans));
                return ans;
            }
        } return ans;
    }

    @Override
    public Cell get(int x, int y) {
        if (isIn(x, y)) {
            return table[x][y];
        }else{
            return null;
        }
    }

    @Override
    public Cell get(String cords) { //get string return x y
        Cell ans = null;
        int x = cords.indexOf(cords.charAt(0));
        if (cords.length() == 2) {
        int y = cords.charAt(1);
        } else {
            int y = cords.charAt(1 + 2);
        }
        return ans;
    }

    @Override
    public int width() {
        return table.length;
    }

    @Override
    public int height() {
        return table[0].length;
    }

    @Override
    public void set(int x, int y, String s) {
        Cell c = new SCell(s);
        table[x][y] = c;
        // Add your code here

        /////////////////////
    }

    @Override
    public void eval() { //
        int[][] dd = depth();
        // Add your code here

        // ///////////////////
    }

    @Override
    public boolean isIn(int xx, int yy) { // chekcing if x, y in the range
        boolean ans = xx >= 0 && yy >= 0;
        if (xx >= 0 && yy >= 0 && xx < width() && yy < height()) {
            return true;
        }
        return ans;
    }

    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        // Add your code here

        // ///////////////////
        return ans;
    }

    @Override
    public void load(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public void save(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public String eval(int x, int y) { // what the cell contains without computation
        String ans = null;
        if (get(x, y) != null) {
            ans = get(x, y).toString(); }
        double w = SCell.computeForm(ans);
        String s = String.valueOf(w);
        return s;
    }
}

