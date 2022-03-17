package mpjp.game.cuttings;

import mpjp.shared.MPJPException;

public interface CuttingFactory {

	public java.util.Set<java.lang.String> getAvaliableCuttings();
	public Cutting createCutting(java.lang.String name) throws MPJPException;
}
