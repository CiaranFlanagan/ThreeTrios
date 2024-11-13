package cs3500.threetrios.model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.controller.TestFiles;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the ThreeTriosModel class.
 */
public class TestModel {
  private Coach coach;
  private Grid gridNoHoles;
  private List<Card> cardsSmall;
  private ModelBase model;
  private Referee referee;

  @Before
  public void setUp() {
    model = new ModelBase();
    referee = new RefereeDefault();
    try {
      gridNoHoles = ConfigGrid.scannerToGrid(new Scanner(TestFiles.GRID_NO_HOLES));
      cardsSmall = ConfigCard.scannerToCardList(new Scanner(TestFiles.CC_SMALL));
    } catch (FileNotFoundException ex) {
      Assert.fail("file not found");
    }
    coach = new Coach(Coach.RED);
  }

  @Test
  public void testAddCardTo() {
    Card card = new Card("TestCard", new HashMap<>());
    model.startGame(gridNoHoles, cardsSmall, referee);
    model.addCardTo(coach, card);
    assertEquals(1, coach.getHand().size());
  }

  @Test
  public void testUpdateGrid() {
    Card card = new Card("TestCard", new HashMap<>());
    model.startGame(gridNoHoles, cardsSmall, referee);
    model.updateGrid(gridNoHoles);
    assertEquals(model.grid, gridNoHoles);
  }
}

