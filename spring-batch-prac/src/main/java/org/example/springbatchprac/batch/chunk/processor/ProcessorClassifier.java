package org.example.springbatchprac.batch.chunk.processor;

import java.util.HashMap;
import java.util.Map;
import org.example.springbatchprac.batch.domain.ApiRequestVO;
import org.example.springbatchprac.batch.domain.ProductVO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

public class ProcessorClassifier<C,T> implements Classifier<C, T> {

    private Map<String, ItemProcessor<ProductVO, ApiRequestVO>> processorMap = new HashMap<>();

    @Override
    public T classify(C classifiable) {
        return (T)processorMap.get(((ProductVO)classifiable).type());
    }

    public void setProcessorMap(Map<String, ItemProcessor<ProductVO, ApiRequestVO>> processorMap) {
        this.processorMap = processorMap;
    }
}
