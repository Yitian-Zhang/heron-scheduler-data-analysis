package heron.scheduler.data.monitor.throughput;

import com.twitter.heron.spi.common.Config;
import heron.scheduler.data.monitor.common.Component;
import heron.scheduler.data.monitor.common.Monitor;
import heron.scheduler.data.utils.YamlUtils;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * @author yitian
 */
public class ThroughputMonitor extends Monitor {
    private static Logger logger = Logger.getLogger(ThroughputMonitor.class.getName());

    private Component component;

    // read specify Component from yaml file
    public ThroughputMonitor() {
        component = (Component) loadTopologyComponent();
    }

    @Override
    public void calculateOriginThroughput() {
        component.constructOriginalComponent();
        component.calculateThroughput();
    }

    @Override
    public void calculateRescheuleThroughput() {
        component.constructRescheduledComponent();
        component.calculateThroughput();
    }

    private Object loadTopologyComponent() {
        String className = (String) YamlUtils.getInstance().getValueByKey("throughput-topology", "active");
        Class clazz = null;
        try {
            // create instance of AuroraRescheduler
            clazz = Class.forName(className);
            logger.info("Loaded the topology class is: " + clazz.getName());

            // invoke the initialize function
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("constructApiUrls", Config.class, Config.class);
            method.invoke(obj);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
