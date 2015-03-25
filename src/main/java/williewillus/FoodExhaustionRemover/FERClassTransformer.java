package williewillus.FoodExhaustionRemover;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;


/**
 * Created by Vincent on 3/24/2015.
 */
public class FERClassTransformer implements IClassTransformer {

	public static FERClassTransformer INSTANCE;
	private static String targetClassName;
	private static String targetMethodName;
	private static String targetMethodDesc;
	private static String innerTargetMethodName;
	private static String innerTargetMethodDesc = "(F)V";


	public static void init(Boolean isObf) {
		if (INSTANCE == null) {
			INSTANCE = new FERClassTransformer();
		}
		if (isObf) {
			targetClassName = "zr";
			targetMethodName = "a";
			targetMethodDesc = "(Lyz;)V";
			innerTargetMethodName = "a";
		} else {
			targetClassName = "net/minecraft/util/FoodStats";
			targetMethodName = "onUpdate";
			targetMethodDesc = "(Lnet/minecraft/entity/player/EntityPlayer;)V";
			innerTargetMethodName = "addExhaustion";
		}
	}

	@Override
	public byte[] transform(String s, String s1, byte[] bytes) {
		ClassNode classNode = new ClassNode();
		ClassReader classReader;
		try {
			classReader = new ClassReader(bytes);
		} catch (NullPointerException e) {
			return bytes;
		}
		classReader.accept(classNode, 0);

		if (classNode.name.equals(targetClassName)) {
			System.out.println("Found target class");
			for (MethodNode m : classNode.methods) {
				if (m.name.equals(targetMethodName) && m.desc.equals(targetMethodDesc)) {
					System.out.println("Found target method");
					InsnList instructions = m.instructions;
					AbstractInsnNode currentInsn;
					for (int i = 0; i < instructions.size(); i++) {
						currentInsn = instructions.get(i);
						if (currentInsn instanceof MethodInsnNode) {
							MethodInsnNode invoke = ((MethodInsnNode) currentInsn);
							if (invoke.name.equals(innerTargetMethodName) && invoke.desc.equals(innerTargetMethodDesc)) {
								instructions.remove(invoke.getPrevious().getPrevious());
								instructions.remove(invoke.getPrevious());
								instructions.remove(invoke);
							}
						}
					}
				}
			}
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			classNode.accept(writer);
			return writer.toByteArray();
		}

		return bytes;
	}
}
