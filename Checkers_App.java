//package CS501.checkBoard_Implementation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Checkers_App extends Application {

    public static final int TILE_SIZE = 70;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] _board = new Tile[WIDTH][HEIGHT];

    private Group tile_Group = new Group();
    private Group piece_Group = new Group();

    /*** main method to start the program*/
    public static void main(String[] args) {
        launch(args);
    }

    private Parent create_Content() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tile_Group, piece_Group);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                _board[x][y] = tile;

                tile_Group.getChildren().add(tile);

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(Piece_Type.RED, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(Piece_Type.GREEN, x, y);
                }

                if (piece != null) {
                    tile.set_Piece(piece);
                    piece_Group.getChildren().add(piece);
                }
            }
        }

        return root;
    }

    /***shows the direction one can move */
    private Move_Result tryMove(Piece piece, int newX, int newY) {
        if (_board[newX][newY].has_Piece() || (newX + newY) % 2 == 0) {
            return new Move_Result(Move_Type.NONE);
        }

        int x0 = toBoard(piece.get_OldX());
        int y0 = toBoard(piece.get_OldY());

        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            return new Move_Result(Move_Type.NORMAL);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (_board[x1][y1].has_Piece() && _board[x1][y1].get_Piece().getType() != piece.getType()) {
                return new Move_Result(Move_Type.KILL, _board[x1][y1].get_Piece());
            }
        }

        return new Move_Result(Move_Type.NONE);
    }

    /*** sets size */
    private int toBoard(double pixel) {
        return (int) (pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(create_Content());
        primaryStage.setTitle("Checkers Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*** setting the position after the each move. */
    private Piece makePiece(Piece_Type type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            Move_Result result;

            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = new Move_Result(Move_Type.NONE);
            } else {
                result = tryMove(piece, newX, newY);
            }

            int x0 = toBoard(piece.get_OldX());
            int y0 = toBoard(piece.get_OldY());

            switch (result.getType()) {
                case NONE:
                    piece.abort_Move();
                    break;
                case NORMAL:
                    piece._move(newX, newY);
                    _board[x0][y0].set_Piece(null);
                    _board[newX][newY].set_Piece(piece);
                    break;
                case KILL:
                    piece._move(newX, newY);
                    _board[x0][y0].set_Piece(null);
                    _board[newX][newY].set_Piece(piece);

                    Piece other_Piece = result.getPiece();
                    _board[toBoard(other_Piece.get_OldX())][toBoard(other_Piece.get_OldY())].set_Piece(null);
                    piece_Group.getChildren().remove(other_Piece);
                    break;
            }
        });

        return piece;
    }
}
