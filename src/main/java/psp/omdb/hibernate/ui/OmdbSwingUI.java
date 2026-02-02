package psp.omdb.hibernate.ui;

import javax.swing.table.DefaultTableModel;
import org.slf4j.Logger;
import psp.omdb.hibernate.dao.MovieActorDAO;
import psp.omdb.hibernate.dao.impl.MovieActorDAOImpl;
import psp.omdb.hibernate.util.LogUtil;
import javax.swing.JOptionPane;


/**
 *
 * @author Jaime Pérez Roget Blanco
 */
public class OmdbSwingUI extends javax.swing.JFrame {

    private DefaultTableModel model;
    private final MovieActorDAO movieActorDAO;
    private static final Logger logger = LogUtil.getLogger(OmdbSwingUI.class);

    public OmdbSwingUI() {

        initComponents();
        initTable();
        
        movieActorDAO = new MovieActorDAOImpl();

    }
    
    private void initTable() {

        model = new DefaultTableModel(
                new String[]{"Película", "Actor"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table.setModel(model);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLoad = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnLoad.setText("Cargar");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Listado de películas y actores (Hibernate + MySQL)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(btnLoad)
                        .addGap(9, 9, 9))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLoad)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Carga las películas y actores desde Hibernate y las muestra en la tabla.
     */
    private void loadMoviesWithActors() {

        model.setRowCount(0);

        try {

            movieActorDAO.findMoviesWithActors()
                    .forEach(r
                            -> model.addRow(new Object[]{r[0], r[1]})
                    );

            logger.info("Datos cargados correctamente en la tabla");

        } catch (Exception e) {

            logger.error("Error cargando datos en la UI", e);

            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar los datos.\nConsulta los logs.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    
    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        
        logger.info("Cargando datos desde Hibernate...");
        
        loadMoviesWithActors();
        
        logger.info("Carga completada. {} filas cargadas", model.getRowCount());
        
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay datos para mostrar",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        
    }//GEN-LAST:event_btnLoadActionPerformed

    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OmdbSwingUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OmdbSwingUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OmdbSwingUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OmdbSwingUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OmdbSwingUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
