package nl.skeetskeet.admintransparency;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandSent implements Listener {
    GatewayDiscordClient _discordClient;
    FileConfiguration _config;

    public CommandSent(GatewayDiscordClient discordClient, FileConfiguration config){
        _discordClient = discordClient;
        _config = config;
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event){
        _discordClient.getChannelById(Snowflake.of(_config.getString("discordChannelID")))
                .ofType(GuildMessageChannel.class)
                .flatMap(channel -> channel.createMessage(generateThumbnail(event.getCommand(), "Console", "f78a4d8d-d51b-4b39-98a3-230f2de0c670")))
                .subscribe();
    }
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();

        // Exit early when player is not an OP
        if(!player.isOp()) return;

        // ALL commands by OP's will be logged to Discord. This is to discourage being an OP.
        _discordClient.getChannelById(Snowflake.of(_config.getString("discordChannelID")))
                .ofType(GuildMessageChannel.class)
                .flatMap(channel -> channel.createMessage(generateThumbnail(event.getMessage(), player.getName(), player.getUniqueId().toString())))
                .subscribe();
    }

    private EmbedCreateSpec generateThumbnail(String command, String senderName, String senderUUID){
        String senderFace, senderUrl, senderBody;
        senderFace = String.format("https://crafatar.com/avatars/%s", senderUUID);
        senderUrl = String.format("https://namemc.com/profile/%s", senderUUID);
        senderBody = String.format("https://crafatar.com/renders/body/%s", senderUUID);

        return EmbedCreateSpec.builder()
                .color(Color.BLUE)
                .title("Command has been executed")
                .description(String.format("`%s`", command))
                .author(senderName, senderUrl, senderFace)
                .thumbnail(senderBody)
                .build();
    }
}
