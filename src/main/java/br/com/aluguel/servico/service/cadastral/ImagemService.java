package br.com.aluguel.servico.service.cadastral;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.Imagem;
import br.com.aluguel.servico.criteria.ImagemCriteria;
import br.com.aluguel.servico.repository.cadastral.ImagemRepository;
import br.com.aluguel.servico.service.GenericService;

import static br.com.aluguel.servico.util.MyHibernateUtils.listAndCast;

import java.util.List;

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
