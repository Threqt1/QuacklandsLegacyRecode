package utils;

import parsers.MapParser;
import parsers.records.ParsedMap;

import java.util.HashMap;

public class MapCache {
    private final HashMap<String, ParsedMap> mapCache;

    public MapCache() {
        this.mapCache = new HashMap<>();
    }

    public ParsedMap getRawMapData(String id) {
        return mapCache.computeIfAbsent(id, MapParser::parseMapFile);
    }
}
