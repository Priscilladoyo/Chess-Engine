/* 
 * Chess board consist of 64 tiles or 8x8
 * 
 * We put class Tile as abstract because we have isTileOccupied() and getPiece() methods as abstract.
 * If we use non-abstract in those classes, we will need to provide default implementations for Tile, ex.,
 * 
 * public boolean isTileOccupied(){
 *      return false; //default to 'unoccupied'
 * }
 * public Piece getPiece(){
 *      return null; //default to 'no piece'
 * }
 * 
 * 'protected' keyword used for immutability so it can only be accessed by a subclasses.
 * 
 */

package com.chess.engine.board;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;

public abstract class Tile {
    protected final int tileCoordinate;

    private static final Map<Integer, emptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer, emptyTile> createAllPossibleEmptyTiles(){
        final Map<Integer, emptyTile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < 64; i++){
            emptyTileMap.put(i, new emptyTile(i));
        }

        Collections.unmodifiableMap(emptyTileMap);
        return emptyTileMap;
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new occupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    private Tile(int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    /* 
     * 'final' keyword means that these classes cannot be subclassed further (set it once).
     *  This helps lock down the design and reinforces that a Tile is either empty or occupied - nothing more.
     * 
     *  We extends Tile to achive polymorphism, allowing diffrent tile types to behave differently while sharing a common interface.
     */
    public static final class emptyTile extends Tile{
        private emptyTile(final int tileCoordinate){
            super(tileCoordinate); //calls the Tile's constructor to set the tileCoordinate
        }

        @Override
        public boolean isTileOccupied(){
            return false; //not occupied because the tile is empty
        }

        @Override
        public Piece getPiece(){
            return null; //return null because the tile is empty
        }
    }

    /* 
     * 'private' keyword means we can't call pieceOnTile outside this method without getPiece() on it.
    */
    public static final class occupiedTile extends Tile{
        private final Piece pieceOnTile;

        private occupiedTile(int tileCoordinate, Piece pieceOnTile) {
            super(tileCoordinate); //calls the Tile's constructor to set the tileCoordinate
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied(){
            return true; 
        }

        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
        
    }
}