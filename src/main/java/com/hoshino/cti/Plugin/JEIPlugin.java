package com.hoshino.cti.Plugin;

import com.hoshino.cti.cti;
import com.hoshino.cti.integration.AtmosphereCondenseRecipeCategory;
import com.hoshino.cti.integration.AtmosphereExtractRecipeCategory;
import com.hoshino.cti.recipe.AtmosphereCondensorRecipe;
import com.hoshino.cti.recipe.AtmosphereExtractorRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static RecipeType<AtmosphereExtractorRecipe> ATMOSPHERE_EXTRACT = new RecipeType<>(AtmosphereExtractRecipeCategory.UID, AtmosphereExtractorRecipe.class);
    public static RecipeType<AtmosphereCondensorRecipe> ATMOSPHERE_CONDENSE = new RecipeType<>(AtmosphereCondenseRecipeCategory.UID, AtmosphereCondensorRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(cti.MOD_ID,"jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AtmosphereExtractRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new AtmosphereCondenseRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<AtmosphereExtractorRecipe> recipesExtract = rm.getAllRecipesFor(AtmosphereExtractorRecipe.Type.INSTANCE);
        List<AtmosphereCondensorRecipe> recipesCondense = rm.getAllRecipesFor(AtmosphereCondensorRecipe.Type.INSTANCE);

        registration.addRecipes(ATMOSPHERE_EXTRACT, recipesExtract);
        registration.addRecipes(ATMOSPHERE_CONDENSE, recipesCondense);
    }
}