package unibl.etf.pisio.trelloproject.core.util;

public class IdGeneratorUtil {
    private static char[] charArray = {
            'a', 'b', 'c', 'd', 'e', 'f',
            'A', 'B', 'C', 'D', 'E', 'F',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private static int idLength = 24;

    public static String generateId() {
        char[] id = new char[idLength];

        for (int i = 0; i < idLength; ++i) {
            int random = (int) Math.random() * idLength;
            id[i] = charArray[random];
        }
        return new String(id);
    }
}
