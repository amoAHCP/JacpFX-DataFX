/************************************************************************
 *
 * Copyright (C) 2010 - 2012
 *
 * [ComponentLeft.java]
 * AHCP Project (http://jacp.googlecode.com)
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 *
 ************************************************************************/
package org.jacpfx.jfxMPD.component;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.message.Message;
import org.jacpfx.jfxMPD.config.BasicConfig;
import org.jacpfx.jfxMPD.controller.Wizard1Controller;
import org.jacpfx.jfxMPD.controller.WizardStartController;
import org.jacpfx.jfxMPD.wrapper.DataFXFlowWrapper;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.util.FXUtil;
import org.jacpfx.rcp.util.LayoutUtil;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * A simple JacpFX UI component
 *
 * @author Andy Moncsek
 */
@View(id = BasicConfig.COMPONENT_LEFT,
        name = "SimpleView",
        active = true,
        resourceBundleLocation = "bundles.languageBundle",
        initialTargetLayoutId = BasicConfig.TARGET_CONTAINER_LEFT)
public class ComponentLeft implements FXComponent {
    private Node pane;
    private Logger log = Logger.getLogger(ComponentLeft.class.getName());
    @Resource
    private Context context;

    @Override
    /**
     * The handle method always runs outside the main application thread. You can create new nodes, execute long running tasks but you are not allowed to manipulate existing nodes here.
     */
    public Node handle(final Message<Event, Object> message) {
        // runs in worker thread

        return null;
    }

    @Override
    /**
     * The postHandle method runs always in the main application thread.
     */
    public Node postHandle(final Node arg0,
                           final Message<Event, Object> message) {
        // runs in FX application thread
        if (message.messageBodyEquals(FXUtil.MessageUtil.INIT)) {

        }
        return this.pane;
    }

    @PostConstruct
    /**
     * The @PostConstruct annotation labels methods executed when the component switch from inactive to active state
     * @param arg0
     * @param resourceBundle
     */
    public void onPostConstructComponent(final FXComponentLayout arg0,
                                         final ResourceBundle resourceBundle) {
        this.pane = createUI();
        this.log.info("run on start of ComponentLeft ");
    }


    /**
     * create the UI on first call
     *
     * @return
     */
    private Node createUI() {
        final VBox main = new VBox();
        main.setSpacing(10);
        main.setPadding(new Insets(0, 20, 10, 20));

        Flow flow = new DataFXFlowWrapper(WizardStartController.class, this.context.getParentId().concat(".").concat(this.context.getId())).
                withLink(WizardStartController.class, "next", Wizard1Controller.class).
                withGlobalBackAction("back").
                withGlobalTaskAction("help", () -> System.out.println("There is no help for the application :("));
        FlowHandler flowHandler = flow.createHandler();
        StackPane pane = null;
        try {
            pane = flowHandler.start(new AnimatedFlowContainer(Duration.millis(320), ContainerAnimations.ZOOM_IN));
        } catch (FlowException e) {
            e.printStackTrace();
        }
        LayoutUtil.VBoxUtil.setVGrow(Priority.ALWAYS, pane);
        main.getChildren().addAll(pane);
        LayoutUtil.GridPaneUtil.setFullGrow(Priority.ALWAYS, main);
        return main;
    }


}
