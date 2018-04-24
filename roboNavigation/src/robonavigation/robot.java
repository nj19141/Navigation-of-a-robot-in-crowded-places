package robonavigation;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class robot {

    public robot(float initxcoord, float initycord) {
        this.xcoord = initxcoord;
        this.ycoord = initycord;
    }
    float xcoord;

    public float getXcoord() {
        return xcoord;
    }

    public void setXcoord(float xcoord) {
        this.xcoord = xcoord;
    }

    public float getYcoord() {
        return ycoord;
    }

    public void setYcoord(float ycoord) {
        this.ycoord = ycoord;
    }
    float ycoord;
    
    public float findDistance(float hxcoord, float hycoord)            
    {
        return(float) (sqrt(pow(hxcoord-xcoord,2)+pow(hycoord-ycoord,2)));
    }

    public float force(human h, float distance) {
        float attraction = h.getC2()/(float)(pow(distance,h.getDelta2()));
        float repulsion = h.getC1()/(float)(pow(distance,h.getDelta1()));
        float totalforce = attraction - repulsion;
        System.out.println("Totalforce= "+totalforce);
        return totalforce;
    }
    
    public float distanceToMove(float[] forceArray)
    {
        int i=0;
        double sum = 0;
        while(true)
        {
            if(forceArray[i]!=0){
            sum = sum + forceArray[i];
            }
            else
            {
                break;
            }
            i++;
        }
        System.out.println("Magnitude = "+sqrt(sum));
        return (float) sqrt(sum);
    }
}
