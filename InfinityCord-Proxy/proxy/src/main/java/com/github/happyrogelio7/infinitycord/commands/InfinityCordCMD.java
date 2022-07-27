package com.github.happyrogelio7.infinitycord.commands;

import com.github.happyrogelio7.infinitycord.utils.MessagesUtilsColor;
import dev._2lstudios.flamecord.FlameCord;
import dev._2lstudios.flamecord.configuration.MessagesConfiguration;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class InfinityCordCMD extends Command {


    public InfinityCordCMD() {
        super( "infinitycord");
    }

    private static String prefix = "&8[&aInfinityCord&8]&r";

    @Override
    public void execute(CommandSender sender, String[] args) {



        final FlameCord flameCord = FlameCord.getInstance();
        final MessagesConfiguration messagesConfiguration = flameCord.getMessagesConfiguration();

        if (args.length == 0){
            sender.sendMessage( ChatColor.translateAlternateColorCodes('&', InfinityCordCMD.prefix+"&aUsing: &f/infinitycord help") );
        }

        if (args.length > 0){

            if (args[0].equalsIgnoreCase("help")){

                sender.sendMessage(MessagesUtilsColor.getMsgColor(InfinityCordCMD.prefix+"&r "));
                sender.sendMessage(MessagesUtilsColor.getMsgColor(InfinityCordCMD.prefix+"&a InfinityCord &7| &3Help"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor(InfinityCordCMD.prefix+"&r "));
                sender.sendMessage(MessagesUtilsColor.getMsgColor(InfinityCordCMD.prefix+"&7 <> Required | [] Optional"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor(InfinityCordCMD.prefix+"&r "));
                sender.sendMessage(MessagesUtilsColor.getMsgColor(InfinityCordCMD.prefix+"&f * &a/infintycord help"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor(InfinityCordCMD.prefix+"&f * &a/infintycord info"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor(InfinityCordCMD.prefix+"&f * &a/infintycord implements"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor(InfinityCordCMD.prefix+"&f * &a/infintycord admin"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor(InfinityCordCMD.prefix+"&r "));

            } else if (args[0].equalsIgnoreCase("info")){

                sender.sendMessage(MessagesUtilsColor.getMsgColor("&r "));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&a InfinityCord &7| &3Info:"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&r "));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&f * &9version: &f"+ProxyServer.getInstance().getVersion()));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&f * &aCreated: &fHappyRogelio7"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&f * &dGitHub: &fhttps://github.com/HappyRogelio7"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&f * &6Website: &fhttps://happyrogelio7.github.io/en/index.html"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&r "));

            } else if (args[0].equalsIgnoreCase("implements")){

                sender.sendMessage(MessagesUtilsColor.getMsgColor("&r "));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&a InfinityCord &7| &3Implements:"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&r "));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&f * &aBungeeCord: &fhttps://github.com/SpigotMC/BungeeCord"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&f * &aWaterfall: &fhttps://github.com/PaperMC/Waterfall"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&f * &aFlameCord: &fhttps://github.com/2lstudios-mc/FlameCord"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&f * &aGeyserMC: &fhttps://github.com/GeyserMC/Geyser"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&f * &aInfinityCord: &fhttps://github.com/HappyRogelio7/InfinityCord"));
                sender.sendMessage(MessagesUtilsColor.getMsgColor("&r "));

            } else  if (args[0].equalsIgnoreCase("admin")){
                sender.sendMessage( ChatColor.translateAlternateColorCodes('&', InfinityCordCMD.prefix+"&c Command available in a future update.") );
            }
        }

    }
}
