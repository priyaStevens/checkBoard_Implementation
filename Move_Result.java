//package CS501.checkBoard_Implementation;

public class Move_Result {
    private Move_Type type;

    public Move_Type getType() {
        return type;
    }

    private Piece piece;

    public Piece getPiece() {
        return piece;
    }

    /** constructor for Move_Result class with move type*/
    public Move_Result(Move_Type type) {
        this(type, null);
    }

    /** constructor for Move_Result class with move type and piece*/
    public Move_Result(Move_Type type, Piece piece) {
        this.type = type;
        this.piece = piece;
    }
}
