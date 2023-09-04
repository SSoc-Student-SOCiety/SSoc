package gwangju.ssafy.backend.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Message<T> {

	private DataHeader dataHeader;
	private T dataBody;


	public static <T> Message<T> success(T dataBody) {
		return Message.<T>builder()
			.dataHeader(DataHeader.success())
			.dataBody(dataBody)
			.build();
	}

	public static Message success() {
		return Message.builder()
			.dataHeader(DataHeader.success())
			.build();
	}

	public static <T> Message<T> fail(String resultCode, String resultMessage) {
		return Message.<T>builder()
			.dataHeader(DataHeader.fail(resultCode, resultMessage))
			.dataBody(null)
			.build();
	}


	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	private static class DataHeader {

		private int successCode;
		private String resultCode;
		private String resultMessage;

		private static DataHeader success() {
			return DataHeader.builder()
				.successCode(1)
				.build();
		}

		private static DataHeader fail(String resultCode, String resultMessage) {
			return DataHeader.builder()
				.successCode(0)
				.resultCode(resultCode)
				.resultMessage(resultMessage)
				.build();
		}


	}
}
