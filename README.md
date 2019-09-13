# Alpaca Emblem

## Units
There are 7 types of units in the game. All of them can use only especific types of items. Units have their owns parameters that define the hit points, location, movement and inventory capacity.

## Items
Items defines all the combat parameters of the unit. A unit can be ranged dps, melee dps or healer depending on the item that have equipped. Items are strong or weaks against other items.

## Roles
Roles are an implementation to manage and group units by their function ingame. Roles are used as properties for units, have a getter and setter. The role is automatically updated when a unit equip or unequip an item.
#### Damage Dealer
A unit gets the role "Damage Dealer" when equips any of the following items: axe, bow, magic books, spear, sword. 
This role let the unit attack when the method combat is called or counter attack when another unit attack this in combat.
If the attack is successful, if the target have a "Damage Dealer" role, it will counter attack the unit.

#### Support
When a unit equip a staff, gets the role "Support". When a support unit enter in combat, heals the targetted unit and don't receive a counter attack from the target. If a "Support" unit gets attacked in a combat by a "Damage Dealer" unit, the "Support" unit can't counter attack.

#### NoRole
Units which don't have any equipped item receive the role "NoRole". A "NoRole" unit can't enter in combat or counter attack, but can be targeted of combats by "Support" units or "Damage Dealer" units.


## Equip Items
A unit can equip an item calling the method "equipItem(IEquipableItem item)". The items must be on the unit items list (inventory) to get equipped. When a unit equip an item, the item makes the unit the owner of this. To disambiguate the type of the item a layer of "Double Dispatch" was applied, letting the item decide itself on with type of unit can be equipped.

## Combat
To execute a combat we need to call the method "combat(IUnit target)" from a unit, this method call the method with the same name in the Role object from the unit. The combat behavior depends on the role that the unit had at the moment of the call. The combat method check if the unit weapon have the range to reach the target unit, if not the combat will finalize without executing a counter attack from the target. The combat method uses a layer of Double Dispatch to disambiguate the type of item.

## Trade
To perform a trade between units, a unit have to call the method "tradeItem(target, item)". This method checks if the item is on the inventory and check if the target of the trade have space in his inventory. The method calls 2 auxiliary methods:
* removeItem(item): remove the item from the inventory, the item will be unbind the owner and if the item is currently equipped by the unit, it will first unequip it (the unequipItem method change change the role of the unit to "NoRole").
* target.addItem(item): this makes the target the new owner of the item

# Assumptions
* Sorcerers with any equipped Magic Book receive increased healing by clerics.
* On increased damages, if the result is a double, the weighted power will be rounded up.
* Since there is not a defined game structure, units don't distinguish between allies or enemies, they only use their items on any target requested.
