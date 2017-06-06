package sonarcollect.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ton on 07/06/2017.
 */
public class ComponentTotals {

    private String component;

    private Map<Severities, Integer> totals;

    public ComponentTotals(String component) {
        this.component = component;
        totals = new HashMap<>();
    }

    public void addTotal(Severities severities, Integer total) {
        totals.put(severities, total);
    }

    @Override
    public String toString() {

        return "ComponentTotals{" +
                "component='" + component + '\'' +
                ", totals=" + totals +
                '}';
    }
}
