package ua.pp.darknsoft.controller;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.pp.darknsoft.dao.CatalogDao;
import ua.pp.darknsoft.entity.LocationType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 16.01.2017.
 */
@Controller
public class CatalogController {
    @Autowired
    CatalogDao catalogDao;

    @PreAuthorize(value = "hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "addlocation", method = RequestMethod.GET)
    public String addlocation(Model uiModel) {
        Map<Integer, String> loctype = new LinkedHashMap();
        List<LocationType> allLoctype = catalogDao.getLocationType();

        for (LocationType items : allLoctype) {
            loctype.put(items.getId(), items.getNote());
        }
        uiModel.addAttribute("loctype", allLoctype);
        return "addlocation";
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "viewlocation", method = RequestMethod.GET)
    public String viewlocation() {
        return "viewlocation";
    }
    //__________________________________________________________________

    @RequestMapping(value = "/ajax", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String ajax(@RequestParam(defaultValue = "0") String x, Model uiModel) {
        int level = 0;
        String html = "";
        List<LocationType> allLoctype = catalogDao.getLocationType();
        try {
            for (LocationType items : allLoctype) {
                if (items.getId() == Integer.parseInt(x)) {
                    level = items.getLevel();
                }
            }
        } catch (Exception e) {
            return "Error: " + e;
        }

        if (level == 3) {
            html = "<select><option value=\"1\">Odesskaya</option><option value=\"2\">Vinnickaya</option></select>" +
                    "<select><option value=\"1\">Odessa</option><option value=\"2\">Chernomorsk</option><option value=\"3\">Ismail</option></select>" +
                    "<br /><input type=\"text\" /><input type=\"submit\" >Добавить</input>";
        }
        if (level == 2) {
            html = "<select><option value=\"1\">Odesskaya</option><option value=\"2\">Vinnickaya</option></select>" +
                    "<br /><input type=\"text\" /><input type=\"submit\" >Добавить</input>";
        }
        if (level == 1) {
            html="<input type=\"text\" /><input type=\"submit\" >Добавить</input>";
        }
        uiModel.addAttribute("loca",level);
        return html + "<br /> type:" + x + " level:" + level;
    }
}
