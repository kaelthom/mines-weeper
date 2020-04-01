package constants;

import java.util.HashMap;
import java.util.Map;

public class Profiles {

    public final static String ADMIN_USER = "ADMIN_USER";
    private final static String DEFAULT_USER = "DEFAULT_USER";

    public final static Map<String, String[]> rightsPerProfile = createRightsMap();

    private static Map<String, String[]> createRightsMap() {
        Map<String, String[]> rightsMap = new HashMap<>();
        rightsMap.put(ADMIN_USER,
                new String[]{
                        Rights.GET_ADMIN_MENU,
                        Rights.GET_OPTIONS_MENU,
                        Rights.GET_GAME_MENU});
        rightsMap.put(DEFAULT_USER,
                new String[]{
                        Rights.GET_OPTIONS_MENU,
                        Rights.GET_GAME_MENU});
        return rightsMap;
    }
}
