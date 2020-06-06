package javaProject.project.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.awt.event.ActionEvent;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javaProject.project.dao.CoursDao;
import javaProject.project.dao.EnseignantDao;
import javaProject.project.dao.GroupeDao;
import javaProject.project.dao.PromotionDao;
import javaProject.project.dao.SalleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.SiteDao;
import javaProject.project.dao.TypeCoursDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Enseignant;
import javaProject.project.model.EnumerableElement;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Salle;
import javaProject.project.model.Seance;

import javaProject.project.model.Site;

import javaProject.project.model.Utilisateur;

import javaProject.project.view.VueLogin;
import javaProject.project.view.VuePlanningListe;
import javaProject.project.view.VueCalendrier;

import javaProject.project.view.VueModifier;
import javax.swing.JTable;
import org.springframework.data.jpa.repository.JpaRepository;

import javaProject.project.view.VueRecap;

import util.cst;

@Component
public class CalendrierController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    SeanceDao seanceDao;

    @Autowired
    GroupeDao groupeDao;

    @Autowired
    EnseignantDao enseignantDao;

    @Autowired
    CoursDao coursDao;

    @Autowired
    PromotionDao promotionDao;

    @Autowired
    TypeCoursDao TypeCoursDao;

    @Autowired
    SiteDao siteDao;

    @Autowired
    SalleDao salleDao;
    
    
    private Object[][] data;
    
    Map<String, Seance> mapCoordToSeance = new HashMap<String, Seance>();

    private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();

    private VueCalendrier vueCalendrier;

    private List<Seance> listSeances;

    public List<Seance> getListSeances() {
        return listSeances;
    }

    public void setListSeances(List<Seance> oui) {
        this.listSeances = oui;
    }

    public Object[][] formatData(List<Seance> seances) {

        data = cst.getCalendarBlankData();

        for (Seance seance : seances) {

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(seance.getDate());

            int heure_fin = seance.getHeure_fin();
            int minute_fin = seance.getMinute_fin();
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);

            int index_debut = hours - 8;

            String salleSeance = "";
            String enseiSenace = "";

            if (seance.getSalle().size() > 1) {
                for (Salle it : seance.getSalle()) {
                    salleSeance += it.getNom() + " / ";
                }
            } else if (seance.getSalle().size() == 1) {
                salleSeance = seance.getSalle().get(0).getNom();
            } else {
                salleSeance = "Null";
            }

            if (seance.getEnseignant().size() > 1) {
                for (Enseignant it : seance.getEnseignant()) {
                    enseiSenace += it.getNom() + " / ";
                }
            } else if (seance.getEnseignant().size() == 1) {
                enseiSenace = seance.getEnseignant().get(0).getNom();
            } else {
                enseiSenace = "Null";
            }

            index_debut = index_debut * 2;
            if (minutes == 30) {
                index_debut += 1;
            }

            int index_fin = heure_fin - 8;
            index_fin = index_fin * 2;
            if (minute_fin == 30) {
                index_fin += 1;
            }

            for (int i = index_debut; i < index_fin; i++) {

                data[i][day_of_week - 1] = "<html> type de cours : " + seance.getType_cours().getNom() + "<br>" + "  cours :  " + seance.getCours().getNom()
                    + "  Professeur :  " + enseiSenace + "  salle :  " + salleSeance + "</html>";
                
                mapCoordToSeance.put(String.valueOf(i) + "-" + String.valueOf(day_of_week - 1), seance);
            }
            if (index_fin - index_debut < 2) {
                data[index_debut][day_of_week - 1] = "<html> type de cours : " + seance.getType_cours().getNom() + "<br>" + "  cours :  " + seance.getCours().getNom()
                    + "  Professeur :  " + enseiSenace + "  salle :  " + salleSeance + "</html>";
                
                mapCoordToSeance.put(String.valueOf(index_debut) + "-" + String.valueOf(day_of_week - 1), seance);
            }
        }
        return data;
    }

    public void allSeances(String email, VueCalendrier view, int semaine) {

        if (Singleton.getInfo().getDroit() == 4) {
            System.out.println("Seance etudiant");
            Etudiant i = (Etudiant) utilisateurDao.findByEmail(email);
            List<Seance> a = seanceDao.findBySemaineAndGroupeContaining(semaine, i.getGroupe());
            setListSeances(a);
            Object[][] data = this.formatData(a);
            view.setData(data);
        } else if (Singleton.getInfo().getDroit() == 3) {
            System.out.println("Seance enseignant");
            Enseignant i = (Enseignant) utilisateurDao.findByEmail(email);
            List<Seance> a = seanceDao.findBySemaineAndEnseignantContaining(semaine, i);
            setListSeances(a);
            System.out.println(a);
            Object[][] data = this.formatData(a);
            view.setData(data);
        } else if (Singleton.getInfo().getDroit() == 1) {
            System.out.println("Seance enseignant");
            Utilisateur i = (Utilisateur) utilisateurDao.findByEmail(email);
            List<Seance> a = seanceDao.findBySemaine(semaine);
            setListSeances(a);
            System.out.println(a);
            Object[][] data = this.formatData(a);
            view.setData(data);
        }
    }

    public void edtFindByName(String name, VueCalendrier view, int semaine) {

        Utilisateur utilisateur = utilisateurDao.findByNom(name);
        List<Seance> a;

        if ((Singleton.getInfo().getDroit() == 4) && (utilisateur.getDroit() == 4)) {
            System.out.println("Seance etudiant");
            Etudiant i = (Etudiant) utilisateur;
            a = seanceDao.findBySemaineAndGroupeContaining(semaine, i.getGroupe());
            Object[][] data = this.formatData(a);
            System.out.println(a);
            view.setData(data);
        } else if ((Singleton.getInfo().getDroit() == 3) && (utilisateur.getDroit() == 3)) {
            System.out.println("Seance enseignant");
            Enseignant i = (Enseignant) utilisateur;
            a = seanceDao.findBySemaineAndEnseignantContaining(semaine, i);
            System.out.println(a);
            Object[][] data = this.formatData(a);
            view.setData(data);
        } else {
            a = Collections.<Seance>emptyList();;
            Object[][] data = this.formatData(a);
            view.setData(data);
        }

    }

    public ArrayList<EnumerableElement> DaoGetListData(JpaRepository dao) {
        ArrayList<EnumerableElement> temp_values = new ArrayList<>();
        var objects = dao.findAll();
        for (int i = 0; i < objects.size(); i++) {
            EnumerableElement elt = (EnumerableElement) objects.get(i);
            temp_values.add(elt);
        }
        return temp_values;
    }

    public void resetData(VueModifier view3) {
        view3.typeList.clear();
        view3.professeurList.clear();
        view3.promotionList.clear();
        view3.sallesList.clear();
        view3.sitesList.clear();
        view3.groupesList.clear();
        view3.matiereList.clear();
    }
    
    public void populateData(VueModifier view3, String hour, String minute, String hourEnd, String minuteEnd, String header) {
        view3.setListEnseignant(DaoGetListData(enseignantDao));
        view3.setListCours(DaoGetListData(coursDao));
        view3.setListGroupe(DaoGetListData(groupeDao));
        view3.setListType_cours(DaoGetListData(TypeCoursDao));
        view3.setListPromotion(DaoGetListData(promotionDao));
        view3.setListSalle(DaoGetListData(salleDao));
        view3.setListSite(DaoGetListData(siteDao));
        view3.setCoordinates(hour, minute, hourEnd, minuteEnd, header);
    }

    public void initController(VueCalendrier vueCalendrier, VueLogin vueLogin, VueModifier view3) {
        System.out.println("Init Controller Calendrier");
        allSeances(vueLogin.mail.getText(), vueCalendrier, 1);
        this.vueCalendrier = vueCalendrier;
        for (int i = 0; i < 52; i++) {
            final int semaine = Integer.parseInt(vueCalendrier.buttonList.get(i).getText());
            vueCalendrier.buttonList.get(i).addActionListener(e -> allSeances(vueLogin.mail.getText(), vueCalendrier, semaine));
        }
//		view.Recherche.addActionListener(e -> edtFindByName(view.Recherche.getText(),view , semaine ));		
        vueCalendrier.tableau.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    final String header = jTable.getColumnName(column);
                    final String valueInCell = (String) jTable.getValueAt(row, column);
                    final String valueOfTime = (String) jTable.getValueAt(row, 0);
                   

                    String hour = valueOfTime.substring(0, 2);
                    String minute = valueOfTime.substring(3, 5);
                    
                    String hourEnd = valueOfTime.substring(8, 10);
                    String minuteEnd = valueOfTime.substring(11, 13);
                    
                    System.out.print(minute);
                    if (Singleton.getInfo().getDroit() == 1) {
                        if (valueInCell.length() <= 1) {
                            if (column != 0) {
                                resetData(view3);
                                view3.setCurrentSession(null);
                                populateData(view3, hour, minute, hourEnd, minuteEnd, header);
                                view3.setVisible(true);
                                view3.setButtonContent("Create session");
                            }
                        } else {
                            if (column != 0) {
                                resetData(view3);
                                populateData(view3, hour, minute, hourEnd, minuteEnd, header);
                                view3.setCurrentSession(mapCoordToSeance.get(row+"-"+column));
                                view3.setButtonContent("Update session");
                                view3.setVisible(true);
                            }
                        }
                    }
                }
            }
        }
        );
    }
}
