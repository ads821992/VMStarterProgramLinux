# VMStarterProgramLinux
Basic Program to Start VM from Linux based on allowed date


**Pre-requiteis**
JDK 11 must be installed

**Steps to Build**
1) Download Main.java and manifest.txt from the repository
2) Go to folder where Main.java is present and run in terminal
_javac Main.java_
3) It will generate Main.class file
4) Keep Main.class file and manigest.txt in the same folder
5) In this folder run
_jar cvfm Main.jar manifest.txt Main.class_
8) It should generate Main.jar
9) Update permission of Main.jar to have execute permission
_chmod +x Main.jar_


**Execute Main.jar**
we can execute Main.jar in terminal as 
_./Main.jar_


**Details to Update**

1. final String abPath --> Update this with absolute path of the file to be updated (do not put / in the end).
                            Example - /home/ads
2. final String fileNameWithExtension--> Mention the file to rename.
3. final String machineNameToUse --> Virtual machine name for starting
4. final String updatedFileName --> File name that should be updated later if end date is reached
5. returnEndDate() --> Here you can modify the numbers for date
