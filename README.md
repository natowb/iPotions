## **iPotions**

_Note: This should work in version other than the currently tested but them may not. please report any version that don't work and ill update it ASAP_

## A plugin for creating custom potions

### Potion Effects
```yml
FIRE_RESISTANCE     =   FIRE_RESISTANCE
INSTANT_DAMAGE      =   HARM
INSTANT_HEAL        =   HEAL
INVISIBILITY        =   INVISIBILITY
JUMP                =   JUMP
LUCK                =   LUCK
NIGHT_VISION        =   NIGHT_VISION
POISON              =   POISON
REGEN               =   REGENERATION
SLOW_FALLING        =   SLOW_FALLING
SLOWNESS            =   SLOW
SPEED               =   SPEED
STRENGTH            =   INCREASE_DAMAGE
WATER_BREATHING     =   WATER_BREATHING
WEAKNESS            =   WEAKNESS
```

### Example Config
```YAML
##########################################################################
# Created by NatoWB
# https://github.com/natowb
# goto https://github.com/natowb/iPotions for colors and effects
##########################################################################
# Potions
# Potion Colors: lime, green, olive, yellow, orange, red, maroon
# fuchsia, purple, aqua, teal, blue, navy, white, silver, gray, black
# Potion Types: base, splash, area
##########################################################################
potions:
  demo:
    type: splash
    display: '&aDemo'
    color: 'red' 
    effects: 
      jump: 
        duration: 10 
        strength: 2 
      slow_falling: 
        duration: 10 
        strength: 0 
```
### Permissions
```yml
  ipot.*	    
  ipot.give	    
  ipot.help	    
  ipot.consume 
  ipot.throw 	  
  ipot.create	  
  ipot.delete
  ipot.edit
  
```
