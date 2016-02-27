package zonca.project.designer;

import javax.swing.JLabel;
import javax.swing.JSlider;

public class CombinedSlider implements CombinedComponent
{
   private final JLabel theLabel;

   private final JSlider theSlider;
   
   public CombinedSlider(String label, int minValue, int maxValue)
   {
      theLabel = new JLabel(label);
      theSlider = new JSlider(minValue, maxValue);
      theSlider.setValue(10);
   }
   
   @Override
   public JLabel getLabel()
   {
      return theLabel;
   }

   @Override
   public JSlider getComponent()
   {
      return theSlider;
   }
}
