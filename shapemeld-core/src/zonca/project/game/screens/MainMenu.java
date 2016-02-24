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

public class MainMenu
{
   private static final float BUTTON_WIDTH = 250;
   private static final float BUTTON_HEIGHT = 60;
   private static final float BUTTON_SPACING = 10;

   private final ShapeRenderer theShapeRenderer;
   private final Stage theStage;
   private final OrthographicCamera theCamera;
   protected boolean theStartButtonClicked;
   protected boolean theQuitButtonClicked;

   public MainMenu(MyGdxGame game)
   {
      theCamera = game.getCamera();
      theShapeRenderer = game.getShapeRenderer();
      theStage = game.getStage();
   }
   
   public void init()
   {
      theStartButtonClicked = false;
      theQuitButtonClicked = false;
      setupStage();
   }

   public void render()
   {
      Gdx.gl.glEnable(GL20.GL_BLEND);
      Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
      
      theShapeRenderer.begin();
      theShapeRenderer.set(ShapeType.Filled);
      theShapeRenderer.setColor(new Color(1, 1, 1, 1));
      theShapeRenderer.rect(0, 0, theCamera.viewportWidth, theCamera.viewportHeight);

      theShapeRenderer.setColor(new Color(.05f, .05f, .05f, 1.0f));
      theShapeRenderer.rect(40, 300, theCamera.viewportWidth - 80, 250);
      theShapeRenderer.end();

      Gdx.gl.glDisable(GL20.GL_BLEND);
   }

   private void setupStage()
   {
      float width = 400;
      float currentY = 400;
      final float buttonX = (width - BUTTON_WIDTH) / 2;

      Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

      // label "welcome"
      Label welcomeLabel = new Label("Shape Saga", skin);
      welcomeLabel.setX((width - welcomeLabel.getWidth()) / 2);
      welcomeLabel.setY(currentY + 100);
      theStage.addActor(welcomeLabel);

      // button "start game"
      TextButton resumeButton = new TextButton("Start", skin);
      resumeButton.setX(buttonX);
      resumeButton.setY(currentY);
      resumeButton.setWidth(BUTTON_WIDTH);
      resumeButton.setHeight(BUTTON_HEIGHT);
      resumeButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeEvent event, Actor actor)
         {
            theStartButtonClicked = true;
         } 
      });
      theStage.addActor(resumeButton);

      // button "options"
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

   public boolean isStartButtonClicked()
   {
      return theStartButtonClicked;
   }

   public boolean isQuitButtonClicked()
   {
      return theQuitButtonClicked;
   }
}
