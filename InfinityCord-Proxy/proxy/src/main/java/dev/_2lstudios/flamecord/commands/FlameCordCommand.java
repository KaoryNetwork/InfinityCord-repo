package dev._2lstudios.flamecord.commands;

import java.util.Collection;
import java.util.HashSet;

import dev._2lstudios.flamecord.FlameCord;
import dev._2lstudios.flamecord.configuration.MessagesConfiguration;
import dev._2lstudios.flamecord.firewall.FirewallManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

public class FlameCordCommand extends Command {
private final BungeeCord bungeeCord;

    public FlameCordCommand(final BungeeCord bungeeCord) {
        super("flamecord");

        this.bungeeCord = bungeeCord;
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        final FlameCord flameCord = FlameCord.getInstance();
        final MessagesConfiguration messagesConfiguration = flameCord.getMessagesConfiguration();

        if (sender.hasPermission("flamecord.usage")) {
            if (args.length > 0) {
                final String arg0 = args[0];

                switch (arg0) {
                    case "firewall": {
                        final FirewallManager firewallManager = flameCord.getFirewallManager();
                        final int amount = firewallManager.getFirewalled().size(),
                                seconds = firewallManager.getSeconds();

                        sender.sendMessage(TextComponent.fromLegacyText(
                                messagesConfiguration.getTranslation("firewall_info", amount, seconds)));
                        break;
                    }
                    case "reload": {
                        // FlameCord - Collect ips from servers
                        final Collection<String> whitelistedAddresses = new HashSet<>();

                        for (final ServerInfo serverInfo : bungeeCord.getServers().values()) {
                            whitelistedAddresses.add(serverInfo.getSocketAddress().toString());
                        }

                        FlameCord.renew(bungeeCord.getLogger(), whitelistedAddresses);
                        sender.sendMessage(TextComponent
                                .fromLegacyText(messagesConfiguration.getTranslation("flamecord_reload")));
                        break;
                    }
                    default: {
                        sender.sendMessage(TextComponent.fromLegacyText(
                                messagesConfiguration.getTranslation("flamecord_help", bungeeCord.getVersion())));
                        break;
                    }
                }
            } else {
                sender.sendMessage(TextComponent
                        .fromLegacyText(messagesConfiguration.getTranslation("flamecord_help", bungeeCord.getVersion())));
            }
        } else {
            sender.sendMessage(TextComponent
                    .fromLegacyText(messagesConfiguration.getTranslation("flamecord_nopermission")));
        }
    }
}
