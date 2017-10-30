package br.com.servico.service.cadastral;

import br.com.entity.cadastral.Imagem;
import br.com.servico.criteria.ImagemCriteria;
import br.com.servico.repository.cadastral.ImagemRepository;
import br.com.servico.service.GenericService;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.servico.util.MyHibernateUtils.listAndCast;

/**
 * Created by thiago on 30/06/17.
 */
@Service
public class ImagemService extends GenericService<Imagem, Integer> {

    @Autowired
    ImagemCriteria imagemCriteria;

    @Autowired
    public ImagemService(ImagemRepository repository) {
        super(repository);
    }

    public List<Imagem> findByProduto(Integer idProduto){
        Criteria criteria = imagemCriteria.getImagemCriteria();
        imagemCriteria.setProduto(idProduto);

        return listAndCast(criteria);
    }

}
