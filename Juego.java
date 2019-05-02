/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscam;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class Juego extends javax.swing.JFrame implements ActionListener, MouseListener {
    int columnas = 5;
    int filas = 5;
    JButton botones[][];
    int minas[][];
    int pisado = 0;
    String nombre;
    int totalMinas = 10;
    
   
    public Juego() {
        initComponents();
        agregaMinas();
        agregaBotones();
    }
    public Juego(String jugador) {
        this.nombre = jugador;
        initComponents();
        agregaMinas();
        agregaBotones();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void agregaMinas(){
        int x,y;
        minas = new int[columnas][filas];
        for(x=0;x<columnas;x++){
             for(y=0;y<filas;y++){
                minas[x][y] = 0;
             }
         }     
        int num1,num2,i=0;
        
        do{
            num1 = (int) (Math.random() * columnas) ;
            num2 = (int) (Math.random() * columnas) ;
            while(minas[num1][num2]==1){
                num1 = (int) (Math.random() * columnas) ;
                num2 = (int) (Math.random() * columnas) ;
            }
            minas[num1][num2] = 1;
            i++;
            
        }while(i<totalMinas);
        
        for (x = 0;x<columnas;x++){

            for (y = 0;y<filas;y++){

                System.out.print(minas[x][y]);

            }

        System.out.println("");}
        
    }
    
    public void agregaBotones(){
        botones = new JButton[columnas][filas];
         setLayout(new GridLayout(columnas,filas+1));
         int x=0,y=0;
         for(x=0;x<columnas;x++){
             for(y=0;y<filas;y++){
                 botones[x][y] = new JButton("?");

                botones[x][y].addActionListener(this);

                botones[x][y].addMouseListener(this);

                add(botones[x][y]);

                botones[x][y].setEnabled(true);
             }
         }        
         pack(); 
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print("Lawea");
        JButton current = (JButton)e.getSource();

        for (int x = 0;x<columnas;x++){

                for (int y = 0;y<filas;y++){

                    JButton t = botones[x][y];

                    if(t == current){
                        if(tocaMina(x,y)){
                            JOptionPane.showMessageDialog(this, "Pisaste una mina "+nombre+":(((!!!!.");
                            Menu nuevo = new Menu();
                            nuevo.setVisible(true);
                            this.dispose(); 
                        }
                        botones[x][y].setEnabled(false);
                        pisado++;
                        System.out.println("El num es: "+checaVecinos(x,y));
                        botones[x][y].setText(""+checaVecinos(x,y));
                        
                        if(pisado == (columnas*filas)-totalMinas){
                            JOptionPane.showMessageDialog(this, "Felicidades "+nombre+", ganaste!!1");
                            Menu nuevo = new Menu();
                            nuevo.setVisible(true);
                            this.dispose(); 
                        }
                        
                        
                    }

                }//end inner for

            }//end for  
        pack();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {

            Object current = e.getSource();

            for (int x = 0;x<columnas;x++){

                for (int y = 0;y<filas;y++){

                    JButton t = botones[x][y];

                    if(t == current){

                        System.out.print("Yaja");

                    }

                }//end inner for

            }//end for
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
           }

    @Override
    public void mouseEntered(MouseEvent e) {
            }

    @Override
    public void mouseExited(MouseEvent e) {
           }

    private boolean tocaMina(int x, int y) {
        if(minas[x][y] == 1){
            return true; 
        }
        else{
            return false;
        }
    }

    private int checaVecinos(int j, int i) {
        int total = 0;
	int izq,cenx,ceny,der,arr,aba;
	cenx=j;
	ceny=i;
	izq=j-1;
	der=j+1;
	arr=i-1;
	aba=i+1;
	if(i==0 || i==filas-1 || j==0 || j==columnas-1)
	{
		if(i==0)
		{
			arr=0;	
		}
		if(i==filas-1)
		{
			aba=filas-1;
		}
		if(j==0)
		{
			izq=0;
		}
		if(j==columnas-1)
		{
			der=columnas-1;
		}

	}
        for(i = arr; i <= aba; i++)
        {
            for(j = izq; j <= der; j++)
            {
                    if(cenx==j && ceny ==i){
                                                            //no suma si esta en la casilla de la casilla que checa
                    }else{
                            total =  total + minas[j][i];
                    }
            }
        }
	return total;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
