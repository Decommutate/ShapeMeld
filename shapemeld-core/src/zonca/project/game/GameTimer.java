package zonca.project.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameTimer
{
   private float theTime;

   public void resetTime(float seconds)
   {
      theTime = seconds;
   }

   public void progressTime(float deltaTime)
   {
      theTime -= deltaTime;
   }

   public float getTimeRemaining()
   {
      return theTime;
   }
   
   public void render(BitmapFont font, SpriteBatch batch)
   { 
      final int roundedTime = (int)Math.ceil(theTime);
      font.draw(batch, "Time: " + roundedTime, 170, 700);
   }

}
