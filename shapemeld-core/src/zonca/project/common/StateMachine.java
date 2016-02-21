package zonca.project.common;

import java.util.HashMap;
import java.util.Map;

public class StateMachine<T extends Enum<?>>
{
   Map<T, State> theStateMap = new HashMap<>();
   
   private State theCurrentState;
   
   public void register(T stateName, State state)
   {
      theStateMap.put(stateName, state);
   }
   
   public void enterState(T stateType)
   {
      theCurrentState = theStateMap.get(stateType);
      theCurrentState.enter();
   }
   
   public void update()
   {
      theCurrentState.update();
   }
}
