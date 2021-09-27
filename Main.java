import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();


        //Path of file to rename
        final String abPath = "/home/aditya";
        //File name to rename
        final String fileNameWithExtension = "aditya.vm";
        //Virtual machine name
        final String machineNameToUse = "win10";

        //Store machine name
        String machineName = machineNameToUse;

        //safe check if file name is not preset then we have an issue
        if (machineName == null) {
            System.out.println("Exit status 1");
        } else {

            //check if code need to bypass the date logic
            if (args.length > 0 && args[0] != null && args[0].equals("SSBypassLCheck1402")) {
                main.startKVMMachine(machineName);
            } else {
                //Get today's date
                Date date = new Date();

                //Get end date which is hardcoded
                Date endDate = main.returnEndDate();

                //stop program as date is after end date
                if (date.after(endDate)) {

                    main.renameTheFile(abPath, fileNameWithExtension);
                    System.out.println("Error 304, program is not allowed to run past " + endDate);
                }
                //allow program to run
                else {
                    main.startKVMMachine(machineName);
                }
            }


        }


    }


    public void startKVMMachine(String machineName) {

        ProcessBuilder processBuilder = new ProcessBuilder();

        //create the command to execute in terminal
        processBuilder.command("bash", "-c", "virsh start " + machineName);
        try {
            //execute the command in terminal
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();


            int exitVal = process.waitFor();

            //Command executed successfully and VM started
            if (exitVal == 0) {
                System.out.println("Success!");
                System.exit(0);
            }
            //Command failed either as VM failed to start or is already running
            else {
                System.out.println("Failed " + exitVal);

                BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(process.getErrorStream())
                );
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    output.append(errorLine + "\n");
                }
                //Print output on screen
                System.out.println(output);
                //Close program
                System.exit(exitVal);

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Date returnEndDate() {
        // creating a Calendar object
        Calendar c1 = Calendar.getInstance();

        // MONTH starts with 0 i.e. ( 0 - Jan)
        c1.set(Calendar.MONTH, 9);

        // set Date
        c1.set(Calendar.DATE, 02);

        // set Year
        c1.set(Calendar.YEAR, 2021);

        // creating a date object with specified time.
        Date endDate = c1.getTime();

        System.out.println("EndDate: "
                + endDate);

        return endDate;
    }

    public void renameTheFile(String absolutePathOfFile, String fileNameWithExtn) {
        File file = new File(absolutePathOfFile + "/" + fileNameWithExtn);

        //File name post update
        final String updatedFileName = "Expired.ds";
        //Check if file exists
        if (file.exists()) {

            if (file.renameTo(new File(absolutePathOfFile + "/" + updatedFileName))) {
                System.out.println("Task done!");
            } else {
                System.out.println("Error 305, Critical error!!");
            }
        }
    }

    //Not needed anymore
    /*
    public String readVMFromFile() throws IOException {
        //hardcoded file name.
        final String FILENAME = "jvmcompiler.dat";

        //Read the content of the file in inputStream
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILENAME);

        try {

            //If null then file jvmcompiler is missing
            if (inputStream == null) {
                System.out.println("Error 302, jvmcompiler file is not found or is corrupt.");
            } else {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream));

                //Read the virtual machine name
                String jvmName = bufferedReader.readLine();

                //If machine name is empty then jvmcompiler file is empty
                if (jvmName == null) {
                    System.out.println("Error 301, jvmcompiler file is corrupt.");
                } else {
                    //jvmcompiler file must contain only 1 line with machine name
                    if (bufferedReader.readLine() != null) {
                        System.out.println("Error 303, jvmcompiler file is corrupt.");
                    }
                    return jvmName;
                }
            }

        }
        //file is not found in resources
        catch (NullPointerException npe) {
            System.out.println("Error 302, jvmcompiler file is not found or is corrupt.");
        }

        return null;
    }
    */

}
