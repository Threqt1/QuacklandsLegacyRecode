package parsers.records;

import engine.math.Vector2;

public record ParsedMap(String id, String[][] map, ParsedMapTile[][] mapTileInfo, Vector2 startingPosition) {

}