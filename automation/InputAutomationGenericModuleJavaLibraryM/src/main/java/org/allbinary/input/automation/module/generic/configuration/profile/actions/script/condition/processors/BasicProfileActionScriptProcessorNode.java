/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.processors;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.data.tree.dom.DomSearchHelper;

public class BasicProfileActionScriptProcessorNode
{
    public BasicProfileActionScriptProcessorNode(Node node)
    throws Exception
    {
        Node actionNode = DomSearchHelper.getNode(
            GenericProfileActionScriptProcessorData.NAME,
            node.getChildNodes());
    }

    public BasicProfileActionScriptProcessorNode()
    {
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(
            GenericProfileActionScriptProcessorData.NAME);
        return node;
    }
}
