package com.worldcretornica.plotme_core.commands;

import com.worldcretornica.plotme_core.PlotMeCoreManager;
import com.worldcretornica.plotme_core.PlotMe_Core;
import com.worldcretornica.plotme_core.api.ICommandSender;
import com.worldcretornica.plotme_core.api.IPlayer;
import com.worldcretornica.plotme_core.api.IServerBridge;

import java.util.Collections;
import java.util.List;

public abstract class PlotCommand {

    final PlotMe_Core plugin;

    final IServerBridge serverBridge;

    final PlotMeCoreManager manager;

    public PlotCommand(PlotMe_Core instance) {
        plugin = instance;
        serverBridge = plugin.getServerBridge();
        manager = PlotMeCoreManager.getInstance();
    }

    short getPlotLimit(IPlayer player) {

        if (player.hasPermission("plotme.limit.*")) {
            return -1;
        }
        short max = -2;
        for (short ctr = 0; ctr < 255; ctr++) {
            if (player.hasPermission("plotme.limit." + ctr)) {
                max = ctr;
            }
        }

        if (max == -2) {
            if (player.hasPermission("plotme.admin")) {
                return -1;
            } else if (player.hasPermission("plotme.use")) {
                return 1;
            } else {
                return 0;
            }
        }

        return max;
    }


    String C(String caption, Object... args) {
        return plugin.C(caption, args);
    }

    public abstract String getName();

    public abstract boolean execute(ICommandSender sender, String[] args);

    public abstract String getUsage();

    boolean isAdvancedLogging() {
        return plugin.getConfig().getBoolean("AdvancedLogging");
    }

    public List<String> getAliases() {
        return Collections.emptyList();
    }

}
