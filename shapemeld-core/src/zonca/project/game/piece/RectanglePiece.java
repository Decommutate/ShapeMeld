package zonca.project.game.piece;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RectanglePiece extends Piece
{
   private float theRotation, theDx, theDy;

   public RectanglePiece(float rotation, float dx, float dy)
   {
      theRotation = rotation;
      theDx = dx;
      theDy = dy;
   }

   @Override
   public void render(float x, float y, final ShapeRenderer renderer)
   {
      renderer.setColor(Color.BLACK);

      float width = 30;
      float height = 80;
      float ox = width / 2;
      float oy = height / 2;
      renderer.rect(x + 10 + theDx, y + 10 + theDy, ox, oy, width, height, 1, 1,
            theRotation, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK);
   }
}
