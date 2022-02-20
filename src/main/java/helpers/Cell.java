package helpers;

public class Cell {
    public Boolean leftWall, bottomWall;
    public int  value;
    public Cell(int v){
        value = v;
        leftWall = bottomWall = true;
    }
}
