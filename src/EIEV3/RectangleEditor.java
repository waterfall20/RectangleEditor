package EIEV3;

public class RectangleEditor {
    public static void main(String[] args) {
        int operation;
        MyFrame mf = new MyFrame();
        mf.setVisible(true);
        Board board = new Board(1200, 1000);
        Input input = new Input(board);
        Command cm = new Command(mf,board,input);
        do {
            System.out.println("行う操作を数字指定で選んでください");
            System.out.println("1:create\n2:move\n3:expand/shrink\n" + "4:delete\n5:deleteAll\n6:intersect\n"
                    + "7:displayBoard\n8:exit");
            operation = input.selectActionNum();
            switch (operation) {
                case 1:
                    cm.create();
                    break;
                case 2:
                    cm.move();
                    break;
                case 3:
                    cm.expandShrink();
                    break;
                case 4:
                    cm.delete();
                    break;
                case 5:
                    cm.deleteAll();
                    break;
                case 6:
                    cm.intersect();
                    break;
                case 7:
                    cm.displayBoard();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("1-8の範囲で入力してください\n");
                    break;
            }
        } while (!(operation == 8));
        System.out.println("プログラムを終了します");
        System.exit(0);
    }
}

