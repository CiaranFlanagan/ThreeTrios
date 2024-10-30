package cs3500.threetrios.publictests.controller;

import cs3500.threetrios.TestFiles;
import cs3500.threetrios.controller.GridConfig;
import cs3500.threetrios.utils.LineWriter;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the BoardConfig class.
 */
public class TestBoardConfig {
  @Test
  public void test1() {
    System.out.println(GridConfig.fileToGridBoard(TestFiles.ASSIGNMENT_HARD));
    String expected = LineWriter.create().line("5 7").line("CCXXXXC").line("CXCXXXC")
            .line("CXXCXXC").line("CXXXCXC").line("CXXXXCC").endWith("CXXXXCC").toString();
    Assert.assertEquals(expected, GridConfig.fileToGridBoard(TestFiles.ASSIGNMENT_HARD));
  }
}
