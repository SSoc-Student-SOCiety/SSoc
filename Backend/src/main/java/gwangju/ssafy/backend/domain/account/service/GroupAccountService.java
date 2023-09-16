package gwangju.ssafy.backend.domain.account.service;

import gwangju.ssafy.backend.domain.account.dto.GetMyGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.dto.GroupAccountInfo;
import gwangju.ssafy.backend.domain.account.dto.RegisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.dto.SendAuthCodeRequest;
import gwangju.ssafy.backend.domain.account.dto.UnregisterGroupAccountRequest;
import java.util.List;

public interface GroupAccountService {

	void sendAuthCode(SendAuthCodeRequest request);

	void registerGroupAccount(RegisterGroupAccountRequest request);

	void unregisterGroupAccount(UnregisterGroupAccountRequest request);

	List<GroupAccountInfo> getMyGroupAccount(GetMyGroupAccountRequest request);


}
