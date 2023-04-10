package utils;

public class Dialogue {
    public static String normalizeMapId(String mapId) {
        return mapId.replace("_", " ");
    }
    public static String teleportDialogue(String key, String location) {
        return "[PRESS " + key + " TO TELEPORT TO " + location + "]";
    }
}
