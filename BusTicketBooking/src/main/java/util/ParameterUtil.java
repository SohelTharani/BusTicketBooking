package util;

import java.util.Map;

public class ParameterUtil {

	public static String getValue(Map<String,String[]> attributes,String key) {
			if(attributes.containsKey(key)) {
				return attributes.get(key)[0];
			}else {
				return null;
			}
	}
}
