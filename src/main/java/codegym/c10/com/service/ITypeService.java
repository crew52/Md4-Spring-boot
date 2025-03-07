package codegym.c10.com.service;

import codegym.c10.com.dto.ITypeDTO;
import codegym.c10.com.model.Type;

public interface ITypeService extends IGenerateService<Type>{
    Iterable<ITypeDTO> getAllTypes();
}
