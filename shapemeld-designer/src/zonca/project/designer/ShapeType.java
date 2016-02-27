package zonca.project.designer;

public enum ShapeType
{
   RECTANGLE(new CombinedSlider("Width", 0, 100), new CombinedSlider("Height", 0, 100)),
   CIRCLE(new CombinedSlider("Radius", 0, 100)),
   ELLIPSE,
   LINE;
   
   private final CombinedComponent[] theComponents;
   
   private ShapeType(CombinedComponent... components)
   {
      theComponents = components;
   }
   
   public CombinedComponent[] getComponents()
   {
      return theComponents.clone();
   }
}
