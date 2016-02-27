package zonca.project.designer;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Ellipse2D;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

public class Designer
{
   private static final int CUSTOM_COMPONENT_START = 5;
   
   private int currentY = CUSTOM_COMPONENT_START;
   private final JFrame frame = new JFrame();
   private JPanel thePanel;

   /**
    * Launch the application.
    */
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         public void run()
         {
            try
            {
               Designer window = new Designer();
               window.frame.setVisible(true);
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public Designer()
   {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize()
   {
      frame.setBounds(100, 100, 558, 360);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new MigLayout("", "[][][][grow]", "[grow][][][][][][][][][][grow]"));
      
      JList list = new JList();
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      list.setToolTipText("z");
      list.setVisibleRowCount(4);
      frame.getContentPane().add(list, "cell 0 0 4 1,grow");
      
      JLabel lblShape = new JLabel("Shape");
      frame.getContentPane().add(lblShape, "cell 0 1,alignx right");
      
      JComboBox<ShapeType> shapeSelectComboBox = new JComboBox<>();
      shapeSelectComboBox.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
               updateSelectedItems((ShapeType)event.getItem());
            }
         }
      });
      shapeSelectComboBox.setModel(new DefaultComboBoxModel<ShapeType>(ShapeType.values()));
      frame.getContentPane().add(shapeSelectComboBox, "cell 2 1,alignx left");
      
      JLabel lblX = new JLabel("X");
      frame.getContentPane().add(lblX, "cell 0 2,alignx right");
      
      JSlider xSlider = new JSlider();
      xSlider.setValue(0);
      xSlider.addChangeListener( e -> { thePanel.repaint(); } );
      frame.getContentPane().add(xSlider, "cell 2 2,alignx left");
      
      JLabel lblY = new JLabel("Y");
      frame.getContentPane().add(lblY, "cell 0 3,alignx right");
      
      JSlider ySlider = new JSlider();
      ySlider.setValue(0);
      ySlider.addChangeListener( e -> { thePanel.repaint(); } );
      frame.getContentPane().add(ySlider, "cell 2 3,alignx left");
      
      JLabel lblRotation = new JLabel("Rotation");
      frame.getContentPane().add(lblRotation, "cell 0 4,alignx right");
      
      JSlider rotationSlider = new JSlider();
      rotationSlider.setValue(0);
      rotationSlider.setMaximum(3600);
      rotationSlider.addChangeListener( e -> { thePanel.repaint(); } );
      frame.getContentPane().add(rotationSlider, "cell 2 4,alignx left");

      thePanel = new JPanel() {
         private static final long serialVersionUID = 5718896947601990885L;

         @Override
         public void paint(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D)g.create();
            Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, 60, 60);
            g2.draw(circle);
            
            ShapeType type = (ShapeType)shapeSelectComboBox.getSelectedItem();
            switch(type)
            {
               case RECTANGLE:
                  int x = xSlider.getValue();
                  int y = ySlider.getValue();
                  int width = type.getComponents()[0].getComponent().getValue();
                  int height = type.getComponents()[1].getComponent().getValue();
                  g2.drawRect(x, y, width, height);
                  break;
               case CIRCLE:
                  x = xSlider.getValue();
                  y = ySlider.getValue();
                  float radius = ((float)type.getComponents()[0].getComponent().getValue());
                  circle = new Ellipse2D.Double(x, y, radius, radius);
                  g2.draw(circle);
                  break;
               case ELLIPSE:
                  break;
               case LINE:
                  break;
               default:
                  break;
            }
            
            g2.dispose();
         }
      };
      frame.getContentPane().add(thePanel, "cell 3 2 1 9,grow");
      
      updateSelectedItems(ShapeType.RECTANGLE);
   }

   protected void updateSelectedItems(ShapeType type)
   {
      while (currentY > CUSTOM_COMPONENT_START) {
         frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
         frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
         currentY--;
      }
      
      for (CombinedComponent component : type.getComponents()) {
         frame.getContentPane().add(component.getLabel(), 
               "cell 0 " + currentY + ",alignx right");
         frame.getContentPane().add(component.getComponent(), 
               "cell 2 " + currentY + ",alignx left");
         
         if (component.getComponent().getChangeListeners().length == 0) {
            component.getComponent().addChangeListener(
                  e -> { thePanel.repaint(); } );
         }
         currentY++;
      }
         
      frame.revalidate();
      frame.repaint();
   }

}
