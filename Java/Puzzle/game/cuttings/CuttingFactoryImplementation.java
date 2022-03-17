package mpjp.game.cuttings;

import java.util.HashSet;

import mpjp.shared.MPJPException;

public class CuttingFactoryImplementation extends java.lang.Object
implements CuttingFactory {

	public CuttingFactoryImplementation() {};
	
	public Cutting createCutting(java.lang.String name) throws MPJPException {
		if (getAvaliableCuttings().contains(name)) {
			if(name.equals("Straight")) {return new StraightCutting();}
			else if(name.equals("Triangular")) {return new TriangularCutting();}
			else if(name.equals("Round")) {return new RoundCutting();}
			else if(name.equals("Standard")) {return new StandardCutting();}
		} else {
			throw new MPJPException();
		}
		return null;
	};
	
	public java.util.Set<java.lang.String> getAvaliableCuttings() {
		java.util.Set<java.lang.String> cuttings = new HashSet<String>();
		cuttings.add("Straight");
		cuttings.add("Triangular");
		//cuttings.add("Round");
		//cuttings.add("Standard");
		return cuttings;
	}
}
