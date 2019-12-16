package controller;

import model.items.Bow;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.Alpaca;
import model.units.Archer;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TacticianTest {

    Tactician tactician;
    Field gameMap;
    IUnit alpaca, archer;
    IEquipableItem bow;

    @BeforeEach
    void setUp() {
        gameMap = new Field();
        gameMap.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
                new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
                new Location(2, 1), new Location(2, 2));
        tactician = new Tactician("Player 1", gameMap);
        alpaca = new Alpaca(50, 2, null);
        archer = new Archer(30, 2, null);
        bow = new Bow("Bow", 10, 2, 3);
    }

    @Test
    void getName() {
        assertEquals(tactician.getName(), "Player 1");
    }

    @Test
    void addUnit() {
        assertTrue(tactician.getUnits().isEmpty());
        tactician.addUnit(alpaca);
        assertEquals(alpaca, tactician.getUnits().get(0));
        assertEquals(tactician, alpaca.getOwner());
    }

    @Test
    void setUnitOnMap() {
        // not owner attempt
        tactician.setUnitOnMap(alpaca, 0, 0);
        assertNull(gameMap.getCell(0,0).getUnit());
        // with ownership
        tactician.addUnit(alpaca);
        tactician.setUnitOnMap(alpaca,0,0);
        assertEquals(gameMap.getCell(0,0).getUnit(), alpaca);
        // overwriting position is not permitted
        tactician.addUnit(archer);
        tactician.setUnitOnMap(archer,0,0);
        assertEquals(gameMap.getCell(0,0).getUnit(), alpaca);
    }

    @Test
    void selectUnitIn() {
        tactician.addUnit(archer);
        tactician.addUnit(alpaca);
        tactician.setUnitOnMap(archer, 2, 2);
        tactician.setUnitOnMap(alpaca, 1, 1);
        tactician.selectUnitIn(2,2);
        assertEquals(archer, tactician.getSelectedUnit());
        tactician.selectUnitIn(1,1);
        assertEquals(alpaca, tactician.getSelectedUnit());
    }

    @Test
    void getSelectedUnit() {
        tactician.addUnit(archer);
        tactician.setUnitOnMap(archer, 2, 2);
        tactician.selectUnitIn(2,2);
        assertEquals(archer,tactician.getSelectedUnit());
    }

    @Test
    void moveSelectedUnitTo() {
        tactician.addUnit(archer);
        tactician.setUnitOnMap(archer, 2, 2);
        tactician.selectUnitIn(2,2);
        tactician.moveSelectedUnitTo(2,1);
        assertEquals(archer, gameMap.getCell(2,1).getUnit());
        //trying to move a second time don't work
        tactician.moveSelectedUnitTo(1,1);
        assertEquals(archer, gameMap.getCell(2,1).getUnit());
    }

    @Test
    void resetMovements() {
        tactician.addUnit(archer);
        tactician.setUnitOnMap(archer, 2, 2);
        tactician.selectUnitIn(2,2);
        tactician.moveSelectedUnitTo(2,1);
        assertEquals(archer, gameMap.getCell(2,1).getUnit());
        //trying to move a second time don't work
        tactician.moveSelectedUnitTo(1,1);
        assertEquals(archer, gameMap.getCell(2,1).getUnit());
        //but works using resetMovements()
        tactician.getSelectedUnit().resetMovements();
        tactician.moveSelectedUnitTo(1,1);
        assertEquals(archer, gameMap.getCell(1,1).getUnit());
    }

    @Test
    void getSelectedUnitHitPoints() {
        tactician.addUnit(archer);
        tactician.setUnitOnMap(archer, 2, 2);
        tactician.selectUnitIn(2,2);
        assertEquals(tactician.getSelectedUnitHitPoints(), 30);
    }

    @Test
    void getSelectedUnitMaxHitPoints() {
        tactician.addUnit(archer);
        tactician.setUnitOnMap(archer, 2, 2);
        tactician.selectUnitIn(2,2);
        assertEquals(tactician.getSelectedUnitMaxHitPoints(), 30);
    }

    @Test
    void getSelectedUnitEquippedItem() {
        tactician.addUnit(archer);
        tactician.setUnitOnMap(archer, 2, 2);
        tactician.selectUnitIn(2,2);
        tactician.addItem(bow);
        tactician.equipItem(bow);
        assertEquals(tactician.getSelectedUnitEquippedItem(), bow);
    }

    @Test
    void getSelectedUnitInventory() {
        tactician.addUnit(archer);
        tactician.setUnitOnMap(archer, 2, 2);
        tactician.selectUnitIn(2,2);
        tactician.addItem(bow);
        List<IEquipableItem> testItems = new ArrayList<>();
        testItems.add(bow);
        assertEquals(tactician.getSelectedUnitInventory(), testItems);
    }

    @Test
    void combat() {
        tactician.addUnit(archer);
        alpaca.setLocation(gameMap.getCell(1,1));
        tactician.setUnitOnMap(archer, 2, 2);
        tactician.selectUnitIn(2,2);
        tactician.addItem(bow);
        tactician.equipItem(0);
        tactician.combat(alpaca);
        assertEquals(alpaca.getHitPoints(), 40);
    }

    @Test
    void tradeItem() {
        tactician.addUnit(archer);
        alpaca.setLocation(gameMap.getCell(1,1));
        tactician.setUnitOnMap(archer, 2, 2);
        tactician.selectUnitIn(2,2);
        tactician.addItem(bow);
        tactician.tradeItem(alpaca, bow);
        List<IEquipableItem> testItems = new ArrayList<>();
        testItems.add(bow);
        assertEquals(alpaca.getItems(), testItems);
        assertTrue(tactician.getSelectedUnitInventory().isEmpty());
    }
}