## **iPotions**

_Note: This should work in version other than the currently tested but them may not. please report any version that don't work and ill update it ASAP_

## A plugin for creating custom potions

First iteration of the plugin so please give feedback on any bugs you notice as well as any suggestions or features you would like to see.

All release will be hosted [here]('https://github.com/natowb/iPotions')

### Planned Features

- [x] Create custom potions in config
- [x] Select potions color
- [x] Select Multiple Sub potions
- [x] change potion type ie Splash & Lingering
- [ ] Show custom particles
- [ ] Create potions in game

### Potion Effects

![https://i.imgur.com/FNVrgag.png](https://i.imgur.com/FNVrgag.png)

### Potion Colors

![https://i.imgur.com/GF8PPff.png](https://i.imgur.com/GF8PPff.png)

### Example Config

```YAML
##########################################################################
# Created by NatoWB
# https://github.com/natowb
# goto https://github.com/natowb/iPotions for colors and effects
##########################################################################
# iPotions Settings
# Supports Color Codes IE &6, &e, &a
##########################################################################
prefix: "[&6i&ePotions&r]"
##########################################################################
# Potions
# Potion Colors: lime, green, olive, yellow, orange, red, maroon
# fuchsia, purple, aqua, teal, blue, navy, white, silver, gray, black
##########################################################################
potions:
  demo:
    type: base
    display: '&aDemo' # display name of the potions item
    color: 'red' # what color of potion to display as the icon
    effects: # list of potion effects this will use
      jump: # name of potion effect goto github above to see list
        duration: 10 # time this effect lastest in seconds
        strength: 2 # the strength of the effect starting from 0
      slow_falling: # name of potion effect goto github above to see list
        duration: 10 # time this effect lastest in seconds
        strength: 0 # the strength of the effect starting from 0
```

### Permissions

```YAML
permissions:
  ipot.*: # access to everything # default op
  ipot.give: # access to give command # default op
  ipot.help: # access to help command # default op or if they have access to the give command
  ipot.consume: # access to consume a iPot Potion # default true
```
