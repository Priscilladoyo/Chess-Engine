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
 */

public abstract class Tile {
    int tileCoordinate;

    Tile(int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    /* 
     * 'final' keyword means that these classes cannot be subclassed further.
     *  This helps lock down the design and reinforces that a Tile is either empty or occupied - nothing more.
     * 
     *  We extends Tile to achive polymorphism, allowing diffrent tile types to behave differently while sharing a common interface.
     */
    public static final class emptyTile extends Tile{
        emptyTile(int tileCoordinate){
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

    public static final class occupiedTile extends Tile{
        Piece pieceOnTile;

        occupiedTile(int tileCoordinate, Piece pieceOnTile) {
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