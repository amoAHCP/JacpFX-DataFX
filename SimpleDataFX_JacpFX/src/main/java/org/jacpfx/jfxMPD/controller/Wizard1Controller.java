/*
 * **********************************************************************
 *
 *  Copyright (C) 2010 - 2014
 *
 *  [Component.java]
 *  JACPFX Project (https://github.com/JacpFX/JacpFX/)
 *  All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS"
 *  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied. See the License for the specific language
 *  governing permissions and limitations under the License.
 *
 *
 * *********************************************************************
 */

package org.jacpfx.jfxMPD.controller;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.jfxMPD.config.BasicConfig;
import org.jacpfx.rcp.context.Context;

import javax.annotation.PostConstruct;

/**
 * Created by amo on 20.10.14.
 */
@FXMLController(value = "/fxml/wizard1.fxml", title = "Wizard: Step 1")
public class Wizard1Controller {

    @FXML
    @ActionTrigger("back")
    private Button backButton;


    @FXML
    @ActionTrigger("next")
    private Button nextButton;

    @Resource
    private Context context;

    @FXMLViewFlowContext
    private ViewFlowContext vContext;

    @FXML
    private TextField name;

    @FXML
    @ActionTrigger("help")
    private Hyperlink helpLink;


    @PostConstruct
    public void init() {
        getNextButton().setDisable(true);
        name.setOnKeyReleased(event -> {
            final String nameValue = name.getText();
            if (context.getParentId().equals(BasicConfig.PERSPECTIVE_ONE)) {
                context.send(BasicConfig.PERSPECTIVE_ONE.concat(".").concat(BasicConfig.COMPONENT_RIGHT), nameValue);
            } else {
                context.send(BasicConfig.PERSPECTIVE_TWO.concat(".").concat(BasicConfig.COMPONENT_RIGHT), nameValue);
            }
        });
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getNextButton() {
        return nextButton;
    }
}
