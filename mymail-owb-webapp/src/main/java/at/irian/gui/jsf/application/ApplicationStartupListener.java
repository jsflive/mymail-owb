package at.irian.gui.jsf.application;

import at.irian.gui.util.CdiUtil;
import at.irian.service.InitializationService;

import javax.faces.application.Application;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/**
 *
 */
public class ApplicationStartupListener implements SystemEventListener {

    public boolean isListenerForSource(Object source) {
        return source instanceof Application;
    }

    public void processEvent(SystemEvent event) {
        InitializationService initializationService = CdiUtil.resolveBean(InitializationService.class);
        initializationService.init();
    }

}
