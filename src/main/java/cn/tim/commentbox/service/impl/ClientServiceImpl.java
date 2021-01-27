package cn.tim.commentbox.service.impl;

import cn.tim.commentbox.entity.ClientInfo;
import cn.tim.commentbox.enums.ResultEnum;
import cn.tim.commentbox.exception.CommentBoxException;
import cn.tim.commentbox.form.ClientForm;
import cn.tim.commentbox.repository.ClientInfoRepository;
import cn.tim.commentbox.service.ClientService;
import cn.tim.commentbox.utils.ChineseNameUtils;
import cn.tim.commentbox.utils.KeyUtil;
import cn.tim.commentbox.utils.RandomIconUtil;
import cn.tim.commentbox.vo.ClientVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
    @Resource
    private ClientInfoRepository clientInfoRepository;

    @Override
    public ClientVO getNewClient(ClientForm clientForm) {
        String email = clientForm.getEmail();
        String clientOs = clientForm.getClientOs();
        ClientVO clientVO = new ClientVO();
        // Email不为空
        if(!StringUtils.isEmpty(email)){
            ClientInfo findResult = clientInfoRepository.findFirstByClientEmail(email);
            if(findResult != null){
                clientVO.setClientIcon(findResult.getClientIcon());
                clientVO.setClientId(findResult.getClientId());
                clientVO.setClientName(findResult.getClientName());
                return clientVO;
            }
        }
        String clientId = KeyUtil.generateKey();
        String nickName = ChineseNameUtils.getChineseName();
        ClientInfo clientInfo = new ClientInfo(clientId, email, nickName, System.currentTimeMillis(), RandomIconUtil.getRandomIconUrl(), clientOs);

        ClientInfo saveResult = clientInfoRepository.save(clientInfo);
        log.info("新的Client已存入数据库 saveResult = {}", saveResult);
        clientVO.setClientIcon(clientInfo.getClientIcon());
        clientVO.setClientId(clientInfo.getClientId());
        clientVO.setClientName(clientInfo.getClientName());
        clientVO.setClientEmail(clientInfo.getClientEmail());
        return clientVO;
    }

    @Override
    public ClientVO getNewClient(String email, String clientOs) {
        return getNewClient(new ClientForm(email, clientOs));
    }

    @Override
    public ClientVO getClientById(String clientId) {
        Optional<ClientInfo> clientInfoOptional = clientInfoRepository.findById(clientId);
        ClientVO clientVO = new ClientVO();
        if(clientInfoOptional.isPresent()){
            ClientInfo info = clientInfoOptional.get();
            clientVO.setClientId(clientId);
            clientVO.setClientEmail(info.getClientEmail());
            clientVO.setClientName(info.getClientName());
            clientVO.setClientIcon(info.getClientIcon());
        }else {
            throw new CommentBoxException(ResultEnum.FAILED);
        }
        return clientVO;
    }

    @Override
    public ClientVO updateClient(String clientId) {
        Optional<ClientInfo> clientInfoOptional = clientInfoRepository.findById(clientId);
        ClientVO clientVO = new ClientVO();
        if(clientInfoOptional.isPresent()){
            ClientInfo info = clientInfoOptional.get();
            info.setClientName(ChineseNameUtils.getChineseName());
            info.setClientIcon(RandomIconUtil.getRandomIconUrl());
            ClientInfo save = clientInfoRepository.save(info);
            log.info("updateClient saveRet = {}", save);
            clientVO.setClientId(clientId);
            clientVO.setClientEmail(save.getClientEmail());
            clientVO.setClientName(save.getClientName());
            clientVO.setClientIcon(save.getClientIcon());
            return clientVO;
        }else{
            log.error("updateClient clientId 无对应ID");
        }
        return clientVO;
    }

    @Override
    public void setClientEmail(String clientId, String newEmail) {
        Optional<ClientInfo> clientInfoOptional = clientInfoRepository.findById(clientId);
        if(clientInfoOptional.isPresent()){
            ClientInfo clientInfo = clientInfoOptional.get();
            clientInfo.setClientEmail(newEmail);
        }
    }
}
