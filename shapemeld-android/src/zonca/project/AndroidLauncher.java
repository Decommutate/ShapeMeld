package zonca.project;

import android.os.Bundle;
import zonca.project.game.MyGdxGame;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
      config.useAccelerometer = false;
      config.useCompass = false;
      
      initialize(new MyGdxGame(), config);
   }
}