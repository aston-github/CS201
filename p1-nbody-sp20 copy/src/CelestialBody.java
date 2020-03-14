

/**
 * Celestial Body class for NBody
 * @author ASTON YONG
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		// TODO: complete constructor
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		// TODO: complete constructor
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}

	/**
	 *
	 * @return object x position
	 */
	public double getX() {
		// TODO: complete method
		return myXPos;
	}

	/**
	 *
	 * @return object y position
	 */
	public double getY() {
		// TODO: complete method
		return myYPos;
	}

	/**
	 *
	 * @return value of x-velocity
	 */
	public double getXVel() {
		// TODO: complete method
		return myXVel;
	}

	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method
		return myYVel;
	}

	/**
	 *
	 * @return value of object mass
	 */
	public double getMass() {
		// TODO: complete method
		return myMass;
	}

	/**
	 *
	 * @return value of object file name
	 */
	public String getName() {
		// TODO: complete method
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		// TODO: complete method
		double dx = myXPos - b.getX();
		double dy = myYPos - b.getY();
		double rSquare = (dx*dx)+(dy*dy);
		double ret = Math.sqrt(rSquare);
		return ret;
	}

	/**
	 *
	 * @return force exerted by object b on this object
	 */
	public double calcForceExertedBy(CelestialBody b) {
		// TODO: complete method
		double G = 6.67*1e-11;
		double r = calcDistance(b);
		double m1 = myMass;
		double m2 = b.getMass();
		double F = G * m1 * m2 / (r*r);
		return F;
	}

	/**
	 *
	 * @return force exerted by object b on this object in x direction
	 */
	public double calcForceExertedByX(CelestialBody b) {
		// TODO: complete method
		double F = calcForceExertedBy(b);
		double r = calcDistance(b);
		double dx = b.getX() - myXPos;
		double Fx = F*dx/r;
		return Fx;
	}

	/**
	 *
	 * @return force exerted by object b on this object in y direction
	 */
	public double calcForceExertedByY(CelestialBody b) {
		// TODO: complete method
		double F = calcForceExertedBy(b);
		double r = calcDistance(b);
		double dy = b.getY() - myYPos;
		double Fy = F*dy/r;
		return Fy;
	}

	/**
	 *
	 * @return force exerted by all objects on this object in x direction
	 */
	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		// TODO: complete method
		double sum = 0;
		for (CelestialBody b : bodies){
			if (! b.equals(this)){
				sum += calcForceExertedByX(b);
			}
		}
		return sum;
	}

	/**
	 *
	 * @return force exerted by all objects on this object in y direction
	 */
	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		// TODO: complete method
		double sum = 0;
		for (CelestialBody b : bodies){
			if (! b.equals(this)){
				sum += calcForceExertedByY(b);
			}
		}
		return sum;
	}

	/**
	 *
	 * updates object variables with new values
	 */
	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nvx = myXVel + deltaT * ax;
		double nvy = myYVel + deltaT * ay;
		double nx = myXPos + deltaT * nvx;
		double ny = myYPos + deltaT * nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}

	/**
	 *
	 * draws the CelestialBody object
	 */
	public void draw() {
		// TODO: complete method
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
