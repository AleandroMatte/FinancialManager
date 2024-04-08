package com.aleandro.financial.shared.infra;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O , D> D parseObject(O origin_object, Class<D> destiny) {
		return mapper.map(origin_object, destiny);	
	}
	
	
	public static <O , D> List<D> parseListObjects(List<O> origin_objects, Class<D> destiny) {
		List<D> destiny_objects = new ArrayList<D>();
		for (O origin_object  : origin_objects ) {
			destiny_objects.add(parseObject(origin_object, destiny));
		}
		return destiny_objects;	
	}
	
	
	

}
