package com.spring.service.post;

import com.spring.dto.model.PostDTO;
import com.spring.exception.NotFoundException;
import com.spring.model.Posts;
import com.spring.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private PostsRepository postsRepository;

    @Autowired
    public PostServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public PostDTO save(PostDTO dto) {
        Posts entity = dto.convertDTOToEntity();

        return this.postsRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public PostDTO update(PostDTO dto) {
        Posts entity = dto.convertDTOToEntity();

        return this.postsRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public List<PostDTO> findByTitle(String title) {
        List<PostDTO> item = new ArrayList<>();
        this.postsRepository.findAllByTitle(title).forEach(t -> item.add(t.convertEntityToDTO()));
        return item;
    }

    @Override
    public Optional<PostDTO> findById(Long id) {
        Optional<Posts> post = this.postsRepository.findById(id);
        if(post.isPresent()){
            return post.map(Posts::convertEntityToDTO);
        }
        return Optional.empty();
    }

    @Override
    public List<PostDTO> findAll() {
        List<PostDTO> item = new ArrayList<>();
        this.postsRepository.findAll().forEach(t -> item.add(t.convertEntityToDTO()));
        return item;
    }

    @Override
    public void hardDelete(Long id) throws NotFoundException {
        Posts entity = this.postsRepository.findById(id).
                orElseThrow(()-> new NotFoundException("Not found Post with :"+id));
        this.postsRepository.delete(entity);
    }
}
