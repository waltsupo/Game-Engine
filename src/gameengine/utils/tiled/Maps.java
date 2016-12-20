package gameengine.utils.tiled;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import gameengine.utils.Files;
import org.w3c.dom.*;

/**
 * Contains methods for handling maps.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class Maps {

    /**
     * Default path to search example.assets.
     */
    public static String defaultPath = "example/assets/";

    /**
     * Creates new tiled map from the source.
     *
     * @param url Path to source
     * @return Tiled map created from the source
     * @throws Exception If Something went wrong setting up map loading
     */
    public static TiledMap loadTiledMap(String url) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new File(defaultPath + url));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Exception when setting up loading the map");
        }

        TiledMap map = new TiledMap();

        // Tile layer z-index, goes up by 2 for each new layer
        int layerIndex = 0;

        // get map's attributes
        NamedNodeMap attributes = document.getDocumentElement().getAttributes();

        // Set up map
        map.columns = Integer.parseInt(
                attributes.getNamedItem("width").getNodeValue());
        map.rows = Integer.parseInt(
                attributes.getNamedItem("height").getNodeValue());
        map.tileWidth = Integer.parseInt(
                attributes.getNamedItem("tilewidth").getNodeValue());
        map.tileHeight = Integer.parseInt(
                attributes.getNamedItem("tileheight").getNodeValue());

        map.width = map.columns * map.tileWidth;
        map.height = map.rows * map.tileHeight;

        // Go through nodes
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {

            // Get node
            Node node = nodeList.item(i);

            // If element node
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                switch (node.getNodeName()) {

                    case "tileset":
                        newTileSet(map, node);
                        break;

                    case "layer":
                        newTileLayer(map, node, layerIndex);
                        layerIndex += 2;
                        break;

                    case "objectgroup":

                        addObjectGroup(map, node);
                        break;
                }
            }
        }

        for (TiledMapLayer layer : map.layers) {

            layer.drawLayerImage(map);
        }

        return map;
    }

    /**
     * Creates new TileSet from given node.
     *
     * @param map TileSet's parent map
     * @param node Node to create TileSet from
     * @throws IOException If TileSet image not found
     */
    private static void newTileSet(TiledMap map, Node node) throws IOException {

        // Get image node
        Node image = null;

        for (int chNumb = 0;
             chNumb < node.getChildNodes().getLength();
             chNumb++) {

            if (node.getChildNodes().item(chNumb).getNodeType()
                    == Node.ELEMENT_NODE) {
                image = node.getChildNodes().item(chNumb);
                break;
            }
        }

        // Create new Tile set, define values and add it to map
        TileSet set = new TileSet();

        if (node.getAttributes().getNamedItem("name") != null) {

            set.name = node.getAttributes().getNamedItem("name").getNodeValue();
        }

        set.image = Files.loadImage(defaultPath + image.getAttributes().
                getNamedItem("source").getNodeValue());

        set.tileWidth = Integer.parseInt(node.getAttributes().
                getNamedItem("tilewidth").getNodeValue());
        set.tileHeight = Integer.parseInt(node.getAttributes().
                getNamedItem("tileheight").getNodeValue());
        set.tileCount = Integer.parseInt(node.getAttributes().
                getNamedItem("tilecount").getNodeValue());
        set.columns = Integer.parseInt(node.getAttributes().
                getNamedItem("columns").getNodeValue());
        set.startIndex = Integer.parseInt(node.getAttributes().
                getNamedItem("firstgid").getNodeValue()) - 1;

        map.tileSets.add(set);
    }

    /**
     * Creates new tile layer from the node.
     *
     * @param map Parent map
     * @param node Information about the layer
     * @param z Z-index
     */
    private static void newTileLayer(TiledMap map, Node node, int z) {

        // Get data node
        Node data = null;
        for (int chNumb = 0;
             chNumb < node.getChildNodes().getLength();
             chNumb++) {

            if (node.getChildNodes().item(chNumb).getNodeType()
                    == Node.ELEMENT_NODE) {
                data = node.getChildNodes().item(chNumb);
                break;
            }
        }

        String all = data.getFirstChild().getNodeValue();
        all = all.replaceAll("\n", "");
        String[] values = all.split(",");
        int[] codes = new int[values.length];

        for (int codeIndex = 0; codeIndex < values.length;
             codeIndex++) {
            codes[codeIndex] =
                    Integer.parseInt(values[codeIndex]) - 1;
        }

        // new layer, define values and add it to map
        TiledMapLayer layer = new TiledMapLayer();

        if (node.getAttributes().getNamedItem("name") != null) {

            layer.name = node.getAttributes().getNamedItem("name")
                    .getNodeValue();
        }

        layer.width = Integer.parseInt(node.getAttributes().
                getNamedItem("width").getNodeValue());
        layer.height = Integer.parseInt(node.getAttributes().
                getNamedItem("height").getNodeValue());
        layer.z = z;

        layer.tiles = codes;

        map.layers.add(layer);
    }

    /**
     * Creates new ObjectGroup to the map.
     *
     * @param map Parent map
     * @param node Information about the group
     */
    private static void addObjectGroup(TiledMap map, Node node) {

        TiledObjectGroup objectLayer = new TiledObjectGroup();

        if (node.getAttributes().getNamedItem("name") != null) {

            objectLayer.name = node.getAttributes().
                    getNamedItem("name").getNodeValue();
        }

        for (int chNumb = 0;
             chNumb < node.getChildNodes().getLength();
             chNumb++) {

            if (node.getChildNodes().item(chNumb).getNodeType()
                    == Node.ELEMENT_NODE) {

                NamedNodeMap objectAtt = node.getChildNodes()
                        .item(chNumb).getAttributes();

                TiledObject tiledObject = new TiledObject();

                if (objectAtt.getNamedItem("name") != null) {

                    tiledObject.name = objectAtt.getNamedItem("name")
                            .getNodeValue();
                }

                if (objectAtt.getNamedItem("type") != null) {

                    tiledObject.type = objectAtt.getNamedItem("type")
                            .getNodeValue();
                }

                if (objectAtt.getNamedItem("width") != null) {

                    tiledObject.width = Float.parseFloat(
                            objectAtt.getNamedItem("width").getNodeValue());
                }

                if (objectAtt.getNamedItem("height") != null) {

                    tiledObject.height = Float.parseFloat(
                            objectAtt.getNamedItem("height").getNodeValue());
                }

                tiledObject.x = Float.parseFloat(objectAtt.getNamedItem("x")
                        .getNodeValue());
                tiledObject.y = map.height - tiledObject.height
                        - Float.parseFloat(objectAtt.getNamedItem("y").
                                getNodeValue());

                objectLayer.objects.add(tiledObject);
            }
        }

        map.objectLayers.add(objectLayer);
    }
}
