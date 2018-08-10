package ru.arhinex.baseentity.manager;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.arhinex.baseapi.to.DateCreatedTO;
import ru.arhinex.baseentity.entity.DateCreatedEntity;
import ru.arhinex.baseentity.repository.BaseRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class BaseManager<T extends DateCreatedEntity, TO extends DateCreatedTO> implements IBaseManager<T> {
    @Autowired
    private MapperFacade orikaMapperFacade;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public TO save(TO value){
        initDateCreated(value);
        T entity = getBaseRepository().save(orikaMapperFacade.map(value, getEntityClass()));
        return orikaMapperFacade.map(entity, getTOClass());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TO getById(@PathVariable UUID id){
        return getBaseRepository().findById(id).map(el -> orikaMapperFacade.map(el, getTOClass())).orElseGet(null);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<TO> getList() {
        return getBaseRepository().findAll().stream().map(el -> orikaMapperFacade.map(el, getTOClass())).collect(Collectors.toList());
    }

    protected TO map(T value){
        return orikaMapperFacade.map(value, getTOClass());
    }

    protected T mapTO(TO value){
        return orikaMapperFacade.map(value, getEntityClass());
    }

    protected  <X extends DateCreatedEntity, XO extends DateCreatedTO> X mapTO(XO value, Class<X> entityClass){
        return orikaMapperFacade.map(value, entityClass);
    }

    protected TO execInContext(TO value, Function<T, T> f){
        return map(f.apply(mapTO(value)));
    }

    protected <X extends DateCreatedTO> X initDateCreated(X value){
        if (value.getId()==null){
            value.setId(UUID.randomUUID());
        }
        if (value.getCreated() == null){
            value.setCreated(new Date());
            value.setCreatedBy("");//TODO
        }
        value.setUpdated(new Date());
        value.setUpdatedBy("");//TODO
        return value;
    }

    public <E extends RuntimeException> void checkCondition(Supplier<Boolean> supplier, E exception) throws E {
        if (!supplier.get()) {
            throw exception;
        }
    }

    public <E extends RuntimeException> void checkPresent(Optional optional, E exception) throws E {
        checkCondition(() -> optional.isPresent(), exception);
    }

    public abstract BaseRepository<T> getBaseRepository();

    public abstract Class<? extends TO> getTOClass();

    public abstract Class<? extends T> getEntityClass();
}
