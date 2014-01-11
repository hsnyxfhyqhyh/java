package com.kang.util;

import java.awt.FileDialog;
import java.io.File;

import javax.swing.JFrame;

/**
 * Simply a utility method used to do a bit of error checking as part of the
 * file selection process. A FileChooser instance can handle either an input
 * file selection or an output file selection.
 *
 *
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 *
 * Copyright (c) 2007 The Trivera Group, Inc.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 * @author The Trivera Group Tech Team.
 */


public class FileChooser {
  // Publically accessible dialog
  public FileDialog dialog;

  public FileChooser() {
    // Create a file dialog to prompt for file to write to
    dialog = new FileDialog(new JFrame());
  }

  /**
   * Handles the actual requesting and checking of that request
   */
  public void askForOpen() {

    boolean fileNotGotten = true;
    dialog.setMode(FileDialog.LOAD);

    // Loop to check and eventually get a file to open
    while (fileNotGotten) {
      dialog.setVisible(true);

      // Check to see if the user has cancelled out of the file selection
      // process.
      if (dialog.getFile() == null)
        break;

      String candidateName = dialog.getDirectory().concat(
          dialog.getFile());
      File candidate_file = new File(candidateName);
      File candidate_directory = new File(dialog.getDirectory());

      // First check to see if we can even read from this directory...
      if (!candidate_directory.canRead()) {
        System.err
            .print(dialog.getDirectory()
                + " cannot be read from.  Please select another filename.");
        continue;
      }
      ;
      // Check to see if the file exists
      if (!candidate_file.exists()) {
        System.err.print(candidateName
            + " does not exist.  Please select another filename.");
        dialog.setFile(null);
        continue;
      }
      ;
      // If it exists and cannot be read, then request another file name.
      if (!candidate_file.canRead()) {
        System.err
            .print(candidateName
                + " exists but cannot be read!  Please select another filename.");
        dialog.setFile(null);
        continue;
      }
      ;
      fileNotGotten = false;
    }
    ;

    dialog.dispose();
  }

  /**
   * Handles the actual requesting and checking of that request
   */
  public void askForOut() {

    boolean fileNotFound = true;
    dialog.setMode(FileDialog.SAVE);

    while (fileNotFound) {
      dialog.setVisible(true);
      // Check to see if the user has cancelled out of the file selection
      // process.
      if (dialog.getFile() == null)
        break;

      String candidateName = dialog.getDirectory().concat(
          dialog.getFile());
      File candidate_file = new File(candidateName);
      File candidate_directory = new File(dialog.getDirectory());
      // First check to see if we can even write to this directory...
      if (!candidate_directory.canWrite()) {
        System.err
            .print(dialog.getDirectory()
                + " cannot be written to.  Please select another directory.");
        dialog.setFile(null);
        continue;
      }
      ;
      // Check to see if the file exists
      if (!candidate_file.exists()) {
        fileNotFound = false;
        break;
      }
      ;
      // If it exists and cannot be overwritten, then request another file
      // name.
      if (!candidate_file.canWrite()) {
        System.err.print(candidateName
            + " already exists and cannot be overwritten!"
            + "  Please select another filename.");
        dialog.setFile(null);
        continue;
      }
      ;
      fileNotFound = false;
      break;
    }
    ;

    dialog.dispose();
  }

}
