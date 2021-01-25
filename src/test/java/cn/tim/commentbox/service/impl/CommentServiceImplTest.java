package cn.tim.commentbox.service.impl;

import cn.tim.commentbox.service.CommentService;
import cn.tim.commentbox.vo.ArticleCommentVO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentServiceImplTest {

    @Resource
    private CommentService commentService;

    @Test
    public void getAllCommentByArticle() {
        List<ArticleCommentVO> commentVOList = commentService.getAllCommentByArticle("http://localhost:4000/2020/11/19/58995.html");
        log.info("commentVOList = {}", commentVOList);
        log.info("commentVOList => JSON = {}", JSON.toJSONString(commentVOList));
    }
}
