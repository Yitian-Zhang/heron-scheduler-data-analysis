package heron.scheduler.data.monitor.throughput;

import heron.scheduler.data.monitor.common.Monitor;
import heron.scheduler.data.monitor.common.TopologyComponent;

/**
 * @author yitian
 */
public class ThroughputMonitor extends Monitor {

    private TopologyComponent topologyComponent;

    public ThroughputMonitor() {
    }

    // TODO: read specify TopologyComponent from yaml file
    public void setTopologyComponent(TopologyComponent component) {
        this.topologyComponent = component;
    }

    public void init() {
        topologyComponent.constructOriginalComponent();
        //
        topologyComponent.constructRescheduledComponent();
        topologyComponent.constructApiUrls();
    }

    @Override
    public void calculate() {
        topologyComponent.calculateThroughput();
    }
}
