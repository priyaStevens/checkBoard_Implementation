//package CS501.checkBoard_Implementation;

public enum Piece_Type {
    RED(1), GREEN(-1);

    final int moveDir;

    /** parameterize constructor for Piece_Type */
    Piece_Type(int move_Dir) {
        this.moveDir = move_Dir;
    }
}
