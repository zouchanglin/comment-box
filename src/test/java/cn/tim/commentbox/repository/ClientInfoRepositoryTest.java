package cn.tim.commentbox.repository;

import cn.tim.commentbox.entity.ClientInfo;
import cn.tim.commentbox.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Optional;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientInfoRepositoryTest {
    @Resource
    private ClientInfoRepository clientInfoRepository;
    private ClientInfo saveRet;

    @Before
    public void save(){
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setClientId(KeyUtil.generateKey());
        clientInfo.setClientName("张三");
        clientInfo.setClientEmail("1877391@qq.com");
        clientInfo.setCreateTime(System.currentTimeMillis());
        saveRet = clientInfoRepository.save((clientInfo));
        assertNotNull(saveRet);
    }

    @Test
    public void find(){
        Optional<ClientInfo> byId = clientInfoRepository.findById(saveRet.getClientId());
        assertTrue(byId.isPresent());
        log.info("ClientInfo = {}", byId.get());
    }

    //@After
    public void delete(){
        clientInfoRepository.deleteById(saveRet.getClientId());
        Optional<ClientInfo> byId = clientInfoRepository.findById(saveRet.getClientId());
        assertFalse(byId.isPresent());
    }

    @Test
    public void findFirstByClientEmail(){
        ClientInfo first = clientInfoRepository.findFirstByClientEmail(saveRet.getClientEmail());
        assertNotNull(first);
        log.info("Find ret clientInfo = {}", first);
    }
}
