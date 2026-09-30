package drx.arrowmystery.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.world.gen.decorator.CountHeightmapDecorator;
import net.minecraft.world.gen.decorator.CountExtraHeightmapDecorator;
import net.minecraft.world.gen.ChunkRandom;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//@Mixin(Entity.class) // ARROW GARBAGE
@Mixin(CountHeightmapDecorator.class) // FOR USE WITH CRIMSON/WARPED HUGE FUNGI GEN
//@Mixin(CountExtraHeightmapDecorator.class) // FOR USE WITH FOREST TREE GEN
public abstract class ExampleMixin {
// TODO: FOR USE WITH CRIMSON/WARPED HUGE FUNGI GEN - MIXIN WITH CountHeightmapDecorator
	@Inject(at = @At("HEAD"), method = "getPositions", cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
	public void init(WorldAccess worldAccess, ChunkGenerator chunkGenerator, Random random, CountDecoratorConfig countDecoratorConfig, BlockPos blockPos, CallbackInfoReturnable<Stream<BlockPos>> cir) {
		ChunkRandom chunkRandom = (ChunkRandom)(Object)random;
		AtomicInteger x = new AtomicInteger(0);
		cir.setReturnValue(IntStream.range(0, countDecoratorConfig.count).mapToObj((i) -> {
			chunkRandom.setPopulationSeed(x.get(), blockPos.getX(), blockPos.getZ());
			x.incrementAndGet();
			int j = chunkRandom.nextInt(16) + blockPos.getX();
			int k = chunkRandom.nextInt(16) + blockPos.getZ();
			int l = worldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, j, k);
			return new BlockPos(j, l, k);
		}));
	}

// TODO: FOR USE WITH FOREST TREE GEN - MIXIN WITH CountExtraHeightmapDecorator
//	@Inject(at = @At("HEAD"), method = "getPositions", cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
//	public void init(WorldAccess worldAccess, ChunkGenerator chunkGenerator, Random random, CountExtraChanceDecoratorConfig countExtraChanceDecoratorConfig, BlockPos blockPos, CallbackInfoReturnable<Stream<BlockPos>> cir) {
//		int i = countExtraChanceDecoratorConfig.count;
//		if (random.nextFloat() < countExtraChanceDecoratorConfig.extraChance) {
//			i += countExtraChanceDecoratorConfig.extraCount;
//		}
//
//		ChunkRandom chunkRandom = (ChunkRandom)(Object)random;
//		AtomicInteger x = new AtomicInteger(0);
//		cir.setReturnValue(IntStream.range(0, i).mapToObj((ix) -> {
//			chunkRandom.setPopulationSeed(x.get(), blockPos.getX(), blockPos.getZ());
//			x.incrementAndGet();
//			int j = random.nextInt(16) + blockPos.getX();
//			int k = random.nextInt(16) + blockPos.getZ();
//			int l = worldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, j, k);
//			return new BlockPos(j, l, k);
//		}));
//	}

//	@Inject(at = @At("HEAD"), method = "tick")
//	private void init(CallbackInfo info) {
//		// this mixin should only affect arrow entities
//		PersistentProjectileEntity thisObject = (PersistentProjectileEntity)(Object)this;
//		if (!(thisObject instanceof ArrowEntity)) return;
//
//		if (thisObject.removed) ArrowMystery.LOGGER.info("LOL");
//		ArrowMystery.LOGGER.info(thisObject.getPos());
//	}

//	@Inject(at = @At("HEAD"), method = "tick")
//	private void init(CallbackInfo info) {
////		// this mixin should only affect arrow entities
//		Entity thisObject = (Entity)(Object)this;
//		if (!(thisObject instanceof ArrowEntity)) return;
////
//		ArrowMystery.LOGGER.info(thisObject.getPos());
//	}

//	@Inject(at = @At("HEAD"), method = "tickMovement")
//	private void init(CallbackInfo info) {
//		return;
//		ArrowMystery.LOGGER.info("LOL");
//		ArrowMystery.LOGGER.info(thisObject.getPos());
//	}

//	@Inject(method = "onEntityHit", at = @At(value = "INVOKE", target = "La/b/c/PersistentProjectileEntity;remove()V"))
////	@Inject(at = @At("HEAD"), method = "onEntityHit")
//	private void findTheIssue(CallbackInfo info) {
//		// this mixin should only affect arrow entities
//		PersistentProjectileEntity thisObject = (PersistentProjectileEntity)(Object)this;
//		if (!(thisObject instanceof ArrowEntity)) return;
//
//		if (thisObject.removed) ArrowMystery.LOGGER.info("LOL");
//		ArrowMystery.LOGGER.info(thisObject.getPos());
//	}
}