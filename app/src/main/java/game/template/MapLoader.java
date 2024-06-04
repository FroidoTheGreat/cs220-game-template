package game.template;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class MapLoader {
    public TileName[][][] tiles;
    public int width;
    public int height;
    public int layers;

    public MapLoader(String name) throws Exception {
        String jsonText = jsonToString("src/main/resources/assets/maps/" + name + ".tmj");
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        LinkedTreeMap<String, Object> map = new Gson().fromJson(jsonText, type);

        @SuppressWarnings("unchecked")
        ArrayList<LinkedTreeMap<String, Object>> layers = (ArrayList<LinkedTreeMap<String, Object>>) map.get("layers");

        // loop through layers, adding the data from each chunk to the tiles array at the appropriate layer
        
        for (int l = 0; l < layers.size(); l++) {
            LinkedTreeMap<String, Object> layer = layers.get(l);

            int[][] chunkData = getChunkData(layer);
            if (l == 0) {
                tiles = new TileName[layers.size()][chunkData.length][chunkData[0].length];
            }

            System.out.println("width: " + width + ", height: " + height);
            for (int x = 0; x < chunkData.length; x++) {
                for (int y = 0; y < chunkData[0].length; y++) {
                    TileName t = TileName.values()[chunkData[x][y]];
                    tiles[l][x][y] = t;
                    width = chunkData.length;
                    height = chunkData[0].length;
                    this.layers = layers.size();
                }
            }
        }
    }

    private int[][] getChunkData(LinkedTreeMap<String, Object> chunk) {
        int width = ((Double)chunk.get("width")).intValue();
        int height = ((Double)chunk.get("height")).intValue();
        @SuppressWarnings("unchecked")
        ArrayList<Double> data = (ArrayList<Double>)chunk.get("data");
        int[][] chunkData = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                chunkData[x][y] = ((Double)data.get(y * width + x)).intValue();
            }
        }
        return chunkData;

    }

    public String jsonToString(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
