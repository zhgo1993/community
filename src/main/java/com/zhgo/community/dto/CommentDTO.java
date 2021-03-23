package com.zhgo.community.dto;

import com.zhgo.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {

    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String content;
    private Integer commentcount;
    private User user;
}
