package cs3500.threetrios.test.controller;

import cs3500.threetrios.TestFiles;
import cs3500.threetrios.controller.GridConfig;
import org.junit.Test;

import java.io.File;

public class TestBoardConfig {
  @Test
  public void test1() {
    System.out.println(GridConfig.fileToGridBoard(TestFiles.grid1));
  }
}
