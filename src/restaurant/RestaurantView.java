/*
 * RestaurantView.java
 */

package restaurant;


import java.awt.ComponentOrientation;
import org.jdesktop.application.*;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;
import java.sql.*;


/**
 * The application's main frame.
 */
public class RestaurantView extends FrameView {

    static Connection databaseConnection;
    static String currentLanguage = "";
    
    public RestaurantView(SingleFrameApplication app) {
        super(app);

        initComponents();
        this.getRootPane().setContentPane(RestaurantApp.welcomeP);

        // status bar initialization - message timeout, idle icon and busy animation, etc

        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(true);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = RestaurantApp.getApplication().getMainFrame();
            aboutBox = new RestaurantAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        RestaurantApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        welcomeScreenMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        languageMenu = new javax.swing.JMenu();
        Eng_langMenuItem = new javax.swing.JMenuItem();
        Jp_langMenuItem = new javax.swing.JMenuItem();
        Tr_langMenuItem = new javax.swing.JMenuItem();
        Sp_langMenuItem = new javax.swing.JMenuItem();
        Ru_langMenuItem = new javax.swing.JMenuItem();
        adminMenu = new javax.swing.JMenu();
        loginMenuItem = new javax.swing.JMenuItem();
        oskMenu = new javax.swing.JMenu();
        oskMenuItem = new javax.swing.JCheckBoxMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        assistanceMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 978, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(restaurant.RestaurantApp.class).getContext().getResourceMap(RestaurantView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        welcomeScreenMenuItem.setText(resourceMap.getString("welcomeScreenMenuItem.text")); // NOI18N
        welcomeScreenMenuItem.setName("welcomeScreenMenuItem"); // NOI18N
        welcomeScreenMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                welcomeScreenMenuItemMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                welcomeScreenMenuItemMousePressed(evt);
            }
        });
        welcomeScreenMenuItem.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                welcomeScreenMenuItemMouseDragged(evt);
            }
        });
        fileMenu.add(welcomeScreenMenuItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(restaurant.RestaurantApp.class).getContext().getActionMap(RestaurantView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        languageMenu.setText(resourceMap.getString("languageMenu.text")); // NOI18N
        languageMenu.setName("languageMenu"); // NOI18N

        Eng_langMenuItem.setText(resourceMap.getString("Eng_langMenuItem.text")); // NOI18N
        Eng_langMenuItem.setName("Eng_langMenuItem"); // NOI18N
        Eng_langMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Eng_langMenuItemMousePressed(evt);
            }
        });
        Eng_langMenuItem.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Eng_langMenuItemMouseDragged(evt);
            }
        });
        languageMenu.add(Eng_langMenuItem);

        Jp_langMenuItem.setText(resourceMap.getString("Jp_langMenuItem.text")); // NOI18N
        Jp_langMenuItem.setName("Jp_langMenuItem"); // NOI18N
        Jp_langMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Jp_langMenuItemMousePressed(evt);
            }
        });
        Jp_langMenuItem.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Jp_langMenuItemMouseDragged(evt);
            }
        });
        languageMenu.add(Jp_langMenuItem);

        Tr_langMenuItem.setText(resourceMap.getString("Tr_langMenuItem.text")); // NOI18N
        Tr_langMenuItem.setName("Tr_langMenuItem"); // NOI18N
        Tr_langMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tr_langMenuItemMousePressed(evt);
            }
        });
        Tr_langMenuItem.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Tr_langMenuItemMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Tr_langMenuItemMouseMoved(evt);
            }
        });
        languageMenu.add(Tr_langMenuItem);

        Sp_langMenuItem.setText(resourceMap.getString("Sp_langMenuItem.text")); // NOI18N
        Sp_langMenuItem.setName("Sp_langMenuItem"); // NOI18N
        Sp_langMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Sp_langMenuItemMousePressed(evt);
            }
        });
        Sp_langMenuItem.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Sp_langMenuItemMouseDragged(evt);
            }
        });
        languageMenu.add(Sp_langMenuItem);

        Ru_langMenuItem.setText(resourceMap.getString("Ru_langMenuItem.text")); // NOI18N
        Ru_langMenuItem.setName("Ru_langMenuItem"); // NOI18N
        Ru_langMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Ru_langMenuItemMousePressed(evt);
            }
        });
        Ru_langMenuItem.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Ru_langMenuItemMouseDragged(evt);
            }
        });
        languageMenu.add(Ru_langMenuItem);

        menuBar.add(languageMenu);

        adminMenu.setText(resourceMap.getString("adminMenu.text")); // NOI18N
        adminMenu.setName("adminMenu"); // NOI18N

        loginMenuItem.setText(resourceMap.getString("loginMenuItem.text")); // NOI18N
        loginMenuItem.setName("loginMenuItem"); // NOI18N
        loginMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMenuItemMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginMenuItemMousePressed(evt);
            }
        });
        adminMenu.add(loginMenuItem);

        menuBar.add(adminMenu);

        oskMenu.setText(resourceMap.getString("oskMenu.text")); // NOI18N
        oskMenu.setName("oskMenu"); // NOI18N

        oskMenuItem.setSelected(false);
        oskMenuItem.setText(resourceMap.getString("oskMenuItem.text")); // NOI18N
        oskMenuItem.setName("oskMenuItem"); // NOI18N
        oskMenu.add(oskMenuItem);

        menuBar.add(oskMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        assistanceMenuItem.setText(resourceMap.getString("assistanceMenuItem.text")); // NOI18N
        assistanceMenuItem.setName("assistanceMenuItem"); // NOI18N
        helpMenu.add(assistanceMenuItem);

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1196, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void Eng_langMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Eng_langMenuItemMousePressed
        // TODO add your handling code here:
        this.language("");
        currentLanguage = "";
    }//GEN-LAST:event_Eng_langMenuItemMousePressed

    private void Eng_langMenuItemMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Eng_langMenuItemMouseDragged
        // TODO add your handling code here:
        this.language("");
        currentLanguage = "";
    }//GEN-LAST:event_Eng_langMenuItemMouseDragged

    private void Jp_langMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jp_langMenuItemMousePressed
        // TODO add your handling code here:
        this.language("Jp_");
        currentLanguage = "Jp_";
    }//GEN-LAST:event_Jp_langMenuItemMousePressed

    private void Jp_langMenuItemMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jp_langMenuItemMouseDragged
        // TODO add your handling code here:
        this.language("Jp_");
        currentLanguage = "Jp_";
    }//GEN-LAST:event_Jp_langMenuItemMouseDragged

    private void Tr_langMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tr_langMenuItemMousePressed
        // TODO add your handling code here:
        this.language("Tr_");
        currentLanguage = "Tr_";
    }//GEN-LAST:event_Tr_langMenuItemMousePressed

    private void Tr_langMenuItemMouseMoved(java.awt.event.MouseEvent evt)
    {
        // TODO add your handling code here:
    }

    private void Tr_langMenuItemMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tr_langMenuItemMouseDragged
        // TODO add your handling code here:
        this.language("Tr_");
        currentLanguage = "Tr_";
    }//GEN-LAST:event_Tr_langMenuItemMouseDragged

    private void Sp_langMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sp_langMenuItemMousePressed
        // TODO add your handling code here:
        this.language("Sp_");
        currentLanguage = "Sp_";
    }//GEN-LAST:event_Sp_langMenuItemMousePressed

    private void Sp_langMenuItemMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sp_langMenuItemMouseDragged
        // TODO add your handling code here:
        this.language("Sp_");
        currentLanguage = "Sp_";
    }//GEN-LAST:event_Sp_langMenuItemMouseDragged

    private void Ru_langMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ru_langMenuItemMousePressed
        // TODO add your handling code here:
        this.language("Ru_");
        currentLanguage = "Ru_";
    }//GEN-LAST:event_Ru_langMenuItemMousePressed

    private void Ru_langMenuItemMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ru_langMenuItemMouseDragged
        // TODO add your handling code here:
        this.language("Ru_");
        currentLanguage = "Ru_";
    }//GEN-LAST:event_Ru_langMenuItemMouseDragged


    private void loginMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMenuItemMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_loginMenuItemMouseClicked

    private void loginMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMenuItemMousePressed
        // TODO add your handling code here:
        clearScreen();
        this.getRootPane().setContentPane(RestaurantApp.loginP);
        RestaurantApp.loginP.setVisible(true);
    }//GEN-LAST:event_loginMenuItemMousePressed

    private void welcomeScreenMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_welcomeScreenMenuItemMousePressed
        // TODO add your handling code here:'
        this.clearScreen();
        this.getRootPane().setContentPane(RestaurantApp.welcomeP);
        RestaurantApp.welcomeP.setVisible(true);
    }//GEN-LAST:event_welcomeScreenMenuItemMousePressed

    private void welcomeScreenMenuItemMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_welcomeScreenMenuItemMouseDragged
        // TODO add your handling code here:
        this.clearScreen();
        this.getRootPane().setContentPane(RestaurantApp.welcomeP);
        RestaurantApp.welcomeP.setVisible(true);
    }//GEN-LAST:event_welcomeScreenMenuItemMouseDragged

    private void welcomeScreenMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_welcomeScreenMenuItemMouseClicked
        // TODO add your handling code here:
        this.clearScreen();
        this.getRootPane().setContentPane(RestaurantApp.welcomeP);
        RestaurantApp.welcomeP.setVisible(true);
    }//GEN-LAST:event_welcomeScreenMenuItemMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenuItem Eng_langMenuItem;
    public static javax.swing.JMenuItem Jp_langMenuItem;
    public static javax.swing.JMenuItem Ru_langMenuItem;
    public static javax.swing.JMenuItem Sp_langMenuItem;
    public static javax.swing.JMenuItem Tr_langMenuItem;
    public static javax.swing.JMenu adminMenu;
    public static javax.swing.JMenuItem assistanceMenuItem;
    public static javax.swing.JMenu languageMenu;
    public static javax.swing.JMenuItem loginMenuItem;
    public static javax.swing.JPanel mainPanel;
    public static javax.swing.JMenuBar menuBar;
    public static javax.swing.JMenu oskMenu;
    public static javax.swing.JCheckBoxMenuItem oskMenuItem;
    public static javax.swing.JProgressBar progressBar;
    public static javax.swing.JLabel statusAnimationLabel;
    public static javax.swing.JLabel statusMessageLabel;
    public static javax.swing.JPanel statusPanel;
    public static javax.swing.JMenuItem welcomeScreenMenuItem;
    // End of variables declaration//GEN-END:variables

    public static void language(String language)
    {
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(restaurant.RestaurantApp.class).getContext().getResourceMap(RestaurantView.class);
        org.jdesktop.application.ResourceMap resourceMapWelcomePanel = org.jdesktop.application.Application.getInstance(restaurant.RestaurantApp.class).getContext().getResourceMap(welcomePanel.class);
        org.jdesktop.application.ResourceMap resourceMapLoginPanel = org.jdesktop.application.Application.getInstance(restaurant.RestaurantApp.class).getContext().getResourceMap(login.class);

        String fileMenu = language + "fileMenu.text";
        String helpMenu = language + "helpMenu.text";
        String welcome = language + "welcome.text";
        String exitMenuItem = language + "exitMenuItem.text";
        String aboutMenuItem = language + "aboutMenuItem.text";
        String hoverAboutMenuItem = language + "showAboutBox.Action.shortDescription";
        String assistanceMenuItem = language + "assistanceMenuItem.text";
        String adminMenu = language + "adminMenu.text";
        String loginMenuItem = language + "loginMenuItem.text";
        String oskMenu = language + "oskMenu.text";
        String oskMenuItem = language + "oskMenuItem.text";
        String welcomeScreen = language + "welcomeScreenMenuItem.text";
        String welcomeLabel = language + "welcomeLabel.text";
        String orderLabel = language + "orderLable.text";
        String loginLabel = language + "loginLabel.text";
        String userNameLabel = language + "userNameLabel.text";
        String passwordLabel = language + "passwordLabel.text";
        String loginButton = language + "loginButton.text";
        String clearButton = language + "clearButton.text";
    


//        this.getFrame().setTitle(resourceMap.getString(welcome));
        menuBar.getMenu(0).setText(resourceMap.getString(fileMenu));
        menuBar.getMenu(4).setText(resourceMap.getString(helpMenu));
        menuBar.getMenu(2).setText(resourceMap.getString(adminMenu));
        menuBar.getMenu(3).setText(resourceMap.getString(oskMenu));

        menuBar.getMenu(0).getItem(0).setText(resourceMap.getString(welcomeScreen));
        menuBar.getMenu(0).getItem(1).setText(resourceMap.getString(exitMenuItem));
        menuBar.getMenu(3).getItem(0).setText(resourceMap.getString(assistanceMenuItem));
        menuBar.getMenu(4).getItem(1).setText(resourceMap.getString(aboutMenuItem));
        menuBar.getMenu(2).getItem(0).setText(resourceMap.getString(loginMenuItem));
        menuBar.getMenu(3).getItem(0).setText(resourceMap.getString(oskMenuItem));

        menuBar.getMenu(4).getItem(1).setToolTipText(resourceMap.getString(hoverAboutMenuItem));

        welcomePanel.welcomeLabel.setText(resourceMapWelcomePanel.getString(welcomeLabel));
        welcomePanel.orderLable.setText(resourceMapWelcomePanel.getString(orderLabel));

        login.loginLabel.setText(resourceMapLoginPanel.getString(loginLabel));
        login.userNameLabel.setText(resourceMapLoginPanel.getString(userNameLabel));
        login.passwordLabel.setText(resourceMapLoginPanel.getString(passwordLabel));
        login.loginButton.setText(resourceMapLoginPanel.getString(loginButton));
        login.clearButton.setText(resourceMapLoginPanel.getString(clearButton));


    }

    public static Connection connectDB()
    {
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(restaurant.RestaurantApp.class).getContext().getResourceMap(RestaurantView.class);

        //database connection
        String database = resourceMap.getString("databaseConnection");
        String databaseDriver = resourceMap.getString("databaseDriver");
        String databaseUserName = resourceMap.getString("databaseUserName");
        String databasePassword = resourceMap.getString("databasePassword");
        System.out.println(database);
        try{
            Class.forName(databaseDriver);
            databaseConnection = java.sql.DriverManager.getConnection(database,databaseUserName, databasePassword);
            return databaseConnection;
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println(cnfe);
            return null;
        }


    }

    public static void clearScreen()
    {
        RestaurantApp.welcomeP.setVisible(false);
        RestaurantApp.chefP.setVisible(false);
        RestaurantApp.customerP.setVisible(false);
        RestaurantApp.loginP.setVisible(false);
        RestaurantApp.waiterP.setVisible(false);
        RestaurantApp.managerP.setVisible(false);
    }

    public void displayLogin()
    {

        this.setComponent(new login());
        this.getFrame().setVisible(true);
    }


    public void displayWelcome()
    {
        clearScreen();
        RestaurantApp.welcomeP.setVisible(true);
        login.currentPosition = "";
        login.currentUser = "";
     
    }


    public final Timer messageTimer;
    public final Timer busyIconTimer;
    public final Icon idleIcon;
    public final Icon[] busyIcons = new Icon[15];
    public int busyIconIndex = 0;

    public JDialog aboutBox;
}
