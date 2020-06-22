package Alex.PO2.Utils;

import java.io.*;
import java.util.ArrayList;

public class DealsWithFiles {

    public ArrayList<String> getData(String path){

        ArrayList<String> data = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;
            while((line = reader.readLine()) != null){
                data.add(line);
            }

        }catch (FileNotFoundException ex){
            System.out.println("Not Found: " + path);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Buffer error: " + path);
            ex.printStackTrace();
        }
        return data;
    }

    public void clearFile(String path){
        File f = new File(path);
        if(f.exists()){
            f.delete();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
