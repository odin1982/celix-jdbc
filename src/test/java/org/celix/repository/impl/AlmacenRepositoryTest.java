package org.celix.repository.impl;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.celix.model.AlmacenModel;
import org.celix.util.ConsultasPropertiesConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@ExtendWith(MockitoExtension.class)
class AlmacenRepositoryTest {
	
	@InjectMocks
	private AlmacenRepositoryImpl almacenRepositoryImpl;
	
	@Mock
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Mock
	private ConsultasPropertiesConfig consultas;
	
	private final Long ID_IN_DB = 1L;
	private final String DESCRIPCION = "Almacen general";
	private AlmacenModel almacen;
	private List<AlmacenModel> almacenes;
	private String anyString = "";
	private int anyInt = 1;
	
	@BeforeEach
	public void init() {
		almacen = new AlmacenModel();
		almacen.setId(1L);
		almacen.setDescripcion("Almacen general");
		
		almacenes = new ArrayList<>();
		almacenes.add(almacen);
		when(consultas.getProperty(anyString())).thenReturn(anyString);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testFindAll() {
		when(namedJdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(almacenes);
		List<AlmacenModel> almacenes = almacenRepositoryImpl.findAll();
		assertNotNull(almacenes);
	}
	
	@Test 
	void testDeleteById() {
		when(namedJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(anyInt);
		almacenRepositoryImpl.deleteById(anyLong());
		verify( namedJdbcTemplate,times(1) ).update( anyString(),any(MapSqlParameterSource.class) );
	}
	
	@Test
	void testSave() {
		when(namedJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(anyInt);
		almacenRepositoryImpl.save(almacen);
		verify( namedJdbcTemplate,times(1) ).update( anyString(),any(MapSqlParameterSource.class) );
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testFindById() {
		Mockito.doReturn(almacen).when(namedJdbcTemplate).queryForObject(anyString(), any(MapSqlParameterSource.class), any(BeanPropertyRowMapper.class));
		AlmacenModel almacen = almacenRepositoryImpl.findById(ID_IN_DB);
		assertNotNull(almacen);
	}
	
	@Test
	void testFindByDescription() {
		List<AlmacenModel> almacenes = almacenRepositoryImpl.findByDescription(DESCRIPCION);
		assertNotNull(almacenes);
	}
	
	@Test
	void testUpdate() {
		when(namedJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(anyInt);
		almacenRepositoryImpl.update(almacen);
		verify( namedJdbcTemplate,times(1) ).update( anyString(),any(MapSqlParameterSource.class) );
	}

}
