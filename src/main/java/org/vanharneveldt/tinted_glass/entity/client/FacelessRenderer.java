package org.vanharneveldt.tinted_glass.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.entity.custom.FacelessEntity;

public class FacelessRenderer extends MobRenderer<FacelessEntity, FacelessRenderState, FacelessModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "textures/entity/faceless/faceless.png");  // Updated texture path

    public FacelessRenderer(EntityRendererProvider.Context context) {
        super(context, new FacelessModel(context.bakeLayer(FacelessModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(FacelessRenderState state) {
        return TEXTURE;
    }

    @Override
    public FacelessRenderState createRenderState() {
        return new FacelessRenderState();
    }

    @Override
    public void extractRenderState(FacelessEntity entity, FacelessRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        
        if (entity.walkAnimation.isMoving()) {
            state.walkAnimationState.startIfStopped(entity.tickCount);
        } else {
            state.walkAnimationState.stop();
        }
        
        state.idleAnimationState.startIfStopped(entity.tickCount);
    }
}