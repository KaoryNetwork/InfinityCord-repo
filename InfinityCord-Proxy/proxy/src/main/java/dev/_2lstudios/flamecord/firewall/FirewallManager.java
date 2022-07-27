package dev._2lstudios.flamecord.firewall;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Logger;

import dev._2lstudios.flamecord.FlameCord;
import dev._2lstudios.flamecord.configuration.FlameCordConfiguration;
import lombok.Getter;

public class FirewallManager {
    private final Logger logger;
    private final Collection<String> whitelistedAddresses;
    private final Collection<String> firewalled;
    private final int defaultSeconds;
    @Getter
    private int seconds;

    public FirewallManager(final Logger logger, final Collection<String> whitelistedAddresses,
            final int defaultSeconds) {
        this.logger = logger;
        this.whitelistedAddresses = whitelistedAddresses;
        this.firewalled = new HashSet<>();
        this.defaultSeconds = defaultSeconds;
        this.seconds = defaultSeconds;
    }

    public boolean isWhitelisted(final SocketAddress address) {
        final String addressString = address.toString();

        for (final String whitelistedAddressString : whitelistedAddresses) {
            if (addressString.endsWith(whitelistedAddressString)) {
                return true;
            }
        }

        return false;
    }

    public void addFirewalled(final SocketAddress address) {
        if (address == null) {
            return;
        }

        if (FlameCord.getInstance().getFlameCordConfiguration().isFirewallEnabled() && !isWhitelisted(address)) {
            final InetSocketAddress iNetSocketAddress = (InetSocketAddress) address;
            final String hostString = iNetSocketAddress.getHostString();

            if (!this.firewalled.contains(hostString)) {
                this.firewalled.add(hostString);
                logAdded(address);
            }
        }
    }

    public void logAdded(final SocketAddress address) {
        final FlameCord flameCord = FlameCord.getInstance();
        final FlameCordConfiguration flameCordConfiguration = flameCord.getFlameCordConfiguration();

        if (flameCordConfiguration.isFirewallNotify()) {
            final InetSocketAddress iNetSocketAddress = (InetSocketAddress) address;
            final String hostString = iNetSocketAddress.getHostString();

            this.logger.info(flameCord.getMessagesConfiguration().getTranslation("firewall_added", hostString));
        }
    }

    public void logBlocked(final SocketAddress address) {
        final FlameCord flameCord = FlameCord.getInstance();
        final FlameCordConfiguration flameCordConfiguration = flameCord.getFlameCordConfiguration();

        if (flameCordConfiguration.isFirewallNotify()) {
            final InetSocketAddress iNetSocketAddress = (InetSocketAddress) address;
            final String hostString = iNetSocketAddress.getHostString();

            this.logger.info(flameCord.getMessagesConfiguration().getTranslation("firewall_blocked", hostString));
        }
    }

    public Collection<String> getFirewalled() {
        return this.firewalled;
    }

    public boolean isFirewalled(final SocketAddress address) {
        final InetSocketAddress iNetSocketAddress = (InetSocketAddress) address;

        return this.firewalled.contains(iNetSocketAddress.getHostString());
    }

    public boolean isFirewalled(final String name) {
        final String nameLowerCase = name.toLowerCase();

        for (final String string : FlameCord.getInstance().getFlameCordConfiguration().getFirewallNames()) {
            if (nameLowerCase.contains(string)) {
                return true;
            }
        }

        return false;
    }

    public void tick() {
        if (--seconds <= 0) {
            final FlameCord flameCord = FlameCord.getInstance();
            final int size = this.firewalled.size();

            if (size > 0) {
                if (flameCord.getFlameCordConfiguration().isFirewallNotify()) {
                    this.logger.info(flameCord.getMessagesConfiguration().getTranslation("firewall_cleared", size));
                }

                this.firewalled.clear();
            }

            this.seconds = defaultSeconds;
        }
    }
}