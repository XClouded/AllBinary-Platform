<%
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
%>

<%
   //Custom View Objects   
   final String HEDGECLASSPATH = "hedges" + ".";
   final String HEADERCLASSPATH = HEDGECLASSPATH + "header";
   final String LEFTCLASSPATH = HEDGECLASSPATH + "left";
   final String RIGHTCLASSPATH = HEDGECLASSPATH + "right";
   final String FOOTERCLASSPATH = HEDGECLASSPATH + "footer";
   
   final String HEADERPOSTCLASSPATH = "Header";
   final String LEFTPOSTCLASSPATH = "Left";
   final String RIGHTPOSTCLASSPATH = "Right";
   final String FOOTERPOSTCLASSPATH = "Footer";
   
   //Insert Hedges
   
   //pre
   HashMap viewNameKeyAndCustomizerHedgesClassPathsPreHashMap = new HashMap();

   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(HEADERPAGE,HEADERCLASSPATH);
   /*
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(HEADERALLPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(HEADERCUSTOMEREXISTINGPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(HEADERPRODUCTSPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(HEADERSERVICESPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(HEADERSERVICESCLIENTAREAPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(HEADERSUBSCRIPTIONPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(HEADERSUPPORTPAGE,HEADERCLASSPATH);
   
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(LEFTALLHEDGEPAGE,LEFTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(LEFTCUSTOMERHEDGEPAGE,LEFTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(LEFTPRODUCTSHEDGEPAGE,LEFTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(LEFTSERVICESHEDGEPAGE,LEFTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(LEFTSUBSCRIPTIONHEDGEPAGE,LEFTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(LEFTSUPPORTHEDGEPAGE,LEFTCLASSPATH);
   
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(RIGHTALLHEDGEPAGE,RIGHTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(RIGHTCUSTOMERHEDGEPAGE,RIGHTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(RIGHTPRODUCTSHEDGEPAGE,RIGHTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(RIGHTSERVICESHEDGEPAGE,RIGHTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(RIGHTSUBSCRIPTIONHEDGEPAGE,RIGHTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(RIGHTSUPPORTHEDGEPAGE,RIGHTCLASSPATH);
   */
   
   viewNameKeyAndCustomizerHedgesClassPathsPreHashMap.put(FOOTERPAGE, FOOTERCLASSPATH);
   
   //post
   HashMap viewNameKeyAndCustomizerHedgesClassPathsPostHashMap = new HashMap();
   
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(HEADERPAGE, HEADERPOSTCLASSPATH);
   /*
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(HEADERALLPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(HEADERCUSTOMEREXISTINGPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(HEADERPRODUCTSPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(HEADERSERVICESPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(HEADERSERVICESCLIENTAREAPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(HEADERSUBSCRIPTIONPAGE,HEADERCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(HEADERSUPPORTPAGE,HEADERCLASSPATH);
   
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(LEFTALLHEDGEPAGE,LEFTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(LEFTCUSTOMERHEDGEPAGE,LEFTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(LEFTPRODUCTSHEDGEPAGE,LEFTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(LEFTSERVICESHEDGEPAGE,LEFTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(LEFTSUBSCRIPTIONHEDGEPAGE,LEFTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(LEFTSUPPORTHEDGEPAGE,LEFTCLASSPATH);
   
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(RIGHTALLHEDGEPAGE,RIGHTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(RIGHTCUSTOMERHEDGEPAGE,RIGHTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(RIGHTPRODUCTSHEDGEPAGE,RIGHTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(RIGHTSERVICESHEDGEPAGE,RIGHTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(RIGHTSUBSCRIPTIONHEDGEPAGE,RIGHTCLASSPATH);
   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(RIGHTSUPPORTHEDGEPAGE,RIGHTCLASSPATH);
   */

   viewNameKeyAndCustomizerHedgesClassPathsPostHashMap.put(FOOTERPAGE, FOOTERPOSTCLASSPATH);
%>