package org.example.springbatchprac.batch.chunk.wrtier;

import java.util.HashMap;
import java.util.Map;
import org.example.springbatchprac.batch.domain.ApiRequestVO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

public class WriterClassifier<C,T> implements Classifier<C, T> {

    private Map<String, ItemWriter<ApiRequestVO>> writerMap = new HashMap<>();

    @Override
    public T classify(C classifiable) {
        String type = ((ApiRequestVO) classifiable).productVO().type();
        return (T)writerMap.get(type);
    }

    public void setWriterMap(Map<String, ItemWriter<ApiRequestVO>> writerMap) {
        this.writerMap = writerMap;
    }
}
