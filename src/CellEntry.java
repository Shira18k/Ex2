/**Implementation of an Index2D and the goal is to find indexes from the table for matching
to cells*/

public class CellEntry implements Index2D {

    private final String cell;
    private final Ex2Sheet sheet = new Ex2Sheet(26, 99);

    public CellEntry(String cell) {
        if (cell == null || cell.isEmpty()) {
            throw new IllegalArgumentException("Error: Cell string cannot be null or empty");
        }
        this.cell = cell;
    }

    @Override
    public String toString() {
        try {
            int columnIndex = Integer.parseInt(cell.substring(0, 1));
            int rowIndex = Integer.parseInt(cell.substring(1));

            if (sheet.isIn(columnIndex, rowIndex)) {
                String column = Ex2Utils.ABC[columnIndex];
                return column + rowIndex;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Error";
        }
        return "Error";
    }

    @Override
    public boolean isValid() {
        if (cell.length() < 2) {
            return false;
        }

        char left = cell.charAt(0);
        String right = cell.substring(1);

        if (Character.isLetter(left)) {
            try {
                int rowIndex = Integer.parseInt(right);
                return rowIndex >= 0 && rowIndex <= 99;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public int getX() {
        if (!isValid()) {
            return Ex2Utils.ERR;
        }

        char columnChar = Character.toUpperCase(cell.charAt(0));
        for (int i = 0; i < Ex2Utils.ABC.length; i++) {
            if (Ex2Utils.ABC[i].equals(String.valueOf(columnChar))) {
                return i;
            }
        }
        return Ex2Utils.ERR;
    }

    @Override
    public int getY() {
        if (!isValid()) {
            return Ex2Utils.ERR;
        }
        try {
            return Integer.parseInt(cell.substring(1));
        } catch (NumberFormatException e) {
            return Ex2Utils.ERR;
        }
    }
}