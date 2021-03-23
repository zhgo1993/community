package com.zhgo.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001,"你的问题找不到了"),
    TARGET_PARAM_NOT_FOUND(2002,"你的问题找不到了"),
    NO_LOGIN(2003,"未登录，不能评论，请先登录"),
    SYS_ERROR(2004,"服务器冒烟了，一会再试试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在！！"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了，换个试试"),
    CONTENT_IS_EMPTY(2007,"回复内容不能为空！！"),
            ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code,String  message){
        this.message = message;
        this.code = code;
    }
}
