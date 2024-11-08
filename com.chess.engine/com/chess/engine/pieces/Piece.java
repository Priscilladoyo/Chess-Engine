package com.chess.engine.pieces;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;

    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }

    /*
     *  Method that is responsible to calculate the legal move of a piece.
     */
    public abstract List<Move> calculateLegalMove(final Board board);
}
