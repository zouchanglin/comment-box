package cn.tim.commentbox.service;

import cn.tim.commentbox.form.ClientForm;
import cn.tim.commentbox.vo.ClientVO;

public interface ClientService {
    ClientVO getNewClient(ClientForm clientForm);
    ClientVO getNewClient(String email, String clientOs);

    ClientVO getClientById(String clientId);

    ClientVO updateClient(String clientId);

    void setClientEmail(String clientId, String newEmail);
}
