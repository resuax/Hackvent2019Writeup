
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ControllerPanel extends JPanel implements KeyListener
{
    private final CardLayout cardLayout;
    private final QuestionPanel questionPanel;
    private final CorrectAnswerPanel correctPanel;
    private final IncorrectAnswerPanel incorrectPanel;
    private final ArrayList<QuestionAnswer> questionsAndAnswers;
    private final Player playah;
    private final ArrayList<Enemy> enemies;
    private final JFrame mainFrame;
    private final ArrayList<Bullet> bullets;
    private Boolean pause;
    private Timer animationsTimer, moveEnemiesTimer, moveBulletsTimer,
        addEnemiesTimer, explosionsTimer, checkerboardColorsTimer;
    private final ArrayList<Explosion> explosions;
    private final World world;
    private PlayBase playBase;
    private NumberOfErrorsPanel errorsPanel;


    final static String MAIN_MENU = "Main Menu";
    final static String PLAY = "Play!";
    final static String HOW_TO_PLAY = "How to Play!";
    final static String IMMUNE_SYSTEM = "The Immune System!";
    final static String QUESTIONS = "Questions!";
    final static String CORRECT_PANELS = "Correct Panels";
    final static String INCORRECT_PANELS = "Incorrect Panels";
    final static String GAME_OVER = "Game Over";

    public ControllerPanel(final CardLayout _cardLayout,
                           final ArrayList<QuestionAnswer> _questionsAndAnswers,
                           final JFrame _mainFrame,
                           final World _world)
    {
        super(_cardLayout);
        cardLayout = _cardLayout;
        world = _world;
        questionsAndAnswers = _questionsAndAnswers;
        playah = world.player;
        enemies = world.enemies;
        mainFrame = _mainFrame;
        bullets = world.bullets;
        pause = world.pause;
        explosions = world.explosions;
        mainFrame.addKeyListener(this);

        final StartMenu startMenu = new StartMenu(this);
        playBase = new PlayBase(this, world);
        HowToPlayPanel howToPlayPanel = new HowToPlayPanel(this);
        ImmuneSystemPanel immuneSystemPanel = new ImmuneSystemPanel(this);
        questionPanel = new QuestionPanel(this, questionsAndAnswers, world);
        correctPanel = new CorrectAnswerPanel(this, questionsAndAnswers);
        incorrectPanel = new IncorrectAnswerPanel(this, questionsAndAnswers);
        GameOverPanel gameOverPanel = new GameOverPanel(this);
        errorsPanel = new NumberOfErrorsPanel(world);


        int delayAnimation = 250; //milliseconds
        ActionListener animatorActionListener = new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    updateAnimations();
                }
            };
        animationsTimer = new Timer(delayAnimation, animatorActionListener);

        int delayExplosion = 50; //milliseconds
        ActionListener explosionActionListener = new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    world.updateExplosions();
                    handleCompletedExplosions(world);
                    repaint();
                }
            };
        explosionsTimer = new Timer(delayExplosion, explosionActionListener);

        int delayAddEnemies = 250; //milliseconds
        ActionListener addEnemiesActionListener = new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    addEnemy(world);
                }
            };
        addEnemiesTimer = new Timer(delayAddEnemies, addEnemiesActionListener);

        int delayEnemies = 1500;
        ActionListener moveEnemyActionListener = new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    moveEnemies(world);
                    handleEnemiesOffScreen(world);
                    handleCollisions(world);
                }
            };
        moveEnemiesTimer = new Timer(delayEnemies, moveEnemyActionListener);

        int delayBullets = 250;
        ActionListener moveBulletActionListener = new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    moveBullets(world);
                    handleCollisions(world);
                }
            };
        moveBulletsTimer = new Timer(delayBullets, moveBulletActionListener);

        int delayCheckerboardColors = 250;
        ActionListener checkerboardColorsActionListener = new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    startMenu.switchColors();
                    startMenu.repaint();
                }
            };
        checkerboardColorsTimer = new Timer(delayCheckerboardColors,
                                            checkerboardColorsActionListener);
        checkerboardColorsTimer.start();

        add(startMenu, MAIN_MENU);
        add(playBase, PLAY);
        add(howToPlayPanel, HOW_TO_PLAY);
        add(immuneSystemPanel, IMMUNE_SYSTEM);
        add(questionPanel, QUESTIONS);
        add(correctPanel, CORRECT_PANELS);
        add(incorrectPanel, INCORRECT_PANELS);
        add(gameOverPanel, GAME_OVER);
    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e)
    {
        char key = e.getKeyChar();
        setFocusable(true);
        if (!pause)
            {
                if (key == 'a')
                    {
                        playah.moveLeft();
                    }
                if (key == 'd')
                    {
                        playah.moveRight();
                    }
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    {