package drx.arrowmystery.mixin;

import drx.arrowmystery.ArrowMystery;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.HugeFungusFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(HugeFungusFeature.class)
public class HugeFungusMixin {
    @Inject(at = @At("HEAD"), method = "generate", cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    public void init(
        ServerWorldAccess serverWorldAccess,
         StructureAccessor structureAccessor,
         ChunkGenerator chunkGenerator,
         Random random,
         BlockPos blockPos,
         HugeFungusFeatureConfig hugeFungusFeatureConfig,
         CallbackInfoReturnable<Boolean> cir) {
        ChunkRandom chunkRandom = (ChunkRandom)(Object)random;
        chunkRandom.setPopulationSeed(0, blockPos.getX(), blockPos.getZ());
    }
}
