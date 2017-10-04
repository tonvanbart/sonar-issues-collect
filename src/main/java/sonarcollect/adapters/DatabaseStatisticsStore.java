package sonarcollect.adapters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import sonarcollect.StatisticsStore;
import sonarcollect.dto.ComponentTotals;
import sonarcollect.repository.DatabaseComponentTotals;
import sonarcollect.repository.TotalsRepository;

/**
 * @since 04/10/2017.
 */
@Component
@Profile("database")
public class DatabaseStatisticsStore implements StatisticsStore {

    private TotalsRepository totalsRepository;

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseStatisticsStore.class);

    @Autowired
    public DatabaseStatisticsStore(TotalsRepository totalsRepository) {
        this.totalsRepository = totalsRepository;
    }

    @Override
    public void saveTotals(ComponentTotals componentTotals) {
        LOG.trace("saveTotals({})", componentTotals);
        DatabaseComponentTotals dbTotals = new DatabaseComponentTotals(componentTotals);
        totalsRepository.save(dbTotals);
    }
}
