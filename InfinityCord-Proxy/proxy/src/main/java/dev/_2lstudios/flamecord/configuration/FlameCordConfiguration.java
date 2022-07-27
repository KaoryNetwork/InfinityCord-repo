package dev._2lstudios.flamecord.configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import lombok.Getter;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;

public class FlameCordConfiguration {

    // FlameCord - Allow Invalid Names
    @Getter
    private boolean allowInvalidNames = false;

    private List<String> colors(final List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, ChatColor.translateAlternateColorCodes('&', strings.get(i)));
        }

        return strings;
    }

    @Getter
    private boolean customMotdEnabled = false;
    private List<String> customMotdMotds = Arrays.asList(new String[] { "&aInfinityCord &fVersion"+ BungeeCord.getInstance().getVersion()+"&e Custom motd!\n&eChange me in &cflamecord.yml&e file!" });

    public String getRandomMotd() {
        final Random random = new Random();

        return customMotdMotds.get(random.nextInt(customMotdMotds.size()));
    }

    @Getter
    private boolean loggerInitialhandler = false;
    @Getter
    private boolean loggerExceptions = false;
    @Getter
    private boolean loggerDump = false;
    @Getter
    private boolean loggerHaProxy = false;


    @Getter
    private boolean firewallNotify = true;
    @Getter
    private boolean firewallEnabled = true;
    @Getter
    private int firewallSeconds = 60;
    @Getter
    private Collection<String> firewallNames = new HashSet<>(Arrays.asList(new String[] { "mcspam" }));

    public FlameCordConfiguration(final ConfigurationProvider configurationProvider) {
        try {
            final String fileName = "./flamecord.yml";
            final File configurationFile = new File(fileName);
            final Configuration configuration;
            final boolean configurationExists = configurationFile.exists();

            if (!configurationExists) {
                configuration = new Configuration();
            } else {
                configuration = configurationProvider.load(configurationFile);
            }

            this.customMotdEnabled = setIfUnexistant("custom-motd.enabled", this.customMotdEnabled, configuration);
            this.customMotdMotds = colors(new ArrayList<>(setIfUnexistant("custom-motd.motds", this.customMotdMotds, configuration)));

            this.loggerInitialhandler = setIfUnexistant("logger.initialhandler", this.loggerInitialhandler, configuration);
            this.loggerExceptions = setIfUnexistant("logger.exceptions", this.loggerExceptions, configuration);
            this.loggerDump = setIfUnexistant("logger.dump", this.loggerDump, configuration);
            this.loggerHaProxy = setIfUnexistant("logger.haproxy", this.loggerHaProxy, configuration);            

            this.firewallEnabled = setIfUnexistant("firewall.enabled", this.firewallEnabled, configuration);
            this.firewallNotify = setIfUnexistant("firewall.notify", this.firewallNotify, configuration);
            this.firewallSeconds = setIfUnexistant("firewall.seconds", this.firewallSeconds, configuration);
            this.firewallNames = setIfUnexistant("firewall.names", this.firewallNames, configuration);
            this.allowInvalidNames = setIfUnexistant("allow-invalid-names", this.allowInvalidNames, configuration);

            if (!configurationExists) {
                configurationProvider.save(configuration, configurationFile);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private int setIfUnexistant(final String arg1, final int arg2, final Configuration configuration) {
        return (int) setIfUnexistant(arg1, (Object) arg2, configuration);
    }

    private boolean setIfUnexistant(final String arg1, final boolean arg2, final Configuration configuration) {
        return (boolean) setIfUnexistant(arg1, (Object) arg2, configuration);
    }

    private Object setIfUnexistant(final String arg1, final Object arg2, final Configuration configuration) {
        if (!configuration.contains(arg1)) {
            configuration.set(arg1, arg2);

            return arg2;
        } else {
            return configuration.get(arg1);
        }
    }

    private Collection<String> setIfUnexistant(final String arg1, final Collection<String> arg2,
            final Configuration configuration) {
        if (!configuration.contains(arg1)) {
            configuration.set(arg1, new ArrayList<>(arg2));

            return arg2;
        } else {
            return new HashSet<>(configuration.getStringList(arg1));
        }
    }
}
