package chess;
import java.util.*;
import java.util.function.BiFunction;

public class PieceMoves {
    private ChessPosition pos;
    private ChessGame.TeamColor color;
    private ChessBoard board;
    public PieceMoves(ChessPosition pos, ChessGame.TeamColor color, ChessBoard board) {
        this.pos = pos;
        this.color = color;
        this.board = board;
    }
    public enum Direction {
        NORTH,
        NORTHEAST,
        EAST,
        SOUTHEAST,
        SOUTH,
        SOUTHWEST,
        WEST,
        NORTHWEST
    }
    public Collection<ChessMove> getRookMoves() {
        BiFunction<Direction, Integer, ChessMove> movement = (dir, num) -> {
            switch (dir) {
                case NORTH -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+num, pos.getColumn()), null);}
                case SOUTH -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-num, pos.getColumn()), null);}
                case EAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow(), pos.getColumn()+num), null);}
                case WEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow(), pos.getColumn()-num), null);}
            }
            return null;
        };
        return straightMoves(movement);
    }
    public Collection<ChessMove> getBishopMoves() {
        BiFunction<Direction, Integer, ChessMove> movement = (dir, num) -> {
            switch (dir) {
                case NORTHEAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+num, pos.getColumn()+num), null);}
                case NORTHWEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+num, pos.getColumn()-num), null);}
                case SOUTHEAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-num, pos.getColumn()+num), null);}
                case SOUTHWEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-num, pos.getColumn()-num), null);}
            }
            return null;
        };
        return straightMoves(movement);
    }

    public Collection<ChessMove> getQueenMoves() {
        BiFunction<Direction, Integer, ChessMove> movement = (dir, num) -> {
            switch (dir) {
                case NORTH -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+num, pos.getColumn()), null);}
                case SOUTH -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-num, pos.getColumn()), null);}
                case EAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow(), pos.getColumn()+num), null);}
                case WEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow(), pos.getColumn()-num), null);}
                case NORTHEAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+num, pos.getColumn()+num), null);}
                case NORTHWEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+num, pos.getColumn()-num), null);}
                case SOUTHEAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-num, pos.getColumn()+num), null);}
                case SOUTHWEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-num, pos.getColumn()-num), null);}
            }
            return null;
        };
        return straightMoves(movement);
    }
    public Collection<ChessMove> getKnightMoves() {
        BiFunction<Direction, Integer, ChessMove> movement = (dir, num) -> {
            if (num > 1) return null;
            switch (dir) {
                case NORTH -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+2, pos.getColumn()+1), null);}
                case SOUTH -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-2, pos.getColumn()+1), null);}
                case EAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+2, pos.getColumn()-1), null);}
                case WEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-2, pos.getColumn()-1), null);}
                case NORTHEAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+1, pos.getColumn()+2), null);}
                case NORTHWEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+1, pos.getColumn()-2), null);}
                case SOUTHEAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-1, pos.getColumn()+2), null);}
                case SOUTHWEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-1, pos.getColumn()-2), null);}
            }
            return null;
        };
        return straightMoves(movement);
    }
    public Collection<ChessMove> getKingMoves() {
        BiFunction<Direction, Integer, ChessMove> movement = (dir, num) -> {
            if (num > 1) return null;
            switch (dir) {
                case NORTH -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+num, pos.getColumn()), null);}
                case SOUTH -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-num, pos.getColumn()), null);}
                case EAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow(), pos.getColumn()+num), null);}
                case WEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow(), pos.getColumn()-num), null);}
                case NORTHEAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+num, pos.getColumn()+num), null);}
                case NORTHWEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()+num, pos.getColumn()-num), null);}
                case SOUTHEAST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-num, pos.getColumn()+num), null);}
                case SOUTHWEST -> {return new ChessMove(pos, new ChessPosition(pos.getRow()-num, pos.getColumn()-num), null);}
            }
            return null;
        };
        return straightMoves(movement);
    }
    public Collection<ChessMove> getPawnMoves() {
        int dir = 1;
        List<ChessMove> moveList = new ArrayList<ChessMove>();
        if (color == ChessGame.TeamColor.BLACK) dir = -1;
        ChessPosition endPos = new ChessPosition(pos.getRow()+dir, pos.getColumn()+1);
        if (isPositionValid(endPos) && isPositionCapture(endPos)) {
            promotePiece(endPos, moveList);
        }
        endPos = new ChessPosition(pos.getRow()+dir, pos.getColumn()-1);
        if (isPositionValid(endPos) && isPositionCapture(endPos)) {
            promotePiece(endPos, moveList);
        }
        endPos = new ChessPosition(pos.getRow()+dir, pos.getColumn());
        if (isPositionValid(endPos) && !isPositionCapture(endPos)) {
            promotePiece(endPos, moveList);

            if (pos.getRow() == 2 || pos.getRow() == 7) {
                endPos = new ChessPosition(pos.getRow()+(2*dir), pos.getColumn());
                if (isPositionValid(endPos) && !isPositionCapture(endPos)) {
                    promotePiece(endPos,moveList);
                }
            }
        }
        return moveList;
    }
    private Collection<ChessMove> straightMoves(BiFunction<Direction, Integer, ChessMove> movement) {
        List<ChessMove> moveList = new ArrayList<ChessMove>();
        for (Direction dir : Direction.values()) {
            for (int i = 1; i < 9; i++) {
                ChessMove move = movement.apply(dir, i);
                if (move == null) {
                    break;
                }
                if (isMoveValid(move)) {
                    moveList.add(move);
                    if (isMoveCapture(move)) {
                        break;
                    }
                }
                else break;
            }

        }
        return moveList;
    }
    private boolean isMoveValid (ChessMove move) {
        if (move.getEndPosition().getRow() > 8 || move.getEndPosition().getColumn() > 8 || move.getEndPosition().getRow() < 1 || move.getEndPosition().getColumn() < 1) {
            return false;
        }
        return board.getPiece(move.getEndPosition()) == null || board.getPiece(move.getEndPosition()).getTeamColor() != color;
    }
    private boolean isPositionValid(ChessPosition pos) {
        if (pos.getRow() > 8 || pos.getColumn() > 8 || pos.getRow() < 1 || pos.getColumn() < 1) {
            return false;
        }
        return board.getPiece(pos) == null || board.getPiece(pos).getTeamColor() != color;
    }
    private boolean isPositionCapture(ChessPosition pos) {
        return board.getPiece(pos) != null && board.getPiece(pos).getTeamColor() != color;
    }
    private boolean isMoveCapture(ChessMove move) {
        return board.getPiece(move.getEndPosition()) != null && board.getPiece(move.getEndPosition()).getTeamColor() != color;
    }
    private void promotePiece(ChessPosition endPos, List<ChessMove> moveList) {
        if (endPos.getRow() == 1 || endPos.getRow() == 8) {
            for (ChessPiece.PieceType type : ChessPiece.PieceType.values()) {
                if (type == ChessPiece.PieceType.PAWN || type == ChessPiece.PieceType.KING) {
                    continue;
                }
                moveList.add(new ChessMove(pos, endPos, type));
            }
        }
        else {
            moveList.add(new ChessMove(pos,endPos,null));
        }
    }

}
