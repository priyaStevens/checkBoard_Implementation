package CS501.checkBoard_Implementation;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Tile extends Rectangle {
    private Piece _piece;

    public boolean has_Piece() {
        return _piece != null;
    }

    public Piece get_Piece() {
        return _piece;
    }

    public void set_Piece(Piece piece) {
        this._piece = piece;
    }

    public Tile(boolean light, int x, int y) {
        setWidth(Checkers_App.TILE_SIZE);
        setHeight(Checkers_App.TILE_SIZE);

        relocate(x * Checkers_App.TILE_SIZE, y * Checkers_App.TILE_SIZE);

        setFill(light ? Color.valueOf("white") : Color.valueOf("black"));
    }
}
