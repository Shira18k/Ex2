//מימוש של אינדקס טו די והמטרה היא מציאה של אינדקסים מהטבלה לצורך התאמה לתאים
public class CellEntry  implements Index2D {

    private String S;
    Ex2Sheet sheet = new Ex2Sheet(26,99); // בנאי

    public CellEntry(String s) {
        if (s != null && !s.isEmpty()) {
            this.S = s;
        } else {
            throw new IllegalArgumentException("Error");
        }
    }

    public String toString() {

        int left = Integer.parseInt(this.S.substring(0, 1));
        int right = Integer.parseInt(this.S.substring(1));


        if ( sheet.isIn(left,right) ) {

            String Column = Ex2Utils.ABC[left];
            String rightString = Integer.toString(right);
            String ans = Column + rightString;
            return ans;
        }
        return "Error";
    }

    //Verifies si le format recu est correct
    @Override
    public boolean isValid() {

        String left = this.S.substring(0, 1);
        String right = this.S.substring(1);

        if (left.length() == 1 && Character.isLetter(left.charAt(0))) {
            try {
                int rightNumber = Integer.parseInt(right);
                if (rightNumber >= 0 && rightNumber <= 99) {
                    return true;
                }
            } catch (Exception e) {
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

        String X = this.S.substring(0, 1).toUpperCase();
        for (int i = 0; i < Ex2Utils.ABC.length; i++) {
            if (Ex2Utils.ABC[i].equals(X)) {
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
        String right = this.S.substring(1 );
        int ans = Integer.parseInt(right);
        return ans;
    }
}
