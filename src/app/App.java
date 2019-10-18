/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import application.Acceuil;
import java.util.Calendar;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
/**
 *
 * @author Bendenia M'hamed
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Preferences userPref = Preferences.userRoot();
        String PREF_KEY = "current_user";
        if(userPref.getInt(PREF_KEY,-1) == -1){
            userPref.putInt(PREF_KEY, Calendar.getInstance().get(Calendar.DAY_OF_YEAR)+1);
            Acceuil x = new Acceuil();
            x.setLocationRelativeTo(null);
            x.setResizable(false);
            x.setVisible(true);
        }else{
            if(userPref.getInt(PREF_KEY, -1) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR) || userPref.getInt(PREF_KEY,-1) == -2){
                userPref.putInt(PREF_KEY, -2);
                JOptionPane.showMessageDialog(null,"Votre licence a expiré. Pour renouveller votre licence, veuillez contacter l'administrateur\nà l'adresse suivante: medbendenia27@gmail.com");
            }
            else{
                userPref.putInt(PREF_KEY, Calendar.getInstance().get(Calendar.DAY_OF_YEAR)+1);
                Acceuil x = new Acceuil();
                x.setLocationRelativeTo(null);
                x.setResizable(false);
                x.setVisible(true);
            }
        }
    }
    
}
