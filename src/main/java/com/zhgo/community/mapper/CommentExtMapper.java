package com.zhgo.community.mapper;

import com.zhgo.community.model.Comment;
import com.zhgo.community.model.CommentExample;
import com.zhgo.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}