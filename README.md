# Rainbow Ore Minecraft Plugin

Rainbow Ore is a Minecraft plugin for Paper servers that adds a custom Rainbow Ore block. Players can obtain the block with a command, place it in the world, and break it to receive one random resource: diamond, iron ingot, raw iron, copper ingot, raw copper, gold ingot, raw gold, emerald, coal, or quartz.

## Install

1. Build the plugin with Java 21 and Maven:
   ```
   mvn package
   ```
2. Copy the generated `target/plugin.jar` file into your server's `plugins` folder.
3. Start or restart a Paper Minecraft server running version 1.21.11 or compatible.
4. Confirm that RainbowOre loads successfully in the server console.

## Use

1. Join the server as a player with the `rainbowore.give` permission. Operators have this permission by default.
2. Give yourself Rainbow Ore:
   ```
   /rainbowore
   ```
3. Give yourself multiple blocks, from 1 to 64:
   ```
   /rainbowore 16
   ```
4. Place the Rainbow Ore block.
5. Break the placed block to receive one randomly selected resource.
6. Use `/rainbowore` without arguments or with an amount between 1 and 64. Invalid amounts and non-player command usage are rejected.

---

More Minecraft plugin projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/33706fee-b8bd-4a83-b668-39e469e1d988?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=33706fee-b8bd-4a83-b668-39e469e1d988
