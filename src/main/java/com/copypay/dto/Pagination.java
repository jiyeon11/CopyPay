package com.copypay.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pagination {
    private int currentPageNo;			//현재 페이지 번호
    private int pageSize = 20;			//페이지에 게시되는 리스트 수
    private int totalCount;		        //전체 리스트 수
    private int realEnd;				//페이징 마지막 숫자
    private int firstPageNoOnPageList;	//페이지 리스트의 첫 페이지 번호
    private int lastPageNoOnPageList;	//페이지 리스트의 마지막 페이지 번호
    private int firstRecordIndex; 		//페이징 sql의 조건절에 사용되는 시작 rownum
    private boolean prev;		        //이전 버튼
    private boolean next;		        //다음 버튼

    private static final int PAGE_GROUP_SIZE = 10;  //화면에 표시될 최대 페이지 수
    
    private int calculateLastPageNoOnPageList() {
        return (int)(Math.ceil(currentPageNo/(double)PAGE_GROUP_SIZE)) * PAGE_GROUP_SIZE;
    }

    public int getFirstPageNoOnPageList() {
        firstPageNoOnPageList = calculateLastPageNoOnPageList() - (PAGE_GROUP_SIZE - 1);
        return firstPageNoOnPageList;
    }

    public int getLastPageNoOnPageList() {
        lastPageNoOnPageList = calculateLastPageNoOnPageList();
        int realEnd = (int)(Math.ceil((getTotalCount() * 1.0) / getPageSize()));
        if(realEnd < lastPageNoOnPageList) {
            lastPageNoOnPageList = realEnd;
        }
        return lastPageNoOnPageList;
    }

    public int getFirstRecordIndex() {
        return (currentPageNo - 1) * pageSize;
    }

    public boolean getPrev() {
        return getFirstPageNoOnPageList() > 1;
    }

    public boolean getNext() {
        return getLastPageNoOnPageList() < getRealEnd();
    }
    public int getRealEnd() {
        realEnd = (int) Math.ceil((double) totalCount / pageSize);
        return realEnd;
    }
}