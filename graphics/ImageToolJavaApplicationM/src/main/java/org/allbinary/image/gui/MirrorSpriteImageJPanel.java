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
package org.allbinary.image.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.ImageIO;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.image.BufferedImageUtil2;
import org.allbinary.media.image.MirrorImageUtil;

public class MirrorSpriteImageJPanel extends javax.swing.JPanel
   implements ImageProcessorInputCompositeInterface
{
   private ImageProcessorInput imageProcessorInput;
   private BufferedImage result;

   public MirrorSpriteImageJPanel(ImageProcessorInput imageProcessorInput)
      throws Exception
   {
      super();

      LogUtil.put(LogFactory.getInstance("Starting", this, "Constructor"));

      initComponents();
      this.imageProcessorInput = imageProcessorInput;

   //setUI((PanelUI)UIManager.getUI(this));
   }

   public void process()
   {
      new Thread()
      {

         public void run()
         {
            try
            {
               BufferedImage generatedBufferedImageArray[];

               ImageProcessorInput imageProcessorInput =
                  MirrorSpriteImageJPanel.this.getImageProcessorInput();
               BufferedImage[] bufferedImageArray =
                  imageProcessorInput.getBufferedImageArray();

               for (int index = 0; index < bufferedImageArray.length; index++)
               {
                  generatedBufferedImageArray =
                     MirrorImageUtil.getImages(
                     bufferedImageArray[index], 
                     MirrorSpriteImageJPanel.this.verticleJCheckBox.isSelected(),
                     MirrorSpriteImageJPanel.this.horizontalJCheckBox.isSelected());

                  MirrorSpriteImageJPanel.this.result =
                     BufferedImageUtil2.createSpriteImage(
                     generatedBufferedImageArray);

                  MirrorSpriteImageJPanel.this.getParent().repaint();

                  File file = imageProcessorInput.getFiles()[index];
                  if (!MirrorSpriteImageJPanel.this.writeOverOriginalJCheckBox.isSelected())
                  {
                     String filePath = file.getAbsolutePath();
                     
                     int extensionIndex = filePath.indexOf(".png");
                     
                     filePath = filePath.substring(0, extensionIndex) + "_mirror" + ".png";
                     
                     LogUtil.put(LogFactory.getInstance("Renamed File: " + filePath, this, ""));
                     
                     file = new File(filePath);
                  }

                  boolean isWritten = ImageIO.write(
                     (RenderedImage) MirrorSpriteImageJPanel.this.result,
                     "PNG", file);

                  LogUtil.put(LogFactory.getInstance("File: " + file + " Wrote: " + isWritten, this, ""));

               }

            }
            catch (Exception e)
            {
               LogUtil.put(LogFactory.getInstance("Exception", this, "run", e));
            }
         }
      }.start();
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLayeredPane1 = new javax.swing.JLayeredPane();
      jPanel1 = new javax.swing.JPanel(){
         public void paint(Graphics graphics)
         {
            if(MirrorSpriteImageJPanel.this.result != null)
            {
               graphics.drawImage(MirrorSpriteImageJPanel.this.result, 0, 0,
                  MirrorSpriteImageJPanel.this.result.getWidth(null),
                  MirrorSpriteImageJPanel.this.result.getHeight(null), null);
            }
         }
      }
      ;
      jPanel2 = new javax.swing.JPanel();
      imageHeightIsFrameSizeJCheckBox = new javax.swing.JCheckBox();
      horizontalJCheckBox = new javax.swing.JCheckBox();
      verticleJCheckBox = new javax.swing.JCheckBox();
      writeOverOriginalJCheckBox = new javax.swing.JCheckBox();
      generateJButton = new javax.swing.JButton();
      jPanel3 = new javax.swing.JPanel();
      jLabel2 = new javax.swing.JLabel();

      org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 507, Short.MAX_VALUE)
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 230, Short.MAX_VALUE)
      );

      imageHeightIsFrameSizeJCheckBox.setSelected(true);
      imageHeightIsFrameSizeJCheckBox.setText("Image Height Is Frame Size");
      imageHeightIsFrameSizeJCheckBox.setEnabled(false);
      imageHeightIsFrameSizeJCheckBox.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            imageHeightIsFrameSizeJCheckBoxActionPerformed(evt);
         }
      });

      horizontalJCheckBox.setSelected(true);
      horizontalJCheckBox.setText("Horizontal");
      horizontalJCheckBox.setEnabled(false);

      verticleJCheckBox.setText("Verticle");
      verticleJCheckBox.setEnabled(false);

      writeOverOriginalJCheckBox.setSelected(true);
      writeOverOriginalJCheckBox.setText("Write Over Original");

      generateJButton.setText("Generate");
      generateJButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            generateJButtonActionPerformed(evt);
         }
      });

      org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
      jPanel2.setLayout(jPanel2Layout);
      jPanel2Layout.setHorizontalGroup(
         jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jPanel2Layout.createSequentialGroup()
            .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(verticleJCheckBox)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(horizontalJCheckBox)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(imageHeightIsFrameSizeJCheckBox)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(writeOverOriginalJCheckBox)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(generateJButton))
      );
      jPanel2Layout.setVerticalGroup(
         jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jPanel2Layout.createSequentialGroup()
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(imageHeightIsFrameSizeJCheckBox, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .add(horizontalJCheckBox)
               .add(verticleJCheckBox)
               .add(writeOverOriginalJCheckBox)
               .add(generateJButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
      );

      jLabel2.setText("Results:");

      org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
      jPanel3.setLayout(jPanel3Layout);
      jPanel3Layout.setHorizontalGroup(
         jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jPanel3Layout.createSequentialGroup()
            .add(jLabel2)
            .addContainerGap(448, Short.MAX_VALUE))
      );
      jPanel3Layout.setVerticalGroup(
         jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jLabel2)
      );

      org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
         .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         .add(layout.createSequentialGroup()
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
   }// </editor-fold>//GEN-END:initComponents
    private void generateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateJButtonActionPerformed

       this.process();

}//GEN-LAST:event_generateJButtonActionPerformed

private void imageHeightIsFrameSizeJCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageHeightIsFrameSizeJCheckBoxActionPerformed
}//GEN-LAST:event_imageHeightIsFrameSizeJCheckBoxActionPerformed
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton generateJButton;
   private javax.swing.JCheckBox horizontalJCheckBox;
   private javax.swing.JCheckBox imageHeightIsFrameSizeJCheckBox;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLayeredPane jLayeredPane1;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JCheckBox verticleJCheckBox;
   private javax.swing.JCheckBox writeOverOriginalJCheckBox;
   // End of variables declaration//GEN-END:variables
   public ImageProcessorInput getImageProcessorInput()
   {
      return imageProcessorInput;
   }

   public void setImageProcessorInput(ImageProcessorInput imageProcessorInput)
   {
      this.imageProcessorInput = imageProcessorInput;
   }
}
