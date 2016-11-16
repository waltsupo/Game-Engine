package gameengine.utils.tiled;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import gameengine.mathlib.Vector;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
/**
 * Created by valts on 21.10.2016.
 */
public class Maps {

    public static String defaultPath = "src/assets/";

    public static TiledMap loadTiledMap(String url) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        // TODO Exception
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace(); //_------------------------------------------------FIX
        }

        Document document = null;

        // TODO Exception
        try {
            document = builder.parse(new File(defaultPath + url));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); //--------------------------------------------------FIX
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

    private static void newTileSet(TiledMap map, Node node) {

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

        set.setImage(defaultPath + image.getAttributes().
                getNamedItem("source")
                .getNodeValue());
        set.tileWidth = Integer.parseInt(node.getAttributes().
                getNamedItem("tilewidth").getNodeValue());
        set.tileHeight = Integer.parseInt(node.getAttributes().
                getNamedItem("tileheight").getNodeValue());
        set.tileCount = Integer.parseInt(node.getAttributes().
                getNamedItem("tilecount").getNodeValue());
        set.columns = Integer.parseInt(node.getAttributes().
                getNamedItem("columns").getNodeValue());
        set.startIndex = Integer.parseInt(node.getAttributes().
                getNamedItem("firstgid").getNodeValue());

        map.tileSets.add(set);
    }

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
