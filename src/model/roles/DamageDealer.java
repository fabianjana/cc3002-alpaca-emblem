package model.roles;

import model.units.IUnit;

public class DamageDealer implements Role {

    /**
     * Creates a new DamageDealer role
     */
    public DamageDealer() {
    }

    /**
     * Units with DamageDealer role can attack and receive counter attacks
     */
    @Override
    public void combat(IUnit unit, IUnit target) {
        unit.useItem(target);
        target.getRole().counterAttack(unit, target);
    }

    @Override
    public void counterAttack(IUnit unit, IUnit target) {
        target.useItem(unit);
    }
}
