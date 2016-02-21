package zonca.project.game.states;

import com.badlogic.gdx.scenes.scene2d.Stage;

import zonca.project.common.InputReader;
import zonca.project.common.State;
import zonca.project.common.StateMachine;
import zonca.project.game.MyGdxGame;
import zonca.project.game.screens.MainGame;
import zonca.project.game.screens.PauseMenu;

public class PausedState implements State
{
   private final StateMachine<GameState> theParentMachine;
   private final MainGame theMainGame;
   private final PauseMenu thePauseMenu;
   private final InputReader theInputReader;
   private final Stage theStage;
   
   public PausedState(MyGdxGame game)
   {
      theStage = game.getStage();
      theMainGame = game.getMainGame();
      thePauseMenu = game.getPauseMenu();
      theParentMachine = game.getStateMachine();
      theInputReader = game.getInputReader();
   }

   @Override
   public void enter()
   {
      theStage.clear();
      thePauseMenu.init();
   }

   @Override
   public void update()
   {
      theMainGame.render();
      thePauseMenu.render();
      
      if (theInputReader.isBackPressed() || thePauseMenu.isResumeButtonClicked())
      {
         theParentMachine.enterState(GameState.MAIN_GAME);
      }
      else if (thePauseMenu.isQuitButtonClicked())
      {
         theParentMachine.enterState(GameState.MAIN_MENU);
      }
   }

}
