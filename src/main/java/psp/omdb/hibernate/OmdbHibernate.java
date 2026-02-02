package psp.omdb.hibernate;

import psp.omdb.hibernate.ui.OmdbSwingUI;

public class OmdbHibernate {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new OmdbSwingUI().setVisible(true);
        });
        
    }
    
}
