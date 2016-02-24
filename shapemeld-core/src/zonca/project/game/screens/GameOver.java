package zonca.project.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import zonca.project.game.MyGdxGame;

public class GameOver
{
   private static final float BUTTON_WIDTH = 250;
   private static final float BUTTON_HEIGHT = 60;
   private static final float BUTTON_SPACING = 10;

   private final ShapeRenderer theShapeRenderer;
   private final Stage theStage;
   private final OrthographicCamera theCamera;
   private final MainGame theMainGame;
   
   protected boolean theQuitButtonClicked;

   public GameOver(MyGdxGame game)
   {
      theCamera = game.getCamera();
      theShapeRenderer = game.getShapeRenderer();
      theStage = game.getStage();
      theMainGame = game.getMainGame();
   }

   public void init()
   {
      theQuitButtonClicked = false;
      setupStage();
   }

   public void render()
   {
      Gdx.gl.glEnable(GL20.GL_BLEND);
      Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
      
      theShapeRenderer.begin();
      theShapeRenderer.set(ShapeType.Filled);
      theShapeRenderer.setColor(new Color(0, 0, 0, 0.5f));
      theShapeRenderer.rect(0, 0, theCamera.viewportWidth, theCamera.viewportHeight);

      theShapeRenderer.setColor(new Color(.05f, .05f, .05f, 1.0f));
      theShapeRenderer.rect(40, 300, theCamera.viewportWidth - 80, 250);
      theShapeRenderer.end();

      Gdx.gl.glDisable(GL20.GL_BLEND);
   }

   public boolean isQuitButtonClicked()
   {
      return theQuitButtonClicked;
   }

   private void setupStage()
   {
      float width = 400;
      float currentY = 400;
      final float buttonX = (width - BUTTON_WIDTH) / 2;

      Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

      Label gameOverLabel = new Label("Game Over", skin);
      gameOverLabel.setX((width - gameOverLabel.getWidth()) / 2);
      gameOverLabel.setY(currentY + 100);
      theStage.addActor(gameOverLabel);

      Label scoreLabel = new Label("Score: " + theMainGame.getScore(), skin);
      scoreLabel.setX((width - scoreLabel.getWidth()) / 2);
      scoreLabel.setY(currentY + 70);
      theStage.addActor(scoreLabel);

      TextButton quitButton = new TextButton("Quit", skin);
      quitButton.setX(buttonX);
      quitButton.setY(currentY - BUTTON_HEIGHT - BUTTON_SPACING);
      quitButton.setWidth(BUTTON_WIDTH);
      quitButton.setHeight(BUTTON_HEIGHT);
      quitButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeEvent event, Actor actor)
         {
            theQuitButtonClicked = true;
         } 
      });
      theStage.addActor(quitButton);
   }

}
