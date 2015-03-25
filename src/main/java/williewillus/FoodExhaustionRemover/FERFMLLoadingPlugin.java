package williewillus.FoodExhaustionRemover;

import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

/**
 * Created by Vincent on 3/24/2015.
 */
public class FERFMLLoadingPlugin implements IFMLLoadingPlugin, IFMLCallHook {
	@Override
	public String[] getASMTransformerClass() {
		return new String[] {FERClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return FERDummyContainer.class.getName();
	}

	@Override
	public String getSetupClass() {
		return FERFMLLoadingPlugin.class.getName();
	}

	@Override
	public void injectData(Map<String, Object> data) {
		FERClassTransformer.init(((Boolean) data.get("runtimeDeobfuscationEnabled")));
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

	@Override
	public Void call() throws Exception {
		return null;
	}
}
