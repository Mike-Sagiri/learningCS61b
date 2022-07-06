public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double Gconst = 6.67e-11;
    /** Constructors
     *
     * @param xP initial xxPos
     * @param yP initial yyPos
     * @param xV initial xxVel
     * @param yV initial yyVel
     * @param m  initial mass
     * @param img initial imgFileName
     */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }

    /** construct a Planet from
     * @param p the other Planet
     */
    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }
    /** Distance calculator
     * @param p the other planet
     */
    public double calcDistance(Planet p){
        double dx=xxPos-p.xxPos;
        double dy=yyPos-p.yyPos;
        return Math.sqrt(dx*dx+dy*dy);
    }

    /** Force calculator
     * @param p another planet
     * @return force between them
     */
    public double calcForceExertedBy(Planet p){
        double r=calcDistance(p);
        return Gconst*mass*p.mass/(r*r);
    }

    /** force in x and y
     */
    public double calcForceExertedByX(Planet p){
        double F=calcForceExertedBy(p);
        double r=calcDistance(p);
        return F*(p.xxPos-xxPos)/r;
    }
    public double calcForceExertedByY(Planet p){
        double F=calcForceExertedBy(p);
        double r=calcDistance(p);
        return F*(p.yyPos-yyPos)/r;
    }

    /** net force in x and y
     */
    public double calcNetForceExertedByX(Planet[] arr){
        double totalXForce=0;
        for (Planet planet : arr) {
            if (!this.equals(planet)) {
                totalXForce += calcForceExertedByX(planet);
            }
        }
        return totalXForce;
    }
    public double calcNetForceExertedByY(Planet[] arr){
        double totalYForce=0;
        for (Planet planet : arr) {
            if (!this.equals(planet)) {
                totalYForce += calcForceExertedByY(planet);
            }
        }
        return totalYForce;
    }

    /** Update the properties of the Planet.
     *
     * @param dt the period of time
     * @param fx the force in x
     * @param fy the force in y
     */
    public void update(double dt,double fx,double fy){
        xxVel=xxVel+dt*fx/mass;
        yyVel+=dt*fy/mass;
        xxPos+=xxVel*dt;
        yyPos+=yyVel*dt;
    }

    /** Draw itself
     */
    public void draw(){
        String file = "./images/"+imgFileName;
        StdDraw.picture(xxPos,yyPos,file);
    }
}
