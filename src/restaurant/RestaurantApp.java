/*
 * RestaurantApp.java
 */

package restaurant;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import java.awt.*;

/**
 * The main class of the application.
 */
public class RestaurantApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    
    public static customerPanel customerP = new customerPanel();
    public static login loginP = new login();
    public static waiterPanel waiterP = new waiterPanel();
    public static chefPanel chefP = new chefPanel();
    public static managerPanel managerP = new managerPanel();
    public static welcomePanel welcomeP = new welcomePanel();

    @Override protected void startup() {
        show(new RestaurantView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
        this.getMainFrame().setTitle("Welcome");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.getMainFrame().setAlwaysOnTop(false);
        this.getMainFrame().setResizable(true);
        this.getMainFrame().setMinimumSize(size);

    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of RestaurantApp
     */
    public static RestaurantApp getApplication() {
        return Application.getInstance(RestaurantApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        RestaurantView.connectDB();
        launch(RestaurantApp.class, args);
    }

}
