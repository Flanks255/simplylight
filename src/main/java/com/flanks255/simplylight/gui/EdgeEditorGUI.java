package com.flanks255.simplylight.gui;

import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.network.UpdateEdgeLightPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nonnull;

public class EdgeEditorGUI extends Screen {
    private static final ResourceLocation BG = SimplyLight.SLRes("textures/gui/small_editor.png");

    private final BlockPos target;
    private byte state;
    private int guiLeft;
    private int guiTop;
    private final int xSize = 128;
    private final int ySize = 70;
    private SwitchButton north;
    private SwitchButton east;
    private SwitchButton south;
    private SwitchButton west;

    public EdgeEditorGUI(BlockPos target, byte initialState) {
        super(Component.literal("Edge Editor"));
        this.target = target;
        this.state = initialState;
    }

    @Override
    protected void init() {
        super.init();

        guiLeft = (int) ((width / 2.0f) - (xSize * 1.5f));
        guiTop = (height - ySize) / 2;

        north = addRenderableWidget(new SwitchButton(guiLeft + 4, guiTop + 3, Component.translatable("simplylight.gui.north"), ((this.state & 1) != 0) , this::button));
        east = addRenderableWidget(new SwitchButton(guiLeft + 4, guiTop + 3 + 16, Component.translatable("simplylight.gui.east"), ((this.state & 2) != 0) , this::button));
        south = addRenderableWidget(new SwitchButton(guiLeft + 4, guiTop + 3 + 32, Component.translatable("simplylight.gui.south"), ((this.state & 4) != 0) , this::button));
        west = addRenderableWidget(new SwitchButton(guiLeft + 4, guiTop + 3 + 48, Component.translatable("simplylight.gui.west"), ((this.state & 8) != 0) , this::button));

        addRenderableWidget(new Button.Builder(Component.translatable("simplylight.gui.exit"), $ -> onClose()).pos(guiLeft + 92, guiTop + 50).size(32,16).build());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        Player player = Minecraft.getInstance().player;
        if (player == null)
            return;
        Component facing = Component.translatable("simplylight.gui.facing");
        Component direction = getDirectionText(getNearestHorizontal(player));
        int x = (guiLeft + xSize - 4) - Math.max(font.width(facing), font.width(direction));
        guiGraphics.drawString(font, facing, x, guiTop + 8, 0x404040, false);
        guiGraphics.drawString(font, direction, x, guiTop + 18, 0x404040, false);
    }

    private Direction getNearestHorizontal(Player player) {
        for (Direction direction : Direction.orderedByNearest(player)) {
            if (direction.getAxis().isHorizontal())
                return direction;
        }
        return Direction.NORTH;
    }

    private Component getDirectionText(Direction direction) {
        return switch(direction) {
            case EAST -> Component.translatable("simplylight.gui.east");
            case SOUTH -> Component.translatable("simplylight.gui.south");
            case WEST -> Component.translatable("simplylight.gui.west");
            default -> Component.translatable("simplylight.gui.north");
        };
    }

    private void button(Button button) {
        if (button instanceof SwitchButton btn)
            btn.state = !btn.state;

        this.state = (byte) ((north.state?1:0) | (east.state?2:0) | (south.state?4:0) | (west.state?8:0));
        PacketDistributor.sendToServer(new UpdateEdgeLightPacket(target, state));
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blit(BG, guiLeft, guiTop, 0, 0, 128, 70, 128, 70);
    }

    class SwitchButton extends Button {
        private static final ResourceLocation SWITCH = SimplyLight.SLRes("textures/gui/switch.png");
        public SwitchButton(int x, int y, Component text, boolean initial, OnPress pressable) {
            super(x,y,32,16, text, pressable, Button.DEFAULT_NARRATION);
            this.textKey = text;
            this.state = initial;
        }
        public boolean state;
        private final Component textKey;

        @Override
        public void renderWidget(@Nonnull GuiGraphics gg, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
            gg.blit(SWITCH, this.getX(), this.getY(), this.width, this.height,0,this.state?16:0,32,16, 32 ,32);
            gg.drawString(font, textKey, this.getX() + 34, this.getY() + 4, 0x404040, false);
        }
    }
}
