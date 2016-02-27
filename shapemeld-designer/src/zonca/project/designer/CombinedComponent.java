package zonca.project.designer;

import javax.swing.JLabel;
import javax.swing.JSlider;

public interface CombinedComponent
{
   abstract JLabel getLabel();
   abstract JSlider getComponent();
}
