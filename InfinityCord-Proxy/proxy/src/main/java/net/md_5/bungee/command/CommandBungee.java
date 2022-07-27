package net.md_5.bungee.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class CommandBungee extends Command
{

    public CommandBungee()
    {
        super( "bungee" );
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        sender.sendMessage( ChatColor.translateAlternateColorCodes('&', "&aThis server is running &fInfinityCord &aversion &f" + ProxyServer.getInstance().getVersion() + "&a by &fHappyRogelio7&a.") );
        sender.sendMessage( ChatColor.translateAlternateColorCodes('&', "&aUse this command to get more information: &f/infinitycord") );
    }
}
