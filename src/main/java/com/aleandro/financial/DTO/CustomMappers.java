package com.aleandro.financial.DTO;

import com.aleandro.financial.models.Dividas;
import com.aleandro.financial.models.Recebimentos;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MappingException;

public class CustomMappers {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	/**
	 * 
	 * @param origin_object - any object that can't be parsed from normal means, if the
	 * custom parser for this object has not been created.
	 * @return 
	 * an instance of the destiny class or a Mapping Error
	 */
	@SuppressWarnings("unchecked")
	public static <O , D> D CustomParse(O origin_object)
	throws MappingException{
		if(origin_object instanceof Dividas) {
			return (D)ParseDebt((Dividas) origin_object);
		};
		if(origin_object instanceof Recebimentos) {
			return (D)ParseRecebimentos((RecebimentosDto) origin_object);
		}
		throw new MappingException("Custom parser not created");
	}
	
	
	
	private static DebtDto ParseDebt(Dividas origin_object) {
		DebtDto novo_Vo = new DebtDto();
		novo_Vo.setId(origin_object.getId());
		novo_Vo.setUser_id(origin_object.getUser_id().getId());
		novo_Vo.setPaga(origin_object.getPaga());
		novo_Vo.setRecorrencia(origin_object.getRecorrencia().getId());
		novo_Vo.setDestino(origin_object.getDestino());
		novo_Vo.setUpdated_at(origin_object.getUpdated_at());
		novo_Vo.setCreated_at(origin_object.getCreated_at());
		return novo_Vo;
	}
	
	private static RecebimentosDto ParseRecebimentos(RecebimentosDto origin_object) {
		return new RecebimentosDto();
	}
	
	

	
}
