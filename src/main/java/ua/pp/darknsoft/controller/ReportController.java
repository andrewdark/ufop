package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.pp.darknsoft.dao.CatalogDao;
import ua.pp.darknsoft.dao.UfopDao;
import ua.pp.darknsoft.entity.LocationCatalog;
import ua.pp.darknsoft.entity.Ufop;

import java.util.List;

/**
 * Created by Andrew on 03.05.2017.
 */
@Controller
public class ReportController {
    @Autowired
    UfopDao ufopDao;
    @Autowired
    CatalogDao catalogDao;

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public ModelAndView excel() {
        System.out.println("ExcelPDFController excel is called");
        List<Ufop> ufopList=null;
        try {
            ufopList = ufopDao.getUfopByPaginatorMultiple(9999, 0, " ");
            for (Ufop ufop : ufopList
                    ) {
                List<LocationCatalog> fulladress = catalogDao.getParentLocationByTreemark(ufop.getA_place_of_reg());
                String adr = "";
                for (LocationCatalog loc : fulladress
                        ) {
                    adr = adr + loc.getStype() + ": " + loc.getName()+" ";
                }
                ufop.setA_place_of_reg(adr);
            }
        } catch (Exception ex) {
            for (Ufop ufop : ufopList
                    ){
                ufop.setA_place_of_reg(" "+ex);
            }

        }

        //excelDocument - look excel-pdf-config.xml
        return new ModelAndView("excelDocument", "modelObject", ufopList);

    }
}
