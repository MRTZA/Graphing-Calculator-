import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JPanel {
   //Modifiable variables
   public static List<Integer> pointsX;
   public static List<Integer> pointsY;
   //constants
   public static final int WIDTH = 800;
   public static final int HEIGHT = 650;
   public static final int MARGIN = 30;
   public static final Color GRAPH_COLOR = Color.BLUE;
   public static final Color GRAPH_POINT_COLOR = Color.BLACK;
   public static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   public static final int GRAPH_POINT_WIDTH = 12;
  

   public GUI(List<Integer> pointsX, List<Integer> pointsY) {
      this.pointsX = pointsX;
      this.pointsY = pointsY;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g1 = (Graphics2D)g;
      g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      double xScale = ((double) getWidth() - 2 * MARGIN) / (pointsX.size() - 1);
      double yScale = ((double) getHeight() - 2 * MARGIN) / (pointsY.size() - 1);

      List<Point> graphPoints = new ArrayList<Point>();
      for (int i = 0; i < pointsX.size(); i++) {
         int x1 = (int) (i * xScale + MARGIN);
         int y1 = (int) (i * yScale + MARGIN);
         graphPoints.add(new Point(x1, y1));
      }

      // create x and y axes 
      g1.drawLine(MARGIN, getHeight() - MARGIN, MARGIN, MARGIN);
      g1.drawLine(MARGIN, getHeight() - MARGIN, getWidth() - MARGIN, getHeight() - MARGIN);

      // create hatch marks for y axis. 
      for (int i = 0; i < pointsY.size(); i++) {
         int x0 = MARGIN;
         int y0 = getHeight() - (((i + 1) * (getHeight() - MARGIN * 2)) / pointsY.size() + MARGIN);
         int x1 = GRAPH_POINT_WIDTH + MARGIN;
         int y1 = y0;
         g1.drawLine(x0, y0, x1, y1);
      }

      // and for x axis
      for (int i = 0; i < pointsX.size() - 1; i++) {
         int x0 = (i + 1) * (getWidth() - MARGIN * 2) / (pointsX.size() - 1) + MARGIN;
         int x1 = x0;
         int y0 = getHeight() - MARGIN;
         int y1 = y0 - GRAPH_POINT_WIDTH;
         g1.drawLine(x0, y0, x1, y1);
      }

      Stroke oldStroke = g1.getStroke();
      g1.setColor(GRAPH_COLOR);
      g1.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g1.drawLine(x1, y1, x2, y2);         
      }

      g1.setStroke(oldStroke);      
      g1.setColor(GRAPH_POINT_COLOR);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g1.fillOval(x, y, ovalW, ovalH);
      }
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(WIDTH, HEIGHT);
   }

   private static void makeGUI() {
	  List<Integer> pointsX = new ArrayList<Integer>();
	  List<Integer> pointsY = new ArrayList<Integer>();
      int maxDataPoints = 16;
      int maxScore = 20;
      for (int i = 0; i < maxDataPoints ; i++) {
    	  Random randomX = new Random();
          Random randomY = new Random();
          pointsX.add(randomX.nextInt(maxScore));
          pointsY.add(randomY.nextInt(maxScore));
      }
      GUI mainPanel = new GUI(pointsX, pointsY);

      JFrame frame = new JFrame("DrawGraph");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            makeGUI();
         }
      });
   }
}