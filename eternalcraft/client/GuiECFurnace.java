package eternalcraft.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import eternalcraft.common.machines.FurnaceType;
import eternalcraft.common.machines.tileentity.ContainerECFurnaceStone;
import eternalcraft.common.machines.tileentity.TileEntityECFurnace;

public class GuiECFurnace extends GuiContainer{
	public enum Resources{
		STONE(new ResourceLocation("eternalcraft", "textures/gui/furnaces/stonegui.png")),
		IRON(new ResourceLocation("eternalcraft", "textures/gui/furnaces/irongui.png"));
		public final ResourceLocation resource;
		private Resources(ResourceLocation loc){
			resource = loc;
		}
	}
	public enum Gui{
		STONE(FurnaceType.STONE, Resources.STONE),
		IRON(FurnaceType.IRON, Resources.IRON);
		private Resources resources;
		private FurnaceType furnaceType;
		private Gui(FurnaceType type, Resources res){
			resources = res;
			furnaceType = type;
		}
		public Container makeContainer(InventoryPlayer invPlayer, TileEntityECFurnace teFurnace){
			switch(furnaceType){
			case STONE: return new ContainerECFurnaceStone(invPlayer, teFurnace);
			case IRON: return new ContainerFurnace(invPlayer, teFurnace);
			}
			return null;
		}
	}
	private TileEntityECFurnace theFurnace;
	private Gui theType;
	public GuiECFurnace(Gui type, InventoryPlayer invPlayer, TileEntityECFurnace furnace) {
		super(type.makeContainer(invPlayer, furnace));
		theType = type;
		theFurnace = furnace;
	}

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(theType.resources.resource);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (theFurnace.isBurning())
        {
            i1 = theFurnace.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = theFurnace.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }

}
