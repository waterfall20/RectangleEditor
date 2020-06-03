package EIEV3;

import java.util.Scanner;

public class Input {
    private static final String ERROR_MESSAGE = "不適切な入力値が検出されました。数字を入力してください！";
    private static final String INPUT_ERROR_MESSAGE = "入力値に誤りがあります";
    private static final String CURRENT_STATE_MSG = "現在以下の長方形がボード上に存在します.";
    private int edge;
    private int coordinate;
    private int selectActionNum;
    private int selectColor;
    private int getRec;
    private int currentCoordinates;
    private float magnification;
    private Board board;
    private Rectangle rec;

    Scanner sc = new Scanner(System.in);

    public Input(Board board) {
        this.board = board;
    }

    // 操作番号を入力するメソッド
    public int selectActionNum() {
        System.out.print("番号を入力してください-> ");
        if (board.canParseInt(sc.next())) {
            selectActionNum = board.getInteger();
        } else {
            System.out.println(ERROR_MESSAGE);
            selectActionNum();
        }
        return selectActionNum;
    }

    // 長方形の幅と高さを作るメソッド
    public int createEdge(String str) {
        if (str.equals("width")) {
            System.out.print("幅 ->");
        } else {
            System.out.print("高さ ->");
        }
        if (board.canParseInt(sc.next())) {
            edge = board.getInteger();
            if (edge <= 0) {
                System.out.println("1以上の数字を入力してください！");
                createEdge(str);
            }
        } else {
            System.out.println(ERROR_MESSAGE);
            createEdge(str);
        }
        return edge;
    }

    // 長方形のxy座標を定めるメソッド
    public int xyCoordinate(String str) {
        if (str.equals("x")) {
            System.out.print("x座標 ->");
        } else {
            System.out.print("y座標 ->");
        }
        if (board.canParseInt(sc.next())) {
            coordinate = board.getInteger();
        } else {
            System.out.println(ERROR_MESSAGE);
            xyCoordinate(str);
        }
        return coordinate;
    }

    // 移動距離メソッド
    public int movingDistance(String str) {
        if (str.equals("x0")) {
            System.out.print("xの移動距離 ->");
        } else {
            System.out.print("yの移動距離 ->");
        }
        if (board.canParseInt(sc.next())) {
            currentCoordinates = board.getInteger();
        } else {
            System.out.println(ERROR_MESSAGE);
            movingDistance(str);
        }
        return currentCoordinates;
    }

    // 色を決めるメソッド
    public int setColor() {
        System.out.println("色を指定してください\n1:red  2:blue  3:yellow  4:gray");
        if (board.canParseInt(sc.next())) {
            selectColor = board.getInteger();
            if (selectColor < 1 || 4 < selectColor) {
                System.out.println("1-4の範囲で数字を入力してください！");
                setColor();
            }
        } else {
            System.out.println(ERROR_MESSAGE);
            setColor();
        }
        return selectColor;
    }

    // 倍率を決めるメソッド
    public float setMagnification(String str) {
        if (str.equals("width")) {
            System.out.print("幅の倍率 ->");
        } else {
            System.out.print("高さの倍率 ->");
        }
        if (board.canParseFloat(sc.next())) {
            this.magnification = board.getFloat();
            if (this.magnification <= 0) {
                System.out.println("長方形が成り立たなくなります。倍率を変えてください。");
                setMagnification(str);
            }
        } else {
            System.out.println(ERROR_MESSAGE);
            setMagnification(str);
        }
        return this.magnification;

    }

    // 長方形を1つ指定するメソッド
    public Rectangle chooseOne() {
        String choose;
        System.out.println(CURRENT_STATE_MSG);
        for (int i = 0; i < Board.arrR.size(); i++) {
            System.out.print("R" + (i + 1) + " ");
        }
        System.out.print("\n入力してください(例: R1)->");
        choose = sc.next();// 空白まで
        try {
            getRec = Integer.parseInt(choose.replaceAll("[^0-9]", "")) - 1;
            rec = Board.arrR.get(getRec);
        } catch (NumberFormatException e) {
            System.out.println(INPUT_ERROR_MESSAGE);
            chooseOne();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INPUT_ERROR_MESSAGE);
            chooseOne();
        }
        return rec;
    }

    // 長方形を2つ指定するメソッド
    public Rectangle chooseTwo(String str) {
        String choose;
        if (str.equals("First")) {
            System.out.println(CURRENT_STATE_MSG);
            for (int i = 0; i < Board.arrR.size(); i++) {
                System.out.print("R" + (i + 1) + " ");
            }
            System.out.print("\n1つめの抽出する長方形(例: R1)->");
        } else {
            System.out.print("\n２つめの抽出する長方形(例: R2)->");
        }
        choose = sc.next();// 空白まで
        try {
            getRec = Integer.parseInt(choose.replaceAll("[^0-9]", "")) - 1;
            rec = Board.arrR.get(getRec);
        } catch (NumberFormatException e) {
            System.out.println(INPUT_ERROR_MESSAGE);
            chooseTwo(str);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INPUT_ERROR_MESSAGE);
            chooseTwo(str);
        }
        return rec;
    }
}
