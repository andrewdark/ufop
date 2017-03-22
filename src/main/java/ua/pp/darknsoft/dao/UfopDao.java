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
}
