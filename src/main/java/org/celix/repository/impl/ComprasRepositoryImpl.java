package org.celix.repository.impl;

import org.celix.model.ComprasModel;
import org.celix.repository.ComprasRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComprasRepositoryImpl implements ComprasRepository{
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private ConsultasPropertiesConfig consultas;

	@Override
	public void saveCompra(ComprasModel comprasModel) {
		
		
	}

}
