package Commons;

import Moderm.Vocabulary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FunFileCSV {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String fileNameVocabulary = "src/Data/Vocabulary.csv";
    // header file CSV Vocabulary.
    private static final String FILE_HEADER = "word, mean";
    public static void writeVocabularyToFileCSV(ArrayList<Vocabulary> listVocabulary){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileNameVocabulary);
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
            for (Vocabulary vocabulary: listVocabulary){
                fileWriter.append(vocabulary.getWord());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(vocabulary.getMean());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        }catch (Exception e){
            System.out.println("Error in CsvFileWriter!!!");
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            }catch (Exception ex){
                System.out.println("Error when flush or close");
            }
        }
    }

    public static ArrayList<Vocabulary> readFileCSVToListVocabulary(){
        BufferedReader bufferedReader = null;
        ArrayList<Vocabulary> listVocabulary = new ArrayList<Vocabulary>();
        Path path = Paths.get(fileNameVocabulary);
        if (!Files.exists(path)){
            try{
                Writer writer = new FileWriter(fileNameVocabulary);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        try {
            String line;
            bufferedReader = new BufferedReader(new FileReader(fileNameVocabulary));

            while ((line = bufferedReader.readLine()) != null){
                String[] splitData = line.split(",");
                if (splitData[0].equals("word")){
                    continue;
                }
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setWord(splitData[0]);
                vocabulary.setMean(splitData[1]);
                listVocabulary.add(vocabulary);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                bufferedReader.close();
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return listVocabulary;
    }
}
