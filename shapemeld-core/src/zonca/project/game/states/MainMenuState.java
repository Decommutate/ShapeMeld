package zonca.project.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import zonca.project.common.InputReader;
import zonca.project.common.State;
import zonca.project.common.StateMachine;
import zonca.project.game.MyGdxGame;
import zonca.project.game.screens.MainMenu;

public class MainMenuState implements State
{
   private final StateMachine<GameState> theParentMachine;
   private final MainMenu theMainMenu;
   private final Stage theStage;
   private final InputReader theInputReader;
   
   public MainMenuState(MyGdxGame game)
   {
      theStage = game.getStage();
      theMainMenu = game.getMainMenu();
      theParentMachine = game.getStateMachine();
      theInputReader = game.getInputReader();
   }

   @Override
   public void enter()
   {
      theStage.clear();
      theMainMenu.init();
   }

   @Override
   public void update()
   {
      theMainMenu.render();
      
      if (theMainMenu.isStartButtonClicked())
      {
         theParentMachine.enterState(GameState.MAIN_GAME);
      }
      else if (theInputReader.isBackPressed() || theMainMenu.isQuitButtonClicked())
      {
         Gdx.app.exit();
      }
   }
}
