package controller;

import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

public class Tactician {

    private String name;
    private List<IUnit> units;
    private IUnit selectedUnit;
    private Field gameMap;

    /**
     * Creates a tactician with a name and a reference of a game map
     * @param name the name of the tactician
     * @param gameMap the reference of the map
     */
    public Tactician (String name, Field gameMap) {
        this.gameMap = gameMap;
        this.name = name;
        this.units = new ArrayList<>();
    }

    /**
     * @return the name of the tactician
     */
    public String getName() {
        return name;
    }

    /**
     * Add a unit to this tactician
     * @param unit the unit who will be add
     */
    public void addUnit(IUnit unit) {
        if(unit.getOwner() != null)
            return;
        unit.setOwner(this);
        units.add(unit);
    }

    public List<IUnit> getUnits() {
        return List.copyOf(units);
    }

    /**
     * Set a unit owned by this tactician on the current game map
     * @param unit the unit
     * @param x horizontal position on the map
     * @param y vertical position on the map
     */
    public void setUnitOnMap(IUnit unit, int x, int y) {
        // checks if this tactician is the owner of the unit
        if(unit.getOwner() != this)
            return;
        Location cell = gameMap.getCell(x, y);
        // check if the cell already have a unit
        if(cell.getUnit() == null) {
            unit.setLocation(cell);
        }
    }

    /**
     * Selects a unit in the game map
     *
     * @param x
     *     horizontal position of the unit
     * @param y
     *     vertical position of the unit
     */
    public void selectUnitIn(int x, int y) {
        selectedUnit = gameMap.getCell(x, y).getUnit();
    }

    /**
     * @return the current player's selected unit
     */
    public IUnit getSelectedUnit() {
        return selectedUnit;
    }


    public void moveSelectedUnitTo(int x, int y) {
        if(!selectedUnit.hasMoved())
            selectedUnit.moveTo(gameMap.getCell(x, y));
    }

    public void resetMovements() {
        for (IUnit unit : units) {
            unit.resetMovements();
        }
    }

    public int getSelectedUnitHitPoints() {
        return selectedUnit.getHitPoints();
    }

    public int getSelectedUnitMaxHitPoints() {
        return selectedUnit.getMaxHitPoints();
    }

    /**
     * Equips an item from the inventory to the currently selected unit.
     *
     * @param index
     *     the location of the item in the inventory.
     */
    public void equipItem(int index) {
        selectedUnit.equipItem(getSelectedUnitInventory().get(index));
    }

    public void addItem(IEquipableItem item) {
        selectedUnit.addItem(item);
    }

    public IEquipableItem getSelectedUnitEquippedItem() {
        return selectedUnit.getEquippedItem();
    }

    public List<IEquipableItem> getSelectedUnitInventory() {
        return List.copyOf(selectedUnit.getItems());
    }

    public void combat(IUnit target) {
        selectedUnit.combat(target);
    }

    public void tradeItem(IUnit target, IEquipableItem item) {
        selectedUnit.tradeItem(target, item);
    }
}
