package study.junit5prac.junitPrac;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FIndSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

//    private static final long Threshold = 1000L;
    private long Threshold;

    public FIndSlowTestExtension(long threshold) {
        Threshold = threshold;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }


    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        SlowTest annotation = testMethod.getAnnotation(SlowTest.class);
        String testMethodName = testMethod.getName();
        ExtensionContext.Store store = getStore(context);
        Long start_time = store.remove("START_TIME", long.class);
        long duration = System.currentTimeMillis() - start_time;
        if (duration > Threshold && annotation == null) {
            System.out.printf("please consider mark method [%s] with @SlowTest.\n", testMethodName);
        }
    }
    private static ExtensionContext.Store getStore(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        return context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
    }
}
