package cn.tim.commentbox.repository;

import cn.tim.commentbox.entity.ArticleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleInfoRepository extends JpaRepository<ArticleInfo, String> {

    @Query(value = "from ArticleInfo where articleUrl = ?1")
    ArticleInfo findByArticleUrl(String url);

    ArticleInfo findFirstByArticleUrl(String url);
}
