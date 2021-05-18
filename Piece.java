//package CS501.checkBoard_Implementation;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static  CS501.checkBoard_Implementation.Checkers_App.TILE_SIZE;
public class Piece extends StackPane {
//    private static final double TILE_SIZE = ;
    private Piece_Type type;

    private double mouse_X, mouse_Y;
    private double old_X, old_Y;

    public Piece_Type getType() {
        return type;
    }

    public double get_OldX() {
        return old_X;
    }

    public double get_OldY() {
        return old_Y;
    }

    /** parameterize constructor for Piece class*/
    public Piece(Piece_Type type, int x, int y) {
        this.type = type;


        _move(x, y);

        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg.setFill(Color.YELLOW);

        bg.setStroke(Color.YELLOW);
        bg.setStrokeWidth(TILE_SIZE * 0.02);

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        ellipse.setFill(type == Piece_Type.RED
                ? Color.valueOf("#c40003") : Color.valueOf("#008000"));

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(TILE_SIZE * 0.03);

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);

        setOnMousePressed(e -> {
            mouse_X = e.getSceneX();
            mouse_Y = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouse_X + old_X, e.getSceneY() - mouse_Y + old_Y);
        });
    }

    /*** for moving in x and x direction*/
    public void _move(int x, int y) {
        old_X = x * TILE_SIZE;
        old_Y = y * TILE_SIZE;
        relocate(old_X, old_Y);
    }

    /** for aborting the move */
    public void abort_Move() {
        relocate(old_X, old_Y);
    }
}
