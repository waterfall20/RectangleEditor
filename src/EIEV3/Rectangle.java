package EIEV3;

import java.awt.Color;

class Rectangle {
    private Integer rWidth;
    private Integer rHeight;
    private Integer rX;
    private Integer rY;
    private Integer diagonalRX;
    private Integer diagonalRY;
    private int rColor;

    // コンストラクタ
    public Rectangle(Integer rWidth, Integer rHeight, Integer rX, Integer rY, int rColor) {
        this.rWidth = rWidth;
        this.rHeight = rHeight;
        this.rX = rX;
        this.rY = rY;
        this.rColor = rColor;
        this.diagonalRX = rX + rWidth;
        this.diagonalRY = rY + rHeight;
    }

    public Integer getRWidth() {
        return this.rWidth;
    }

    public void setRWidth(Integer rWidth) {
        this.rWidth = rWidth;
    }

    public Integer getRHeight() {
        return this.rHeight;
    }

    public void setRHeight(Integer rHeight) {
        this.rHeight = rHeight;
    }

    public Integer getRX() {
        return this.rX;
    }

    public void setRX(Integer rX) {
        this.rX = rX;
    }

    public Integer getRY() {
        return this.rY;
    }

    public void setRY(Integer rY) {
        this.rY = rY;
    }

    public Integer getDiagonalRX() {
        return this.diagonalRX;
    }

    public void setDiagonalRX(Integer dRX) {
        this.diagonalRX = dRX;
    }

    public void setDiagonalRY(Integer dRY) {
        this.diagonalRY = dRY;
    }

    public Integer getDiagonalRY() {
        return this.diagonalRY;
    }

    public int getColorNumber() {
        return this.rColor;
    }

    // CUI
    public String getColor() {
        switch (this.rColor) {
            case 1:
                return "red";
            case 2:
                return "blue";
            case 3:
                return "yellow";
            case 4:
                return "gray";
            case 5:
                return "green";
            case 6:
                return "orange";
            case 7:
                return "magenta";
            default:
                return "cyan";
        }
    }

    // GUI
    public Color recColor() {
        switch (this.rColor) {
            case 1:
                return Color.red;
            case 2:
                return Color.blue;
            case 3:
                return Color.yellow;
            case 4:
                return Color.gray;
            case 5:
                return Color.green;
            case 6:
                return Color.orange;
            case 7:
                return Color.magenta;
            default:
                return Color.cyan;
        }
    }
}
