package cn.tim.commentbox.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ChildComment implements ICustomSort{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private int commentParent;

    private String commentClient;

    private String commentContent;

    private int commentPraise;

    private String replyClient;

    private long createTime;

    @Override
    public int compareTo(Object o) {
        if(o instanceof ChildComment){
            ChildComment other = (ChildComment) o;
            int i = commentPraise - other.commentPraise;
            if(i == 0){
                return (int) (other.createTime - createTime);
            }
            return i;
        }
        return -1;
    }
}
