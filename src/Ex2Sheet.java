import java.io.*;
// in this class we wrote a help functions for the table

public class Ex2Sheet implements Sheet {
    private Cell[][] table;
    //////
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
                    return Ex2Utils.ERR_FORM;

                case Ex2Utils.ERR_CYCLE_FORM:
                    return Ex2Utils.ERR_CYCLE;

                case Ex2Utils.TEXT:
                    return c.getData();

                case Ex2Utils.NUMBER:
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
        if (!isIn(x, y)) return;

        Cell c = new SCell(s,this);
        table[x][y] = c;
        eval();
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
    public void load(String fileName) throws IOException { //using in saved file
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length >= 3) {
                    int x = Integer.parseInt(parts[0].trim());
                    int y = Integer.parseInt(parts[1].trim());
                    String data = parts[2].trim();
                    if (isIn(x, y)) {
                        set(x, y, data);
                    }
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        eval();
    }

    @Override
    public void save(String fileName) throws IOException { // saving cell as a fail
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        writer.write("I2CS ArielU: SpreadSheet (Ex2) assignment - this line should be ignored in the load method");
        writer.newLine();

        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                Cell cell = get(x, y);
                if (cell != null && !cell.getData().isEmpty()) {
                    writer.write(x + "," + y + "," + cell.getData());
                    writer.newLine();
                }
            }
        }

        writer.close();
    }
    @Override
    public String eval(int x, int y) {
        String ans = null;
        SCell c = (SCell) get(x, y);
        if (c != null) {
            ans = c.computeForm(c.getData()) + "";
        }
        return ans;
    }
}


