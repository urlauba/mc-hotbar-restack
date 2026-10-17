# Hotbar Restack

A [Fabric](https://fabricmc.net/) mod for Minecraft that automatically refills your active hotbar slot as you use up a stack, pulling matching items from other hotbar slots or your inventory.

## How it works

- While you hold a stackable item, your selected stack is continuously topped back up to capacity — it never runs out while you still own more of that item.
- Donor stacks drain in slot order: other hotbar slots first, then the main inventory.
- Refills follow vanilla stacking rules: if two stacks wouldn't combine in your inventory normally (like firework rockets with different flight durations, or an item renamed in an anvil), the mod won't mix them either.
- Weapons, tools, armor, and other non-stackables are never touched or moved.
- Runs server-side: works in singleplayer and on any server with the mod installed. Creative and spectator players are unaffected.

## Building

Requires Java 25.

```sh
./gradlew build
```

The built jar is placed in `build/libs/`.

## Development

```sh
./gradlew runClient
```

## License

[MIT](LICENSE)
