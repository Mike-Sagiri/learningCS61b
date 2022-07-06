public class NBody {
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename=args[2];
        double uni_rad = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-uni_rad,uni_rad);
        StdDraw.picture(0,0,"./images/starfield.jpg");
        StdDraw.show();
        for(Planet i:planets){
            i.draw();
        }
        StdDraw.enableDoubleBuffering();
        double time=0;

        for(;time<=T;time+=dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i=0;i< planets.length;i++){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0;i< planets.length;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.setScale(-uni_rad,uni_rad);
            StdDraw.picture(0,0,"./images/starfield.jpg");
            for(Planet i:planets){
                i.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", uni_rad);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }
    }
    /** Read the radius of the universe from
     *
     * @param filename the filename
     * @return the radium
     */
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }

    /** readPlanets from filename
     *
     * @param filename the source file
     * @return an array of Planet
     */
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int num = in.readInt();
        in.readDouble();
        Planet[] ans = new Planet[num];
        for(int i=0;i<num;i++){
            ans[i]=new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(), in.readString());
        }
        return ans;
    }
}