# Alpaca Emblem
## Units
The game have 7 types of units:
### Alpaca
* Can't equip objects but can carry an unlimited amount of them on the inventory.

## Roles
An implementation to manage and group units by their function ingame.
### Damage Dealer
A unit gets the role "Damage Dealer" when equips any of the following items: axe, bow, magic books, spear, sword. 
This role let the unit attack when the method combat is called or counter attack when another unit attack this in combat.
If the attack is successful, if the target have a "Damage Dealer" role, it will counter attack the unit.

### Support
When a unit equip a staff, gets the role "Support". When a support unit enter in combat, heals the targetted unit and 
