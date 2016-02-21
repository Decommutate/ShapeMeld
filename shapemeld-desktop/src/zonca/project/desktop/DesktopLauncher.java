package zonca.project.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import zonca.project.game.MyGdxGame;

public class DesktopLauncher
{
   public static void main(String[] arg)
   {
      LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

      config.title = "Shape Saga";
      config.width = 400;
      config.height = 800;

      new LwjglApplication(new MyGdxGame(), config);
   }
}
