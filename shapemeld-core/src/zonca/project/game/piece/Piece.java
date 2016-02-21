package zonca.project.game.piece;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Piece
{
   boolean theIsSelected;

   public abstract void render(float x, float y, final ShapeRenderer renderer);

   public boolean isSelected()
   {
      return theIsSelected;
   }

   public void select()
   {
      theIsSelected = !theIsSelected;
   }

   public void reset()
   {
      theIsSelected = false;
   }
}
