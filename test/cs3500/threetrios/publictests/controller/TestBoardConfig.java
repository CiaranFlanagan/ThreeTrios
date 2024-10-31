package cs3500.threetrios.publictests.controller;

import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.controller.GridConfig;
import cs3500.threetrios.utils.LineWriter;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Tests for the BoardConfig class.
 */
public class TestBoardConfig {
  @Test
  public void test1() {
    try {
      System.out.println(GridConfig.scannerToGrid(new Scanner(TestFiles.GRID_ASSN_HARD)));
      String expected = LineWriter.create().line("5 7").line("CCXXXXC").line("CXCXXXC")
              .line("CXXCXXC").line("CXXXCXC").line("CXXXXCC").endWith("CXXXXCC").toString();
      Assert.assertEquals(expected,
              GridConfig.scannerToGrid(new Scanner(TestFiles.GRID_ASSN_HARD)));
    } catch (FileNotFoundException ex) {
      Assert.fail("file not found");
    }

  }
}
