package com.flanks255.simplylight.network;

import com.flanks255.simplylight.gui.EdgeEditorGUI;
import net.minecraft.client.Minecraft;

public class ClientPacketStuff {
    public static void OpenEdgeEditorPacket(OpenEdgeEditorPacket message) {
        Minecraft.getInstance().setScreen(new EdgeEditorGUI(message.target(), message.initialState()));
    }
}
