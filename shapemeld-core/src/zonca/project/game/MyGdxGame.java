package zonca.project.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import zonca.project.common.InputReader;
import zonca.project.common.StateMachine;
import zonca.project.game.screens.MainGame;
import zonca.project.game.screens.MainMenu;
import zonca.project.game.screens.PauseMenu;
import zonca.project.game.states.GameState;
import zonca.project.game.states.MainGameState;
import zonca.project.game.states.MainMenuState;
import zonca.project.game.states.PausedState;

public class MyGdxGame extends Game
{
   public StateMachine<GameState> theStateMachine;
   private SpriteBatch theSpriteBatch;
   private ShapeRenderer theShapeRenderer;
   private BitmapFont theFont;
   private MainGame theMainGame;
   private PauseMenu thePauseMenu;
   private OrthographicCamera theCamera;
   private InputReader theInputReader;
   private Stage theStage;
   private MainMenu theMainMenu;
        
   @Override
   public void create()
   {
      theSpriteBatch = new SpriteBatch();
      theShapeRenderer = new ShapeRenderer();
      theShapeRenderer.setAutoShapeType(true);
      
      theFont = new BitmapFont();
      
      theCamera = new OrthographicCamera();
      theCamera.setToOrtho(false, 400, 800);
      
      theStateMachine = new StateMachine<>();
      theInputReader = new InputReader();
      theStage = new Stage(new FitViewport(400, 800, theCamera));
      
      theMainMenu = new MainMenu(this);
      theMainGame = new MainGame(this);
      thePauseMenu = new PauseMenu(this);

      theStateMachine.register(GameState.MAIN_MENU, new MainMenuState(this));
      theStateMachine.register(GameState.MAIN_GAME, new MainGameState(this));
      theStateMachine.register(GameState.PAUSED, new PausedState(this));
      
      theStateMachine.enterState(GameState.MAIN_MENU);
      
      Gdx.input.setInputProcessor(theStage);
      Gdx.input.setCatchBackKey(true);

      theShapeRenderer.setProjectionMatrix(theCamera.combined);
      
   }
   
   @Override
   public void resize(int width, int height)
   {
      theShapeRenderer.setProjectionMatrix(theCamera.combined);
      theStage.getViewport().update(width, height);
   }

   @Override
   public void render()
   {
      Gdx.gl.glClearColor(0, 0, 0, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      
      theInputReader.update();
      
      theSpriteBatch.begin();
      theShapeRenderer.begin();
      
      theStateMachine.update();
      
      theStage.act();
      theStage.draw();

      theSpriteBatch.end();
      theShapeRenderer.end();
      
      super.render();
   }
   
   @Override
   public void dispose()
   {
      theShapeRenderer.dispose();
      theSpriteBatch.dispose();
      theFont.dispose();
   }
   
   public MainGame getMainGame()
   {
      return theMainGame;
   }

   public PauseMenu getPauseMenu()
   {
      return thePauseMenu;
   }
   
   public MainMenu getMainMenu()
   {
      return theMainMenu;
   }
   
   public StateMachine<GameState> getStateMachine()
   {
      return theStateMachine;
   }
   
   public SpriteBatch getSpriteBatch()
   {
      return theSpriteBatch;
   }
   
   public ShapeRenderer getShapeRenderer()
   {
      return theShapeRenderer;
   }

   public BitmapFont getFont()
   {
      return theFont;
   }
   
   public OrthographicCamera getCamera()
   {
      return theCamera;
   }
   
   public InputReader getInputReader()
   {
      return theInputReader;
   }
   
   public Stage getStage()
   {
      return theStage;
   }
}
