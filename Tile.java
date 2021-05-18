//package CS501.checkBoard_Implementation;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/***Tile class*/
public class Tile extends Rectangle {
    private Piece _piece;

    public boolean has_Piece() {
        return _piece != null;
    }

    /***getter function for Piece*/
    public Piece get_Piece() {
        return _piece;
    }

    //setter function for Piece
    public void set_Piece(Piece piece) {
        this._piece = piece;
    }

    /***parameterize constructor*/
    public Tile(boolean light, int x, int y) {
        setWidth(Checkers_App.TILE_SIZE);
        setHeight(Checkers_App.TILE_SIZE);

        relocate(x * Checkers_App.TILE_SIZE, y * Checkers_App.TILE_SIZE);

        setFill(light ? Color.valueOf("white") : Color.valueOf("black"));
    }
}
