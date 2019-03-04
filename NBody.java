/** @author Dev Ojha */
import java.util.Arrays;

public class NBody {
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
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];

    double radius = readRadius(filename);
    Body[] bodies = readBodies(filename);

    StdDraw.setScale(-(radius), radius);
    String imageToDraw = "images/starfield.jpg";
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
      bodies[i].update(dt, xForces[i], yForces[i]);
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
