package com.zyjy.dto;

/**
 * @ClassName PageBean
 * @Description
 * @Author 清Great
 * @Date 2020/10/28 10:26
 */
public class PageBean {
    private int currentPage;
    private int pageSize;
    private int start;
    private int end;

    private int totalRecord = 0;
    private int totalPage;


    public PageBean() {

    }

    public PageBean(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        setEnd();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        setTotalPage();
    }

    public int getTotalPage() {

        return totalPage;
    }

    public void setTotalPage() {
        int totalPage;
        if (totalRecord == 0) {
            totalPage = 1;
        } else if (totalRecord % pageSize == 0) {
            totalPage = totalRecord / pageSize;
        } else {
            totalPage = totalRecord / pageSize + 1;
        }

        this.totalPage = totalPage;
    }

    public int getStart() {
        setStart();
        return start;
    }

    public void setStart() {
        this.start = (currentPage - 1) * pageSize;
    }

    public int getEnd() {
        setEnd();
        return end;
    }

    public void setEnd() {
        this.end = start + pageSize;
    }


    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", start=" + start +
                ", end=" + end +
                ", totalRecord=" + totalRecord +
                ", totalPage=" + totalPage +
                '}';
    }
}
