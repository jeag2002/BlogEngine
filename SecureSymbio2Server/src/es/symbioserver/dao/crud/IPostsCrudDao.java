package es.symbioserver.dao.crud;

import org.springframework.data.repository.CrudRepository;

import es.symbioserver.beans.PostsBean;

/**
 * Get Data from table Posts (Crud functionalities)
 * @author Usuario
 */
public interface IPostsCrudDao extends CrudRepository<PostsBean, Long> {
		//Query Post by ID
		PostsBean findPostsById(int id);
}
