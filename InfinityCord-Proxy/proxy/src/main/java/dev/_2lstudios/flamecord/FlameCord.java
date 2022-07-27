package dev._2lstudios.flamecord;

import java.util.Collection;
import java.util.logging.Logger;

import dev._2lstudios.flamecord.configuration.FlameCordConfiguration;
import dev._2lstudios.flamecord.configuration.MessagesConfiguration;
import dev._2lstudios.flamecord.configuration.ModulesConfiguration;
import dev._2lstudios.flamecord.firewall.FirewallManager;
import lombok.Getter;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class FlameCord {
    @Getter
    private static FlameCord instance;
    @Getter
    private final FirewallManager firewallManager;
    @Getter
    private final FlameCordConfiguration flameCordConfiguration;
    @Getter
    private final ModulesConfiguration modulesConfiguration;
    @Getter
    private final MessagesConfiguration messagesConfiguration;
    @Getter
    private final Thread thread;
    private boolean running = true;

    public static void renew(final Logger logger, final Collection<String> whitelistedAddresses) {
        if (FlameCord.instance != null) {
            FlameCord.instance.running = false;
        }

        final FlameCord instance = new FlameCord(logger, whitelistedAddresses);

        FlameCord.instance = instance;
    }

    private FlameCord(final Logger logger, final Collection<String> whitelistedAddresses) {
        final ConfigurationProvider configurationProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);

        this.flameCordConfiguration = new FlameCordConfiguration(configurationProvider);
        this.firewallManager = new FirewallManager(logger, whitelistedAddresses,
                flameCordConfiguration.getFirewallSeconds());
        this.modulesConfiguration = new ModulesConfiguration(configurationProvider);
        this.messagesConfiguration = new MessagesConfiguration(logger, configurationProvider);
        this.thread = new Thread() {
            @Override
            public void run() {
                while (running) {
                    try {
                        sleep(1000L);

                        if (!running) {
                            return;
                        }

                        firewallManager.tick();
                    } catch (final Exception e) {
                        // Ignored
                    }
                }
            }
        };

        this.thread.start();
    }
}