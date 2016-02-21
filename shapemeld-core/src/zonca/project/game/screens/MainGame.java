package zonca.project.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

import zonca.project.common.Utilites;
import zonca.project.game.Goal;
import zonca.project.game.MyGdxGame;
import zonca.project.game.PieceTray;

public class MainGame
{
   private OrthographicCamera theCamera;
   private ShapeRenderer theShapeRenderer;

   private Goal theGoal;
   private PieceTray theTray;

   private boolean touchedLastFrame;

   public MainGame(MyGdxGame game)
   {
      theCamera = game.getCamera();
      theShapeRenderer = game.getShapeRenderer();
      
      theTray = new PieceTray();
      theGoal = new Goal(theTray.getPieces());
   }

   public void render()
   {
      if (theGoal.isSolved())
      {
         theTray.reset();
         theGoal.reset(theTray.getPieces(), Utilites.randomIntInRange(2, 4));
      }
      theShapeRenderer.setColor(Color.WHITE);
      theShapeRenderer.set(ShapeType.Filled);
      theShapeRenderer.rect(0, 0, theCamera.viewportWidth, theCamera.viewportHeight);
      
      theGoal.render(170, 310, theShapeRenderer);
      theTray.render(theShapeRenderer);
   }

   public void processInputs()
   {
      Vector3 touchPos = new Vector3();
      if (Gdx.input.isTouched() && !touchedLastFrame)
      {
         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
         theCamera.unproject(touchPos);
         touchedLastFrame = true;
      }
      else if (!Gdx.input.isTouched())
      {
         touchedLastFrame = false;
      }

      theTray.handleInput(touchPos);
   }
}
