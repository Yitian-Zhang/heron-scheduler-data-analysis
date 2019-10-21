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

    public void setTopologyComponent(TopologyComponent component) {
        // TODO: read specify TopologyComponent from yaml file
        this.topologyComponent = component;
    }

    @Override
    public void calculate() {
        topologyComponent.constructOriginalComponent();
        //
        topologyComponent.constructRescheduledComponent();
        topologyComponent.constructApiUrls();
        topologyComponent.calculateThroughput();
    }
}
