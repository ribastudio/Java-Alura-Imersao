import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONParser {

  private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
  private static final Pattern REGEX_ATTRIBUTES_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
  public List<Map<String, String>> parse(String json) {
    Matcher matcher = REGEX_ITEMS.matcher(json);
    if (!matcher.find()) {
      throw new IllegalArgumentException("Not found items");
    }
    String[] items = matcher.group().split("\\},\\{");

    List<Map<String, String>> data = new ArrayList<>();

    for (String item : items) {
      Map<String, String> attribItems = new HashMap<>();

      Matcher matcherAttribsJson = REGEX_ATTRIBUTES_JSON.matcher(item);
      while (matcherAttribsJson.find()) {
        String attrib = matcherAttribsJson.group(1);
        String value = matcherAttribsJson.group(2);
        attribItems.put(attrib, value);
      }
      data.add(attribItems);
    }

    return data;
  }
}
