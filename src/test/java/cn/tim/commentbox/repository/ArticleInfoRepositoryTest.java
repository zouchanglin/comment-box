package cn.tim.commentbox.repository;

import cn.tim.commentbox.entity.ArticleInfo;
import cn.tim.commentbox.utils.KeyUtil;
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
public class ArticleInfoRepositoryTest {
    @Resource
    private ArticleInfoRepository articleInfoRepository;
    private ArticleInfo saveRet;

    @Before
    public void save(){
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticleId(KeyUtil.generateKey());
        articleInfo.setArticleUrl("http://localhost:4000/categories/");
        articleInfo.setArticleCommentCount(0);
        saveRet = articleInfoRepository.save(articleInfo);
        assertNotNull(saveRet);
        log.info("saveRet = {}", saveRet);
    }

    @Test
    public void find(){
        List<ArticleInfo> all = articleInfoRepository.findAll();
        all.forEach(e -> System.out.println(e + ","));
        assertNotEquals(all.size(), 0);
        Optional<ArticleInfo> byId = articleInfoRepository.findById(saveRet.getArticleId());
        assertTrue(byId.isPresent());
    }

    @After
    public void delete(){
        String articleId = saveRet.getArticleId();
        articleInfoRepository.deleteById(articleId);
        assertFalse(articleInfoRepository.findById(articleId).isPresent());
    }

    @Test
    public void findByArticleUrl(){
        ArticleInfo byArticleUrl = articleInfoRepository.findByArticleUrl("http://localhost:4000/2020/11/19/58995.html");
        assertNotNull(byArticleUrl);
        log.info("byArticleUrl = {}", byArticleUrl);
        assertNull(articleInfoRepository.findByArticleUrl("http://example.com"));
    }
}
