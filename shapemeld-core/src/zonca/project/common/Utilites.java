package zonca.project.common;

import java.util.Random;

public class Utilites
{
   static Random theRandom = new Random();

   public static int randomIntInRange(int min, int max)
   {
      return theRandom.nextInt(max - min + 1) + min;
   }
}
