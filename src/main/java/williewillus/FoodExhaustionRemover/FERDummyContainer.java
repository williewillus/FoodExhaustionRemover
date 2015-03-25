package williewillus.FoodExhaustionRemover;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

import java.util.Arrays;

/**
 * Created by Vincent on 3/24/2015.
 */
public class FERDummyContainer extends DummyModContainer {
	public FERDummyContainer() {
		super(new ModMetadata());
		ModMetadata myMeta = super.getMetadata();
		myMeta.authorList = Arrays.asList("williewillus");
		myMeta.description = "Removes the exhaustion applied when regenerating, a 1.6 change.";
		myMeta.modId = "FoodExhaustionRemover";
		myMeta.version = "@VERSION@";
		myMeta.name = "FoodExhaustionRemover";
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}
}
