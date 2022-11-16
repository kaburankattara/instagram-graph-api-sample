package kaburankattara.instagram.graph.api.utils;//import com.facebook.ads.sdk.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PrintUtils {

    public static void printMap(Map<String, Object> map) {
        printMap(map, "");
    }

    private static void printMap(Map<String, Object> map, String indent) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {

            System.out.println(indent + "key：" + entry.getKey());
            Object value = entry.getValue();
            if (value.getClass() == ArrayList.class) {
                System.out.println(indent + "value(list)：");
                printList((List) value, indent + " ");
                continue;
            }
            if (value.getClass() == LinkedHashMap.class) {
                System.out.println(indent + "value：");
                printMap((Map<String, Object>) value, indent + " ");
                continue;
            }
            System.out.println(indent + "value：" + value);
        }
    }

    public static void printList(List list) {
        printList(list, "");
    }

    private static void printList(List list, String indent) {
        int i = 0;
        for (Object listValue : list) {
            i++;
            System.out.println(indent + "index：" + i);
            if (listValue.getClass() == ArrayList.class) {
                printList((List) listValue, indent + " ");
                continue;
            }
            if (listValue.getClass() == LinkedHashMap.class) {
                printMap((Map<String, Object>) listValue, indent + " ");
                continue;
            }
            System.out.println(indent + " " + "value：" + listValue);
        }
    }
}
