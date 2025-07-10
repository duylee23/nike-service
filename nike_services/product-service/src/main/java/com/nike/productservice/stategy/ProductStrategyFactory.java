package com.nike.productservice.stategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductStrategyFactory {

    private final Map<String, ProductStrategy> strategies;

    @Autowired
    public ProductStrategyFactory(List<ProductStrategy> strategyList) {
        strategies = strategyList.stream()
                .collect(Collectors
                        .toMap(
                                strategy -> strategy.getClass().getSimpleName()
                                .replace("Strategy", "").toLowerCase(), strategy -> strategy));
    }

    public ProductStrategy getStrategy(String productType) {
        return strategies.get(productType.toLowerCase());
    }
}
