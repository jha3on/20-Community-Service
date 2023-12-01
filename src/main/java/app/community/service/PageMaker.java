package app.community.service;

import app.community.model.Criteria;

public class PageMaker {
    private Criteria criteria;

    private int totalCount;
    private int startPage;
    private int endPage;

    private boolean previous;
    private boolean next;
    private int displayPageNumber = 5;

    private void calcData() {
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNumber) * displayPageNumber);

        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNumber()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        startPage = (endPage - displayPageNumber) + 1;
        if (startPage <= 0) {
            startPage = 1;
        }

        previous = startPage == 1 ? false : true;
        next = endPage * criteria.getPerPageNumber() >= totalCount ? false : true;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.calcData();
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrevious() {
        return previous;
    }

    public void setPrevious(boolean previous) {
        this.previous = previous;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getDisplayPageNumber() {
        return displayPageNumber;
    }

    public void setDisplayPageNumber(int displayPageNumber) {
        this.displayPageNumber = displayPageNumber;
    }
}
