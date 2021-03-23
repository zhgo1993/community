package com.zhgo.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages;
    private Integer totalPage;

    public void setPagination(int totalCount, Integer page, Integer pagesize) {
        totalPage = totalCount % pagesize ==0 ? totalCount/pagesize : (totalCount/pagesize+1);

        if(page<0){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }

        this.page=page;
        pages = new ArrayList<>();
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>=1){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }

        //是否展示上一页
        if(page==1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否展示下一页
        if(page==totalPage){
            showNext = false;
        }else {
            showNext= true;
        }
        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }

        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }
}
