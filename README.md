## **iPotions**

_Note: This should work in version other than the currently tested but them may not. please report any version that don't work and ill update it ASAP_

## A plugin for creating custom potions

First iteration of the plugin so please give feedback on any bugs you notice as well as any suggestions or features you would like to see.

All release will be hosted [here]('https://github.com/natowb/iPotions')


### Potion Effects
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
```YAML
permissions:
  ipot.*: 		    # access to everything 				        | default op
  ipot.give: 	    # access to give command 			        | default op
  ipot.help: 	    # access to help command			        | default op 
  ipot.consume:   # access to consume a iPot Potion 	  | default true
  ipot.throw: 	  # acces to throw a iPot Potion 		    | default true
  ipot.create: 	  # access to create potions in game 	  | deafult op
  ipot.delete: 	  # access to delete potions in game 	  | deafult op
  ipot.edit: 	    # access to edit potions in game 	    | deafult op
  
```
