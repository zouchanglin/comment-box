package cn.tim.commentbox.repository;

import cn.tim.commentbox.entity.ChildComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildCommentRepository extends JpaRepository<ChildComment, Integer> {
    List<ChildComment> findAllByCommentParent(Integer parentCommentId);
}
