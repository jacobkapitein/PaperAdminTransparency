# PaperMC Admin Transparency

## What does this plugin do?
This plugin logs any commands from the console and server operators to a Discord channel.
**ALL** commands will be sent, even `/msg`, so being an OP is discouraged. 

## Backstory
This plugin has been created for the skeetskeet Minecraft SMP.
Some players have played other SMP's in the past with bad experience regarding admins.
This was mainly due to admin abuse, like creative mode or giving themselves enormous amounts of gear and loot.
Even though the admins on the skeetskeet SMP are trusted, we still built this plugin.
If you say you can be trusted, why not be transparent?

## How to set up?
1. Go to the [Discord Developer](https://discord.com/developers/applications) panel.
2. Create a new Application with a new bot in it.
3. Put the plugin JAR in your plugins folder.
4. Start the server and shut it down immediatly.
5. Go to your plugins folder and find the `config.yml` file for this plugin.
   1. Update the `discordKey` with the Bot token created in step 2. 
   2. Update the `discordChannelID` with the Channel ID for the bot to send message to.
6. Start your server again and you're done!

## Development environment
- Java SDK 17
- Maven 3.8.x

## Verifying integrity of build
Generate the hash of your downloaded file.

Powershell:
`Get-FileHash .\{FILENAME}.jar`

Compare the hash with the hash of your downloaded file from the [GitHub Releases page](https://github.com/jacobkapitein/PaperAdminTransparency/releases).