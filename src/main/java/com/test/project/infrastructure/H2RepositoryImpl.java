package com.test.project.infrastructure;

import com.test.project.service.domain.ConvertibleImage;
import com.test.project.service.repository.MetaDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class H2RepositoryImpl implements MetaDataRepository {
    @Override
    public void save(ConvertibleImage convertibleImage) {

    }

//    private final H2Repository h2Repository;


}
