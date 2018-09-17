package logging;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SelenideStepsListener implements StepLifecycleListener {

    @Override
    public void beforeStepStart(StepResult result) {
        setNameForAllureReport(result);
    }

    @Override
    public void afterStepStart(StepResult result) { }

    @Override
    public void beforeStepUpdate(StepResult result) { }

    @Override
    public void afterStepUpdate(StepResult result) { }

    @Override
    public void beforeStepStop(StepResult result) { }

    @Override
    public void afterStepStop(StepResult result) { }

    private void setNameForAllureReport(final StepResult result) {
        String name = "";
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (stackTraceElement.getClassName().startsWith("com.bonial.core.kdt.widget")) {
                name = stackTraceElement.getFileName().replaceAll("\\.java", "");
                break;
            }
        }
        result.setName(!"".equals(name) ? name + "." + result.getName() : result.getName());
    }
}

