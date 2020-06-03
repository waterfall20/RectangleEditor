package EIEV3;

public class Command {
    private Integer rWidth;
    private Integer rHeight;
    private Integer rX;
    private Integer rY;
    private int selectColor;
    private Rectangle R;
    private Rectangle R1;
    private Rectangle R2;
    private MyFrame myFrame;
    private Board board;
    private Input input; 

    public Command(MyFrame mFrame, Board board, Input input) {
        this.myFrame = mFrame;
        this.board = board;
        this.input = input;
    }

    public void create() {
        if (board.isOverTheLimit()) {
            System.out.println("上限の１０個を超えたため、これ以上作ることはできません.");
            return;
        } else {
            System.out.println("長方形を作成します。幅(1以上)、高さ(1以上)、x座標(0以上)、y座標(0以上)の数値を入力してください\n確定したらEnterを押してください");
            rWidth = input.createEdge("width");
            rHeight = input.createEdge("height");
            rX = input.xyCoordinate("x");
            rY = input.xyCoordinate("y");
            selectColor = input.setColor();
            if (board.isRectangleInBoard(rWidth, rHeight, rX, rY)) {
                if (board.isRepeat(rWidth, rHeight, rX, rY)) {
                    System.out.println("すでに同じ長方形が存在します。値を変えてもう一度入力し直してください。\n");
                    create();
                } else {
                    board.addRectangle(rWidth, rHeight, rX, rY, selectColor);
                    myFrame.repaint();
                    System.out.println("新しい長方形を追加しました\n");
                }
            } else {
                System.out.println("長方形がボードに入りません。もう一度入力し直してください。\n");
                create();
            }
        }
    }

    // 長方形の移動
    public void move() {
        if (board.isMoreThanOne()) {
            R = input.chooseOne();
            move(R, input.movingDistance("x0"), input.movingDistance("y0"));
        } else {
            System.out.println("ボードに長方形は存在しません。");
        }
    }

    // 長方形の移動
    public void move(Rectangle rec, int rX, int rY) {
        int tentativeRX = rX + rec.getRX();
        int tentativeRY = rY + rec.getRY();
        if (board.isRectangleInBoard(rec.getRWidth(), rec.getRHeight(), tentativeRX, tentativeRY)) {
            if (board.isRepeat(rec.getRWidth(), rec.getRHeight(), tentativeRX, tentativeRY)) {
                System.out.println("移動先に同じ長方形が存在します。値を変えてもう一度入力し直してください。\n");
                move(rec, input.movingDistance("x0"), input.movingDistance("y0"));
            } else {
                rec.setRX(tentativeRX);
                rec.setRY(tentativeRY);
                rec.setDiagonalRX(tentativeRX + rec.getRWidth());
                rec.setDiagonalRY(tentativeRY + rec.getRHeight());
                myFrame.repaint();
                System.out.println("長方形を移動しました\n");
            }
        } else {
            System.out.println("長方形がボードに入りません。もう一度入力し直してください");
            move(rec, input.movingDistance("x0"), input.movingDistance("y0"));
        }
    }

    // 長方形の拡大または縮小
    public void expandShrink() {
        if (board.isMoreThanOne()) {
            R = input.chooseOne();
            expandShrink(R, input.setMagnification("width"), input.setMagnification("height"));
        } else {
            System.out.println("ボードに長方形は存在しません。");
        }
    }

    // 長方形の拡大または縮小
    public void expandShrink(Rectangle rec, Float mWidth, Float mHeight) {
        int tentativeWidth = Math.round(rec.getRWidth() * mWidth);
        int tentativeHeight = Math.round(rec.getRHeight() * mHeight);
        if (board.isRectangleInBoard(tentativeWidth, tentativeHeight, rec.getRX(), rec.getRY())) {
            if (board.isRepeat(tentativeWidth, tentativeHeight, rec.getRX(), rec.getRY())) {
                System.out.println("移動先に同じ長方形が存在します。値を変えてもう一度入力し直してください。\n");
                expandShrink(rec, input.setMagnification("width"), input.setMagnification("height"));
            } else {
                rec.setRWidth(tentativeWidth);
                rec.setRHeight(tentativeHeight);
                rec.setDiagonalRX(rec.getRX() + tentativeWidth);
                rec.setDiagonalRY(rec.getRY() + tentativeHeight);
                myFrame.repaint();
                System.out.println("幅を" + mWidth + "倍、高さを" + mHeight + "倍することに成功しました\n");
            }
        } else {
            System.out.println("長方形がボードに入りません。もう一度入力し直してください");
            expandShrink(rec, input.setMagnification("width"), input.setMagnification("height"));
        }
    }

    public void delete() {
        if (board.isMoreThanOne()) {
            R = input.chooseOne();
            Board.arrR.remove(R);
            myFrame.repaint();
            System.out.println("長方形を削除しました");
        } else {
            System.out.println("ボードに長方形は存在しません。");
        }
    }

    public void deleteAll() {
        if (board.isMoreThanOne()) {
            Board.arrR.clear();
            myFrame.repaint();
            System.out.println("全ての長方形を削除しました");
        } else {
            System.out.println("ボードに長方形は存在しません。");
        }

    }

    public void intersect() {
        if (board.rangeTwoToNine()) {
            System.out.println("\n2つの長方形を指定し、重なる部分を抽出し、新たな長方形と" + "して配置します。");
            R1 = input.chooseTwo("First");
            R2 = input.chooseTwo("Second");
            if (board.isOverlap(R1, R2)) {
                board.overlappingRectangles(R1, R2);
                myFrame.repaint();
            } else {
                System.out.println("選択した長方形は重なりません");
            }
        } else {
            System.out.println("\nintersectは長方形がボード上に2つ以上9つ以下のみ操作可能です\nintersectは範囲を超えているため操作できません\n");
            return;
        }

    }

    public void displayBoard() {
        if (board.isMoreThanOne()) {
            System.out.println("現在長方形は" + Board.arrR.size() + "個あります");
            for (int i = 0; i < Board.arrR.size(); i++) {
                System.out.println("\n[R" + (i + 1) + ": width = " + Board.arrR.get(i).getRWidth() + ", height = "
                        + Board.arrR.get(i).getRHeight() + ", 座標(" + Board.arrR.get(i).getRX() + ","
                        + Board.arrR.get(i).getRY() + ") , color:" + Board.arrR.get(i).getColor() + "]");
                if (i == (Board.arrR.size() - 1)) {
                    System.out.println("");
                }
            }
        } else {
            System.out.println("ボードに長方形は存在しません。");
        }
    }
}
