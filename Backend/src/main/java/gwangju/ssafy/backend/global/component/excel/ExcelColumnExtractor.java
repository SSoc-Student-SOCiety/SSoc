package gwangju.ssafy.backend.global.component.excel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelColumnExtractor {

	public static List<String> extractHeaderName(Class type) {

		Field[] fields = type.getDeclaredFields();

		ArrayList<String> list = new ArrayList<>();
		for (Field field : fields) {
			// 필드에 붙은 어노테이션을 가져오고
			ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);

			if (annotation != null) {
				list.add(annotation.headerName());
			}
		}
		return list;
	}

	public static List<String> extractFieldValue(Object data) {
		Field[] fields = data.getClass().getDeclaredFields();
		ArrayList<String> list = new ArrayList<>();

		for (Field field : fields) {
			ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);

			if (annotation != null) {

				Object value = null;
				String addValue = null;

				field.setAccessible(true);
				try {
					// Field Value를 참조한다.
					value = field.get(data);
				} catch (IllegalAccessException e) {
					log.info("Reflection Error. {}", e);
				}

				list.add(value== null ? "" : value.toString());
			}

		}

		return list;
	}

}
