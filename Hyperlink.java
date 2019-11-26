package example.drawing;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.text.html.*;
public class Hyperlink extends JScrollPane{
	public static EditingArea editor;
	 final Clipboard clipboard;
	 static String copiedSelection = null;
	 Hyperlink(){
		 super();
	        editor = new  EditingArea();
	        LinkController handler = new LinkController();
	        this.setViewportView(editor);
	        this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
	        editor.addMouseListener(handler );
	        clipboard = getToolkit().getSystemClipboard();
	        editor.addMouseMotionListener( handler );
	 }
	 public class EditingArea extends JTextPane{
		 public void append(Color c, String text) {
			 try {
				 Document doc = editor.getDocument();
	                SimpleAttributeSet attrs = new SimpleAttributeSet();
	                StyleConstants.setForeground(attrs, c);
	                doc.insertString(doc.getLength(), text, attrs);
			 }catch(BadLocationException e) {
				 e.printStackTrace(System.err);
			 }
		 }
	 public void addHyperLink(URL url, String text, Color color) {
		 try {
			 Document doc = editor.getDocument();
             SimpleAttributeSet attrs = new SimpleAttributeSet();
             StyleConstants.setUnderline(attrs, true);
             StyleConstants.setForeground(attrs, color);//unvisited color
             //StyleConstants.setFontSize(null, 13);
             attrs.addAttribute(HTML.Attribute.HREF, url.toString());
             doc.insertString(doc.getLength(), text, attrs);
		 }catch(BadLocationException e) {
			 e.printStackTrace(System.err);
		 }
	 }
	 }
	 public class LinkController extends MouseAdapter implements MouseMotionListener{
	     public void mouseReleased(MouseEvent e){
	                copiedSelection =editor.getSelectedText();
	                if(copiedSelection!=null){
	                    StringSelection data = new StringSelection(copiedSelection);
	                    clipboard.setContents(data, data);
	                }
	            }
	           public void mouseClicked(MouseEvent e){
	              java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
	              JTextPane editor = (JTextPane) e.getSource();
	              Document doc =  editor.getDocument();
	              Point pt = new Point(e.getX(), e.getY());
	              int pos = editor.viewToModel(pt);
	              if (pos >= 0){
	                 if (doc instanceof DefaultStyledDocument){
	                      DefaultStyledDocument hdoc = (DefaultStyledDocument) doc;
	                      Element el = hdoc.getCharacterElement(pos);
	                      AttributeSet a = el.getAttributes();
	                      String href = (String) a.getAttribute(HTML.Attribute.HREF);
	                      if (href != null){
	                        try{
	                              java.net.URI uri = new java.net.URI( href );
	                              desktop.browse( uri );
	                        }
	                          catch ( Exception ev ){
	                              System.err.println( ev.getMessage() );
	                          }
	                      }                      
	                  }//if (doc instanceof DefaultStyledDocument)
	              }// if (pos >= 0)
	          }// public void mouseClicked(MouseEvent e)
	          public void mouseMoved(MouseEvent ev){
	              Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	              Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR);
	              JTextPane editor = (JTextPane) ev.getSource();
	              Point pt = new Point(ev.getX(), ev.getY());
	              int pos = editor.viewToModel(pt);
	              if (pos >= 0){
	                Document doc = editor.getDocument();
	                if (doc instanceof DefaultStyledDocument){
	                  DefaultStyledDocument hdoc = (DefaultStyledDocument) doc;
	                  Element e = hdoc.getCharacterElement(pos);
	                  AttributeSet a = e.getAttributes();
	                  String href = (String) a.getAttribute(HTML.Attribute.HREF);
	                  if (href != null){
	                      if (getCursor() != handCursor){                         
	                        editor.setCursor(handCursor);
	                      }
	                  }
	                  else{        
	                        editor.setCursor(defaultCursor);                    
	                  }
	                }//(doc instanceof DefaultStyledDocument)
	              }//pos >=0
	              else
	              {
	                setToolTipText(null);
	              }
	            }//mouseMoved
	        }//LinkController
}
