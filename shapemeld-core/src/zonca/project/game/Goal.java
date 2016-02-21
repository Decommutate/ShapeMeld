package zonca.project.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import zonca.project.game.piece.Piece;

public class Goal
{
   Random theRandom = new Random();

   final List<Piece> thePieces = new ArrayList<>();
   final Set<Integer> theSolution = new HashSet<>();

   public Goal(List<Piece> pieces)
   {
      reset(pieces, 1);
   }

   public void render(float x, float y, ShapeRenderer renderer)
   {
      renderer.set(ShapeType.Filled);
      renderer.setColor(Color.BLACK);
      for (Integer pieceIndex : theSolution)
      {
         thePieces.get(pieceIndex).render(x, y, renderer);
      }

      renderer.set(ShapeType.Line);
      renderer.circle(x + 25, y + 50, 60);
   }

   public boolean isSolved()
   {
      for (int i = 0; i < thePieces.size(); i++)
      {
         Piece currentPiece = thePieces.get(i);
         if (theSolution.contains(i) && !currentPiece.isSelected())
         {
            return false;
         }

         if (!theSolution.contains(i) && currentPiece.isSelected())
         {
            return false;
         }
      }
      return true;
   }

   public void reset(List<Piece> newPieces, int piecesInSolution)
   {
      thePieces.clear();
      theSolution.clear();

      thePieces.addAll(newPieces);
      while (theSolution.size() < piecesInSolution)
      {
         theSolution.add(theRandom.nextInt(thePieces.size()));
      }
   }
}
