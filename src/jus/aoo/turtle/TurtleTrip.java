/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aoo.turtle;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Window;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JApplet;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.GridLayout;

import jus.aoo.geometrie.DrawingSpace;
import jus.aoo.geometrie.Figure;
import jus.aoo.geometrie._NewFigure;

/**
 * Applet proposant une Interface utilisateur pour la manipulation
 * d'une tortue � la Logo.
 * 
 * Cette interface peut �tre invoqu�e :<BR>
 * <UL>
 *    <LI>soit comme une applet dans le contexte d'une page html
 *    (charger par exemple la page testTurtle.htm avec un navigateur
 *    �quip� d'une machine virtuelle java 2, ou utiliser l'application
 *    appletviewer).
 *    </LI>
 *    <LI>soit comme une application autonome (lanc�e par la commande
 *    java TurtleTrip.
 * </UL>
 * 
 * @author	P.Morat ou http://imag.fr/Philippe.Morat ...
 * 
 * @version	1.0 date : 1/9/99
 * @version      1.1 date : 26/10/2001 Philippe Genoud
 * @version      2.0 date : 26/1/2006 Philippe Morat
 * 
 * @motcle tortue, Logo, trac�, dessin
 * @see <a href="TurtleTrip.java">testTurtle</a>
 * @see jus.aoo.geometrie.DrawingSpace
 * @see Turtle
 */
public class TurtleTrip extends JApplet {

    /** La tortue control�e par cette interface */
    private Turtle turtle;
    /** les widgets */
    private JPanel jContentPane = null;
    private JPanel control = null;
    private DrawingSpace turtleArea = null;
    private JButton avancer = null;
    private JButton reculer = null;
    private JButton droite = null;
    private JButton gauche = null;
    private JButton allerA = null;
    private JButton tournerVers = null;
    private JButton quit = null;
    private JButton clear = null;
    private JPanel jPanel = null;
    private JPanel jPanel1 = null;
    private JPanel jPanel2 = null;
    private JSlider distance = null;
    private JSlider rotation = null;
    private JCheckBox plume = null;
    private JPanel jPanel3 = null;
    private JPanel jPanel4 = null;
    private JLabel jLabel = null;
    private JLabel valueDistance = null;
    private JLabel jLabel1 = null;
    private JLabel valueRotation = null;
    private JPanel jPanel5 = null;
    private JPanel jPanel6 = null;
    private JLabel jLabel2 = null;
    private JSlider abscisse = null;
    private JLabel valueAbscisse = null;
    private JPanel jPanel7 = null;
    private JLabel jLabel3 = null;
    private JSlider ordonnee = null;
    private JLabel valueOrdonnee = null;

    /**
     * This is the default constructor
     */
    public TurtleTrip() {
        super();
    }

    /**
     * This method initializes this
     */
    @Override
    public void init() {
        this.setSize(674, 440);
        buildUI(this.getContentPane(), null);
    }

    /** cr�ation et positionnement des diff�rents composants de l'interface
     * utilisateur.
     * @param container Le Container qui accueille les diff�rents composants de l'interface
     * @param f Le frame qui affiche l'interface dans le cas d'une application standalone. C'est n�cessaire
     * 					pour faire le pack qui fixe les dimensions des diff�rents composants. Ce param�etre est null
     * 					quand on l'utilise via une applet.
     * @require ArgumentOk : container!=null
     */
    private void buildUI(Container container, Window f) {
        container.add(getJContentPane());
        if (f != null) {
            f.pack();
        }
        turtle = new Turtle(turtleArea);
    }

    /**
     * This method initializes jContentPane
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJPanel5(), java.awt.BorderLayout.NORTH);
            jContentPane.add(getControl(), java.awt.BorderLayout.SOUTH);
        }
        return jContentPane;
    }

    /**
     * This method initializes control
     * @return javax.swing.JPanel
     */
    private JPanel getControl() {
        if (control == null) {
            control = new JPanel();
            control.setLayout(new BorderLayout());
            control.add(getJPanel(), java.awt.BorderLayout.WEST);
            control.add(getJPanel1(), java.awt.BorderLayout.EAST);
            control.add(getJPanel2(), java.awt.BorderLayout.NORTH);
        }
        return control;
    }

    /**
     * This method initializes turtleArea
     * @return jus.aoo.turtle.TurtleArea
     */
    private DrawingSpace getTurtleArea() {
        if (turtleArea == null) {
            turtleArea = new DrawingSpace(false);
            turtleArea.setBackground(new java.awt.Color(215, 255, 215));
            turtleArea.setMinimumSize(new java.awt.Dimension(650, 400));
            turtleArea.setPreferredSize(new java.awt.Dimension(650, 400));
            turtleArea.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

                @Override
                public void mouseMoved(java.awt.event.MouseEvent e) {
                    geometrieMouseMoved(e);
                }
            });
            turtleArea.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    geometrieMousePressed(e);
                }
            });
        }
        return turtleArea;
    }

    /**
     * This method initializes avancer
     * @return javax.swing.JButton
     */
    private JButton getAvancer() {
        if (avancer == null) {
            avancer = new JButton();
            avancer.setText("Avancer");
            avancer.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        turtle.avancer(distance.getValue());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            });
        }
        return avancer;
    }

    private JButton getAllerA() {
        if (allerA == null) {
            allerA = new JButton();
            allerA.setText("AllerA");
            allerA.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        turtle.allerA(abscisse.getValue(), ordonnee.getValue());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            });
        }

        return allerA;
    }

    private JButton getTournerVers() {
        if (tournerVers == null) {
            tournerVers = new JButton();
            tournerVers.setText("TournerVers");
            tournerVers.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    turtle.tournerVers(rotation.getValue());
                }
            });
        }

        return tournerVers;
    }

    /**
     * This method initializes reculer
     * @return javax.swing.JButton
     */
    private JButton getReculer() {
        if (reculer == null) {
            reculer = new JButton();
            reculer.setText("Reculer");
            reculer.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        turtle.reculer(distance.getValue());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            });
        }
        return reculer;
    }

    /**
     * This method initializes droite
     * @return javax.swing.JButton
     */
    private JButton getDroite() {
        if (droite == null) {
            droite = new JButton();
            droite.setText("Droite");
            droite.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    turtle.droite(rotation.getValue());
                }
            });
        }
        return droite;
    }

    /**
     * This method initializes gauche
     * @return javax.swing.JButton
     */
    private JButton getGauche() {
        if (gauche == null) {
            gauche = new JButton();
            gauche.setText("Gauche");
            gauche.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    turtle.gauche(rotation.getValue());
                }
            });
        }
        return gauche;
    }

    /**
     * This method initializes quit
     * @return javax.swing.JButton
     */
    private JButton getQuit() {
        if (quit == null) {
            quit = new JButton();
            quit.setText("Quit");
            quit.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    System.exit(0);
                }
            });
        }
        return quit;
    }

    /**
     * This method initializes clear
     * @return javax.swing.JButton
     */
    private JButton getClear() {
        if (clear == null) {
            clear = new JButton();
            clear.setText("Clear");
            clear.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    turtleArea.clear();
                }
            });
        }
        return clear;
    }

    /**
     * This method initializes jPanel
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel() {
        if (jPanel == null) {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
            jPanel = new JPanel();
            jPanel.setLayout(flowLayout);
            jPanel.add(getAvancer(), null);
            jPanel.add(getReculer(), null);
            jPanel.add(getDroite(), null);
            jPanel.add(getGauche(), null);
            jPanel.add(getAllerA(), null);
            jPanel.add(getTournerVers(), null);
            jPanel.add(getPlume(), null);
        }
        return jPanel;
    }

    /**
     * This method initializes jPanel1
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel1() {
        if (jPanel1 == null) {
            jPanel1 = new JPanel();
            jPanel1.setLayout(new FlowLayout());
            jPanel1.add(getClear(), null);
            jPanel1.add(getQuit(), null);
        }
        return jPanel1;
    }

    /**
     * This method initializes jPanel2
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel2() {
        if (jPanel2 == null) {
            GridLayout gridLayout1 = new GridLayout();
            gridLayout1.setRows(2);
            gridLayout1.setColumns(2);
            jPanel2 = new JPanel();
            jPanel2.setLayout(gridLayout1);
            jPanel2.add(getJPanel3(), null);
            jPanel2.add(getJPanel4(), null);
            jPanel2.add(getJPanel6(), null);
            jPanel2.add(getJPanel7(), null);
        }
        return jPanel2;
    }

    /**
     * This method initializes distance
     * @return javax.swing.JSlider
     */
    private JSlider getDistance() {
        if (distance == null) {
            distance = new JSlider();
            distance.setToolTipText("distance de d�placement");
            distance.addChangeListener(new javax.swing.event.ChangeListener() {

                public void stateChanged(javax.swing.event.ChangeEvent e) {
                    valueDistance.setText("" + distance.getValue());
                }
            });
        }
        return distance;
    }

    /**
     * This method initializes jSlider
     * @return javax.swing.JSlider
     */
    private JSlider getJSlider() {
        if (rotation == null) {
            rotation = new JSlider();
            rotation.setMaximum(360);
            rotation.setToolTipText("Angle de rotation");
            rotation.setValue(90);
            rotation.addChangeListener(new javax.swing.event.ChangeListener() {

                public void stateChanged(javax.swing.event.ChangeEvent e) {
                    valueRotation.setText("" + rotation.getValue());
                }
            });
        }
        return rotation;
    }

    /**
     * This method initializes plume
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getPlume() {
        if (plume == null) {
            plume = new JCheckBox();
            plume.setText("Tra�ant");
            plume.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (!plume.isSelected()) {
                        turtle.lever();
                    } else {
                        turtle.baisser();
                    }
                }
            });
        }
        return plume;
    }

    /**
     * This method initializes jPanel3
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel3() {
        if (jPanel3 == null) {
            jLabel = new JLabel();
            jLabel.setText("distance");
            jPanel3 = new JPanel();
            jPanel3.add(jLabel, null);
            jPanel3.add(getDistance(), null);
            valueDistance = new JLabel();
            valueDistance.setText("" + distance.getValue());
            jPanel3.add(valueDistance, null);
        }
        return jPanel3;
    }

    /**
     * This method initializes jPanel4
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel4() {
        if (jPanel4 == null) {
            jLabel1 = new JLabel();
            jLabel1.setText("rotation");
            jPanel4 = new JPanel();
            jPanel4.add(jLabel1, null);
            jPanel4.add(getJSlider(), null);
            valueRotation = new JLabel();
            valueRotation.setText("" + rotation.getValue());
            jPanel4.add(valueRotation, null);
        }
        return jPanel4;
    }

    /**
     * This method initializes jPanel5
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel5() {
        if (jPanel5 == null) {
            jPanel5 = new JPanel();
            jPanel5.add(getTurtleArea(), null);
            jPanel5.addComponentListener(new java.awt.event.ComponentAdapter() {

                @Override
                public void componentResized(java.awt.event.ComponentEvent e) {
                }
            });
        }
        return jPanel5;
    }

    /**
     * This method initializes jPanel6
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel6() {
        if (jPanel6 == null) {
            jLabel2 = new JLabel();
            jLabel2.setText("abscisse");
            jPanel6 = new JPanel();
            jPanel6.add(jLabel2, null);
            jPanel6.add(getAbscisse(), null);
            valueAbscisse = new JLabel();
            valueAbscisse.setText("" + abscisse.getValue());
            jPanel6.add(valueAbscisse, null);
        }
        return jPanel6;
    }

    /**
     * This method initializes abscisse
     * @return javax.swing.JSlider
     */
    private JSlider getAbscisse() {
        if (abscisse == null) {
            abscisse = new JSlider();
            abscisse.setMinimum(-325);
            abscisse.setMaximum(325);
            abscisse.addChangeListener(new javax.swing.event.ChangeListener() {

                public void stateChanged(javax.swing.event.ChangeEvent e) {
                    valueAbscisse.setText("" + abscisse.getValue());
                }
            });
        }
        return abscisse;
    }

    /**
     * This method initializes jPanel7
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel7() {
        if (jPanel7 == null) {
            jLabel3 = new JLabel();
            jLabel3.setText("ordonn�e");
            jPanel7 = new JPanel();
            jPanel7.add(jLabel3, null);
            jPanel7.add(getOrdonnee(), null);
            valueOrdonnee = new JLabel();
            valueOrdonnee.setText("" + ordonnee.getValue());
            jPanel7.add(valueOrdonnee, null);
        }
        return jPanel7;
    }

    /**
     * This method initializes ordonnee
     * @return javax.swing.JSlider
     */
    private JSlider getOrdonnee() {
        if (ordonnee == null) {
            ordonnee = new JSlider();
            ordonnee.setMinimum(-200);
            ordonnee.setMaximum(200);
            ordonnee.addChangeListener(new javax.swing.event.ChangeListener() {

                public void stateChanged(javax.swing.event.ChangeEvent e) {
                    valueOrdonnee.setText("" + ordonnee.getValue());
                }
            });
        }
        return ordonnee;
    }

    /**
     *  ex�cution en mode application autonome (stand-alone application).
     *  l'application cr�e une fen�tre dans laquelle l'interface est affich�e
     * @param args les arguments de la commande
     */
    public static void main(String[] args) {
        // cr�ation de la fen�tre principale de l'application
        JFrame f = new JFrame("TurtleTrip");
        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // cr�ation de l'interface
        TurtleTrip turtleTrip = new TurtleTrip();
        turtleTrip.buildUI(f.getContentPane(), f);
        // calcul automatique de la taille de la fen�tre en fonction de
        // son contenu et des layout managers utilis�s
        f.pack();
        // affichage de la fen�tre
        // et lancement du thread de gestion des �v�nements
        f.setVisible(true);
    }
    /** la liste des figures en cours */
    private _NewFigure<Figure> newFigure;
    /** le point de s�lection */
    private java.awt.Point positionMouseInGeometrie;

    private void geometrieMouseMoved(java.awt.event.MouseEvent evt) {
        try {
            java.awt.geom.Point2D p = evt.getPoint();
            for (java.awt.geom.AffineTransform t : turtleArea.inverseTransformations()) {
                p = t.transform(p, null);
            }
            positionMouseInGeometrie = new java.awt.Point((int) p.getX(), (int) p.getY());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return;
        }
        try {
            if (newFigure != null) {
                newFigure.changePoint(new jus.util.geometrie.Point(jus.util.geometrie.Point.CARTESIEN, positionMouseInGeometrie.getX(), positionMouseInGeometrie.getY()));
                if (newFigure.newFigure() != null) {
                    turtleArea.setTemporaire(newFigure.newFigure());
                }
            }
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, x);
        }
    }

    private void geometrieMousePressed(java.awt.event.MouseEvent evt) {
        //Si on n'a pas atteint la limite défini par l'environnement.
        if (Turtle.env.getNbObstacles() < Environnement.nbMaxObstacle) {
            try {
                java.awt.geom.Point2D p = evt.getPoint();
                for (java.awt.geom.AffineTransform t : turtleArea.inverseTransformations()) {
                    p = t.transform(p, null);
                }
                positionMouseInGeometrie = new java.awt.Point((int) p.getX(), (int) p.getY());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                return;
            }
            if (newFigure == null) {
                try {
                    newFigure = new NewObstacle();
                    newFigure.setGeometrie(turtleArea);
                    newFigure.newPoint(new jus.util.geometrie.Point(jus.util.geometrie.Point.CARTESIEN, positionMouseInGeometrie.getX(), positionMouseInGeometrie.getY()));

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    newFigure.newPoint(new jus.util.geometrie.Point(jus.util.geometrie.Point.CARTESIEN, positionMouseInGeometrie.getX(), positionMouseInGeometrie.getY()));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                    return;
                }
                if (newFigure.isComplete()) {



                    turtleArea.addPermanent(newFigure.newFigure());
                    Turtle.env.addObstacle((Obstacle) newFigure.newFigure());


                    turtleArea.clearTemporaire();
                    newFigure = null;
                }
            }
        }
    }
}
