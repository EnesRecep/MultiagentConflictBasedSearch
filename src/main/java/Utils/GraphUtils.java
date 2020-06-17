package Utils;

import Model.BuildingEdge;
import Model.BuildingNode;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GraphUtils {


    public static void printGraph(Graph<BuildingNode, BuildingEdge> graph, String name){
        JGraphXAdapter<BuildingNode, BuildingEdge> adapter =
                new JGraphXAdapter<>(graph);
        mxIGraphLayout l = new mxCircleLayout(adapter);
        l.execute(adapter.getDefaultParent());

        BufferedImage img =
                mxCellRenderer.createBufferedImage(adapter, null, 2, Color.WHITE, true, null);
        File iFile = new File(name + ".png");
        try {
            ImageIO.write(img, "PNG", iFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> reverseArray(ArrayList<String> original){
        ArrayList<String> temp = (ArrayList<String>) original.clone();
        Collections.reverse(temp);
        temp.remove(0);
        return temp;
    }
}
