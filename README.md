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