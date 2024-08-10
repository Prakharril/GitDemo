import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetJsonData {

    private String jsonData;

    public void readJsonData() {
        File file = new File("C:\\Users\\lenovo-pc\\IdeaProjects\\NewMavenProject\\src\\data.json");

        try {
            jsonData = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            jsonData = null;
        }
    }

    public List<HashMap<String, String>> getJsonAsHashMap() {
        if (jsonData == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        GetJsonData reader = new GetJsonData();
        reader.readJsonData();

        List<HashMap<String, String>> list = reader.getJsonAsHashMap();
        if (list != null) {
            for (HashMap<String, String> map : list) {
                System.out.println(map);
            }
        } else {
            System.out.println("Failed to parse JSON data.");
        }
    }
}
