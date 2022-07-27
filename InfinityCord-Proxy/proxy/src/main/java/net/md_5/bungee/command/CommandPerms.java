package net.md_5.bungee.command;

import java.util.HashSet;
import java.util.Set;
import net.md_5.bungee.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

@Deprecated
public class CommandPerms extends Command
{

    public CommandPerms()
    {
        super( "perms" );
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        sender.sendMessage( ChatColor.translateAlternateColorCodes('&', "&8[&aInfinityCord&8]&r &cThe &f/perms &ccommand is disabled in this Custom Build.") );
    }


    //InfinityCord Note: This command is disabled as it is not widely used, usually in BungeeCord.

    /*@Override
    public void execute(CommandSender sender, String[] args)
    {
        Set<String> permissions = new HashSet<>();
        for ( String group : sender.getGroups() )
        {
            permissions.addAll( ProxyServer.getInstance().getConfigurationAdapter().getPermissions( group ) );
        }
        sender.sendMessage( ProxyServer.getInstance().getTranslation( "command_perms_groups", Util.csv( sender.getGroups() ) ) );

        for ( String permission : permissions )
        {
            sender.sendMessage( ProxyServer.getInstance().getTranslation( "command_perms_permission", permission ) );
        }
    }*/
}
