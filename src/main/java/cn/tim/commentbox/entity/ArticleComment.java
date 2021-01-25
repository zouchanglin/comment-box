package cn.tim.commentbox.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ArticleComment implements ICustomSort{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private String articleId;

    private int commentParent;

    private String commentClient;

    private String commentContent;

    /**
     * 点赞数
     */
    private int commentPraise;

    private long createTime;

    /**
     * 文章评分
     */
    private int gradeStar;

    @Override
    public int compareTo(Object o) {
        if(o instanceof ArticleComment){
            ArticleComment other = (ArticleComment) o;
            int i = other.commentPraise - commentPraise;
            if(i == 0){
                return (int) (createTime - other.createTime);
            }
            return i;
        }
        return -1;
    }
}
