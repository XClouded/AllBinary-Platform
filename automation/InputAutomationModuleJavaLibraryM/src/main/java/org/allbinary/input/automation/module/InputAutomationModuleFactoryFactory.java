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
package org.allbinary.input.automation.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;
import javax.help.HelpSet;
import javax.help.event.HelpSetEvent;
import javax.help.event.HelpSetListener;
import javax.swing.ListModel;

import org.allbinary.input.automation.module.configuration.InputAutomationModuleConfiguration;
import org.allbinary.input.automation.module.configuration.InputAutomationModuleConfigurations;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.help.JavaHelpSetNotifier;
import org.allbinary.input.automation.configuration.InputAutomationConfiguration;
import org.allbinary.input.automation.configuration.InputAutomationConfigurationFactory;
import org.allbinary.input.automation.module.configuration.InputAutomationModuleConfigurationsSingletonFactory;
import org.allbinary.logic.communication.log.LogFactory;

public class InputAutomationModuleFactoryFactory
{
    private HashMap hashMap;
    private DefaultListModelHelper defaultListModelHelper;
    private HelpSetListener helpSetListenerInterface;
    
    public InputAutomationModuleFactoryFactory(
        HelpSetListener helpSetListenerInterface)
    throws Exception
    {
        this.helpSetListenerInterface = helpSetListenerInterface;
        this.defaultListModelHelper = new DefaultListModelHelper();
        
        this.hashMap = new HashMap();
        
        InputAutomationConfiguration inputAutomationConfiguration =
            InputAutomationConfigurationFactory.getInstance();
        
        InputAutomationModuleConfigurations inputAutomationModuleConfigurations =
          InputAutomationModuleConfigurationsSingletonFactory.getInstance();

        Collection collection =
            inputAutomationModuleConfigurations.getHashMap().values();
        Iterator iterator = collection.iterator();
        while(iterator.hasNext())
        {
            InputAutomationModuleConfiguration inputAutomationModuleConfiguration =
                (InputAutomationModuleConfiguration) iterator.next();
            
            this.add(inputAutomationModuleConfiguration);
        }
        
        this.defaultListModelHelper.initDefaultModelList();
        LogUtil.put(LogFactory.getInstance("Loaded " + this.hashMap.size() + " Input Automation Modules", this,"Contructor"));
    }
    
    private void add(
        InputAutomationModuleConfiguration inputAutomationModuleConfiguration)
    {
        InputAutomationModuleFactoryInterface inputAutomationModuleInterface =
            inputAutomationModuleConfiguration.getInputAutomationModuleInterface();

        this.hashMap.put(inputAutomationModuleConfiguration.getName(),
            inputAutomationModuleInterface);
        this.defaultListModelHelper.add(inputAutomationModuleConfiguration.getName());

        HelpSet helpSet = inputAutomationModuleInterface.getHelpSet();
        if(!JavaHelpSetNotifier.isNotified(helpSet))
        {
            HelpSetEvent helpSetEvent = new HelpSetEvent(
                this, helpSet, HelpSetEvent.HELPSET_ADDED);
            this.helpSetListenerInterface.helpSetAdded(helpSetEvent);
        }
    }
    
    public ListModel getListModel()
    {
        return (ListModel) this.defaultListModelHelper.getListModel();
    }
    
    public InputAutomationModuleFactoryInterface getInstance(String moduleName)
    {
        return (InputAutomationModuleFactoryInterface) this.hashMap.get(moduleName);
    }
}
