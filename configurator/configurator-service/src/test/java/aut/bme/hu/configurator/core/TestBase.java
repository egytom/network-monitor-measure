package aut.bme.hu.configurator.core;

import aut.bme.hu.configurator.core.dto.ComplexConfigResult;
import aut.bme.hu.configurator.core.model.Config;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBase {

    protected void assertConfig(Config result, Config config) {
        assertEquals(result.getId(), config.getId());
        assertEquals(result.getName(), config.getName());
        assertEquals(result.getCategory(), config.getCategory());
        assertEquals(result.getDurationInSec(), config.getDurationInSec());
        assertEquals(result.getProtocol(), config.getProtocol());
    }

    protected void assertComplexResult(List<ComplexConfigResult> resultList, List<Config> configList) {
        int seq = 1;
        for (int i = 0; i < resultList.size() && i < configList.size(); i++) {
            ComplexConfigResult result = resultList.get(i);
            Config config = configList.get(i);

            assertEquals(result.getSequenceNumber(), seq++);
            assertEquals(result.getId(), config.getId());
            assertEquals(result.getName(), config.getName());
            assertEquals(result.getCategory(), config.getCategory());
            assertEquals(result.getDurationInSec(), config.getDurationInSec());
            assertEquals(result.getProtocol(), config.getProtocol());
        }
    }

}
