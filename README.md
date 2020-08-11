![Imgur](https://i.imgur.com/qqIUQbX.png)

# iPotions

A plugin for creating custom potions

### Planned Features

- [x] Create custom potions in config
- [x] Select potions color
- [x] Select Multiple Sub potions
- [ ] change potion type ie Splash & Lingering
- [ ] Show custom particles
- [ ] Create potions in game

### Potion Effects

| Potion                    | Effect          | Potion                    | Effect          |
| ------------------------- | --------------- | ------------------------- | --------------- |
| Potion of Invisibility    | INVISIBILITY    | Potion of Water Breathing | WATER_BREATHING |
| Potion of Leaping         | JUMP            | Potion of Harming         | HARM            |
| Potion of Fire Resistance | FIRE_RESISTANCE | Potion of Poison          | POISON          |
| Potion of Swiftness       | SPEED           | Potion of Weakness        | WEAKNESS        |
| Potion of Luck            | LUCK            | Potion of Slow Falling    | SLOW_FALLING    |
| Potion of Healing         | HEAL            | Potion of Night Vision    | NIGHT_VISION    |
| Potion of Regeneration    | REGENERATION    | Potion of Strength        | INCREASE_DAMAGE |

### Potion Colors

![](https://i.imgur.com/GF8PPff.png)

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

```YAML
permissions:
  ipot.*: # access to everything # default op
  ipot.give: # access to give command # default op
  ipot.help: # access to help command # default op or if they have access to the give command
  ipot.consume: # access to consume a iPot Potion # default true
```
