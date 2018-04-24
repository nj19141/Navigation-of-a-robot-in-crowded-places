# Navigation-of-a-robot-in-crowded-places
In the output result 1, yellow represents the path of the robot. Greeen represents the path of humans.
The output may not be very clear because the movement is very precise(1 pixel at a time).
The end point of every path is connected to the origin (top-left corner) so that it is easy to figure out the endpoints.
Dynamic output was not possible so after calculating all points, they were plotted together.
The output image attached is for the following input:
Initial coordinates of robot:(400,400), No. of humans: 3, Initial co-ordinates of Human1: (150,175), Initial co-ordinates
of Human2: (600,370), Initial co-ordinates of Human 3: (750,700), Initial co-ordinates of the robot:(10,750).
Every movement is so less that it took 2272 iterations to reach the goal. But if the goal has a better attraction force, it will take less.
In result 2, with the same coordinates and increased attraction force of the goal, it took only 1120 iterations.
