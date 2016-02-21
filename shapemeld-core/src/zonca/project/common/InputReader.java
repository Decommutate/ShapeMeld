package zonca.project.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class InputReader
{
   boolean isBackPressedOld;
   boolean isBackPressed;
   
   Vector2 touchLocation;
   
   public void update()
   {
      isBackPressedOld = isBackPressed;
      isBackPressed = Gdx.input.isKeyPressed(Input.Keys.BACK) || 
            Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
   }
   
   public boolean isBackPressed()
   {
      return !isBackPressedOld && isBackPressed;
   }
}
