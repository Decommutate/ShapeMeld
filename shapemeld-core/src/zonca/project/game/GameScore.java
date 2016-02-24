package zonca.project.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScore
{
   int theScore = 0;
   int theFrontendScore = 0;

   public void addPoints(int points)
   {
      theScore += points;
   }

   public void render(BitmapFont font, SpriteBatch batch, float deltaTime)
   {
      if (theFrontendScore != theScore) 
      {
         theFrontendScore = Math.min(theScore, 
               theFrontendScore + (int)(600 * deltaTime));
      }
      font.draw(batch, "Score: " + theFrontendScore, 170, 670);
   }

   public int getScore()
   {
      return theScore;
   }

}
