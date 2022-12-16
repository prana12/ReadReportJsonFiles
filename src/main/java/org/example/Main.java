package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.json.PostalReport;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("START");

        //LocalDateTime yesterday = LocalDateTime.now().minus(1, ChronoUnit.DAYS);
        //LocalDateTime startDate =  yesterday.with(LocalTime.of(2, 0, 0));
        //LocalDateTime startDate = LocalDateTime.now().minusDays(1).withHour(2).withMinute(0).withSecond(0).withNano(0);
        //LocalDateTime endDate = LocalDate.now().atTime(7, 0);;

        //LocalDateTime startDateTime = LocalDateTime.now().minusDays(1).with(LocalTime.of(2, 0, 0));
        //LocalDateTime endDateTime = LocalDateTime.now().with(LocalTime.of(7, 0, 0));

        //use below start and end datetimes
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.of(2022, 8, 1), LocalTime.of(02, 00));
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.of(2022, 8, 2), LocalTime.of(07, 00));
        //System.out.println("startDateTime=" + startDateTime + " AND endDateTime=" + endDateTime);

        File[] selectedFiles = readReportFiles("/Users/prakash/Desktop/ReadJsonFiles/src/main/resources/postal/", startDateTime, endDateTime);
        Arrays.sort(selectedFiles, Comparator.comparing(File::getName));
        //Arrays.sort(selectedFiles, Comparator.comparing(File::getName).reversed());

        //readReportFile(selectedFiles[0]);
        /*String fileContents = readFile(selectedFiles[0].getAbsolutePath());
        System.out.println(fileContents);*/

        //PostalReport postalReport = readPostalReport(selectedFiles[0]);
        PostalReport postalReport = readPostalReport(new File("/Users/prakash/Desktop/ReadJsonFiles/src/main/resources/postal/postal_20220801-113411.json"));

        System.out.println("STOP");
    }

    //read files from directory
    private static File[] readReportFiles(String location, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        File directory = new File(location);
        File[] files = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String fileName) {
                if(fileName.startsWith("postal") && fileName.endsWith(".rpt")) {
                    boolean isReportFileSelected = isFileSelected(fileName, startDateTime, endDateTime);
                    return isReportFileSelected;
                }
                return false;
            }
        });

        /*String[] fileNames = directory.list(new FilenameFilter() {
            public boolean accept(File dir, String fileName) {
                if(fileName.startsWith("postal") && fileName.endsWith(".rpt")) {
                    boolean isReportFileSelected = isFileSelected(fileName, startDateTime, endDateTime);
                    return isReportFileSelected;
                }
                return false;
            }
        });

        //Arrays.sort(fileNames, Collections.reverseOrder());
        //List<String> fileArray = Arrays.asList(fileNames);

        Arrays.stream(fileNames).sorted().forEach(file -> {
            System.out.println(file);
        });

        System.out.println(fileNames.length);*/

        return files;
    }

    //check if the provided file needs to be selected
    private static boolean isFileSelected(String fileName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        //System.out.println("Filter " + fileName + ":: " +startDateTime + "-" + endDateTime );

        //check if filename length is not equal to 26
        if (fileName.length() != 26) {
            return false;
        }

        int underline = fileName.indexOf("_");
        int dash = fileName.indexOf("-");
        int dot = fileName.indexOf(".");
        String datePart = fileName.substring(underline+1, dash);
        String timePart = fileName.substring(dash+1, dot);
        System.out.println("datePart=" + datePart + " and timePart=" + timePart);

        //check if datePart and timePart length is not equal to 26
        if (datePart.length() != 8 || timePart.length() != 6) {
            return false;
        }

        Integer year = Integer.valueOf(datePart.substring(0, 4));
        Integer month = Integer.valueOf(datePart.substring(4, 6));
        Integer day = Integer.valueOf(datePart.substring(6));
        Integer hour = Integer.valueOf(timePart.substring(0, 2));
        Integer minute = Integer.valueOf(timePart.substring(2, 4));
        Integer second = Integer.valueOf(timePart.substring(4));

        LocalDateTime fileGeneratedDateTime = LocalDateTime.of(
                LocalDate.of(year, month, day),
                LocalTime.of(hour, minute, second));

        boolean selectFile = fileGeneratedDateTime.compareTo(startDateTime) >= 0 && fileGeneratedDateTime.compareTo(endDateTime) <= 0;
        return selectFile;
    }

    //read a single file contents
    private static void readReportFile(File file) {
        //System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        try {
            File myObj = new File(file.getAbsolutePath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //read file
    public static String readFile(String filename)
    {
        String content = null;
        File file = new File(filename); // For example, foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return content;
    }

    //read json rpt file
    private static PostalReport readPostalReport(File file) {
        ObjectMapper mapper = new ObjectMapper();
        PostalReport postalReport = null;

        try {
            //postalReport = mapper.readValue(new File(file.getAbsolutePath()), PostalReport.class);
            postalReport = mapper.readValue(Paths.get(file.getAbsolutePath()).toFile(), PostalReport.class);
            System.out.println(postalReport);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return postalReport;
    }

}