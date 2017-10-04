package sonarcollect.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import sonarcollect.StatisticsStore;
import sonarcollect.dto.ComponentTotals;

/**
 * @since 03/10/2017.
 */
@Component
@Profile("default")
public class DummyStatisticsStore implements StatisticsStore {

    private static final Logger LOG = LoggerFactory.getLogger(DummyStatisticsStore.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void saveTotals(ComponentTotals componentTotals) {
        try {
            String totals = objectMapper.writeValueAsString(componentTotals);
            LOG.info("saving: {}", totals);
        } catch (JsonProcessingException e) {
            LOG.warn("Failed to serialize totals {}", componentTotals, e);
        }
    }
}
