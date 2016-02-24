package zonca.project.game.states;

import com.badlogic.gdx.scenes.scene2d.Stage;

import zonca.project.common.InputReader;
import zonca.project.common.State;
import zonca.project.common.StateMachine;
import zonca.project.game.MyGdxGame;
import zonca.project.game.screens.MainGame;

public class MainGameState implements State
{
   private final StateMachine<GameState> theStateMachine;
   private final MainGame theMainGame;
   private final InputReader theInputReader;
   private final Stage theStage;
   
   
   public MainGameState(final MyGdxGame game)
   {
      theStage = game.getStage();
      theMainGame = game.getMainGame();
      theStateMachine = game.getStateMachine();
      theInputReader = game.getInputReader();
   }

   @Override
   public void enter()
   {
      theStage.clear();
   }

   @Override
   public void update()
   {
      theMainGame.update();
      
      theMainGame.render();

      if (theMainGame.isGameFinished())
      {
         theStateMachine.enterState(GameState.GAME_OVER);
      }
      if (theInputReader.isBackPressed())
      {
         theStateMachine.enterState(GameState.PAUSED);
      }
   }

}
