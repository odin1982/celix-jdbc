package org.celix.service;

import java.util.List;
import org.celix.model.MarcaModel;

public interface MarcaService {
	List<MarcaModel> findAll();
	void deleteById(Long id);
	void save(MarcaModel marca);
	MarcaModel findById(Long id);
	void update(MarcaModel marca);
}
