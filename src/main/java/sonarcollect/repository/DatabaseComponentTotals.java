package sonarcollect.repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import sonarcollect.dto.ComponentTotals;
import sonarcollect.dto.Severities;

/**
 * @since 04/10/2017.
 */
@Entity
public class DatabaseComponentTotals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private String component;

    private Integer blockers;

    private Integer criticals;

    private Integer majors;

    protected DatabaseComponentTotals() {
    }

    public DatabaseComponentTotals(LocalDateTime created, String component, Integer blockers, Integer criticals, Integer majors) {
        Instant instantCreated = created.atZone(ZoneId.systemDefault()).toInstant();
        this.created = Date.from(instantCreated);
        this.component = component;
        this.blockers = blockers;
        this.criticals = criticals;
        this.majors = majors;
    }

    public DatabaseComponentTotals(ComponentTotals compTotals) {
        Instant instantCreated = compTotals.getCreated().atZone(ZoneId.systemDefault()).toInstant();
        this.created = Date.from(instantCreated);
        this.component = compTotals.getComponent();
        Map<Severities, Integer> totals = compTotals.getTotals();
        this.blockers = totals.get(Severities.BLOCKER);
        this.criticals = totals.get(Severities.CRITICAL);
        this.majors = totals.get(Severities.MAJOR);
    }
}
