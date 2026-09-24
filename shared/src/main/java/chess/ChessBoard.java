package chess;

import java.util.*;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] board = new ChessPiece[8][8];
    public ChessBoard() {
        
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        ChessBoard other = (ChessBoard) obj;
        System.out.println(toString());
        System.out.println(other.toString());
        for (int i = 1; i <= 8; i++) {
            for (int j  = 1; j <= 8; j++) {
                ChessPosition pos =  new ChessPosition(i,j);
                if (getPiece(pos) == null) {
                    if (other.getPiece(pos) == null) {
                        continue;
                    }
                    return false;
                }
                if (!getPiece(pos).equals(other.getPiece(pos))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ChessPosition pos =  new ChessPosition(i,j);
                if (getPiece(pos) == null) {
                    str += "  ";
                    continue;
                }
                str += getPiece(pos).toString();
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        board = new ChessPiece[8][8];
        pawnRow(ChessGame.TeamColor.WHITE);
        pawnRow(ChessGame.TeamColor.BLACK);
        backRow(ChessGame.TeamColor.WHITE);
        backRow(ChessGame.TeamColor.BLACK);
    }
    public void pawnRow(ChessGame.TeamColor color) {
        int row = 2;
        if (color == ChessGame.TeamColor.BLACK) row = 7;
        for (int i = 1; i <= 8; i++) {
            addPiece(new ChessPosition(row,i), new ChessPiece(color, ChessPiece.PieceType.PAWN));
        }
    }
    public void backRow(ChessGame.TeamColor color) {
        int row = 1;
        if (color == ChessGame.TeamColor.BLACK) row = 8;
        addPiece(new ChessPosition(row,1), new ChessPiece(color, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(row,2), new ChessPiece(color, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(row,3), new ChessPiece(color, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(row,4), new ChessPiece(color, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(row,5), new ChessPiece(color, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(row,6), new ChessPiece(color, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(row,7), new ChessPiece(color, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(row,8), new ChessPiece(color, ChessPiece.PieceType.ROOK));
    }
}
