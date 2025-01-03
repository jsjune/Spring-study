package org.example.springbatchprac.batch.job.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.ClassPathResource;

//@Component
public class TestPartitioner implements Partitioner {

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> result = new HashMap<>();
        int totalLines;
        try {
            totalLines = (int)new BufferedReader(new InputStreamReader(
                new ClassPathResource("data/test.txt").getInputStream())).lines().count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("totalLines = " + totalLines);
        int linesPerPartition = totalLines / gridSize;
        int remainingLines = totalLines % gridSize;

        for (int i = 0; i < gridSize; i++) {
            ExecutionContext context = new ExecutionContext();
            context.put("startLine", i * linesPerPartition + 1);
            context.put("endLine", (i + 1) * linesPerPartition);

            // 나머지 라인을 마지막 파티션에 추가
            if (i == gridSize - 1) {
                context.put("endLine", context.getInt("endLine") + remainingLines);
            }

            System.out.println("Partition " + i + ": " + context);
            result.put("partition" + i, context);
        }
        return result;
    }
}
