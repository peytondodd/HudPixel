package com.palechip.hudpixelmod.extended.cooldowndisplay;

import com.palechip.hudpixelmod.detectors.GameDetector;
import com.palechip.hudpixelmod.extended.HudPixelExtendedEventHandler;
import com.palechip.hudpixelmod.extended.configuration.Config;
import com.palechip.hudpixelmod.extended.util.IEventHandler;
import com.palechip.hudpixelmod.util.GameType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.util.ArrayList;

import static com.palechip.hudpixelmod.extended.cooldowndisplay.CooldownManagerFactory.setCooldownDisplay;

public class CooldownDisplayManager implements IEventHandler{

    static ArrayList<CooldownDisplayModule> cdModules = new ArrayList<CooldownDisplayModule>();
    private static float xStart = Config.xOffsetCooldownDisplay;
    private static float yStart = Config.yOffsetCooldownDisplay;
    private static CooldownDisplayManager instance;

    public static CooldownDisplayManager getInstance(){
        if(instance == null) instance = new CooldownDisplayManager();
        return instance;
    }

    private CooldownDisplayManager(){
        HudPixelExtendedEventHandler.registerIEvent(this);
    }


    int count = 0;
    @Override
    public void onClientTick() {
        count++;
        if(count > 40){
            count = 0;
            cdModules = setCooldownDisplay(GameType.getTypeByID(GameDetector.currentGame.getConfiguration().getModID()));
        }
        if(cdModules.isEmpty()) return;
        for(CooldownDisplayModule cdM : cdModules)
            cdM.onClientTick();
    }

    @Override
    public void onChatReceived(ClientChatReceivedEvent e) throws Throwable {

    }

    @Override
    public void onRender() {

        if(cdModules.isEmpty() || Config.isHideCooldownDisplay) return;

        Minecraft mc = Minecraft.getMinecraft();
        int scale;
        if(mc.gameSettings.guiScale == 0){
            ScaledResolution res = new ScaledResolution( mc);
            scale = res.getScaleFactor();
        } else {
            scale = mc.gameSettings.guiScale;
        }

        float xCenter = (Minecraft.getMinecraft().displayWidth / 2 / scale) - ((cdModules.size()/2) * 25) + 4;
        float yCenter = Minecraft.getMinecraft().displayHeight  / 2 / scale;
        for(int i = 0; i < cdModules.size(); i++)
            cdModules.get(i).renderModule(xCenter + xStart + (i * 25), yCenter + yStart);
    }

    @Override
    public void handleMouseInput(int i, int mX, int mY) {

    }

    @Override
    public void onMouseClick(int mX, int mY) {

    }
}