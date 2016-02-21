package zonca.project.game;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

import zonca.project.game.piece.Piece;
import zonca.project.game.piece.PieceGenerator;

public class PieceTray
{
   private PieceGenerator theGenerator = new PieceGenerator();
   private List<Piece> thePieces;

   public PieceTray()
   {
      thePieces = theGenerator.getPieces(6);
   }

   public List<Piece> getPieces()
   {
      return thePieces;
   }

   public void reset()
   {
      for (int i = 0; i < thePieces.size(); i++)
      {
         Piece piece = thePieces.get(i);
         if (piece.isSelected())
         {
            piece.reset();
            theGenerator.returnPiece(piece);
            thePieces.set(i, theGenerator.getPieces(1).get(0));
         }
      }
   }

   public void render(ShapeRenderer theShapeRenderer)
   {
      theShapeRenderer.set(ShapeType.Filled);
      theShapeRenderer.setColor(Color.BLACK);
      for (int i = 0; i < thePieces.size(); i++)
      {
         float x = 40 + ((i % 3) * 130);
         float y = 20 + (i / 3 * 130);
         thePieces.get(i).render(x, y, theShapeRenderer);
      }

      theShapeRenderer.set(ShapeType.Line);
      for (int i = 0; i < thePieces.size(); i++)
      {
         float x = 40 + ((i % 3) * 130);
         float y = 20 + (i / 3 * 130);
         theShapeRenderer.setColor(thePieces.get(i).isSelected() ? Color.ORANGE : Color.BLACK);
         theShapeRenderer.circle(x + 25, y + 50, 60);
      }
   }

   public void handleInput(Vector3 touchPos)
   {
      for (int i = 0; i < thePieces.size(); i++)
      {
         float x = 40 + ((i % 3) * 130);
         float y = 20 + (i / 3 * 130);

         Circle circle = new Circle();
         circle.x = x + 25;
         circle.y = y + 50;
         circle.radius = 60;
         if (circle.contains(touchPos.x, touchPos.y))
         {
            thePieces.get(i).select();
         }
      }
   }

}
