package parsers;

import engine.math.Vector2;
import parsers.records.ParsedMap;
import parsers.records.ParsedMapTile;
import parsers.records.ParsedTeleportInfo;
import utils.ASCII;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MapParser {
    static File DEFAULT_MAP = new File("data/maps/test.txt");
    public static ParsedMap parseMapFile(String id) {
        File mapFile = new File("data/maps/" + id + ".txt");
        if (!mapFile.exists()) mapFile = DEFAULT_MAP;

        try (Scanner scanner = new Scanner(mapFile)) {
            ArrayList<String> map = new ArrayList<>();
            int mapSizeX = 0;

            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                if (data.trim().equalsIgnoreCase("")) break;
                mapSizeX = Math.max(mapSizeX, data.length());
                map.add(data);
            }

            HashMap<String, String> broadColorMap = new HashMap<>();

            while (scanner.hasNext()) {
                String data = scanner.nextLine().trim();
                if (data.equalsIgnoreCase("")) break;
                String[] splitData = data.split(" ");
                broadColorMap.put(splitData[0].trim(), splitData[1].trim());
            }

            HashMap<String, String> specificColorMap = new HashMap<>();

            while (scanner.hasNext()) {
                String data = scanner.nextLine().trim();
                if (data.equalsIgnoreCase("")) break;
                String[] splitData = data.split(" ");
                specificColorMap.put(splitData[0].trim(), splitData[1].trim());
            }

            HashMap<String, Boolean> barriers = new HashMap<>();

            while (scanner.hasNext()) {
                String data = scanner.nextLine().trim();
                if (data.equalsIgnoreCase("")) break;
                barriers.put(data.trim(), true);
            }

            String[][] visualMapTiles = new String[map.size()][mapSizeX];
            ParsedMapTile[][] parsedMapTiles = new ParsedMapTile[map.size()][mapSizeX];

            for (int y = 0; y < parsedMapTiles.length; y++) {
                for (int x = 0; x < parsedMapTiles[y].length; x++) {
                    String character = " ";
                    String row = map.get(y);
                    if (x < row.length()) {
                        character = Character.toString(row.charAt(x));
                    }
                    String color = ASCII.DEFAULT_COLOR;
                    color = specificColorMap.getOrDefault(x + "," + y, color);
                    color = broadColorMap.getOrDefault(character, color);
                    visualMapTiles[y][x] = ASCII.colorize(character, color);
                    parsedMapTiles[y][x] = new ParsedMapTile(!barriers.containsKey(character), false, null);
                }
            }

            while (scanner.hasNext()) {
                String data = scanner.nextLine().trim();
                if (data.equalsIgnoreCase("")) break;
                String[] splitData = data.split(" ");

                String[] startCoordinate = splitData[0].trim().split(",");
                int startCoordinateX = Integer.parseInt(startCoordinate[0].trim());
                int startCoordinateY = Integer.parseInt(startCoordinate[1].trim());

                String[] endCoordinate = splitData[1].trim().split(",");
                int endCoordinateX = Integer.parseInt(endCoordinate[0].trim());
                int endCoordinateY = Integer.parseInt(endCoordinate[1].trim());

                String teleportId = splitData[2].trim();

                String[] teleportCoordinate = splitData[3].trim().split(",");
                int teleportCoordinateX = Integer.parseInt(teleportCoordinate[0].trim());
                int teleportCoordinateY = Integer.parseInt(teleportCoordinate[1].trim());

                ParsedTeleportInfo info = new ParsedTeleportInfo(teleportId, new Vector2(teleportCoordinateX, teleportCoordinateY));
                for (int y = startCoordinateY; y <= endCoordinateY; y++) {
                    for (int x = startCoordinateX; x <= endCoordinateX; x++) {
                        ParsedMapTile tile = parsedMapTiles[y][x];
                        parsedMapTiles[y][x] = new ParsedMapTile(tile.canWalkOn(), true, info);
                    }
                }
            }

            Vector2 startingPosition = Vector2.IDENTITY;

            while (scanner.hasNext()) {
                String data = scanner.nextLine().trim();
                if (data.equalsIgnoreCase("")) break;
                String[] splitData = data.split(",");
                startingPosition = new Vector2(Integer.parseInt(splitData[0].trim()), Integer.parseInt(splitData[1].trim()));
            }

            return new ParsedMap(id, visualMapTiles, parsedMapTiles, startingPosition);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
