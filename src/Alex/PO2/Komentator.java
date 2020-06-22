package Alex.PO2;

import Alex.PO2.GUI.KomentatorLogsList;
import Alex.PO2.Utils.DealsWithFiles;

import java.io.*;


public final class Komentator {

    private static final String path= System.getProperty("user.dir") + "/saves/logs.txt";

    public static void newRecord(String msg){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))){
            writer.write(msg);
            writer.newLine();
        }catch (FileNotFoundException ex){
            System.out.println("Not Found: " + path);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Error with BufferedWriter");
            ex.printStackTrace();
        }
    }

    public static void getKomentatorWindow(){
        KomentatorLogsList Bob = new KomentatorLogsList(path);
    }

    public static void clearKomentatorLogs(){
        new DealsWithFiles().clearFile(path);
    }

}
