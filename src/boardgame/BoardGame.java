
package boardgame;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.Desktop;
import java.net.URI;

public class BoardGame extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    boolean gameStart = false;
    boolean presetStart = false;
    boolean gamePause = false;
    boolean allowPlace = false;
    int notFirstClick = 0; //bug prevention
    int notFirstClick2 = 0;
    boolean howToPlay;
    static boolean win;
    static boolean howToPlayVid = false;
    
    boolean aboutSelectStart = false;
    boolean aboutSelectHow = false;
    boolean aboutSelectBack = false;
    boolean aboutSelectReset = false;
    boolean aboutSelectHome = false;
    boolean aboutSelectTurtle = false;
    boolean aboutSelectHorse = false;
    boolean aboutSelectCustom = false;
    
    sound menuClick = null;
    sound music = null;
    Image pawnImage;
    Image turtle;
    Image horse;
    Image custom;
    
    public static void main(String[] args) throws Exception {
        
        BoardGame frame = new BoardGame();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
//        if(howToPlayVid){
//        Desktop d = Desktop.getDesktop();
//        d.browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
//        }
        
    }
    
    

    public BoardGame() {
            
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.BUTTON1 == e.getButton() ) {
                    int x = e.getX() - Window.getX(0);
                    int y = e.getY() - Window.getY(0);
                    
                    
                    
                    if(x > Window.getWidth2()/2-130 && x < Window.getWidth2()/2+130 &&
                       y > Window.getHeight2()/2+165 && y < Window.getHeight2()/2+240){
                            if(!gameStart  && !howToPlay && !presetStart) {
                            menuClick = new sound("button3.wav");
//                            gameStart = true;
                            presetStart = true;
                            allowPlace = true;
                            }
                            if(gamePause && !howToPlay) {
                            menuClick = new sound("button3.wav");
                            gamePause = false;
                            }
                        }
                    if(x > Window.getWidth2()/2-130 && x < Window.getWidth2()/2+130 &&
                       y > Window.getHeight2()/2+25 && y < Window.getHeight2()/2+105){
                            if(!gameStart && !howToPlay && !presetStart) {
                            menuClick = new sound("button3.wav");
                            howToPlay = true;
                            }
                            if(gamePause && !howToPlay) {
                            menuClick = new sound("button3.wav");
                            gamePause = false;
                            howToPlay = true;
                            allowPlace = false;
                            }
                        }   
                    if(x > Window.getWidth2()/2-130 && x < Window.getWidth2()/2+130 &&
                       y > Window.getHeight2()/2-105 && y < Window.getHeight2()/2-30){
                            if(gamePause && !howToPlay) {
                            menuClick = new sound("button3.wav");
                            gamePause = false;
                            allowPlace = true;
                            reset();
                            }
                    }
                     if(x > Window.getWidth()+25 && x < Window.getWidth()+125 &&
                        y > Window.getHeight2()-50 && y < Window.getHeight2()-20){
                            if(howToPlay) {
                            menuClick = new sound("buttonclickrelease.wav");
                            howToPlay = false;
                            allowPlace = true;
                            //System.out.println("test");
                            }
                        }
                     if(x > Window.getWidth2()/2-130 && x< Window.getWidth2()/2+130 &&
                        y > Window.getHeight2()/2-250 && y < Window.getHeight2()/2-170){
                         if(gamePause) {
                             menuClick = new sound("button3.wav");
                             gameStart = false;
                             Board.Reset();
                         }
                     }
                     
                     if(x > Window.getWidth2()-930 && x < Window.getWidth2()-725 &&
                        y > Window.getHeight2()/2+20&& y < Window.getHeight2()/2+220&& notFirstClick>=1) {
                         if(presetStart && !gameStart && !howToPlay) {
                              menuClick = new sound("button3.wav");
                              gameStart = true;
                              presetStart = false;
                              allowPlace = true;
                              Board.presets(true, false, false);
                            }
                     }
                     if(x > Window.getWidth2()-630 && x < Window.getWidth2()-425 &&
                     y > Window.getHeight2()/2+20&& y < Window.getHeight2()/2+220&& notFirstClick>=1) {
                         if(presetStart && !gameStart && !howToPlay) {
                              menuClick = new sound("button3.wav");
                              gameStart = true;
                              presetStart = false;
                              allowPlace = true;
                              Board.presets(false, true, false);
                            }
                     }
                     if(x > Window.getWidth2()-330 && x < Window.getWidth2()-125 &&
                        y > Window.getHeight2()/2+20&& y < Window.getHeight2()/2+220 && notFirstClick>=1 ) {
                            if(presetStart && !gameStart && !howToPlay) {
                              menuClick = new sound("button3.wav");
                              gameStart = true;
                              presetStart = false;
                              allowPlace = true;
                              Board.presets(false, false, true);
                            }
                         
                     }
                     if(!gameStart && allowPlace && presetStart)   
                        notFirstClick++;
                        
                     else if(gameStart && allowPlace)   
                        notFirstClick2++;
                    
                    if(gameStart && notFirstClick2>=2 && allowPlace && !gamePause) {
                       Board.CheckValidPawnPlacement(x, y); 
                       Board.MouseSelect(x,y);
                       }
                      
            
                }
                if (e.BUTTON3 == e.getButton()) {
                    int x = e.getX() - Window.getX(0);
                    int y = e.getY() - Window.getY(0);
                    if(gameStart && !gamePause && allowPlace){
                    Board.CheckValidWallPlacement(x, y);
                    
                    }
                    }

                repaint();
            }
        });
            

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
          int xHover = e.getX() - Window.getX(0);
          int yHover = e.getY() - Window.getY(0);
         
          
          if(xHover > Window.getWidth2()/2-130 && xHover < Window.getWidth2()/2+130 &&
            yHover > Window.getHeight2()/2+165 && yHover < Window.getHeight2()/2+240) {
            aboutSelectStart = true;  
          }
          else 
              aboutSelectStart = false;
          
          if(xHover > Window.getWidth2()/2-130 && xHover < Window.getWidth2()/2+130 &&
            yHover > Window.getHeight2()/2+25 && yHover < Window.getHeight2()/2+105) {
            aboutSelectHow = true;  
          }
          else 
              aboutSelectHow = false;
          
          if(xHover > Window.getWidth2()/2-130 && xHover < Window.getWidth2()/2+130 &&
             yHover > Window.getHeight2()/2-250 && yHover < Window.getHeight2()/2-170){
              aboutSelectHome = true;
          }
              else 
              aboutSelectHome = false;
          if(xHover > Window.getWidth2()/2-130 && xHover < Window.getWidth2()/2+130 &&
            yHover > Window.getHeight2()/2-105 && yHover < Window.getHeight2()/2-30) {
            aboutSelectReset = true;  
          }
          else 
              aboutSelectReset = false;
          if(xHover > Window.getWidth()+25 && xHover < Window.getWidth()+125 &&
             yHover > Window.getHeight2()-50 && yHover < Window.getHeight2()-20){
              
              aboutSelectBack = true;
          }
              else 
              aboutSelectBack = false;
          
          
        if(xHover > Window.getWidth2()-930 && xHover < Window.getWidth2()-725 &&
           yHover > Window.getHeight2()/2+20&& yHover < Window.getHeight2()/2+220) {
            aboutSelectTurtle = true;
            
        }
        else
            aboutSelectTurtle = false;
        
        if(xHover > Window.getWidth2()-630 && xHover < Window.getWidth2()-425 &&
           yHover > Window.getHeight2()/2+20&& yHover < Window.getHeight2()/2+220) {
            aboutSelectHorse = true;
            
        }
        else
            aboutSelectHorse = false;
        
        if(xHover > Window.getWidth2()-330 && xHover < Window.getWidth2()-125 &&
           yHover > Window.getHeight2()/2+20&& yHover < Window.getHeight2()/2+220) {
            aboutSelectCustom = true;
        }
        else
            aboutSelectCustom = false;
        

        

        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                
                if (e.VK_UP == e.getKeyCode() || e.VK_W == e.getKeyCode()) {  
                    Player.GetCurrentPlayer().moveUp();
                    Board.MovePawnPiece();
                    
                } else if (e.VK_DOWN == e.getKeyCode() || e.VK_S == e.getKeyCode()) {
                    Player.GetCurrentPlayer().moveDown();
                    Board.MovePawnPiece();
                } else if (e.VK_LEFT == e.getKeyCode() || e.VK_A == e.getKeyCode()) {
                    Player.GetCurrentPlayer().moveLeft();
                    Board.MovePawnPiece();
                } else if (e.VK_RIGHT == e.getKeyCode() || e.VK_D == e.getKeyCode()) { 
                    Player.GetCurrentPlayer().moveRight();
                    Board.MovePawnPiece();
                }
                
                else if (e.VK_ESCAPE == e.getKeyCode()) {
                      if(!gamePause && !howToPlay) {
                      gamePause = true;
                      notFirstClick = 0;
                      }
//                    gameStart = false;
//                    reset();
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld){
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
//        Color darkred = new Color(170,0,0);
        Color orange = new Color(255,130,0);
         
        g.setColor(Color.black);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }
        
        if(gameStart && !howToPlay) {
        Board.Draw(g);
        }
        //game start menu
        if(!gameStart && !howToPlay && !presetStart) {
        pawnImage = Toolkit.getDefaultToolkit().getImage("./redHelm.png");
        g.drawImage(pawnImage,Window.getWidth2()/2-100,Window.getHeight2()/2-35,null);
        pawnImage = Toolkit.getDefaultToolkit().getImage("./blueHelm.png");
        g.drawImage(pawnImage,Window.getWidth2()/2+165,Window.getHeight2()/2-35,null);
        g.setColor(orange);
        g.setFont(new Font("Cambria",Font.PLAIN,50));
        g.drawString("Wallgame",Window.getWidth2()/2-55, Window.getHeight2()/2);
        g.fillRect(Window.getWidth2()/2-75, Window.getHeight2()/2+235, 250, 75);
        g.fillRect(Window.getWidth2()/2-75, Window.getHeight2()/2+100, 250, 75);
        g.setColor(Color.white);
        g.setFont(new Font("Franklin Gothic",Font.PLAIN,50));
        g.drawString("START",Window.getWidth2()/2-30,Window.getHeight2()/2+290);
        g.setFont(new Font("Franklin Gothic",Font.PLAIN,30));
        g.drawString("HOW TO PLAY",Window.getWidth2()/2-50,Window.getHeight2()/2+150);
        }
        if(howToPlay) {
           Image howToPlay;
           howToPlay = Toolkit.getDefaultToolkit().getImage("./HowToPlay.png");
           g.drawImage(howToPlay,100,100,null);
           g.setColor(Color.red);
           g.fillRect(Window.getWidth()+75, Window.getHeight2()+25, 100, 25);
           g.setColor(Color.white);
           g.setColor(Color.white);
           g.setFont(new Font("Franklin Gothic",Font.PLAIN,20));
           g.drawString("BACK",Window.getWidth()+100,Window.getHeight2()+45);
           }
        //game pause menu

        if(gamePause && gameStart) {
        g.setColor(Color.white);
        g.fillRect(Window.getWidth2()/2-100, Window.getHeight2()/2-300, 300, 700);
        g.setColor(orange);
        g.setFont(new Font("Franklin Gothic",Font.PLAIN,50));
        g.drawString("PAUSED",Window.getWidth2()/2-55, Window.getHeight2()/2-225);
        g.fillRect(Window.getWidth2()/2-75, Window.getHeight2()/2+235, 250, 75);
        g.fillRect(Window.getWidth2()/2-75, Window.getHeight2()/2+100, 250, 75);
        g.fillRect(Window.getWidth2()/2-75, Window.getHeight2()/2-35, 250, 75);
        g.fillRect(Window.getWidth2()/2-75, Window.getHeight2()/2-170, 250, 75);
        g.setColor(Color.white);
        g.drawString("BACK",Window.getWidth2()/2-25,Window.getHeight2()/2+290);
        g.drawString("RESET",Window.getWidth2()/2-30,Window.getHeight2()/2+20);
        g.setFont(new Font("Franklin Gothic",Font.PLAIN,40));
        g.drawString("MAIN MENU",Window.getWidth2()/2-60,Window.getHeight2()/2-120);
        g.setFont(new Font("Franklin Gothic",Font.PLAIN,30));
        g.drawString("HOW TO PLAY",Window.getWidth2()/2-50,Window.getHeight2()/2+150);
        }
        //preset menu
        if(presetStart) {
        
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman",Font.PLAIN,50));
        g.drawString("PRESETS",Window.getWidth2()/2-55, Window.getHeight2()/4); 
        g.setColor(orange);
        turtle = Toolkit.getDefaultToolkit().getImage("./turtle.png");
        horse = Toolkit.getDefaultToolkit().getImage("./horse.png");
        custom = Toolkit.getDefaultToolkit().getImage("./custom.png");
        g.drawImage(turtle,Window.getWidth2()-870, Window.getHeight2()/2+100,null);
        g.drawImage(horse,Window.getWidth2()-570, Window.getHeight2()/2+100,null);
        g.drawImage(custom,Window.getWidth2()-270, Window.getHeight2()/2+100,null);
        g.setFont(new Font("Times New Roman",Font.PLAIN,30));
        g.drawString("Turtle",Window.getWidth2()-800, Window.getHeight2()/2+350); 
        g.drawString("Horse",Window.getWidth2()-500, Window.getHeight2()/2+350); 
        g.drawString("Custom",Window.getWidth2()-215, Window.getHeight2()/2+350); 
        g.drawString("(Recommended)",Window.getWidth2()-260, Window.getHeight2()/2+375); 
        }
        //game start menu outlines
        if(!gameStart && aboutSelectStart && !howToPlay && !presetStart) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth2()/2-75, Window.getHeight2()/2+235, 250, 75);
        }
        if(!gameStart && aboutSelectHow && !howToPlay && !presetStart) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth2()/2-75, Window.getHeight2()/2+100, 250, 75);
        }
        if(!gameStart && aboutSelectBack && howToPlay) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth()+75, Window.getHeight2()+25, 100, 25);
        }
        //preset menu outlines
//        g.fillRect(Window.getWidth2()-570, Window.getHeight2()/2+100, 200, 200);
        if(!gameStart && aboutSelectTurtle && !howToPlay && presetStart) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth2()-870, Window.getHeight2()/2+100, 200, 200);
        }
         if(!gameStart && aboutSelectHorse && !howToPlay && presetStart) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth2()-570, Window.getHeight2()/2+100, 200, 200);
        }
        if(!gameStart && aboutSelectCustom && !howToPlay && presetStart) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth2()-270, Window.getHeight2()/2+100, 200, 200);
        }
       
        
        /////game paused menu
        if(gamePause && gameStart &&aboutSelectHome && !howToPlay) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth2()/2-75, Window.getHeight2()/2-170, 250, 75);
        }
        if(gamePause && gameStart && aboutSelectReset && !howToPlay) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth2()/2-75, Window.getHeight2()/2-35, 250, 75);
        }
        if(gamePause && gameStart && aboutSelectStart && !howToPlay) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth2()/2-75, Window.getHeight2()/2+235, 250, 75);
        }
        if(gamePause && gameStart &&aboutSelectHow && !howToPlay) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth2()/2-75, Window.getHeight2()/2+100, 250, 75);
        }

        if(aboutSelectBack && howToPlay && gameStart) {
        g.setColor(Color.black);
        g.drawRect(Window.getWidth()+75, Window.getHeight2()+25, 100, 25);
        }
       
        
        gOld.drawImage(image, 0, 0, null);
    }

////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = .1;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
    
    
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        Player.Reset();
        Board.Reset();
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            
//            if (welcome.donePlaying && !welcome.stopPlaying)     
            
            
            
            reset();

        }

        
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
}

