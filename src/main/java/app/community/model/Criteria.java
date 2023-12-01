package app.community.model;

import lombok.Getter;

@Getter
public class Criteria {
    private int page;
    private int perPageNumber;

    public Criteria() {
        this.page = 1;
        this.perPageNumber = 15;
    }

    public int getPageStart() {
        return (this.page - 1) * perPageNumber;
    }

    public void setPage(int page) {
        if (page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public void setPerPageNumber(int pageCount) {
        int count = this.perPageNumber;

        if (pageCount != count) {
            this.perPageNumber = count;
        } else {
            this.perPageNumber = pageCount;
        }
    }
}