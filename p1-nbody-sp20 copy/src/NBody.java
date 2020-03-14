	

/**
 * @author YOUR NAME THE STUDENT IN 201
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
	
		// TODO: read values at beginning of file to
		// find the radius
		int bodies = s.nextInt();
		double radius = s.nextDouble();
		s.close();
		
		// TODO: return radius read
		return radius;
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static CelestialBody[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
			
			// TODO: read # bodies, create array, ignore radius
			int nb = s.nextInt(); // # bodies to be read
			double radius = s.nextDouble();
			CelestialBody[] array = new CelestialBody[nb];
			for(int k=0; k < nb; k++) {
				
				// TODO: read data for each body
				// construct new body object and add to array
				CelestialBody body = new CelestialBody(s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(),
						s.nextDouble(), s.next());
				array[k] = body;
			}
			
			s.close();
			
			// TODO: return array of body objects read
			return array;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 10^9;
		double dt = 1000000;
		
		String fname= "data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		CelestialBody[] bodies = readBodies(fname);
		double radius = readRadius(fname);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
		//StdAudio.play("images/2001.wav");
	
		// run simulation until time up

		for(double t = 0.0; t < totalTime; t += dt) {
			
			// TODO: create double arrays xforces and yforces
			// to hold forces on each body
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			for (int k=0; k < bodies.length; k++){
				xForces[k] = bodies[k].calcNetForceExertedByX(bodies);
				yForces[k] = bodies[k].calcNetForceExertedByY(bodies);
			}
			// TODO: loop over all bodies, calculate netForcesX and Y
			// net forces and store in xforces and yforces
			
			// TODO: loop over all bodies and call update
			// with dt and corresponding xforces, yforces values
			for (int k=0; k < bodies.length; k++){
				bodies[k].update(dt,xForces[k],yForces[k]);
			}

			StdDraw.picture(0,0,"images/starfield.jpg");
			
			// TODO: loop over all bodies and call draw on each one
			for (CelestialBody body: bodies){
				body.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
		}
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
