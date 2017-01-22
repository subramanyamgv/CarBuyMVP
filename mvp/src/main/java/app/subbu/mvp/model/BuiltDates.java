package app.subbu.mvp.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class BuiltDates {

    private int page;
    private int pageSize;
    private int totalPageCount;
    private Map<String, String> wkda = new LinkedHashMap<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Map<String, String> getWkda() {
        return wkda;
    }

    public void setWkda(Map<String, String> wkda) {
        this.wkda = wkda;
    }
}
