package sonarcollect.dto;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ton on 07/06/2017.
 */
public class ComponentTotals {

    private LocalDateTime created;

    private String component;

    private Map<Severities, Integer> totals;

    public ComponentTotals(LocalDateTime created, String component) {
        this.created = created;
        this.component = component;
        totals = new EnumMap<>(Severities.class);
    }

    public ComponentTotals(String component) {
        this(LocalDateTime.now(), component);
    }

    public String getComponent() {
        return component;
    }

    public Map<Severities, Integer> getTotals() {
        return totals;
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
