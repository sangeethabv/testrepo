package reports;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.Spring;

import org.apache.log4j.Logger;

import util.TestUtil;

// Auto-generated Javadoc
/**
 * The Class ReportUtil.
 */
public final class ReportUtil {

    /**
     * The private constructor instance.
     */
    private ReportUtil() {
    }
    /**
     * The Logger instance.
     */
    private static final Logger LOG = Logger.getLogger(ReportUtil.class);
    /** The script number. */
    public static int scriptNumber = 1;

    /** The index result filename. */
    public static String indexResultFilename;

    /** The current dir. */
    public static String currentDir;

    /** The current suite name. */
    public static String currentSuiteName;

    /** The tcid. */
    public static int tcid;
    // public static String currentSuitePath;

    /** The pass number. */
    public static double passNumber;

    /** The fail number. */
    public static double failNumber;

    /** The new test. */
    public static boolean newTest = true;

    /** The description. */
    public static ArrayList<String> description = new ArrayList<String>();;

    /** The keyword. */
    public static ArrayList<String> keyword = new ArrayList<String>();;

    /** The teststatus. */
    public static ArrayList<String> teststatus = new ArrayList<String>();;

    /** The screen shot path. */
    public static ArrayList<String> screenShotPath = new ArrayList<String>();;

    // Deletes all files and subdirectories under dir.
    // Returns true if all deletions were successful.
    // If a deletion fails, the method stops attempting to delete and returns
    // false.
    /**
     * Delete dir.
     *
     * @param dir
     *        the dir
     * @return true, if successful
     */
    public static boolean deleteDir(final File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = new File(dir, children[i]).delete();
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        // return dir.delete();
        return true;
    }

    /**
     * Start testing.
     *
     * @param filename
     *        the filename
     * @param testStartTime
     *        the test start time
     * @param env
     *        the env
     * @param rel
     *        the rel
     */
    public static void startTesting(final String filename,
        final String testStartTime,
        final String env,
        final String rel) {
        indexResultFilename = filename;
        currentDir =
            indexResultFilename.substring(0,
                indexResultFilename.lastIndexOf("//"));
        LOG.debug("Current Dir:" + currentDir);
        ReportUtil.deleteDir(new File(currentDir));
        FileWriter fstream = null;
        BufferedWriter out = null;
        try {
            // Create file

            fstream = new FileWriter(filename);
            out = new BufferedWriter(fstream);

            String rUNDATE = TestUtil.now("dd.MMMMM.yyyy").toString();

            String eNVIRONMENT = env;
            // SeleniumServerTest.ConfigurationMap.getProperty("environment");
            String rELEASE = rel;
            // SeleniumServerTest.ConfigurationMap.getProperty("release");

            out.newLine();

            out.write("<html>\n");
            out.write("<HEAD>\n");
            out.write(" <TITLE>Automation Test Results</TITLE>\n");
            out.write("</HEAD>\n");

            out.write("<body>\n");
            out.write("<h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b><u> Automation Test Results</u></b></h4>\n");
            out.write("<table  border=1 cellspacing=1 cellpadding=1 >\n");
            out.write("<tr>\n");

            out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> <u>Test Details :</u></h4>\n");
            out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run Date</b></td>\n");
            out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"
                + rUNDATE + "</b></td>\n");
            out.write("</tr>\n");
            out.write("<tr>\n");

            out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run StartTime</b></td>\n");

            out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"
                + testStartTime + "</b></td>\n");
            out.write("</tr>\n");
            out.write("<tr>\n");
            // out.newLine();
            out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Run EndTime</b></td>\n");
            out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>END_TIME</b></td>\n");
            out.write("</tr>\n");
            out.write("<tr>\n");
            // out.newLine();

            out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Environment</b></td>\n");
            out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"
                + eNVIRONMENT + "</b></td>\n");
            out.write("</tr>\n");
            out.write("<tr>\n");

            out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Release</b></td>\n");
            out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"
                + rELEASE + "</b></td>\n");
            out.write("</tr>\n");
            out.write("<tr>\n");
            // out.newLine();
            out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Test Data Created</b></FONT></td>\n");
            out.write("<td width=150 align= left ><b>TEST_DATA</b></td>\n");
            out.write("</tr>\n");

            out.write("</table>\n");

            /*
             * out.write("<table border=0 cellspacing=0 cellpadding=0 ><tr>");
             * out
             * .write("<td><FONT COLOR= #000066  FACE= Arial  SIZE=2.75><b>"+""
             * +"</b></td>"); out.write("GRAPH"); out.write("</tr></table>");
             * out.write(
             * "<h4> <FONT COLOR=660000 FACE= Arial  SIZE=4.5> Detailed Report :</h4>"
             * ); out.write("<table  border=1 cellspacing=1 cellpadding=1 >");
             * out.write("<tr>"); out.write(
             * "<td width=80  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Script#</b></td>"
             * ); out.write(
             * "<td width=300 align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Case Name</b></td>"
             * ); out.write(
             * "<td width=75 align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Status</b></td>"
             * ); out.write(
             * "<td width=200 align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run End Time</b></td>"
             * ); out.write("</tr>");
             */
            // Close the output stream
            out.close();
        } catch (Exception e) { // Catch exception if any
            System.err.println("Error: " + e.getMessage());
        } finally {

            fstream = null;
            out = null;
        }
    }

    /**
     * Start suite.
     *
     * @param suiteName
     *        the suite name
     */
    public static void startSuite(final String suiteName) {

        FileWriter fstream = null;
        BufferedWriter out = null;
        currentSuiteName = suiteName.replaceAll(" ", "_");
        tcid = 1;
        try {
            // build the suite folder
            // currentSuitePath = currentDir;
            // //+"//"+suiteName.replaceAll(" ","_");
            // currentSuiteDir = suiteName.replaceAll(" ","_");
            // File f = new File(currentSuitePath);
            // f.mkdirs();

            fstream = new FileWriter(indexResultFilename, true);
            out = new BufferedWriter(fstream);

            out.write("<h4> <FONT COLOR=660000 FACE= Arial  SIZE=4.5> <u>"
                + suiteName + " Report :</u></h4>\n");
            out.write("<table  border=1 cellspacing=1 cellpadding=1 width=100%>\n");
            out.write("<tr>\n");
            out.write("<td width=10%  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Script#</b></td>\n");

            //out.write("<td width=40% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Case Name</b></td>\n");
            out.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Case Name</b></td>\n");
            out.write("<td width=30% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Title</b></td>\n");
            out.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Status</b></td>\n");
            out.write("<td width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run Start Time</b></td>\n");
            out.write("<td width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run End Time</b></td>\n");

            out.write("</tr>\n");
            out.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {

            fstream = null;
            out = null;
        }
    }

    /**
     * End suite.
     */
    public static void endSuite() {
        FileWriter fstream = null;
        BufferedWriter out = null;

        try {
            fstream = new FileWriter(indexResultFilename, true);
            out = new BufferedWriter(fstream);
            out.write("</table>\n");
            out.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {

            fstream = null;
            out = null;
        }

    }

    /**
     * Adds the test case.
     *
     * @param testCaseName
     *        the test case name
     * @param testCaseStartTime
     *        the test case start time
     * @param testCaseEndTime
     *        the test case end time
     * @param status
     *        the status
     */
    public static void addTestCase(final String testCaseName,
    	final String currentTitle,
        final String testCaseStartTime,
        final String testCaseEndTime,
        final String status) {
        newTest = true;
        FileWriter fstream = null;
        BufferedWriter out = null;

        try {
            newTest = true;
            // build the keywords page
            if (status.equalsIgnoreCase("Skipped")
                || status.equalsIgnoreCase("Skip")) {
                LOG.debug("In If loop");
            } else {
                File f =
                    new File(currentDir + "//" + currentSuiteName + "_TC"
                        + tcid + "_" + testCaseName.replaceAll(" ", "_")
                        + ".html");
                f.createNewFile();
                fstream =
                    new FileWriter(currentDir + "//" + currentSuiteName + "_TC"
                        + tcid + "_" + testCaseName.replaceAll(" ", "_")
                        + ".html");
                out = new BufferedWriter(fstream);
                out.write("<html>");
                out.write("<head>");
                out.write("<title>");
                out.write(testCaseName + " Detailed Reports");
                out.write("</title>");
                out.write("</head>");
                out.write("<body>");

                out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> Detailed Report :</h4>");
                out.write("<table  border=1 cellspacing=1    cellpadding=1 width=100%>");
                out.write("<tr> ");
                out.write("<td align=center width=10%  align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Step/Row#</b></td>");
                out.write("<td align=center width=50% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Description</b></td>");
                out.write("<td align=center width=10% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Keyword</b></td>");
                out.write("<td align=center width=15% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Result</b></td>");
                out.write("<td align=center width=15% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Screen Shot</b></td>");
                out.write("</tr>");
                if (description != null) {
                    for (int i = 0; i < description.size(); i++) {
                        out.write("<tr> ");

                        out.write("<td align=center width=10%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>TS"
                            + (i + 1) + "</b></td>");
                        out.write("<td align=center width=50%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"
                            + description.get(i) + "</b></td>");
                        out.write("<td align=center width=10%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"
                            + keyword.get(i) + "</b></td>");
                        if (teststatus.get(i).startsWith("Pass")) {
                            out.write("<td width=20% align= center  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"
                                + teststatus.get(i) + "</b></td>\n");
                        } else if (teststatus.get(i).startsWith("Fail")) {
                            out.write("<td width=20% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                                + teststatus.get(i) + "</b></td>\n");
                        }

                        // out.write("<td align=center width=20%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+teststatus.get(i)+"</b></td>");
                        if (screenShotPath.get(i) != null) {
                            out.write("<td align=center width=20%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b><a href="
                                + screenShotPath.get(i)
                                + " target=_blank>Screen Shot</a></b></td>");
                        } else {
                            out.write("<td align=center width=20%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>&nbsp;</b></td>");
                        }
                        out.write("</tr>");
                    }
                }

                out.close();

            }

            fstream = new FileWriter(indexResultFilename, true);
            out = new BufferedWriter(fstream);

            fstream = new FileWriter(indexResultFilename, true);
            out = new BufferedWriter(fstream);
            // out.newLine();

            out.write("<tr>\n");
            // System.out.println(currentSuitePath);
            out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                + scriptNumber + "</b></td>\n");
            if (status.equalsIgnoreCase("Skipped")
                || status.equalsIgnoreCase("Skip")) {
                out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                    + testCaseName + "</b></td>\n");
            } else {
                // Commented by Gunjan
                // currentDir= currentDir.replaceAll(" ", "%20"); //Replace
                // space in directory name to %20 so that path doesn't get
                // truncated in HTML so that browser consider it as space
                LOG.debug("After replacing spaces current Dir is:" + currentDir);
                out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b><a href=file:///"
                    + currentDir.replaceAll(" ", "%20")
                    + "//"
                    + currentSuiteName
                    + "_TC"
                    + tcid
                    + "_"
                    + testCaseName.replaceAll(" ", "_")
                    + ".html>"
                    + testCaseName + "</a></b></td>\n");
            }
            out.write("<td width=30% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+currentTitle+"</b></td>\n");
            tcid++;
            if (status.startsWith("Pass")) {
                out.write("<td width=10% align= center  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"
                    + status + "</b></td>\n");
            } else if (status.startsWith("Fail")) {
                out.write("<td width=10% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                    + status + "</b></td>\n");
            } else if (status.equalsIgnoreCase("Skipped")
                || status.equalsIgnoreCase("Skip")) {
                out.write("<td width=10% align= center  bgcolor=yellow><FONT COLOR=153E7E FACE=Arial SIZE=2><b>"
                    + status + "</b></td>\n");
            }

            out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                + testCaseStartTime + "</b></td>\n");
            out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                + testCaseEndTime + "</b></td>\n");

            out.write("</tr>\n");

            scriptNumber++;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        description = new ArrayList<String>();
        keyword = new ArrayList<String>();
        teststatus = new ArrayList<String>();
        screenShotPath = new ArrayList<String>();
        newTest = false;
    }

    /**
     * Adds the keyword.
     *
     * @param desc
     *        the desc
     * @param key
     *        the key
     * @param stat
     *        the stat
     * @param path
     *        the path
     */
    public static void addKeyword(final String desc,
        final String key,
        final String stat,
        final String path) {

        description.add(desc);
        keyword.add(key);
        teststatus.add(stat);
        screenShotPath.add(path);

    }

    /**
     * Update end time.
     *
     * @param endTime
     *        the end time
     */
    public static void updateEndTime(final String endTime, String testStat) {
        StringBuffer buf = new StringBuffer();
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(indexResultFilename);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            // Read File Line By Line

            while ((strLine = br.readLine()) != null) {

                if (strLine.indexOf("END_TIME") != -1) {
                    strLine = strLine.replace("END_TIME", endTime);
                }
                if (strLine.indexOf("TEST_DATA") != -1) {
                    if (testStat == "Passed") {
                        strLine =
                            strLine.replace("TEST_DATA",
                                "<FONT COLOR=GREEN FACE= Arial  SIZE=2.75>"
                                    + testStat + "</FONT>");
                    } else if (testStat == "Failed") {
                        strLine =
                            strLine.replace("TEST_DATA",
                                "<FONT COLOR=RED FACE= Arial  SIZE=2.75>"
                                    + testStat + "</FONT>");
                    }

                }
                buf.append(strLine);
            }

            // Close the input stream
            in.close();
            LOG.debug(buf);
            FileOutputStream fos = new FileOutputStream(indexResultFilename);
            DataOutputStream output = new DataOutputStream(fos);
            output.writeBytes(buf.toString());
            fos.close();

        } catch (Exception e) { // Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

}
