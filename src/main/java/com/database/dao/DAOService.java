package com.database.dao;

import com.database.repository.impl.DAORepository;

/**
 * Created by Nick on 12.06.17.
 */
public abstract class DAOService {

    protected DAORepository daoRepository = new DAORepository();
}
