package zonca.project.game.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PieceGenerator
{
   private final Random theRandom = new Random();
   private final List<Piece> thePieces = new ArrayList<>();

   public PieceGenerator()
   {
      for (int i = 0; i < 6; i++)
      {
         thePieces.add(new RectanglePiece(360 * (float) i / 12, 0, 0));
      }
      thePieces.add(new RectanglePiece(0, -20, 0));
      thePieces.add(new RectanglePiece(0, 20, 0));
      thePieces.add(new RectanglePiece(90, 0, -20));
      thePieces.add(new RectanglePiece(90, 0, 20));
   }

   public List<Piece> getPieces(int count)
   {
      List<Piece> result = new ArrayList<>();
      for (int i = 0; i < count; i++)
      {
         result.add(thePieces.remove(theRandom.nextInt(thePieces.size())));
      }
      return result;
   }

   public void returnPiece(Piece piece)
   {
      thePieces.add(piece);
   }
}
