package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by Andrew on 09.03.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UfopDaoImpl implements UfopDao,Serializable {
}
