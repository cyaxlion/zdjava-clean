package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextInputOutput {

    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("ouput.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append("tslkjalk");
            bufferedWriter.flush();
            bufferedWriter.close();;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
