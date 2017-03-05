package net.unaussprechlich.project.connect

import com.palechip.hudpixelmod.extended.util.LoggerHelper
import net.unaussprechlich.managedgui.lib.GuiManagerMG
import net.unaussprechlich.managedgui.lib.ManagedGui
import net.unaussprechlich.managedgui.lib.helper.SetupHelper
import net.unaussprechlich.project.connect.gui.ConnectGUI
import net.unaussprechlich.project.connect.socket.io.SocketConnection

/**
 * Connect Created by unaussprechlich on 20.12.2016.
 * Description:
 */
object Connect {

    fun setup() {
        LoggerHelper.logInfo("Setting up Connect!")
        ManagedGui.setup(SetupHelper())
        ManagedGui.isIsDisabled = false

        GuiManagerMG.addGUI("ConnectGUI", ConnectGUI)
        SocketConnection.getINSTANCE().setup()

    }

}