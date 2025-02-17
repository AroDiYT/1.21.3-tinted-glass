package org.vanharneveldt.tinted_glass.entity.client;

// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import org.vanharneveldt.tinted_glass.TintedGlass;
import org.vanharneveldt.tinted_glass.entity.animation.FacelessAnimations;
import org.vanharneveldt.tinted_glass.entity.custom.FacelessEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.neoforged.neoforge.client.renderstate.BaseRenderState;

public class FacelessModel extends EntityModel<FacelessRenderState> {
    private static final float WALK_ANIMATION_SPEED_MAX = 1.0F;
    private static final float WALK_ANIMATION_SCALE = 1.0F;
    
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TintedGlass.MOD_ID, "faceless"), "main");
	private final ModelPart Rightleg;
	private final ModelPart LeftLeg;
	private final ModelPart Torso;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart Head;
	public final AnimationState walkAnimation = new AnimationState();

	public FacelessModel(ModelPart root) {
		super(root);
		this.Rightleg = root.getChild("Rightleg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.Torso = root.getChild("Torso");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Rightleg = partdefinition.addOrReplaceChild("Rightleg", CubeListBuilder.create().texOffs(38, 0).addBox(-1.0F, -3.0F, -3.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 15.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(38, 16).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 15.0F, -1.0F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 30).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 22.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 2.0F, -1.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(24, 14).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 22.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 2.0F, -1.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(FacelessRenderState state) {
		super.setupAnim(state);
		
		// Reset all parts
        this.Head.resetPose();
        this.Torso.resetPose();
        this.LeftArm.resetPose();
        this.RightArm.resetPose();
        this.LeftLeg.resetPose();
        this.Rightleg.resetPose();

        // Apply animations
		if (state.walkAnimationState.isStarted()) {  // Changed from hasStarted() to isStarted()
            animateWalk(FacelessAnimations.ANIM_FACELESS_WALK, 
                       state.walkAnimationPos, 
                       state.walkAnimationSpeed, 
                       WALK_ANIMATION_SPEED_MAX, 
                       WALK_ANIMATION_SCALE);
        }
        
        animate(state.idleAnimationState, FacelessAnimations.IDLE, state.ageInTicks);
	}
}