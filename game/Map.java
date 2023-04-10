package game;

import engine.math.Vector2;
import parsers.records.ParsedMap;
import parsers.records.ParsedMapTile;

public class Map {
    private String id;
    private ParsedMapTile[][] mapTileData;

    public Map() {
    }

    public void loadMapData(ParsedMap mapData) {
        this.id = mapData.id();
        this.mapTileData = mapData.mapTileInfo();
    }

    public boolean isWalkable(Vector2 position) {
        return isInBounds(position) && mapTileData[position.getY()][position.getX()].canWalkOn();
    }

    public ParsedMapTile getTileInfo(Vector2 position) {
        return mapTileData[position.getY()][position.getX()];
    }

    public boolean isInBounds(Vector2 position) {
        return position.getY() >= 0 && position.getY() < mapTileData.length && position.getX() >= 0 && position.getX() < mapTileData[0].length;
    }

    public String getId() {
        return id;
    }
}
