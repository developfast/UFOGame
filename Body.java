/** @author Dev Ojha */

  import static java.lang.Math.sqrt;

public class Body {
  //setting  the instance variables
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
  //declaring G - a constant - as a 'static final' variable
    private final static double G = 6.67e-11;
  //setting the constructors
  public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }
  public Body(Body b) {
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }
  //calcDistance
  public double calcDistance(Body p) {
    return sqrt(((xxPos - p.xxPos)*(xxPos - p.xxPos) +
            (yyPos - p.yyPos)*(yyPos - p.yyPos)));
  }
  //calcForceExertedBy
  public double calcForceExertedBy(Body p) {
    return (G * mass * p.mass)/(calcDistance(p) * calcDistance(p));
  }
  //calcForceExertedByX
  public double calcForceExertedByX(Body p) {
    return (calcForceExertedBy(p) * (p.xxPos - xxPos))/(calcDistance(p));
  }
  //calcForceExertedByY
  public double calcForceExertedByY(Body p) {
    return (calcForceExertedBy(p) * (p.yyPos - yyPos))/(calcDistance(p));
  }
  //calcNetForceExertedByX
  public double calcNetForceExertedByX(Body[] bodies) {
    int i; //array index
    double sum = 0; //sum of net forces
    for (i = 0; i < bodies.length; i++) {
    if (!this.equals(bodies[i])) {
      sum = sum + calcForceExertedByX(bodies[i]);
    }
  }
    return sum;
  }
  //calcNetForceExertedByY
  public double calcNetForceExertedByY(Body[] bodies) {
    int i; //array index
    double sum = 0; //sum of net forces
    for (i = 0; i < bodies.length; i++) {
    if (!this.equals(bodies[i])) {
      sum = sum + calcForceExertedByY(bodies[i]);
    }
  }
    return sum;
  }
  //update
  public void update(double dt, double fX, double fY) {
    double xAcc = fX / mass;
    double yAcc = fY / mass;
    xxVel = xxVel + (dt * xAcc);
    yyVel = yyVel + (dt * yAcc);
    xxPos = xxPos + (dt * xxVel);
    yyPos = yyPos + (dt * yyVel);
  }
  public void draw() {
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }
}
