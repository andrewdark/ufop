package ua.pp.darknsoft.dao;

import org.springframework.security.access.prepost.PreAuthorize;
import ua.pp.darknsoft.entity.Ufop;

import java.util.List;

/**
 * Created by Andrew on 09.03.2017.
 */
public interface UfopDao {
    List<Ufop> searchUfopByCode(String ufop_code);

    List<Ufop> searchUfopById(long ufop_link);

    @PreAuthorize(value = "isAuthenticated()")
    Ufop createUfop(Ufop ufop);

    List<Ufop> getEntrepreneurByPaginator(int total, int pageid, short ufop_is);

    List<Ufop> getUfopByPaginatorMultiple(int total, int pageid, String stext);

    List<Ufop> getUfopByCreatorLink(int total, int pageid, int creator_link);

    @PreAuthorize(value = "isAuthenticated()")
    Ufop editUfop(Ufop ufop);

    List<Ufop> getUfopByWithoutEvent(int total, int pageid);

    List<Ufop> getUfopByWithoutEventForOneYear(int total, int pageid);
}
