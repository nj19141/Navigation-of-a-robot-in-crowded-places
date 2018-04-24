package robonavigation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.Scanner;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Simulation extends JPanel {
//    Timer t = new Timer(5,this);

    float robotPathX[];
    float robotPathY[];
    float humansPathX[][];
    float humansPathY[][];

    public Simulation(float[] robotPathX, float[] robotPathY, float[][] humansPathX, float[][] humansPathY) {        
        this.robotPathX = robotPathX;
        this.robotPathY = robotPathY;
        this.humansPathX = humansPathX;
        this.humansPathY = humansPathY;
    }

    private void drawPaths(Graphics graphicsObject) throws InterruptedException {
        Graphics2D graphicsObject2D = (Graphics2D) graphicsObject;
        this.setBackground(Color.black);
        graphicsObject2D.setStroke(new BasicStroke(4.0f));
        graphicsObject2D.setColor(Color.YELLOW);
        GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);

        path.moveTo(robotPathX[0], robotPathY[0]);
        for (int i = 1; i < robotPathX.length; i++) {
            path.lineTo(robotPathX[i], robotPathY[i]);
        }
        //path.curveTo(150, 150, 300, 300, 50, 250);
        //path.closePath();
        graphicsObject2D.draw(path);
        //g2d.drawLine(x,y,x+50,y+50);               
        //t.start();
        GeneralPath humanPath[] = new GeneralPath[200000];
        graphicsObject2D.setColor(Color.green);
        for (int i = 0; i < humansPathX.length; i++) {
            humanPath[i] = new GeneralPath(GeneralPath.WIND_NON_ZERO);
            humanPath[i].moveTo(humansPathX[i][0], humansPathY[i][0]);
            for (int j = 1; j < humansPathX[i].length; j++) {
                humanPath[i].lineTo(humansPathX[i][j], humansPathY[i][j]);
            }
            graphicsObject2D.draw(humanPath[i]);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawPaths(g);
        } catch (InterruptedException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //    @Override
//    public void actionPerformed(ActionEvent a)
//    {
//        x = x+10;
//        y = y+10;
//        repaint();
//    }
}

public class RoboNavigation {

    static robot rob;
    static human[] humans;
    static human goal;
    static float humanXPath[][];
    static float humanYPath[][];
    static int humanPathCounter = 0;

    public static void visualize(float[] finalXPath, float[] finalYPath) {
        System.out.println("Visualization Begins...");
        JFrame x = new JFrame("Robot and Human Paths");
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Simulation simulate = new Simulation(finalXPath, finalYPath, humanXPath, humanYPath);
        x.add(simulate);
        x.setSize(800, 800);
        x.setVisible(true);
    }

    public static void moveHumans() {
        int i = 0;
        Random rand = new Random();
        while (i < humans.length) {
            int direction = rand.nextInt(4) + 1;
            switch (direction) {
                case 1: {
                    humanXPath[i][humanPathCounter] = humans[i].getXcoord();
                    humanYPath[i][humanPathCounter] = humans[i].getYcoord();
                    humans[i].setYcoord(humans[i].getYcoord() + humans[i].getSpeed());
                    System.out.println("Humans Moved");
                    break;
                }
                case 2: {
                    humanXPath[i][humanPathCounter] = humans[i].getXcoord();
                    humanYPath[i][humanPathCounter] = humans[i].getYcoord();
                    humans[i].setYcoord(humans[i].getYcoord() - humans[i].getSpeed());
                    System.out.println("Humans Moved");
                    break;
                }
                case 3: {
                    humanXPath[i][humanPathCounter] = humans[i].getXcoord();
                    humanYPath[i][humanPathCounter] = humans[i].getYcoord();
                    humans[i].setXcoord(humans[i].getXcoord() + humans[i].getSpeed());
                    System.out.println("Humans Moved");
                    break;
                }
                case 4: {
                    humanXPath[i][humanPathCounter] = humans[i].getXcoord();
                    humanYPath[i][humanPathCounter] = humans[i].getYcoord();
                    humans[i].setXcoord(humans[i].getYcoord() - humans[i].getSpeed());
                    System.out.println("Humans Moved");
                    break;
                }
            }
            i++;
        }
        humanPathCounter++;
        System.out.println("HumansPathCounter= "+humanPathCounter);
    }

    public static void main(String[] args) {
        System.out.println("Enter the initial coordinates of the robot: ");
        Scanner sc = new Scanner(System.in);
        float robx = sc.nextInt();
        float roby = sc.nextInt();
        rob = new robot(robx, roby);
        float[] finalXPath = new float[200000];
        float[] finalYPath = new float[200000];
        int finalPathCounter = 0;
        System.out.println("Enter the number of humans you want the robot to avoid: ");
        int noOfHumans = sc.nextInt();
        humans = new human[noOfHumans];
        humanXPath = new float[noOfHumans][200000];
        humanYPath = new float[noOfHumans][200000];
        float[] forceArray = new float[200000];
        int i = 0;
        while (i < noOfHumans) {
            System.out.println("Please enter the initial co-ordinates of human " + (i + 1) + " : ");
            float xcoord = sc.nextInt();
            float ycoord = sc.nextInt();
            humans[i] = new human(xcoord, ycoord, 1, 60, 1, 2, 1);
            i++;
        }
        System.out.println("Enter the initial co-ordinates of the goal: ");
        float goalx = sc.nextInt();
        float goaly = sc.nextInt();
        human goal = new human(goalx, goaly, 0, 0, 10, 2, 1);

        while (!(((rob.getXcoord()<= goal.getXcoord()+1)&&(rob.getXcoord()>= goal.getXcoord()-1)) && ((rob.getYcoord()<= goal.getYcoord()+1)&&(rob.getYcoord()>= goal.getYcoord()-1)))) {
            i = 0;
            float sumx = 0;
            float sumy = 0;
            while (i < noOfHumans) {
                float distance = rob.findDistance(humans[i].getXcoord(), humans[i].getYcoord());
                forceArray[i] = rob.force(humans[i], distance);
                sumx = sumx + (humans[i].getXcoord() - rob.getXcoord())*forceArray[i];
                sumy = sumy + (humans[i].getYcoord() - rob.getYcoord())*forceArray[i];
                i++;
            }
            float distanceFromGoal = rob.findDistance(goal.getXcoord(), goal.getYcoord());
            forceArray[i] = rob.force(goal, distanceFromGoal);
            sumx = sumx + goal.getXcoord()-rob.getXcoord();
            sumy = sumy + goal.getYcoord()-rob.getYcoord();
            float magnitudeOfDistance = rob.distanceToMove(forceArray);
            float newx = (float) (sumx * magnitudeOfDistance / (sqrt(pow(sumx, 2) + pow(sumy, 2))));
            float newy = (float) (sumy * magnitudeOfDistance / (sqrt(pow(sumx, 2) + pow(sumy, 2))));
            float moveToXCoord = rob.getXcoord() + newx;
            float moveToYCoord = rob.getYcoord() + newy;
            System.out.println("Xcoord "+moveToXCoord);
            System.out.println("Ycoord "+moveToYCoord);
            finalXPath[finalPathCounter] = moveToXCoord;
            finalYPath[finalPathCounter] = moveToYCoord;
            rob.setXcoord(moveToXCoord);
            rob.setYcoord(moveToYCoord);
            finalPathCounter++;            
            System.out.println("FinalPathCounter= "+finalPathCounter);
            moveHumans();
            if(finalPathCounter==200000)
            break;              
        }        
        visualize(finalXPath, finalYPath);
    }
}