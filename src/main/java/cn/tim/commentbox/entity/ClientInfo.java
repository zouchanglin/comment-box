package cn.tim.commentbox.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfo {
    @Id
    private String clientId;

    private String clientEmail;

    private String clientName;

    private long createTime;

    private String clientIcon;

    private String clientOs;
}
