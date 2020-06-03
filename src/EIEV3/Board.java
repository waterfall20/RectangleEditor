package EIEV3;

import java.util.ArrayList;

public class Board {
    private static final int RED = 1;
    private static final int BLUE = 2;
    private static final int YELLOW = 3;
    private static final int GRAY = 4;
    private static final int GREEN = 5;
    private static final int ORANGE = 6;
    private static final int MAGENTA = 7;
    private static final int CYAN = 8;

    public static int bWidth;
    public static int bHeight;
    private Integer intVal_;
    private Float floatVal_;
    private boolean isIntParsed_;
    private boolean isFloatParsed_;

    private static final int MAX_RECTANGLE = 10;
    public static ArrayList<Rectangle> arrR = new ArrayList<Rectangle>();

    // コンストラクタ
    public Board(int bWidth, int bHeight) {
        Board.bWidth = bWidth;
        Board.bHeight = bHeight;
    }

    public ArrayList<Rectangle> getArrR() {
        return arrR;
    }

    // 入力値で長方形が作成できるか判定①
    public boolean canParseInt(String s) {
        try {
            intVal_ = Integer.parseInt(s);
            isIntParsed_ = true;
            return true;
        } catch (NumberFormatException e) {
            isIntParsed_ = false;
            return false;
        }
    }

    // 入力値で長方形が作成できるか判定②
    public Integer getInteger() {
        if (isIntParsed_) {
            return intVal_;
        }
        throw new UnsupportedOperationException("it isn't parsed yet.");
    }

    // Float型か判定①
    public boolean canParseFloat(String s) {
        try {
            floatVal_ = Float.parseFloat(s);
            isFloatParsed_ = true;
            return true;
        } catch (NumberFormatException e) {
            isFloatParsed_ = false;
            return false;
        }
    }

    // Float型か判定②
    public Float getFloat() {
        if (isFloatParsed_) {
            return floatVal_;
        }
        throw new UnsupportedOperationException("it isn't parsed yet.");
    }

    // 長方形がボード内にあるか判定(はみ出していないか)
    public boolean isRectangleInBoard(Integer width, Integer height, Integer x, Integer y) {
        if (0 <= x && x <= Board.bWidth - 1 && 0 <= y && y <= Board.bHeight - 1) {// x,yが範囲内にある
            if (1 <= width && width <= (Board.bWidth - x) && 1 <= height && height <= (Board.bHeight - y)) {// x,yが範囲内にあり、かつボードをはみ出さないか
                return true;
            } else {// x,yが範囲内にあるが、ボードをはみ出す
                return false;
            }
        } else {// x,yが範囲外
            return false;
        }
    }

    // ボード内に重複する長方形がないか判定
    boolean isRepeat(Integer width, Integer height, Integer x, Integer y) {
        for (int i = 0; i < arrR.size(); i++) {
            if (width.equals(arrR.get(i).getRWidth())) {
                if (height.equals(arrR.get(i).getRHeight())) {
                    if (x.equals(arrR.get(i).getRX())) {
                        if (y.equals(arrR.get(i).getRY())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // ボードに長方形を追加する
    public void addRectangle(Integer width, Integer height, Integer x, Integer y, int color) {
        arrR.add(new Rectangle(width, height, x, y, color));
    }

    // 長方形の数が上限を超えているか判定
    public boolean isOverTheLimit() {
        if (arrR.size() >= MAX_RECTANGLE) {
            return true;
        } else {
            return false;
        }
    }

    // 長方形の数が 1以上であるか判定
    public boolean isMoreThanOne() {
        if (arrR.size() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    // 長方形の数が2以上かつ9以下であるか判定
    public boolean rangeTwoToNine() {
        if (1 < arrR.size() && arrR.size() < 10) {
            return true;
        } else {
            return false;
        }
    }

    // 引数の矩形と２点が交差するかどうかを判定します
    public boolean isOverlap(Rectangle rec1, Rectangle rec2) {
        if (Math.max(rec1.getRX(), rec2.getRX()) < Math.min(rec1.getDiagonalRX(), rec2.getDiagonalRX())
                && Math.max(rec1.getRY(), rec2.getRY()) < Math.min(rec1.getDiagonalRY(), rec2.getDiagonalRY())) {
            return true;
        } else {
            return false;
        }
    }

    // ２点が交差する場合の交差している領域を表現する矩形を返します
    public void overlappingRectangles(Rectangle rec1, Rectangle rec2) {
        int newRX = Math.max(rec1.getRX(), rec2.getRX());
        int newRY = Math.max(rec1.getRY(), rec2.getRY());
        int newDiagonalRX = Math.min(rec1.getDiagonalRX(), rec2.getDiagonalRX());
        int newDiagonalRY = Math.min(rec1.getDiagonalRY(), rec2.getDiagonalRY());
        int newWidth = newDiagonalRX - newRX;
        int newHeight = newDiagonalRY - newRY;
        if (!(newRX < newDiagonalRX && newRY < newDiagonalRY)) {
            throw new IllegalStateException("not intersect 2point.");
        } else if (isRepeat(newWidth, newHeight, newRX, newRY)) {
            System.out.println("新規作成する長方形はすでに同じ長方形が存在するため作れませんでした。");
            return;
        } else {
            addRectangle(newWidth, newHeight, newRX, newRY, decideColor(rec1, rec2));
            System.out.println("新しい長方形R" + arrR.size() + "の作成に成功しました。");
        }
    }

    // 2つの長方形の色から新規長方形の色を定める
    public int decideColor(Rectangle rec1, Rectangle rec2) {
        Integer color1 = rec1.getColorNumber();
        Integer color2 = rec2.getColorNumber();
        if (color1.equals(color2)) {
            return GRAY;
        } else if ((color1.equals(BLUE) || color1.equals(YELLOW)) && (color2.equals(BLUE) || color2.equals(YELLOW))) {
            return GREEN;
        } else if ((color1.equals(RED) || color1.equals(YELLOW)) && (color2.equals(RED) || color2.equals(YELLOW))) {
            return ORANGE;
        } else if ((color1.equals(BLUE) || color1.equals(RED)) && (color2.equals(BLUE) || color2.equals(RED))) {
            return MAGENTA;
        } else {
            return CYAN;
        }
    }
}
