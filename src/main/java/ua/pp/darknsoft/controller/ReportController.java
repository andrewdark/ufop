package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.pp.darknsoft.dao.UfopDao;
import ua.pp.darknsoft.entity.Ufop;

import java.util.List;

/**
 * Created by Andrew on 03.05.2017.
 */
@Controller
public class ReportController {
    @Autowired
    UfopDao ufopDao;

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public ModelAndView excel() {
        System.out.println("ExcelPDFController excel is called");

        List<Ufop> ufopList = ufopDao.getUfopByPaginatorMultiple(9999,0," ");

        //excelDocument - look excel-pdf-config.xml
        return new ModelAndView("excelDocument", "modelObject", ufopList);

    }
}
