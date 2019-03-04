/** @author Dev Ojha */
/** 
RUN THIS FILE TO PLAY GAME. Copy text below into terminal. 

javac NBodyExtreme.java
java NBodyExtreme 157788000.0 25000.0 data/planets.txt

*/
import java.util.Arrays;

public class NBodyExtreme {
  //readRadius
  public static double readRadius(String fdir) {
    In in = new In(fdir);

		int numberofplanets = in.readInt();
		double radiusuniverse = in.readDouble();

    return radiusuniverse;
  }
  //readBodies
  public static Body[] readBodies(String fdir) {
    In in = new In(fdir);

    int numberofplanets = in.readInt();
    Body[] bodies = new Body[numberofplanets];
		double radiusuniverse = in.readDouble();

    for (int i = 0; i < numberofplanets; i++) {
  		double x_cord = in.readDouble();
  		double y_cord = in.readDouble();
  		double x_vel = in.readDouble();
      double y_vel = in.readDouble();
      double mass = in.readDouble();
      String name = in.readString();

      bodies[i] = new Body(x_cord, y_cord, x_vel, y_vel, mass, name);
    }

    return bodies;
  }
  /** main method
  move the UFO using the arrow keys */

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];

    double radius = readRadius(filename);
    Body[] bodies = readBodies(filename);

    StdDraw.setScale(-(radius), radius);
    String imageToDraw = "images/newgalaxy.jpg";
    //StdDraw.picture(0, 0, imageToDraw);

    StdDraw.enableDoubleBuffering();
    // for (int i = 0; i < bodies.length; i++) {
    //   bodies[i].draw();
    // }
    //animation elements


    double period = 0;


    while (period < T) {
      double[] xForces = new double[bodies.length];
      double[] yForces = new double[bodies.length];

      for (int i = 0; i < bodies.length; i++) {
        xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
      }

      StdDraw.clear();
      StdDraw.picture(0, 0, imageToDraw);

      for (int i = 0; i < bodies.length; i++) {
        for (int k = 0; k < (bodies.length - 1); k++) {
          bodies[k].update(dt, xForces[k], yForces[k]);
        }

        //keyboard input to move UFO
        if (StdDraw.hasNextKeyTyped()) {
          char lastkey = StdDraw.nextKeyTyped();
          if (lastkey == 'w' || lastkey == 'W') {
            bodies[bodies.length - 1].update(dt, 0, 295000);
            bodies[bodies.length - 1].xxVel = 0;
            bodies[bodies.length - 1].yyVel = 0;
          }
          else if (lastkey == 'd' || lastkey == 'D') {
            bodies[bodies.length - 1].update(dt, 295000, 0);
            bodies[bodies.length - 1].xxVel = 0;
            bodies[bodies.length - 1].yyVel = 0;
          }
          else if (lastkey == 's' || lastkey == 'S') {
            bodies[bodies.length - 1].update(dt, 0, -295000);
            bodies[bodies.length - 1].xxVel = 0;
            bodies[bodies.length - 1].yyVel = 0;
          }
          else if (lastkey == 'a' || lastkey == 'A') {
            bodies[bodies.length - 1].update(dt, -295000, 0);
            bodies[bodies.length - 1].xxVel = 0;
            bodies[bodies.length - 1].yyVel = 0;
          }
        }
          //prevent the UFO from flying off the screen
          for (int k = 0; k < (bodies.length - 1); k++) {
          if (((Math.abs(bodies[bodies.length - 1].xxPos - bodies[k].xxPos)) <= 1e-8 ||
            (Math.abs(bodies[bodies.length - 1].xxPos - bodies[k].xxPos)) >= 0)
                  && (Math.abs(bodies[bodies.length - 1].yyPos - bodies[k].yyPos)) <= 1e-8 ||
                  (Math.abs(bodies[bodies.length - 1].yyPos - bodies[k].yyPos)) >= 0) {
            System.out.println("COLLISION!!!! GAME OVER.");
            break;
        }
      }
        bodies[i].draw();
      }
      StdDraw.show();
			StdDraw.pause(10);

      period = period + dt;

    }
    StdOut.printf("%d\n", bodies.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bodies.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
}


  }
}
