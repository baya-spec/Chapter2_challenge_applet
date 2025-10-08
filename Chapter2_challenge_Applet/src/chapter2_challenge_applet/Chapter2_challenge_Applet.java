/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chapter2_challenge_applet;

/**
 *
 * @author Bahar
 */
import java.awt.color;
import java.awt.font;
import java.awt.Graphics;
public class Chapter2_challenge_Applet {

    
    // Animation constants
    private static final int ANIMATION_DELAY = 100;
    private static final int TEXT_START_POSITION = 0;
    private static final int FONT_SIZE = 24;
    
    // Animation state variables
    private Thread animationThread;
    private int textPositionX;
    private int appletWidth;
    private int appletHeight;
    private final String displayText;
    private boolean isRunning;
    
    /**
     * Default constructor initializes display text
     */
    public BouncingTextApplet() {
        this.displayText = "Java Applet";
        this.isRunning = false;
    }
    
    /**
     * Initializes the applet - sets size, background color, and initial values
     */
    @Override
    public void init() {
        // Set applet size and background color
        appletWidth = 400;
        appletHeight = 100;
        resize(appletWidth, appletHeight);
        setBackground(Color.BLACK);
        
        // Initialize text position
        textPositionX = TEXT_START_POSITION;
        
        System.out.println("Applet initialized: " + appletWidth + "x" + appletHeight);
    }
    
    /**
     * Starts the animation thread when applet becomes visible
     */
    @Override
    public void start() {
        if (animationThread == null) {
            animationThread = new Thread(this);
            isRunning = true;
            animationThread.start();
            System.out.println("Animation thread started");
        }
    }
    
    /**
     * Stops the animation thread when applet is no longer visible
     */
    @Override
    public void stop() {
        isRunning = false;
        if (animationThread != null) {
            animationThread = null;
            System.out.println("Animation thread stopped");
        }
    }
    
    /**
     * Main animation loop - runs in separate thread
     */
    @Override
    public void run() {
        System.out.println("Animation loop started");
        
        while (isRunning) {
            // Update text position
            updateTextPosition();
            
            // Request repaint
            repaint();
            
            // Control animation speed
            try {
                Thread.sleep(ANIMATION_DELAY);
            } catch (InterruptedException interruptedException) {
                System.out.println("Animation thread interrupted: " + interruptedException.getMessage());
                break;
            }
        }
        
        System.out.println("Animation loop ended");
    }
    
    /**
     * Updates the horizontal position of the text for bouncing effect
     */
    private void updateTextPosition() {
        textPositionX += 5;
        
        // Reset position when text moves beyond right edge
        if (textPositionX > appletWidth) {
            textPositionX = TEXT_START_POSITION;
        }
    }
    
    /**
     * Paints the applet surface - called automatically by AWT
     * @param graphics The Graphics object for drawing
     */
    @Override
    public void paint(Graphics graphics) {
        // Set text color and font
        graphics.setColor(Color.GREEN);
        graphics.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        
        // Calculate vertical center position
        int textPositionY = appletHeight / 2 + FONT_SIZE / 3;
        
        // Draw the bouncing text
        graphics.drawString(displayText, textPositionX, textPositionY);
        
        // Draw additional information
        graphics.setColor(Color.GRAY);
        graphics.setFont(new Font("Arial", Font.PLAIN, 10));
        graphics.drawString("Java Applet Demo - Deprecated Technology", 10, appletHeight - 10);
    }
    
    /**
     * Destroys applet resources when applet is permanently removed
     */
    @Override
    public void destroy() {
        System.out.println("Applet destroyed");
    }
}
   
