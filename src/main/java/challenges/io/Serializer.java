package challenges.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Serializer<T> {


    private final String CSV_SEP = ",";

    public void writeCSV(ArrayList<T> objectList) {
//        for(T o : objectList) {
//            System.out.println(new Gson().toJson(o));
//        }

        try {
//            JsonObject object = new JsonObject(objectList.stream().map(k -> new Gson().toJson(k)).toArray().toString());
            CsvSchema.Builder builder = CsvSchema.builder();


            JsonNode node = new ObjectMapper().readTree(objectList.stream().map(k -> new Gson().toJson(k)).toList().toString());
            node.elements().next().fieldNames().forEachRemaining(f -> builder.addColumn(f));
            CsvSchema schema = builder
                    .build()
                    .withHeader();
            CsvMapper mapper = new CsvMapper();
            mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
            mapper.writerFor(JsonNode.class)
                    .with(schema)
                    .writeValue(new File("src/main/resources/data.csv"), node);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
