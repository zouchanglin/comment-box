package cn.tim.commentbox.repository;

import cn.tim.commentbox.entity.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, String> {
    ClientInfo findFirstByClientEmail(String email);
}
