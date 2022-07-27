package dev._2lstudios.flamecord.configuration;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;

public class ModulesConfiguration {
        // Reconnect Module
        public boolean reconnectEnabled = true;

        // Alert Module
        public boolean alertEnabled = true;

        // Find Module
        public boolean findEnabled = true;

        // IP Module
        public boolean ipEnabled = true;

        // List Module
        public boolean listEnabled = true;

        // Perms Module
        public boolean permsEnabled = false;

        // Reload Module
        public boolean reloadEnabled = true;

        // Send Module
        public boolean sendEnabled = true;

        // Server
        public boolean serverEnabled = true;

        //GeyserMC
        public boolean geysermcEnabled = false;

        public ModulesConfiguration(final ConfigurationProvider configurationProvider) {
                try {
                        final String fileName = "./modules.yml";
                        final File configurationFile = new File(fileName);
                        final Configuration configuration;
                        final boolean configurationExists = configurationFile.exists();

                        if (!configurationExists) {
                                configuration = new Configuration();
                        } else {
                                configuration = configurationProvider.load(configurationFile);
                        }

                        this.alertEnabled = setIfUnexistant("alert.enabled", this.alertEnabled, configuration);

                        this.findEnabled = setIfUnexistant("find.enabled", this.findEnabled, configuration);

                        this.ipEnabled = setIfUnexistant("ip.enabled", this.ipEnabled, configuration);

                        this.listEnabled = setIfUnexistant("list.enabled", this.listEnabled, configuration);

                        this.permsEnabled = setIfUnexistant("perms.enabled", this.permsEnabled, configuration);

                        this.reloadEnabled = setIfUnexistant("reload.enabled", this.reloadEnabled, configuration);

                        this.sendEnabled = setIfUnexistant("send.enabled", this.sendEnabled, configuration);

                        this.serverEnabled = setIfUnexistant("server.enabled", this.serverEnabled, configuration);

                        this.reconnectEnabled = setIfUnexistant("reconnect.enabled", this.reconnectEnabled, configuration);

                        this.geysermcEnabled = setIfUnexistant("geysermc.enabled", this.geysermcEnabled, configuration);

                        if (!configurationExists) {
                                configurationProvider.save(configuration, configurationFile);
                        }
                } catch (final IOException e) {
                        e.printStackTrace();
                }
        }

        private String setIfUnexistant(final String arg1, final String arg2, final Configuration configuration) {
                return (String) setIfUnexistant(arg1, (Object) arg2, configuration);
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
}
