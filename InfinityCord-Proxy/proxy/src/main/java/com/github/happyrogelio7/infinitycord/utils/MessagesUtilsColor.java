package com.github.happyrogelio7.infinitycord.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessagesUtilsColor {

    public static String getMsgColor(String text) {

        String getversioncolor1 = ProxyServer.getInstance().getGameVersion();

        if (getversioncolor1.contains("1.16") && getversioncolor1.contains("1.16.1")
                && getversioncolor1.contains("1.16.2") && getversioncolor1.contains("1.16.3")
                && getversioncolor1.contains("1.16.4") && getversioncolor1.contains("1.16.5")
                && getversioncolor1.contains("1.17") && getversioncolor1.contains("1.17.1")
                && getversioncolor1.contains("1.18") && getversioncolor1.contains("1.18.1")
                && getversioncolor1.contains("1.18.2") && getversioncolor1.contains("1.19")
        ){
            Pattern pattern = Pattern.compile("#[a-fA-F0-9]]{6}");
            Matcher matcher = pattern.matcher(text);

            while(matcher.find()){
                String color = text.substring(matcher.start(),matcher.end());
                text = text.replace(color, ChatColor.of(color)+"");

                matcher = pattern.matcher(text);
            }
        }

        return ChatColor.translateAlternateColorCodes('&',text);
    }

}
