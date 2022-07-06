public class NBody {
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
}