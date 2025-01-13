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
                table[i][j] = new SCell("", this);
            }
        }
        eval();
    }

    public Ex2Sheet() {
        this(Ex2Utils.WIDTH, Ex2Utils.HEIGHT);
    }

    @Override
    public String value(int x, int y) {
        String ans = Ex2Utils.EMPTY_CELL;
        SCell c = (SCell) get(x, y);

        if (c != null) {
            int type = c.getType();

            switch (type) {
                case Ex2Utils.ERR_FORM_FORMAT:
                    System.out.println("1");
                    return Ex2Utils.ERR_FORM;

                case Ex2Utils.ERR_CYCLE_FORM:
                    System.out.println("2");
                    return Ex2Utils.ERR_CYCLE;

                case Ex2Utils.TEXT:
                    System.out.println("3");
                    return c.getData();

                case Ex2Utils.NUMBER:
                    System.out.println("4");
                    return Double.parseDouble(c.getData()) + "";

                case Ex2Utils.FORM:
                    return c.computeForm(c.getData()) + "";

                default:
                    ans = c.toString();
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
        Cell c = new SCell(s, this);
        table[x][y] = c;
        // Add your code here

        /////////////////////
    }

    @Override
    public void eval() { //
        int[][] dd = depth();
        for (int i = 0; i < this.height(); i++) {
            for (int j = 0; j < this.width(); j++) {
                SCell Cell = (SCell) get(j, i);
                String data = Cell.getData();
                if (!data.isEmpty() && data.charAt(0) == '=') {
                    if (Cell.isForm(Cell.getData())) {
                        Cell.setType(Ex2Utils.FORM);
                    } else {
                        Cell.setType(Ex2Utils.ERR_FORM_FORMAT);
                    }
                } else if (Cell.isNumber(Cell.getData())) {
                    Cell.setType(Ex2Utils.NUMBER);
                } else {
                    Cell.setType(Ex2Utils.TEXT);
                }
                System.out.println(Cell.getType());
            }

        }

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
        {
            return ans;
        }
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
        String ans = null;
        SCell c = (SCell) get(x, y);
        if (c != null) {
            ans = c.computeForm(c.getData()) + "";
        }
        return ans;
    }
}


