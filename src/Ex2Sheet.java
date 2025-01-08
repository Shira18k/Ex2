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
    public String value(int x, int y) {  // מה רואים בתא (בלי חישוב)
        String ans = Ex2Utils.EMPTY_CELL;

        Cell c = get(x, y);
        if (c != null) {
            ans = c.toString();
            if (SCell.isNumber(ans) || SCell.isText(ans)) {
                return ans;
            }
            if (SCell.isForm(ans)) {
                ans = String.valueOf(SCell.isForm(ans));
                return ans;
            }
        }
        return ans;
    }

    @Override
    public Cell get(int x, int y) { //get x y return cell
        if (!isIn(x, y)) {
            return null;
        } else {
            return table[x][y];
        }
    }


    @Override
    public Cell get(String cords) { //get string return cell
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
      int[][] ans = new int [width()] [height()];
        {
              return ans;
        }
        //

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
    public String eval(int x, int y) { //מוציאה את הערך
        String gValue = value(x, y);
//        //להכניס ל value כדי שיצא מה שכתוב בפנים
//        if (gValue == null) {
//            return null;
//        }
//        if (SCell.isNumber(gValue) || SCell.isText(gValue)) {
//            return gValue;
//        } else{
//            if (SCell.isForm(gValue)) {
//                double G = SCell.computeForm(gValue);
//                String g = String.valueOf(G);
//                return g;
//            } else {
                return "ERR;";
            }
}


