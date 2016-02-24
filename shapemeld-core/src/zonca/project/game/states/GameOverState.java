package zonca.project.game.states;

import com.badlogic.gdx.scenes.scene2d.Stage;

import zonca.project.common.InputReader;
import zonca.project.common.State;
import zonca.project.common.StateMachine;
import zonca.project.game.MyGdxGame;
import zonca.project.game.screens.GameOver;
import zonca.project.game.screens.MainGame;

public class GameOverState implements State
{
   private final StateMachine<GameState> theParentMachine;
   private final MainGame theMainGame;
   private final InputReader theInputReader;
   private final Stage theStage;
   private final GameOver theGameOver;
   
   public GameOverState(MyGdxGame game)
   {
      theStage = game.getStage();
      theMainGame = game.getMainGame();
      theGameOver = game.getGameOver();
      theParentMachine = game.getStateMachine();
      theInputReader = game.getInputReader();
   }

   @Override
   public void enter()
   {
      theStage.clear();
      theGameOver.init();
   }

   @Override
   public void update()
   {
      theMainGame.render();
      theGameOver.render();
      
      if (theInputReader.isBackPressed() || theGameOver.isQuitButtonClicked())
      {
         theParentMachine.enterState(GameState.MAIN_MENU);
      }
   }

}
