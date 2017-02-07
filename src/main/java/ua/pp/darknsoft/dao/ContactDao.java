package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.Contact;

import java.util.List;

/**
 * Created by Andrew on 31.01.2017.
 */
public interface ContactDao {
    List<Contact> selectContact();

    long insert(Contact contact);

    String SelectContactByRntc(String rntc);
}
