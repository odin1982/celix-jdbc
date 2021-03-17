package org.celix.repository.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.List;
import org.celix.model.MarcaModel;
import org.celix.util.ConsultasPropertiesConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/*
 * Link de como usar junit y mockito en spring boot
 * https://mkyong.com/spring-boot/spring-boot-junit-5-mockito/
 * https://programmingtechie.com/2020/10/16/spring-boot-testing-tutorial-unit-testing-with-junit-5-and-mockito/  -->Este parece haber funcionado
 * */

@ExtendWith(MockitoExtension.class)
class MarcaRepositoryImplTest2 {
	
	@InjectMocks
	private MarcaRepositoryImpl marcaRepositoryImpl;
	
	@Mock
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Mock
	private ConsultasPropertiesConfig consultas;

	private final Long ID_IN_DB = 1L; 
	private MarcaModel marca;
	private List<MarcaModel> marcas;
	private String anyString = "";
	private int anyInt = 1;
	private final String DESCRIPCION = "Mobo";
	
	
	@BeforeEach
	public void init() {
		marca = new MarcaModel();
		marca.setId(1L);
		marca.setDescripcion("Mobo");
		
		marcas = new ArrayList<>();
		marcas.add(marca);
		
		when(consultas.getProperty(anyString())).thenReturn(anyString);
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testFindAllMarcasNotNullAndNotEmpty() {
		when(namedJdbcTemplate.query( anyString() , any(BeanPropertyRowMapper.class))).thenReturn(marcas);
		List<MarcaModel> marcas = marcaRepositoryImpl.findAll();
		assertNotNull(marcas,"Debe existir un catálogo de marcas");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void test_findById() {
		when(namedJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class),any(BeanPropertyRowMapper.class))).thenReturn(marca);
		MarcaModel marca = marcaRepositoryImpl.findById(ID_IN_DB);
		assertNotNull(marca);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void test_findByDescription() {
		List<MarcaModel> marcas = marcaRepositoryImpl.findByDescription(DESCRIPCION);
		System.out.println(marcas);
		assertNotNull(marcas);
	}
	
	@Test
	void testSave() {
		when(namedJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(1);
		marcaRepositoryImpl.save(marca);
		verify(namedJdbcTemplate,times(1)).update(anyString(),any(MapSqlParameterSource.class));
	}
	
	@Test
	void testUpdate() {
		when(namedJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(1);
		marcaRepositoryImpl.update(marca);
		verify( namedJdbcTemplate,times(1) ).update( anyString(),any(MapSqlParameterSource.class) );
	}
	
	@Test
	void should_deleteById_execute_once() {
		marcaRepositoryImpl.deleteById(ID_IN_DB);
		verify(namedJdbcTemplate,times(1)).update(anyString(),any(MapSqlParameterSource.class));
	}

}
