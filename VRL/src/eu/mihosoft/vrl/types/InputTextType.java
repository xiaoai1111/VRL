/* 
 * InputTextType.java
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2009–2015 Steinbeis Forschungszentrum (STZ Ölbronn),
 * Copyright (c) 2007–2017 by Michael Hoffer
 * 
 * This file is part of Visual Reflection Library (VRL).
 *
 * VRL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * see: http://opensource.org/licenses/LGPL-3.0
 *      file://path/to/VRL/src/eu/mihosoft/vrl/resources/license/lgplv3.txt
 *
 * VRL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * This version of VRL includes copyright notice and attribution requirements.
 * According to the LGPL this information must be displayed even if you modify
 * the source code of VRL. Neither the VRL Canvas attribution icon nor any
 * copyright statement/attribution may be removed.
 *
 * Attribution Requirements:
 *
 * If you create derived work you must do three things regarding copyright
 * notice and author attribution.
 *
 * First, the following text must be displayed on the Canvas:
 * "based on VRL source code". In this case the VRL canvas icon must be removed.
 * 
 * Second, the copyright notice must remain. It must be reproduced in any
 * program that uses VRL.
 *
 * Third, add an additional notice, stating that you modified VRL. A suitable
 * notice might read
 * "VRL source code modified by YourName 2012".
 * 
 * Note, that these requirements are in full accordance with the LGPL v3
 * (see 7. Additional Terms, b).
 *
 * Please cite the publication(s) listed below.
 *
 * Publications:
 *
 * M. Hoffer, C. Poliwoda, & G. Wittum. (2013). Visual reflection library:
 * a framework for declarative GUI programming on the Java platform.
 * Computing and Visualization in Science, 2013, 16(4),
 * 181–192. http://doi.org/10.1007/s00791-014-0230-y
 */

package eu.mihosoft.vrl.types;

import eu.mihosoft.vrl.annotation.TypeInfo;
import eu.mihosoft.vrl.lang.CompilerProvider;
import eu.mihosoft.vrl.lang.VLangUtils;
import eu.mihosoft.vrl.lang.visual.EditorProvider;
import eu.mihosoft.vrl.reflection.RepresentationType;
import eu.mihosoft.vrl.reflection.TypeRepresentationBase;
import eu.mihosoft.vrl.visual.VBoxLayout;
import eu.mihosoft.vrl.visual.VCodeEditor;
import eu.mihosoft.vrl.visual.VContainer;
import java.awt.Color;

/**
 * TypeRepresentation for
 * <code>java.lang.String</code>.
 *
 * <p>Sample:</p> <br/> <img src="doc-files/string-text-01.png"/> <br/>
 * 
 * Style name: "editor"
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
@TypeInfo(type=String.class, input = true, output = false, style="editor")
public class InputTextType extends TypeRepresentationBase {

    private VCodeEditor editor;
    private int minimumEditorWidth = 120;
    private int minimumEditorHeight = 24;
    private VContainer editorContainer = new VContainer();

    public InputTextType() {

        VBoxLayout layout = new VBoxLayout(this, VBoxLayout.Y_AXIS);

        setLayout(layout);

        nameLabel.setText("Text:");
        nameLabel.setAlignmentX(0.5f);
        this.add(nameLabel);

        editor = EditorProvider.getEditor(CompilerProvider.LANG_NONE, this);

        editorContainer.add(editor);

        // add minimum editor width functionality
        editorContainer.setMinPreferredWidth(minimumEditorWidth);
        editorContainer.setMinPreferredHeight(minimumEditorHeight);


        add(editorContainer);

        setInputDocument(editor.getEditor().getDocument());
    }

    @Override
    public void emptyView() {
        editor.getEditor().setText("");
    }

    @Override
    public void setViewValue(Object o) {
        emptyView();
        editor.getEditor().setText(o.toString());
    }

    @Override
    public Object getViewValue() {
        Object o = null;
        try {
            o = editor.getEditor().getText();
        } catch (Exception ex) {
        }
        return o;
    }

    /**
     * @return the editor
     */
    public VCodeEditor getEditor() {
        return editor;
    }

    /**
     * @param editor the editor to set
     */
    public void setEditor(VCodeEditor editor) {
        this.editor = editor;
    }

    /**
     * @return the minimumEditorWidth
     */
    public Integer getMinimumEditorWidth() {
        return minimumEditorWidth;
    }

    /**
     * @param minimumEditorWidth the minimumEditorWidth to set
     */
    public void setMinimumEditorWidth(Integer minimumEditorWidth) {
        this.minimumEditorWidth = minimumEditorWidth;
        editorContainer.setMinPreferredWidth(minimumEditorWidth);
        editor.revalidate();
    }

//    @Override()
//    public String getValueAsCode() {
//        String result = "null";
//
//        Object o = getValue();
//
//        if (o != null) {
//            result = "\"" + o.toString() + "\"";
//        }
//
//        return result;
//    }
    
    @Override
    public String getValueAsCode() {
        return "\""
                + VLangUtils.addEscapesToCode(getValue().toString()) + "\"";
    }
}
