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
public class ArticleInfo {
    @Id
    private String articleId;
    private String articleUrl;
    private int articleCommentCount;
}
