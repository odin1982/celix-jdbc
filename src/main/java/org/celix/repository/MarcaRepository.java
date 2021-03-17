package org.celix.repository;

import java.util.List;
import org.celix.model.MarcaModel;

public interface MarcaRepository {
	public List<MarcaModel> findAll();
	public MarcaModel findById(Long id);
	public List<MarcaModel> findByDescription(String description);
	public void save(MarcaModel marca);
	public void update(MarcaModel marca);
	public void deleteById(Long id);
}
