//Authors:  Joel Menja
//          Manuel Ucles
//          Michael Warnick
package CSE360;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.Charset;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.*;


import org.json.*;


public class Team6  extends JPanel{
    
     public static String readThis(Reader rd) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		int cp;
		
		while((cp = rd.read()) != -1){
			sb.append((char) cp);
			
		}
		
		return sb.toString();
	}
	
	
	public static JSONObject readURL(String url) throws IOException, JSONException{
	
		 InputStream in =  new URL(url).openStream();
		 try{
			 BufferedReader read = new BufferedReader(
					 new InputStreamReader(in, Charset.forName("UTF-8")));
			 String jsonText = readThis(read);
			 JSONObject jsn = new JSONObject(jsonText);
			 
			 return jsn;
			 
		 } finally {
			 
			 in.close();
		 }
    }
        
   /* HomeWork 3 */ 
        
      private static JDialog d;
       static String city;
      
    public static String newDialog(){
           
           /* make combobox */
        // String city = "";
         String country[]={"LosAngeles","Tempe","Dallas","NewYork", "SanJose", "Nairobi", "Tokyo", "Dubai", "Chicago", "Johanesberg"};        
         JComboBox cb=new JComboBox(country);  
           
           
        JFrame frame = new JFrame();  
        d = new JDialog(frame , "Weather Forcast", true);  
        d.setLayout( new FlowLayout() );  
        JButton ok = new JButton ("OK");
        JButton c = new JButton ("cancel");
        c.addActionListener ( new ActionListener()   // cancel button
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                Team6.d.setVisible(false);  
            }  
        });
        
        ok.addActionListener ( new ActionListener()    // okay button
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                String c;
                city = (String) cb.getItemAt(cb.getSelectedIndex());
               // System.out.println("Selected city " + city);
                Team6.d.setVisible(false);
               
                //city = c;
            }  
        });
        
        d.add( new JLabel ("Select a City."));
        d.add(cb);
        d.add(c);
        d.add(ok);   
        d.setSize(300,100);    
        d.setVisible(true); 
        
        
         return city;
         
     }
       
   public String setURL(String ci){

       double lon = 0.00; 
       double lat = 0.00;
     //System.out.println(ci);
      // String en = "https://api.darksky.net/forecast/4f02d91363f259f5ca95263c5c032dfc/";
      
       switch(ci){
           
           case "Dubai":
               lat = 25.204849;
               lon = 55.270783;
               break;
           case "Nairobi":
               lat = -1.292066;
               lon = 36.821946;
               break;
               
             case "Tokyo":
               lat = 35.689487;
               lon = 139.691706;
               break;
            case "Chicago":
               lat = 41.878114;
               lon = -87.629798;
               break;
            case "NewYork":
               lat = 40.712784;
               lon = -74.005941;
               break;
           case "LosAngeles":
               lat = 34.052234;
               lon = -118.243685;
               break;
           case "SanJose":
               lat = 37.338208;
               lon = -121.886329;
               break;
           case "Tempe":
               lat = 33.425510;
               lon = -111.940005;
               break;
           case "Johanesberg":
               lat = -26.204103;
               lon = 28.047305;
               break;
            case "Dallas":
               lat = 32.776664;
               lon = -96.796988;
               break;
               
           default:
                 lat = 32.776664;
                 lon = -96.796988;
               break;
                 
       }
      
       String en = "https://api.darksky.net/forecast/4f02d91363f259f5ca95263c5c032dfc/" + Double.toString(lat) + "," + Double.toString(lon);
      // System.out.println(en);
       return en;
    }
     
   /********************** HomeWork 3 ******************************************/ 
     String cf = "";
     String sa = "";
     String weather = "";
     JSONObject jon;
     double dewPoint = 0.00;
     double visibility = 0.00;
     double humidity = 0.00;
     double time = 0.00;
     int temperature = 0;
      JLabel label, label2,label3, label4;
    public Team6() {
        JLabel label1 = new JLabel(" Weather: ");

        JLabel label2 = new JLabel("\n Temperature: ");

        JLabel label3 = new JLabel("\n Humidity: ");

        JLabel label4 = new JLabel("\n Visibility: ");

        JLabel label5 = new JLabel("\n dewPoint: ");
        
        JLabel map = new JLabel( new ImageIcon(( new ImageIcon("image.jpg")).getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
                 
        JPanel panel = new JPanel();
        // panel.setBackground(Color.RED);
         panel.setPreferredSize(new Dimension(150, 150));
         panel.setVisible(true);

         JPanel panel2 = new JPanel();
         //panel.setBackground(Color.YELLOW);
         panel2.setPreferredSize(new Dimension(150,150));
         panel2.setVisible(true);
        
	try {
            
            /************************** Start try and catch **************************************/
           Double atl, lon;
           
           setPreferredSize(new Dimension(500,500));
           
           jon = readURL("https://api.darksky.net/forecast/4f02d91363f259f5ca95263c5c032dfc/33.6744664,-112.1386462");
             JButton dialog = new JButton("city");
                dialog.addActionListener(new ActionListener(){
                    
                     public void actionPerformed( ActionEvent e ) 
                             
                      {  
                        try {
				sa = newDialog();
				System.out.println(sa);

				cf = setURL(sa);
				//System.out.println(cf);

				jon = readURL(cf);
				System.out.println(jon);

				String degree = "\u00b0";
				Font myFont = new Font("Serif", Font.BOLD, 12);   
				weather = jon.getJSONObject("currently").getString("summary");
				temperature = jon.getJSONObject("currently").getInt("temperature");
				time = jon.getJSONObject("currently").getDouble("time");
				humidity = jon.getJSONObject("currently").getDouble("humidity");
				visibility = jon.getJSONObject("currently").getDouble("visibility");
				System.out.println(visibility);
				dewPoint = jon.getJSONObject("currently").getDouble("dewPoint");
			       // JLabel label = new JLabel(" Weather: " + weather);
                        
				label1.setText(" Weather: " + weather);

				label2.setText("\n Temperature: " + Double.toString(temperature) + degree  + "F");

				label3.setText("\n Humidity: " + Double.toString(humidity) + " ");

				label4.setText("\n Visibility: " + Double.toString(visibility) + "%");

				label5.setText("\n dewPoint: " + Double.toString(dewPoint) + degree +  "F ");
                        
				String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center=" + city + "&zoom=12&size=150x150";
				System.out.println(imageURL);
				String destinationFile = "image.jpg";
				URL url = new URL(imageURL);

				java.io.InputStream in = url.openStream();
				OutputStream out = new FileOutputStream(destinationFile);

				byte b[] = new byte[2048];
				int lenght;

				while((lenght = in.read(b)) != -1){
					out.write(b,0,lenght); //change
				}
				URL urlmap = new URL(imageURL);
				BufferedImage img = ImageIO.read(urlmap);
				ImageIcon icon = new ImageIcon(img);

				map.setIcon(icon);
				panel.add(map);
				in.close();
				out.close();

				panel2.add(label1);
				panel2.add(label2);
				panel2.add(label3);
				panel2.add(label4);
				panel2.add(label5);
				panel2.add(dialog);

				add(panel);

				add(panel2);

				setVisible(true);
                        
                      
                        } catch (IOException ex) {
                            Logger.getLogger(Team6.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JSONException ex) {
                            Logger.getLogger(Team6.class.getName()).log(Level.SEVERE, null, ex);
                        }
    
                      }
                     
                });
                 panel.add(map);
                 
                 panel2.add(label1);
                 panel2.add(label2);
                 panel2.add(label3);
                 panel2.add(label4);
                 panel2.add(label5);
                 panel2.add(dialog);

                 add(panel);
                 add(panel2);

        } catch(IOException e){
                 System.exit(1);     
        } catch(JSONException e){
                 System.exit(1);       
        }
    }
    
}
