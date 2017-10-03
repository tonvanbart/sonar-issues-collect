package sonarcollect;

import sonarcollect.dto.ComponentTotals;

/**
 * @since 03/10/2017.
 */
public interface StatisticsStore {

    void saveTotals(ComponentTotals componentTotals);

}
