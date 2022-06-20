package nl.skeetskeet.admintransparency;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Admin Transparency plugin starting...");

        // If config doesn't exist yet, create one
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();

        // Initiate Discord client
        final GatewayDiscordClient client = DiscordClientBuilder.create(config.getString("discordKey")).build().login().block();

        // Initiate listeners
        getServer().getPluginManager().registerEvents(new CommandSent(client, config), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
