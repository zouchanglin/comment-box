package cn.tim.commentbox.repository;

import cn.tim.commentbox.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {
    List<ArticleComment> findAllByArticleId(String articleId);
}
