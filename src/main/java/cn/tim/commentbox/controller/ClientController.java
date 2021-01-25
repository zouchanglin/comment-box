package cn.tim.commentbox.controller;

import cn.tim.commentbox.form.ClientForm;
import cn.tim.commentbox.service.ClientService;
import cn.tim.commentbox.utils.ResultVOUtil;
import cn.tim.commentbox.vo.ClientVO;
import cn.tim.commentbox.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {
    @Resource
    private ClientService clientService;

    @PostMapping
    public ResultVO newClient(ClientForm clientForm){
        ClientVO newUser = clientService.getNewClient(clientForm);
        log.info("newClient newUser = {}", newUser);
        return ResultVOUtil.success(newUser);
    }

    @GetMapping("{clientId}")
    public ResultVO getClientInfo(@PathVariable String clientId){
        ClientVO client = clientService.getClientById(clientId);
        log.info("getClientInfo client = {}", client);
        return ResultVOUtil.success(client);
    }

    @PutMapping("{clientId}")
    public ResultVO updateClientInfo(@PathVariable String clientId){
        ClientVO client = clientService.updateClient(clientId);
        log.info("getClientInfo client = {}", client);
        return ResultVOUtil.success(client);
    }
}
