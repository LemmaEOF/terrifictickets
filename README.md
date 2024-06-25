<img src="icon.png" align="right" width="180px"/>

# Terrific Tickets
![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo-api.sleeping.town%2Fgay%2Flemmaeof%2Fterrifictickets%2Fmaven-metadata.xml)


[>> Downloads <<](https://github.com/LemmaEOF/terrifictickets/releases)

*Insert Coin to Continue!*

**This mod is open source and under a permissive license.** As such, it can be included in any modpack on any platform without prior permission. We appreciate hearing about people using our mods, but you do not need to ask to use them. See the [LICENSE file](LICENSE) for more details.

Terrific Tickets is a simple utility mod made for ModFest: Carnival! It adds tokens and tickets, plus a passcard for compactly storing both. Tokens stack to 128, while tickets stack to 256.

## Depending on Terrific Tickets
```groovy
repositories {
    maven { 
        url 'https://repo.sleeping.town'
        content {
            includeGroup 'gay.lemmaeof'
        }
    }
}

dependencies {
    modImplementation include("gay.lemmaeof:terrifictickets:<VERSION>")
}
```

[![Made for ModFest: Carnival](https://raw.githubusercontent.com/ModFest/art/v2/badge/svg/carnival/cozy.svg)](https://modfest.net/carnival)

## Credits

- Coin textures from [Heroic Icon Pack by Aleksandr Makarov](https://iknowkingrabbit.itch.io/heroic-icon-pack)
- Passcard texture from [RPG Items - Retro Pack by Blodyavenger](https://blodyavenger.itch.io/rpg-items-retro-pack)
- Token and ticket acceptor textures by apfelrunder
- Ticket accept sound from [tramticket_pinching.wav by KrystofJakobe](https://freesound.org/people/KrystofJakobe/sounds/499135/)
- Token accept sound from  [vending_machine_coin_insert.wav by Krokulator](https://freesound.org/people/Krokulator/sounds/696745/)