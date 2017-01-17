package ua.pp.darknsoft.controller;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String addlocation(Model uiModel){
        Map<Integer,String> loctype = new LinkedHashMap();
        List<LocationType> allLoctype = catalogDao.getLocationType();

        for (LocationType items: allLoctype) {
            loctype.put(items.getId(),items.getNote());
        }
        uiModel.addAttribute("loctype",allLoctype);
        return "addlocation";
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "viewlocation", method = RequestMethod.GET)
    public String viewlocation(){
        return "viewlocation";
    }
}
