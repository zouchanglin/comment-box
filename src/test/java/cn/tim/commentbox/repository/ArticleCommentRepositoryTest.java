package cn.tim.commentbox.repository;

import cn.tim.commentbox.entity.ArticleComment;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleCommentRepositoryTest {
    @Resource
    private ArticleCommentRepository commentInfoRepository;

    private ArticleComment saveRet;

    @Before
    public void save(){
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleId("8219089301Article");
        articleComment.setCommentClient("63889131Client");
        articleComment.setCommentContent("Content.Content.Content.Content.Content");
        articleComment.setCommentParent(0);
        articleComment.setCommentPraise(0);
        articleComment.setCreateTime(System.currentTimeMillis());
        saveRet = commentInfoRepository.save(articleComment);
        log.info("saveRet = {}", saveRet);
        assertNotNull(saveRet);
    }

    @Test
    public void find(){
        List<ArticleComment> all = commentInfoRepository.findAll();
        all.forEach(e -> System.out.print(e + ","));
        Optional<ArticleComment> byId = commentInfoRepository.findById(saveRet.getCommentId());
        assertTrue(byId.isPresent());
    }

    @After
    public void delete(){
        commentInfoRepository.deleteById(saveRet.getCommentId());
        assertFalse(commentInfoRepository.findById(saveRet.getCommentId()).isPresent());
    }
}
