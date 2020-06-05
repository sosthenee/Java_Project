package javaProject.project.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import javaProject.project.view.VueLogin;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueModifier;
import javax.swing.JTable;
import org.springframework.data.jpa.repository.JpaRepository;
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

    private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();

    public Object[][] formatData(List<Seance> seances) {

        data = cst.getCalendarBlankData();
        for (Seance seance : seances) {

            Calendar calendar = Calendar.getInstance();
            TimeZone tzInFrance = TimeZone.getTimeZone("France/Paris");
            calendar.setTimeZone(tzInFrance);

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
            } else {
                salleSeance = seance.getSalle().get(0).getNom();
            }

            if (seance.getEnseignant().size() > 1) {
                for (Enseignant it : seance.getEnseignant()) {
                    enseiSenace += it.getNom() + " / ";
                }
            } else {
                enseiSenace = seance.getEnseignant().get(0).getNom();
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
            }
            if (index_fin - index_debut < 2) {
                data[index_debut][day_of_week - 1] = "<html> type de cours : " + seance.getType_cours().getNom() + "<br>" + "  cours :  " + seance.getCours().getNom()
                    + "  Professeur :  " + enseiSenace + "  salle :  " + salleSeance + "</html>";
            }
        }
        return data;
    }

    public void allSeances(String email, VueCalendrier view, int semaine) {

        if (Singleton.getInfo().getDroit() == 4) {

            System.out.println("Seance etudiant");
            Etudiant i = (Etudiant) utilisateurDao.findByEmail(email);
            List<Seance> a = seanceDao.findBySemaineAndGroupeContaining(semaine, i.getGroupe());
            Object[][] data = this.formatData(a);
            view.setData(data);

        } else if (Singleton.getInfo().getDroit() == 3) {

            System.out.println("Seance enseignant");
            Enseignant i = (Enseignant) utilisateurDao.findByEmail(email);
            List<Seance> a = seanceDao.findBySemaineAndEnseignantContaining(semaine, i);
            System.out.println(a);
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

    public void initController(VueCalendrier view, VueLogin view2, VueModifier view3) {
        for (int i = 0; i < 52; i++) {
            final int semaine = Integer.parseInt(view.buttonList.get(i).getText());
            view.buttonList.get(i).addActionListener(e -> allSeances(view2.mail.getText(), view, semaine));

        }

        view.tableau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    final String header = jTable.getColumnName(column);
                    final String valueInCell = (String) jTable.getValueAt(row, column);
                    if (valueInCell.length() <= 1) {
                        if (column != 0) {
                            resetData(view3);
                            view3.setVisible(true);
                            view3.setListEnseignant(DaoGetListData(enseignantDao));
                            view3.setListCours(DaoGetListData(coursDao));
                            view3.setListGroupe(DaoGetListData(groupeDao));
                            view3.setListType_cours(DaoGetListData(TypeCoursDao));
                            view3.setListPromotion(DaoGetListData(promotionDao));
                            view3.setListSalle(DaoGetListData(salleDao));
                            view3.setListSite(DaoGetListData(siteDao));
                            view3.setCoordinates(row, column, header);
                        }

                    } else {
                        view3.setVisible(true);
                    }
                }
            }
        }
        );
    }
}
