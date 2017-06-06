package sonarcollect.dto;

/**
 * Model the result of the Sonar <code>issues/search</code> query. Only the total is modeled for now.
 */
public class IssuesSearchResult {

    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
