package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.Contact;

import java.util.List;

/**
 * Created by Andrew on 31.01.2017.
 */
public interface ContactDao {
    List<Contact> getContactByOrganizationLink(long organization);

    List<Contact> getContactById(long id);

    List<Contact> getContact(int total, int pageid);

    Contact getContactByName(String username);

    Contact getContactByUserId(int user_id);

    long insert(Contact contact);

    String getContactByRntc(String rntc);

    void updateContact(Contact contact);
}
