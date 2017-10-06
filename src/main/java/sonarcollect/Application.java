package sonarcollect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import sonarcollect.dto.ComponentTotals;
import sonarcollect.dto.IssuesSearchResult;
import sonarcollect.dto.Severities;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private RestTemplate restTemplate;

    @Autowired
    private StatisticsStore statisticsStore;

    private static final String urlTemplate =
            "http://sonar.backbase.dev:9000/api/issues/search?componentKeys={component}&resolved=false&severities={severities}&languages=java&statuses=OPEN,CONFIRMED&facetMode=count&ps=2";

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    public Application() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("run({})", args);
        String componentsList = args[0];
        log.info("reading components from file: '{}'", Paths.get(componentsList).toAbsolutePath());
        List<String> components = Files.readAllLines(Paths.get(componentsList));
        components.stream()
                .map(this::getTotals)
                .forEach(logTotals.andThen(statisticsStore::saveTotals));
    }

    private Consumer<ComponentTotals> logTotals = (totals) -> log.info("{}", totals);

    private ComponentTotals getTotals(String component) {
        log.trace("getTotals('{}')", component);
        ComponentTotals componentTotals = new ComponentTotals(component);
        Arrays.stream(Severities.values())
                .forEach(sev -> componentTotals.addTotal(sev, getTotal(component, sev)));
        return componentTotals;
    }

    private Integer getTotal(String component, Severities severities) {
        log.trace("getTotal('{}',{})", component, severities);
        IssuesSearchResult result = restTemplate.getForObject(urlTemplate, IssuesSearchResult.class, component, severities);
        Integer total = result.getTotal();
        log.trace("getTotal('{}',{}) --> {}", component, severities, total);
        return total;
    }

}