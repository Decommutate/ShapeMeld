package zonca.project.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

import zonca.project.common.Utilites;
import zonca.project.game.GameScore;
import zonca.project.game.GameTimer;
import zonca.project.game.Goal;
import zonca.project.game.MyGdxGame;
import zonca.project.game.PieceTray;

public class MainGame
{
   private final OrthographicCamera theCamera;
   private final ShapeRenderer theShapeRenderer;
   private final BitmapFont theFont;
   private final SpriteBatch theSpriteBatch;

   private Goal theGoal;
   private PieceTray theTray;
   private GameTimer theTimer;
   private GameScore theScore;

   private boolean touchedLastFrame;

   public MainGame(MyGdxGame game)
   {
      theCamera = game.getCamera();
      theShapeRenderer = game.getShapeRenderer();
      theFont = game.getFont();
      theSpriteBatch = game.getSpriteBatch();
   }
   
   public void reset()
   {
      theTray = new PieceTray();
      theGoal = new Goal(theTray.getPieces());
      theTimer = new GameTimer();
      theScore = new GameScore();
      theTimer.resetTime(60);
   }

   public void render()
   {
      theShapeRenderer.begin();
      theShapeRenderer.setColor(Color.WHITE);
      theShapeRenderer.set(ShapeType.Filled);
      theShapeRenderer.rect(0, 0, theCamera.viewportWidth, theCamera.viewportHeight);
      
      theGoal.render(170, 310, theShapeRenderer);
      theTray.render(theShapeRenderer);
      theShapeRenderer.end();
      
      theSpriteBatch.begin();
      theTimer.render(theFont, theSpriteBatch);
      theScore.render(theFont, theSpriteBatch, Gdx.graphics.getDeltaTime());
      theSpriteBatch.end();
   }

   public void update()
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
      if (theGoal.isSolved())
      {
         theScore.addPoints(100 + 100 * theGoal.getPieceCount());
         theTray.reset();
         theGoal.reset(theTray.getPieces(), Utilites.randomIntInRange(2, 4));
      }

      theTimer.progressTime(Gdx.graphics.getDeltaTime());
   }
   
   public void progressTime(float deltaTime)
   {
      theTimer.progressTime(deltaTime);
   }
   
   public boolean isGameFinished()
   {
      return theTimer.getTimeRemaining() < 1e-6;
   }
   
   public int getScore()
   {
      return theScore.getScore();
   }
}
